package com.serveza.lepet.serveza.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.LocalDatas.KeyValue;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.FacebookUtils;
import com.serveza.lepet.serveza.Utils.ImageDownloader;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class RegistrerActivity extends AppCompatActivity {

    private Core core;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;

    private Context context;

    private FacebookUtils fbu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_registrer);
        Intent i = getIntent();
        context = this;
        core = (Core) i.getSerializableExtra("Core");
        callbackManager = CallbackManager.Factory.create();
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();
                fbu = new FacebookUtils(accessToken);
                fbu.GetAllInfo(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return SetValue();
                    }
                });
            }

            @Override
            public void onCancel() {
                Log.e("onCancel", "");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("onError", error.toString());
            }
        });

    }

    private int SetValue() {
        Log.d("picture", fbu.image);

        TextViewUtils.SetText((TextView) findViewById(R.id.mailEntryRegisterEntry), fbu.mail);
        TextViewUtils.SetText((TextView) findViewById(R.id.firstNameEntryRegisterEntry), fbu.first_name);
        TextViewUtils.SetText((TextView) findViewById(R.id.lastNameRegisterEntry), fbu.last_name);
        ImageDownloader.SetImage(fbu.image, (ImageView) findViewById(R.id.imageRegisterView));
        core.network.Register(this.getApplicationContext(), core, fbu, new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        KeyValue.putValue(context, "api_token", "token");
                        KeyValue.putValue(context, "email", fbu.mail);
                        KeyValue.putValue(context, "password", String.valueOf(fbu.id));
                        return gobackAndConnect();
                    }
                }
        );
        return 0;
    }

    private int gobackAndConnect() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        return 0;
    }


    public void RegisterClick(View view) {

        core.network.Register(this.getApplicationContext(), core,
                TextViewUtils.GetText((TextView) findViewById(R.id.mailEntryRegisterEntry)),
                TextViewUtils.GetText((TextView) findViewById(R.id.firstNameEntryRegisterEntry)),
                TextViewUtils.GetText((TextView) findViewById(R.id.lastNameRegisterEntry)),
                TextViewUtils.GetText((TextView) findViewById(R.id.passwordRegisterEntry)),
                "https://1uebkg.bl3302.livefilestore.com/y3mhs-zdmiKaJ_xMhJo-SqBvrWwcz03OMfWGFW8wvEJqLl_X11Qj2u5pQzsfOCjNZiNaa_I9bZ8VrE3c7-5WBKO0CX07QB1ymkXBlTQPzZQAXKtu7GuSJpKRZ8L1Hf_orGjvonLjYLfY6ST9k09I7ISOTmA9dBSinl8RiEE1awlCoI/Logo.png?psid=1",
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return goback();
                    }
                }
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private int goback() {
        Toast.makeText(this.getApplicationContext(), "Account Create", Toast.LENGTH_SHORT);
        super.onBackPressed();
        return 0;
    }


    public void FindImage(View view) {

    }
}
