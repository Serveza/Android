package com.serveza.lepet.serveza.Classes.Settings;

import android.content.Context;

import com.serveza.lepet.serveza.Classes.LocalDatas.KeyValue;

import java.io.Serializable;

/**
 * Created by lepet on 5/5/2016.
 */
public class Settings implements Serializable {

    static private String rangeKey = "RANGE";

    public static double GetRange(Context context) {
        if (context == null)
            return 30.0;
        return Double.valueOf(KeyValue.getValue(context, rangeKey, "30"));
    }

    public static void SetRange(Context context, double range) {
        KeyValue.putValue(context, rangeKey, String.valueOf(range));
    }
}
