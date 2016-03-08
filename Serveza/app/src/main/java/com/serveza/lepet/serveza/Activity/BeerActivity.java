package com.serveza.lepet.serveza.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.ImageDownloader;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

public class BeerActivity extends AppCompatActivity {

    private Core core;
    private Beer beer;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        InitTab();
        GetValueSerializable();
        SetElements();
    }

    private void InitTab() {
        TabHost host = (TabHost)findViewById(R.id.BeerTabhost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec(getString(R.string.InformationTab));
        spec.setContent(R.id.BeerInformationTab);
        spec.setIndicator(getString(R.string.InformationTab));
        host.addTab(spec);

        spec = host.newTabSpec(getString(R.string.MapTab));
        spec.setContent(R.id.BeerMapTab);
        spec.setIndicator(getString(R.string.MapTab));
        host.addTab(spec);

        spec = host.newTabSpec(getString(R.string.CommentTab));
        spec.setContent(R.id.BeerCommentTab);
        spec.setIndicator(getString(R.string.CommentTab));
        host.addTab(spec);
    }

    private void GetValueSerializable() {
        Intent i = getIntent();
        core = (Core) i.getSerializableExtra("Core");
        beer = (Beer) i.getSerializableExtra("Beer");
    }

    private void SetElements() {
        TextViewUtils.SetText((TextView) (findViewById(R.id.BeerName)), beer.get_name());
        TextViewUtils.SetText((TextView) (findViewById(R.id.BeerBrowerName)), beer.get_product());
        TextViewUtils.SetText((TextView) (findViewById(R.id.BeerDegreName)), String.valueOf(beer.get_degre()) + "%");

        TextViewUtils.SetText((TextView) (findViewById(R.id.BeerInformation)), beer.get_desc());

        ImageDownloader.SetImage(beer.get_image(), (ImageView) (findViewById(R.id.BeerImage)));
    }
}
