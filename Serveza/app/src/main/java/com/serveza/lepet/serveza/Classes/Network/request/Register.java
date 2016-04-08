package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 4/3/2016.
 */
public class Register extends HttpRequest {
    public Register(Context context, Core core, Callable<Integer> integerCallable) {
        super(context, core, "/api/user/register", RequestType.POST, integerCallable);
    }

    public void SetParam(String mail, String firstname, String lastname, String password, String image) {
        urlParameters.put("firstName", firstname);
        urlParameters.put("lastname", lastname);
        urlParameters.put("email", mail);
        urlParameters.put("password", password);
        urlParameters.put("avatar", image);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("Register", result);
        if (!checkReturnCode())
            return;
        try {
            JSONObject jsonObj = new JSONObject(result);
            if (callback != null)
                callback.call();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
