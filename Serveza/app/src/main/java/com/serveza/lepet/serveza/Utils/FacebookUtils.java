package com.serveza.lepet.serveza.Utils;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

/**
 * Created by lepet on 5/6/2016.
 */
public class FacebookUtils {

    public int id;
    public String last_name;
    public String first_name;
    public String mail;
    public String image;
    private AccessToken accessToken;

    public FacebookUtils(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void GetAllInfo(final Callable<Integer> callable) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        Log.d("Response ", object.toString());

                        try {
                            last_name = object.getString("last_name");
                            first_name = object.getString("first_name");
                            mail = object.getString("email");
                            id = object.getInt("id");
                            image = object.getJSONObject("picture").getJSONObject("data").getString("url");
                            callable.call();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,first_name,last_name,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }


}
