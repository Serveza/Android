package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Bar;
import com.serveza.lepet.serveza.Classes.Data.CommentList;
import com.serveza.lepet.serveza.Classes.Data.EventList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/5/2016.
 */
public class getEventBar extends HttpRequest {
    public Bar bar;

    public getEventBar(Context context, Core core, Callable<Integer> callable, Bar bar)
    {
        super(context, core, "/api/bars/" + bar.getID() + "/events", RequestType.GET, callable);
        this.bar = bar;
    }

    protected void onPostExecute(String result) {
        Log.d("GetBarComment result :", result);
        if (!checkReturnCode())
            return;
        try {
            JSONObject object = new JSONObject(result);
            bar.eventList = EventList.GetEventList(object.getJSONArray("events"), bar);
            if (callback != null)
                callback.call();
        } catch (JSONException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
