package ch.heigvd.amt.projectOne.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

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

    @Test
    void shouldBePossibleToAddAFutureDateForTrail() throws ParseException {

        String date = "12-10-2020";
        boolean res = DateFormat.possibleDate(date);
        assertTrue(res);
    }

    @Test
    void shouldNotBePossibleToAddAndOldDateForATrail() throws ParseException {

        String date = "12-10-2018";
        boolean res = DateFormat.possibleDate(date);
        assertFalse(res);
    }

}