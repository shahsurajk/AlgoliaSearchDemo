package com.madscientists.algoliademo;

import com.madscientists.algoliademo.util.Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void intFormatting_isCorrect() throws Exception {
        assertEquals("1.5k", Utils.intFormater(1526, 0));
        assertEquals("100", Utils.intFormater(100, 0));
        assertEquals("5.4k", Utils.intFormater(5453, 0));
        assertEquals("10k", Utils.intFormater(10026, 0));
    }

    @Test
    public void formatDate_isCorrect() throws Exception {
        assertEquals("Jan `17", Utils.formatDate("2017-01-30T21:47:30.000Z"));
        assertEquals("Mar `15", Utils.formatDate("2015-03-30T21:47:30.000Z"));
        assertEquals("May `21", Utils.formatDate("2021-05-30T21:47:30.000Z"));
    }
}