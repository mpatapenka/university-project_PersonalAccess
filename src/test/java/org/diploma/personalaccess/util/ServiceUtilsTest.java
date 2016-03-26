package org.diploma.personalaccess.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests for service utils
 *
 * @author Maksim Patapenka
 */
@RunWith(JUnit4.class)
public class ServiceUtilsTest {

    /**
     * Tests subtract sets method
     */
    @Test
    public void testSubtract() {
        Set<Integer> minuend = createMockSet(1, 2, 3, 4, 5, 6, 7);
        Set<Integer> subtrahend = createMockSet(1, 3, 4, 5, 6);
        Set<Integer> expectedResult = createMockSet(2, 7);

        Set<Integer> result = ServiceUtils.subtract(minuend, subtrahend);
        assertArrayEquals(result.toArray(), expectedResult.toArray());
    }


    /**
     * Create mock set by arguments
     *
     * @param args integer arguments
     * @return {@code HashSet<Integer>}
     */
    private Set<Integer> createMockSet(int... args) {
        Set<Integer> mockSet = new HashSet<>();
        for (int arg : args) {
            mockSet.add(arg);
        }
        return mockSet;
    }

}
