package com.serveza.lepet.serveza.Utils;

import android.view.View;
import android.widget.Button;

/**
 * Created by lepet on 3/29/2016.
 */
public class ButtonUtils {

    public static void SetActionLister(Button button, View.OnClickListener click) {
        button.setOnClickListener(click);
    }
}
