package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/4/2016.
 */
public class sendComment extends HttpRequest {

    public sendComment(Context context, Core core, String url, Callable<Integer> callable)
    {
        super(context, core, url, RequestType.POST, callable);
    }

    public void SetParam(String token, String comment, int note)
    {
        urlParameters.put("api_token", token);
        urlParameters.put("comment", comment);
        urlParameters.put("score", note);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("Send Comment result ", result);
        if (!checkReturnCode())
            return;
        if (callback != null)
            try {
                callback.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
