package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Bar;
import com.serveza.lepet.serveza.Classes.Data.EventList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/5/2016.
 */
public class getEventList extends HttpRequest {

    private EventList list;

    public getEventList(Context context, Core core, Callable<Integer> callable) {
        super(context, core, "/api/user/notifications", RequestType.GET, callable);
        list = null;
    }

    public getEventList(Context context, Core core, Callable<Integer> callable, EventList eventList) {
        super(context, core, "/api/user/notifications", RequestType.GET, callable);
        list = eventList;
    }

    public void SetParam(boolean update, String Token) {
        urlParameters.put("api_token", Token);
        if (update)
            urlParameters.put("update", "true");
    }

    protected void onPostExecute(String result) {
        Log.d("GetBarComment result :", result);
        if (!checkReturnCode())
            return;
        try {
            JSONObject object = new JSONObject(result);
            if (list != null)
                list = EventList.GetEventList(object.getJSONArray("notifications"), core.userBarList);
            else
                core.userEventList = EventList.GetEventList(object.getJSONArray("notifications"), core.userBarList);
            if (callback != null)
                callback.call();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
