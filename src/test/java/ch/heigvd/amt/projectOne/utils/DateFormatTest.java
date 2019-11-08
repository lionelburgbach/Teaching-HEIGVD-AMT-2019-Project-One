package ch.heigvd.amt.projectOne.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatTest {

    @Test
    void shouldBeGoodFormatForDate(){

        boolean res = DateFormat.correctFormatDate("09-03-2019");
        assertTrue(res);
    }

    @Test
    void shouldBeWrongFormatForDate(){

        boolean res = DateFormat.correctFormatDate("2019-03-56");
        assertFalse(res);
    }

}