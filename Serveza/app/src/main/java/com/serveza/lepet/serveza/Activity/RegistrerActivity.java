package com.serveza.lepet.serveza.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

import java.util.concurrent.Callable;

public class RegistrerActivity extends AppCompatActivity {

    private Core core;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);
        Intent i = getIntent();
        core = (Core) i.getSerializableExtra("Core");
    }


    public void RegisterClick(View view) {
        core.network.Register(this.getApplicationContext(), core,
                TextViewUtils.GetText((TextView) findViewById(R.id.mailEntryRegisterEntry)),
                TextViewUtils.GetText((TextView) findViewById(R.id.firstNameEntryRegisterEntry)),
                TextViewUtils.GetText((TextView) findViewById(R.id.lastNameRegisterEntry)),
                TextViewUtils.GetText((TextView) findViewById(R.id.passwordRegisterEntry)),
                "https://scontent-dfw1-1.xx.fbcdn.net/hphotos-xfl1/v/t1.0-9/10922803_10208019930037378_4351704227098544776_n.jpg?_nc_eui=ARiNrULLRgB2xky4bchP2c4BNIPR8KuJ2wqA7FDtb4dRcMns_anJbg&oh=813cde3ed0d4fbf8fe6884af8fd90cb3&oe=57779FD7",
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return goback();
                    }
                }
        );
    }

    private int goback() {
        Toast.makeText(this.getApplicationContext(), "Account Create", Toast.LENGTH_SHORT);
        super.onBackPressed();
        return 0;
    }

    public void FacebookClick(View view) {
    }

    public void FindImage(View view){

    }
}
