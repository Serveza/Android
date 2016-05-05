package com.serveza.lepet.serveza.Utils;

/**
 * Created by lepet on 5/5/2016.
 */
public class Converter {

    public static double GetPrice(String raw)
    {
        String[] tmp = raw.split(" ");
        return Double.valueOf(tmp[1]);
    }
}
