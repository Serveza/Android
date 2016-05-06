package com.serveza.lepet.serveza.Utils;

import android.widget.TextView;

/**
 * Created by lepet on 3/4/2016.
 */
public class TextViewUtils {

    public static void SetText(TextView textView, String Text)
    {
        if (textView != null)
            textView.setText(Text);
    }
    public static String GetText(TextView textView)
    {
        return textView.getText().toString();
    }
}
