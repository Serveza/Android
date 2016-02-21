package com.serveza.lepet.serveza.Utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by lepet on 2/21/2016.
 */
public class ImageUtils {

    public static void SetImageViewByUrl(ImageView imageView, String Url)
    {
       try
       {
           URL req = new URL(Url);
           Bitmap bm = BitmapFactory.decodeStream(req.openConnection().getInputStream());
           imageView.setImageBitmap(bm);
       }
       catch (Exception ex)
       {
            Log.d("SetImageViewByUrl", ex.getMessage());
       }



    }


}
