package com.serveza.lepet.serveza.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.serveza.lepet.serveza.Activity.HomeActivity;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.LocalDatas.KeyValue;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

import java.util.concurrent.Callable;

public class LoginActivity extends AppCompatActivity {


    private static String ACTIVITYNAME = "MainActivity";
    private Core core;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        core = new Core();


        core.network.Token = KeyValue.getValue(this.getApplicationContext(), "api_token", "");
        Log.d(ACTIVITYNAME, "Token {" + core.network.Token + "}");
        dialog=new ProgressDialog(this);
        dialog.setMessage("Connection");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);

        if (core.network.Token != "") {
            dialog.show();
            core.network.Connection(this.getApplicationContext(), core,
                    KeyValue.getValue(this.getApplicationContext(), "email", ""),
                    KeyValue.getValue(this.getApplicationContext(), "password", ""),
                    new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            return Login();
                        }
                    });
        }
    }

    public void Login(View view) {
        Log.d(ACTIVITYNAME, "Login");
        dialog.show();
        core.network.Connection(this.getApplicationContext(), core,
                TextViewUtils.GetText((TextView) findViewById(R.id.mailEntry)),
                TextViewUtils.GetText((TextView) findViewById(R.id.passwordEntry)),
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return Login();
                    }
                });
    }

    public int Login() {
        Log.d("Login", "login");

        if (!core.isConnect) {
            dialog.hide();
            Toast.makeText(this.getApplicationContext(), "Can't connect", Toast.LENGTH_LONG);
            return 1;
        }

        core.Init();
        Log.d("Login", "GetBar");
        core.network.GetFevBars(this, core, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return GetEvent();
            }
        });
        return 0;
    }

    private int GetEvent() {
        Log.d("Login", "GetEvent");
        core.network.GetUserEvent(this, core, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getBeer();
            }
        });
        return 0;
    }

    private int getBeer() {
        Log.d("Login", "GetBeer");
        core.network.GetFavBeer(this, core, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return Navigate();
            }
        });
        return 0;
    }

    private int Navigate() {
        dialog.hide();
        Intent i = new Intent(this, HomeActivity.class);
        i.putExtra("Core", core);
        startActivity(i);
        return 0;
    }

    public void Register(View view) {
        Log.d(ACTIVITYNAME, "Register");
        Intent i = new Intent(this, RegistrerActivity.class);
        i.putExtra("Core", core);
        startActivity(i);
    }

    @Override
    public void onBackPressed()
    {
        this.finishAffinity();
    }
}
