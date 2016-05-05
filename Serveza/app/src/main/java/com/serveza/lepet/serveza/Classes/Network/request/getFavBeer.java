package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.BeerList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/4/2016.
 */
public class getFavBeer extends HttpRequest {

    public getFavBeer(Context context, Core core, Callable<Integer> callback)
    {
        super(context, core, "/api/user/favorites/beers", RequestType.GET, callback);
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
            core.userBeerList = BeerList.GetBeerList(jsonObj.getJSONArray("beers"));
            if (callback != null)
                callback.call();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
