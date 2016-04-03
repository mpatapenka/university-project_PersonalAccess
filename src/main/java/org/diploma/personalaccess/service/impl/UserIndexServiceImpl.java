package org.diploma.personalaccess.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.config.WebConfig;
import org.diploma.personalaccess.entity.*;
import org.diploma.personalaccess.repository.DocumentRepository;
import org.diploma.personalaccess.repository.UserIndexRepository;
import org.diploma.personalaccess.service.UserIndexService;
import org.diploma.personalaccess.util.DateUtils;
import org.diploma.personalaccess.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * UserIndex service default implementation
 *
 * @author Maksim Patapenka
 */
@Service
public class UserIndexServiceImpl implements UserIndexService {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(UserIndexServiceImpl.class);


    /**
     * UserIndex repository bean
     */
    @Autowired
    private UserIndexRepository userIndexRepository;

    /**
     * Document repository bean
     */
    @Autowired
    private DocumentRepository documentRepository;


    /**
     * Generate unique file name, which depend on currentPeriod
     * time in milliseconds
     *
     * @param name original filename
     * @return unique file name for file system
     */
    private static String generateUniqueSystemName(String name) {
        return DigestUtils.sha256Hex(name) + System.currentTimeMillis();
    }


    @Override
    @Transactional
    public List<UserIndex> getAllUserIndexesBySpecifiedPeriod(User user, Period period, int year,
                                                              Period currentPeriod, int currentYear) {
        Date start = period.getStartDateForYear(year);
        Date end = period.getEndDateForYear(year);

        List<UserIndex> userIndexes = userIndexRepository.findByUserIdAndFillDateBetween(user.getId(), start, end);
        if (!period.equals(currentPeriod) || year != currentYear) {
            return userIndexes;
        }

        Set<Index> availIndexes = user.getForm().getPosition().getAvailableIndexes();
        if (userIndexes.size() == availIndexes.size()) {
            return userIndexes;
        }

        Date date = DateUtils.today();
        Set<Index> needToAdd = availIndexes;

        // TODO: Update UserIndexes when index was transferred to other group (need delete appropriate UserIndex)

        if (!userIndexes.isEmpty()) {
            /* Save all index elements from each UserIndex to new Set */
            Set<Index> existIndexes = userIndexes.stream().map(UserIndex::getIndex).collect(Collectors.toSet());
            needToAdd = ServiceUtils.subtract(availIndexes, existIndexes);
        }

        /* Create and push empty user indexes */
        for (Index index : needToAdd) {
            UserIndex userIndex = ServiceUtils.createUserIndexStub(date, user, index);
            index.getUserIndexes().add(userIndex);

            userIndexRepository.save(userIndex);
        }

        return userIndexRepository.findByUserIdAndFillDateBetween(user.getId(), start, end);
    }

    @Override
    @Transactional
    public List<UserIndex> getAllUserIndexesForLeadBySpecifiedPeriod(User user, Period period, int year) {
        Date start = period.getStartDateForYear(year);
        Date end = period.getEndDateForYear(year);
        return userIndexRepository.findByUserIdAndFillDateBetween(user.getId(), start, end);
    }

    @Override
    @Transactional
    public void setupSelfEstimatesOfUser(List<UserIndex> userIndexes, User user) {
        Date date = DateUtils.today();
        for (UserIndex userIndex : userIndexes) {
            UserIndex oldUserIndex = userIndexRepository.findOne(userIndex.getId());

            if (!oldUserIndex.getUser().equals(user)) {
                String msg = "Trying to edit user indexes by other user! Editor '" + user.getUsername() + "'!";
                log.error(msg);
                throw new IllegalArgumentException(msg);
            }

            oldUserIndex.setFillDate(date);
            oldUserIndex.setSelfEstimate(userIndex.getSelfEstimate());
            oldUserIndex.setComplete(true);
        }
    }

    @Override
    @Transactional
    public void uploadAdditionalInfo(long userIndexId, String description, MultipartFile doc) {
        UserIndex userIndex = userIndexRepository.findOne(userIndexId);
        userIndex.setDescription(description);

        if (doc.getSize() == 0) {
            return;
        }

        /* Check for previous document */
        boolean isOldDocExist = userIndex.getDocument() != null;
        long oldDocId = isOldDocExist ? userIndex.getDocument().getId() : -1;

        String fileName = doc.getOriginalFilename();
        String systemFileName = generateUniqueSystemName(fileName);

        Document document = new Document();
        document.setName(fileName);
        document.setSystemName(systemFileName);
        document.getUserIndexes().add(userIndex);

        userIndex.setDocument(document);

        try {
            File file = new File(WebConfig.STORAGE_PATH + systemFileName);
            FileUtils.writeByteArrayToFile(file, doc.getBytes());
        } catch (IOException e) {
            String msg = "File '" + fileName + "' don't upload to server.";
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }

        /* Delete old doc file if it's exists */
        if (isOldDocExist) {
            Document oldDoc = documentRepository.findOne(oldDocId);
            File file = new File(WebConfig.STORAGE_PATH + oldDoc.getSystemName());
            if (file.delete()) {
                documentRepository.delete(oldDocId);
            }
        }
    }

    @Override
    public void downloadAdditionalInfo(long docId, HttpServletResponse response) {
        Document doc = documentRepository.findOne(docId);
        File file = new File(WebConfig.STORAGE_PATH + doc.getSystemName());

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", ServiceUtils.createContentDispositionWithFilename(doc.getName()));

        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            IOUtils.copy(is, os);
            response.flushBuffer();
        } catch (IOException e) {
            String msg = "Can't copy file from server to client stream.";
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }
    }

    @Override
    @Transactional
    public void setupLeadEstimatesOfUser(List<UserIndex> userIndexes, User lead, User sub) {
        Position subPos = sub.getForm().getPosition();
        Position leadPos = lead.getForm().getPosition();
        if (!leadPos.getSubs().contains(subPos)
                || !lead.getForm().getFaculty().equals(sub.getForm().getFaculty())) {
            String msg = "Trying to edit user indexes of other user which not your subordinate! Editor '" +
                    lead.getUsername() + "'!";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        for (UserIndex userIndex : userIndexes) {
            UserIndex oldUserIndex = userIndexRepository.findOne(userIndex.getId());

            if (!oldUserIndex.getUser().equals(sub)) {
                String msg = "Trying to edit user indexes of other user! Editor '" + lead.getUsername() + "'!";
                log.error(msg);
                throw new IllegalArgumentException(msg);
            }

            oldUserIndex.setLeadEstimate(userIndex.getLeadEstimate());
        }
    }

}
