package com.serveza.lepet.serveza.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.serveza.lepet.serveza.Activity.HomeActivity;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.R;

public class LoginActivity extends AppCompatActivity {


    private static String ACTIVITYNAME = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Login(View view)
    {
        Log.d(ACTIVITYNAME, "Login");

        Core core = new Core();
        core.Init();
        Intent i = new Intent(this, HomeActivity.class);
        i.putExtra("Core", core);
        startActivity(i);
    }

    public void Register(View view)
    {
        Log.d(ACTIVITYNAME, "Register");

    }
}
