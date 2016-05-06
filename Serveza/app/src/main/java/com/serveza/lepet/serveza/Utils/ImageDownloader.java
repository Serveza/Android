package com.serveza.lepet.serveza.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lepet on 2/26/2016.
 */
public class ImageDownloader extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private ImageView imageView;

    public ImageDownloader(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            System.err.println(e.toString());
            Log.d("ImageDownloader, exception", e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        Log.d("ImageDownloader", "Set Image");
        if (result == null)
            return;
        imageView.setImageBitmap(result);
    }

    public static void SetImage(String Url, ImageView imageView)
    {
        if (Url == "")
            return;
        new ImageDownloader(Url, imageView).execute();
    }
}
