package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.BarList;
import com.serveza.lepet.serveza.Classes.Data.BeerList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/5/2016.
 */
public class getFavBar extends HttpRequest {

    public getFavBar(Context context, Core core, Callable<Integer> callback)
    {
        super(context, core, "/api/user/favorites/bars", RequestType.GET, callback);
    }

    public void SetParam(String token)
    {
        urlParameters.put("api_token", token);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("result", result);
        if (!checkReturnCode())
            return;
        try {
            JSONObject jsonObj = new JSONObject(result);
            core.userBarList = BarList.GetBarList(jsonObj.getJSONArray("bars"));
            Log.d("GetfavBar", "GetFevBars");
            if (callback != null)
                callback.call();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
