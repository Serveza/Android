package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.BarList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/5/2016.
 */
public class getLocalBar extends HttpRequest {

    public getLocalBar(Context context, Core core, Callable<Integer> callable) {
        super(context, core, "/api/bars", RequestType.GET, callable);
    }

    public void SetParam(double longitude, double latitude, double range) {
        urlParameters.put("latitude", latitude);
        urlParameters.put("longitude", longitude);
        urlParameters.put("range", range);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("GetAllBeer result :", result);
        if (!checkReturnCode())
            return;
        try {
            JSONObject resultObj = new JSONObject(result);
            core.localBar = BarList.GetBarList(resultObj.getJSONArray("bars"));
            callback.call();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
