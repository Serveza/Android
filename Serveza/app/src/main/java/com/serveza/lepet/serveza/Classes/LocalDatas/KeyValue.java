package com.serveza.lepet.serveza.Classes.LocalDatas;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by lepet on 4/7/2016.
 */
public class KeyValue {

    public static final String PREFS_NAME = "APP_DATA";

    public static void putValue(Context context, String key, String value)
    {
        Log.d("PutValue", key + " : " + value);
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getValue(Context context, String key, String defaultValue)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        String value = settings.getString(key, defaultValue);
        Log.d("getValue", key + " : " + value);
        return value;
    }


}
