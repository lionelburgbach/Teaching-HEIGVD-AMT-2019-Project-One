package ch.heigvd.amt.projectOne.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {

    public static boolean correctFormatDate(String date){

        return date.matches("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
    }

    public static boolean possibleDate(String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date now = Calendar.getInstance().getTime();
        String dateS = format.format(now);
        Date date1 = format.parse(date);
        Date date2 = format.parse(dateS);
        return date1.compareTo(date2) > 0;
    }

    public static boolean futurDate(String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date now = Calendar.getInstance().getTime();
        String dateS = format.format(now);
        Date date1 = format.parse(date);
        Date date2 = format.parse(dateS);
        return date1.compareTo(date2) < 0;
    }


    public static String mysqlToJava(String date) {

        String[] s = date.split("-");
        String year = s[0];
        String month = s[1];
        String day = s[2];
        return day+"-"+month+"-"+year;
    }

    public static String javaToMysql(String date) {

        String[] s = date.split("-");
        String year = s[2];
        String month = s[1];
        String day = s[0];
        return year+"-"+month+"-"+day;
    }
}
