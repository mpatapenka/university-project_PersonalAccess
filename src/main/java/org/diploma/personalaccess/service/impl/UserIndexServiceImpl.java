package org.diploma.personalaccess.service.impl;

import org.diploma.personalaccess.bean.Period;
import org.diploma.personalaccess.entity.User;
import org.diploma.personalaccess.entity.UserIndex;
import org.diploma.personalaccess.repository.UserIndexRepository;
import org.diploma.personalaccess.service.UserIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

}
