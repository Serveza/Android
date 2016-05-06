package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Bar;
import com.serveza.lepet.serveza.Classes.Data.Beer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/5/2016.
 */
public class GetBarInfo extends HttpRequest {
    public Bar bar;

    public GetBarInfo(Context context, Core core, Callable<Integer> callback, Bar bar) {
        super(context, core, "/api/bars", RequestType.GET, callback);
        this.bar = bar;
    }

    public void SetParam() {
        UrlFinal += "/" + bar.getID();
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("GetBar result :", result);
        if (!checkReturnCode())
            return;
        try {
            JSONObject object = new JSONObject(result);

            bar.setName(object.getString("name"));
            bar.setName(object.getString("name"));
            bar.setName(object.getString("name"));
            bar.setName(object.getString("name"));

            callback.call();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
