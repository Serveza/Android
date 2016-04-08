package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.BeerList;
import com.serveza.lepet.serveza.Classes.Data.User;
import com.serveza.lepet.serveza.Classes.LocalDatas.DataBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 4/5/2016.
 */
public class getAllBeer extends HttpRequest {

    public getAllBeer(Context context, Core core, Callable<Integer> integerCallable)
    {
        super(context, core, "/api/beers", RequestType.GET, integerCallable);
        setParmam(core.network.Token);
    }

    public void setParmam(String token){
        urlParameters.put("api_token", token);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("GetAllBeer result :", result);
        if (!checkReturnCode())
            return;
        try {
            BeerList.GetBeerListToDataBase(new JSONArray(result), context).close();
            if (callback != null)
                callback.call();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
