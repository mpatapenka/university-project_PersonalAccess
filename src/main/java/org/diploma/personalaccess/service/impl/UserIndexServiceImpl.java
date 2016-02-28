package org.diploma.personalaccess.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;
import org.diploma.personalaccess.repository.UserIndexRepository;
import org.diploma.personalaccess.service.UserIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * UserIndex service default implementation
 *
 * @author Maksim Patapenka
 */
@Service
public class UserIndexServiceImpl implements UserIndexService {

    /**
     * UserIndex repository bean
     */
    @Autowired
    private UserIndexRepository userIndexRepository;



    @Override
    public List<UserIndex> getAllUserIndexesByCurrentPeriod(User user, Period period) {
        // TODO: implement this
        return new ArrayList<>();
    }

    @Override
    public boolean isUserIndexesAvailableForPeriod(User user, Period period) {
        // TODO: implement this
        return false;
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
            } else {
                userIndex.setDocument(null);
            }

            userIndex.setUser(user);
            userIndex.setFillDate(date);

            userIndexRepository.save(userIndex);
        }
    }



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

}
