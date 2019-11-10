package ch.heigvd.amt.projectOne.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {

    /**
     *
     * @param date that we want to check (if it pass the regex)
     * @return a boolean who indicate if the date has a correct format
     */
    public static boolean correctFormatDate(String date){

        return date.matches("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
    }

    /**
     *
     * @param date that we want to check
     * @return
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
     *
     * @param date that we want to check
     * @return a boolean who indicate
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
     *
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
     *
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
