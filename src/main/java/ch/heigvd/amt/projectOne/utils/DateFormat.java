package ch.heigvd.amt.projectOne.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {

    /**
     * Check if the date has a correct format
     * @param date  that we want to check
     * @return a boolean who indicate if the date has a correct format
     */
    public static boolean correctFormatDate(String date){

        return date.matches("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
    }

    /**
     * Check if the trail date is later than the current date
     * @param date that we want to check
     * @return if the date is in the future
     * @throws ParseException
     */
    public static boolean possibleDate(String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date now = Calendar.getInstance().getTime();
        String dateS = format.format(now);
        Date date1 = format.parse(date);
        Date date2 = format.parse(dateS);
        return date1.compareTo(date2) > 0;
    }

    /**
     * Check if the date is in the future or not
     * @param date that we want to check
     * @return a boolean who indicate if in the future or not
     * @throws ParseException
     */
    public static boolean futurDate(String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date now = Calendar.getInstance().getTime();
        String dateS = format.format(now);
        Date date1 = format.parse(date);
        Date date2 = format.parse(dateS);
        return date1.compareTo(date2) < 0;
    }

    /**
     * Change format from mysql to java
     * @param date that we want to transforme
     * @return a date in the Java format
     */
    public static String mysqlToJava(String date) {

        String[] s = date.split("-");
        String year = s[0];
        String month = s[1];
        String day = s[2];
        return day+"-"+month+"-"+year;
    }

    /**
     * Change format from java to mysql
     * @param date that we want to transforme
     * @return a date in the MySQL format
     */
    public static String javaToMysql(String date) {

        String[] s = date.split("-");
        String year = s[2];
        String month = s[1];
        String day = s[0];
        return year+"-"+month+"-"+day;
    }
}
