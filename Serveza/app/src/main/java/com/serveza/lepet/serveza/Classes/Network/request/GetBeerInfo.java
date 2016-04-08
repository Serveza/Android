package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Beer;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.Reference;
import java.util.concurrent.Callable;

/**
 * Created by lepet on 4/7/2016.
 */
public class GetBeerInfo extends HttpRequest {
    public Beer beer;

    public GetBeerInfo(Context context, Core core, Callable<Integer> callback, Beer beer) {
        super(context, core, "/api/beers", RequestType.GET, callback);
        this.beer = beer;
    }

    public void SetParam() {
        UrlFinal += "/" + beer.get_id();
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("GetAllBeer result :", result);
        if (!checkReturnCode())
            return;
        try {
            JSONObject object = new JSONObject(result);
            beer.set_desc(object.getString("description"));
            beer.set_degre(object.getDouble("degree"));
            beer.set_image(object.getString("image"));
            beer.set_product(object.getString("brewery"));
            callback.call();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
