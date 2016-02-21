package org.diploma.personalaccess.service;

import org.diploma.personalaccess.config.DatabaseConfig;
import org.diploma.personalaccess.entity.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@WebAppConfiguration
@Transactional
public class PersonalAccessPersistenceTest {

    @Autowired
    private PersonalAccessService personalAccessService;

    @Test
    public void testThatIndexCanBeSaveAndGetWithActualValues() {
        Index index = new Index();
        index.setName("Test index");
        index.setEstimate(50);
        index.setMultiplier(1);
        index.setWorkName("test");

        personalAccessService.saveOrUpdateIndex(index);
    }

}
