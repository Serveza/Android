package com.serveza.lepet.serveza.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lepet on 5/5/2016.
 */
public class Converter {

    public static double GetPrice(String raw) {
        String[] tmp = raw.split(" ");
        return Double.valueOf(tmp[1]);
    }

    public static String getM(String raw)
    {
        switch (raw)
        {
            case "Jan":
                return "01";
            case "Feb":
                return "02";
            case "Mar":
                return "03";
            case "Apr":
                return "04";
            case "May":
                return "05";
            case "Jun":
                return "06";
            case "Jul":
                return "07";
            case "Aug":
                return "08";
            case "Sep":
                return "09";
            case "Oct":
                return "10";
            case "Nov":
                return "11";
            case "Dec":
                return "12";
        }
        return "";
    }

    public static Date GetDate(String raw) {
        Date date;
        String[] rawSplit = raw.split(" ");

        String dateRaw = rawSplit[1] + "-" + getM(rawSplit[2]) + "-" + rawSplit[3] + " " + rawSplit[4];

        SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        try {
            Date newdate = dateformat2.parse(raw);
            System.out.println(newdate);
            return newdate;
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return new Date();
    }
}
