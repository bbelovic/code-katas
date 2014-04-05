package org.bbelovic.codekatas.euler1;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class Euler1KataTest {

    @Test
    public void should_return_sum_of_all_numbers_that_are_multiples_of_3_and_5_for_given_maximum() {
        final Euler1Kata euler1Kata = new Euler1Kata();
        int actual = euler1Kata.compute(10);
        assertEquals(actual, 23);
        actual = euler1Kata.compute(1000);
        assertEquals(actual, 233168);

    }
}
