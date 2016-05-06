package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.User;
import com.serveza.lepet.serveza.Classes.LocalDatas.KeyValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 4/3/2016.
 */
public class Login extends HttpRequest {

    public Login(Context context, Core core, Callable<Integer> integerCallable) {
        super(context, core, "/api/user/login", RequestType.POST, integerCallable);
    }

    public void SetParam(String mail, String password) {
        KeyValue.putValue(context, "email", mail);
        KeyValue.putValue(context, "password", password);
        urlParameters.put("email", mail);
        urlParameters.put("password", password);
    }

    @Override
    protected void onPostExecute(String result) {

        try {
            if (!checkReturnCode())
            {
                core.isConnect = false;
                if (callback != null)
                    callback.call();
                return;
            }
            JSONObject jsonObj = new JSONObject(result);
            core.user = User.GetUser(jsonObj, (String) urlParameters.get("email"));
            core.network.Token = jsonObj.getString("api_token");
            KeyValue.putValue(context, "api_token", core.network.Token);
            core.isConnect = true;
            if (callback != null)
                callback.call();


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
