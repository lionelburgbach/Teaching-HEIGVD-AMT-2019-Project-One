package ch.heigvd.amt.projectOne.utils;

public class DateFormat {


    public static boolean correctFormatDate(String date){

        return date.matches("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
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
