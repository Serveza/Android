package com.serveza.lepet.serveza.Utils;

/**
 * Created by lepet on 5/5/2016.
 */
public class GetCoor {

    static public double GetLongitude(String result)
    {
        String[] tmp = result.split(",");
        return Double.valueOf(tmp[1]);
    }

    static public double GetLatidude(String result)
    {
        String[] tmp = result.split(",");
        return Double.valueOf(tmp[0]);

    }

}
