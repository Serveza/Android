package com.serveza.lepet.serveza.Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Created by lepet on 4/5/2016.
 */
public class JsonObjectExtention extends JSONObject {
    public JsonObjectExtention() {
        super();
    }

    public OutputStream getOutputStream(HttpURLConnection connection) {
        OutputStream out = null;
        try {
            out = connection.getOutputStream();
            out.write(this.toString().getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}
