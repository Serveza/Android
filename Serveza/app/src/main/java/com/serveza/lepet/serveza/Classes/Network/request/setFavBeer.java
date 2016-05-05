package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/4/2016.
 */
public class setFavBeer extends HttpRequest {

    public setFavBeer(Context context, Core core, Callable<Integer> integerCallable) {
        super(context, core, "/api/user/favorites/beers", RequestType.POST, integerCallable);
    }

    public void SetParma(int id, String token)
    {
        urlParameters.put("beer", id);
        urlParameters.put("api_token", token);
    }

    @Override
    protected void onPostExecute(String result) {
        if (!checkReturnCode())
            return;
        Log.d("Set Beer Fav", "ok");
    }
}
