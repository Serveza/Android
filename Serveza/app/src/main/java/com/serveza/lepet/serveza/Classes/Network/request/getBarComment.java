package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Bar;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.Classes.Data.CommentList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/5/2016.
 */
public class getBarComment extends HttpRequest {

    private Bar bar;
    public getBarComment(Context context, Core core, Callable<Integer> callback, Bar bar)
    {
        super(context, core, "/api/bars/" + bar.getID() + "/comments", RequestType.GET, callback);
        this.bar = bar;
    }

    protected void onPostExecute(String result) {
        Log.d("GetBarComment result :", result);
        if (!checkReturnCode())
            return;
        try {
            JSONObject object = new JSONObject(result);

            bar.commentList = CommentList.GetCommentList(object.getJSONArray("comments"));

            if (callback != null)
                callback.call();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
