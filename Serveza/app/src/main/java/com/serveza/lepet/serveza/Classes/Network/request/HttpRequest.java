package com.serveza.lepet.serveza.Classes.Network.request;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Utils.JsonObjectExtention;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by lepet on 4/3/2016.
 */


public abstract class HttpRequest extends AsyncTask<String, Void, String> {
    public Callable<Integer> callback;
    protected static String UrlBase = "http://37.187.4.92:5010";
    protected String UrlFinal;
    protected Map<String, Object> urlParameters;
    protected Context context;
    protected Core core;
    protected RequestType requestType;

    private int StatuCode;

    public HttpRequest(Context context, Core core, String requestURL, RequestType requestType, Callable<Integer> callback) {
        urlParameters = new LinkedHashMap<>();
        this.context = context;
        this.core = core;
        this.UrlFinal = UrlBase + requestURL;
        this.requestType = requestType;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        if (requestType == RequestType.POST)
            return PostRequest();
        if (requestType == requestType.GET)
            return GetRequest();
        return "";
    }

    private String PostRequest() {
        String result = "";
        try {
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : urlParameters.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            Log.d("urlParamaetes", postData.toString());
            URL url = new URL(UrlFinal);
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            StatuCode = conn.getResponseCode();
            result = convertInputStreamToString(conn.getInputStream());
        } catch (IOException ex) {
            Log.d("HttpRequest Post exception", ex.toString());
        }
        Log.d("HttpRequest result : ", result);
        return result;
    }

    private String GetRequest() {
        String result = "";

        try {
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : urlParameters.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            Log.d("Get Request", postData.toString());
            URL url = new URL(UrlFinal + "?" + postData);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            StatuCode = conn.getResponseCode();
            result = convertInputStreamToString(conn.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Get Request", result);
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }

    protected boolean checkReturnCode() {
        Log.d("HttpStatueCode ", String.valueOf(StatuCode));
        if (StatuCode >= 400 && StatuCode < 500) {
            Toast.makeText(context, "Client Error", Toast.LENGTH_LONG).show();
            return false;
        } else if (StatuCode >= 500) {
            Toast.makeText(context, "Server Error", Toast.LENGTH_LONG).show();
            return false;
        } else if (StatuCode == 200) {
            return true;
        }
        return false;
    }

}
