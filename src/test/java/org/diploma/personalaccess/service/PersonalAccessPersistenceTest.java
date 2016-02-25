package org.diploma.personalaccess.service;

import org.diploma.personalaccess.config.DatabaseConfig;
import org.diploma.personalaccess.entity.Index;
import org.diploma.personalaccess.repository.IndexRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for index repository
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@WebAppConfiguration
@Transactional
public class PersonalAccessPersistenceTest {

    /**
     * Index repository bean
     */
    @Autowired
    private IndexRepository indexRepository;

    /**
     * Complex test
     */
    @Test
    public void testThatIndexCanBeSaveAndGetWithActualValues() {
        /* Data for testing index repository */
        final String name = "Test index";
        final int estimate = 50;
        final int multiplier = 1;
        final String workName = "test";

        /* Creating mock index */
        Index index = new Index();
        index.setName(name);
        index.setEstimate(estimate);
        index.setMultiplier(multiplier);
        index.setWorkName(workName);

        /* Save mock index into database */
        indexRepository.save(index);

        /* Find some indexes with test name */
        List<Index> foundIndexes = indexRepository.findAll();

        /* Checking results */
        assertNotNull("foundIndexes is null", foundIndexes);
        assertTrue("foundIndexes is empty", foundIndexes.size() > 0);

        /* Search test index */
        Index expected = null;
        for (Index foundIndex : foundIndexes) {
            if (name.equals(foundIndex.getName())) {
                expected = foundIndex;
                break;
            }
        }

        /* Checking data from database */
        assertNotNull("Test index not found in database", expected);
        assertNotNull("Id is null", expected.getId());
        assertEquals("Names not equal", expected.getName(), name);
        assertEquals("Estimate not equal", expected.getEstimate(), estimate);
        assertEquals("Multiplier not equal", expected.getMultiplier(), multiplier);
        assertEquals("Work name not equal", expected.getWorkName(), workName);

        /* Delete test index */
        indexRepository.delete(expected.getId());

        /* Checking deletion */
        List<Index> afterDeletion = indexRepository.findAll();

        /* Search test index */
        expected = null;
        for (Index afterDeletionIndex : afterDeletion) {
            if (name.equals(afterDeletionIndex.getName())) {
                expected = afterDeletionIndex;
                break;
            }
        }

        assertNull("Test index didn't delete..", expected);
    }

}
