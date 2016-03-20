package org.diploma.personalaccess.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.config.WebConfig;
import org.diploma.personalaccess.entity.Document;
import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;
import org.diploma.personalaccess.repository.UserIndexRepository;
import org.diploma.personalaccess.repository.UserRepository;
import org.diploma.personalaccess.service.UserIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
     * User repository bean
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Generate unique file name, which depend on current
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
    public List<UserIndex> getAllUserIndexesBySpecifiedPeriod(long userId, Date start, Date end) {
        List<UserIndex> userIndexes = userIndexRepository.findByUserIdAndFillDateBetween(userId, start, end);
        if (!userIndexes.isEmpty()) {
            return userIndexes;
        }

        User user = userRepository.findOne(userId);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTime().getTime());

        /* Create and push empty user indexes */
        for (Index index : user.getForm().getPosition().getAvailableIndexes()) {
            UserIndex userIndex = new UserIndex();
            userIndex.setFillDate(date);
            userIndex.setUser(user);
            userIndex.setDocument(null);
            userIndex.setComplete(false);
            userIndex.setDescription(null);
            userIndex.setIndex(index);
            userIndex.setLeadEstimate(0);
            userIndex.setSelfEstimate(0);

            index.getUserIndexes().add(userIndex);

            userIndexRepository.save(userIndex);
        }

        return userIndexRepository.findByUserIdAndFillDateBetween(userId, start, end);
    }

    @Override
    @Transactional
    public boolean isUserIndexesAvailableForPeriod(User user, Period period) {
        return userIndexRepository.countByUserAndFillDateBetween(user.getId(),
                period.getCurrentStartDate(), period.getCurrentEndDate()) > 0;
    }

    @Override
    @Transactional
    public void publishAllUserIndexes(List<UserIndex> userIndexes, User user) {
        // TODO: implement support of document uploading

        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTime().getTime());
        for (UserIndex userIndex : userIndexes) {
            String docName = userIndex.getDocument().getName();
            if (!docName.isEmpty()) {
                userIndex.getDocument().setSystemName(generateUniqueSystemName(docName));
            }
            /*
             * Add else block and set document to 'null' in it
             * Now it's working without else because upload documents is
             * unsupported operation
             */
            userIndex.setDocument(null);

            userIndex.setUser(user);
            userIndex.setFillDate(date);

            userIndexRepository.save(userIndex);
        }
    }

    @Override
    @Transactional
    public void setUpAllEstimates(List<UserIndex> userIndexes, User user) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTime().getTime());
        for (UserIndex userIndex : userIndexes) {
            UserIndex oldUserIndex = userIndexRepository.findOne(userIndex.getId());
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
    }

    @Override
    @Transactional
    public void publishLeadEstimates(Map<Long, Integer> userIndexIdMarkDependency, User user) {
        for (Map.Entry<Long, Integer> entry : userIndexIdMarkDependency.entrySet()) {
            UserIndex userIndex = userIndexRepository.findOne(entry.getKey());

            /* If user is not a lead for current subordinate, then throw exception and rollback transaction */
            if (!userIndex.getUser().getLeads().contains(user)) {
                String msg = "User with username '" + user.getUsername() + "' trying to set estimate for incorrect" +
                        "subordinate (username of sub '" + userIndex.getUser().getUsername() + "'.";
                log.warn(msg);
                throw new IllegalArgumentException(msg);

            }

            userIndex.setLeadEstimate(entry.getValue());
        }
    }

    @Override
    @Transactional
    public boolean isLeadSubmitAllEstimatesForUser(long userId, Date start, Date end) {
        User user = userRepository.findOne(userId);
        return userIndexRepository.countByUserAndFillDateBetween(userId, start, end) > 0
                && userIndexRepository.countByUserAndFillDateBetweenAndLeadEstimate(user, start, end, 0) == 0;
    }

}
