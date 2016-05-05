package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.Classes.Data.CommentList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/4/2016.
 */
public class getBeerComment extends HttpRequest {

    private Beer beer;
    public getBeerComment(Context context, Core core, Callable<Integer> callback, Beer beer)
    {
        super(context, core, "/api/beers/" + beer.get_id() + "/comments", RequestType.GET, callback);
        this.beer = beer;
    }

    protected void onPostExecute(String result) {
        Log.d("GetAllBeer result :", result);
        if (!checkReturnCode())
            return;
        try {
            JSONObject object = new JSONObject(result);

            beer.commentList = CommentList.GetCommentList(object.getJSONArray("comments"));

            if (callback != null)
                callback.call();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
