package cl.ihov.project.view.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String intMonth2stringMonth(int month) {
        switch (month - 1) {
            case Calendar.JANUARY:
                return "Enero";
            case Calendar.FEBRUARY:
                return "Febrero";
            case Calendar.MARCH:
                return "Marzo";
            case Calendar.APRIL:
                return "Abril";
            case Calendar.MAY:
                return "Mayo";
            case Calendar.JUNE:
                return "Junio";
            case Calendar.JULY:
                return "Julio";
            case Calendar.AUGUST:
                return "Agosto";
            case Calendar.SEPTEMBER:
                return "Septiembre";
            case Calendar.OCTOBER:
                return "Octubre";
            case Calendar.NOVEMBER:
                return "Noviembre";
            case Calendar.DECEMBER:
                return "Diciembre";
            default:
                return "";
        }
    }
    
     public static String stringMonth2intMonth(String month) {
        switch (month) {
            case "Enero":
                return "1";
            case "Febrero":
                return "2";
            case "Marzo":
                return "3";
            case "Abril":
                return "4";
            case "Mayo":
                return "5";
            case "Junio":
                return "6";
            case "Julio":
                return "7";
            case "Agosto":
                return "8";
            case "Septiembre":
                return "9";
            case "Octubre":
                return "10";
            case "Noviembre":
                return "11";
            case "Diciembre":
                return "12";
            default:
                return "";
        }
    }
     
    public static String date2string(Date date) {
        String result = "";
        Calendar fecha = Calendar.getInstance();
        if (date != null) {
            fecha.setTime(date);
            result = (new SimpleDateFormat("dd/MM/yyyy")).format(fecha.getTime());
        }
        return result;
    }
    
    public static Date string2date(String p_dateString) {
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(p_dateString);
        } catch (Exception ex) {
            date = null;
        }
        return  date;
    }

}
