package com.serveza.lepet.serveza.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.serveza.lepet.serveza.Adapter.BarFragmentAdapter;
import com.serveza.lepet.serveza.Adapter.BeerFragmentsAdapter;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Bar;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.Fragments.BarActivityFragment.BarCommentFragment;
import com.serveza.lepet.serveza.Fragments.BarActivityFragment.BarHomeFragment;
import com.serveza.lepet.serveza.R;

import java.util.concurrent.Callable;

public class BarActivity extends AppCompatActivity
    implements BarHomeFragment.OnFragmentInteractionListener,
        BarCommentFragment.OnFragmentInteractionListener{

    private Core core;
    private Bar bar;
    private ViewPager mViewPager;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        context = this.getApplicationContext();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        GetValueSerializable();
        SetElements();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.network.SetFavBar(context, core, bar.getID());
            }
        });
        SetElements();
    }


    private void GetValueSerializable() {
        Intent i = getIntent();
        core = (Core) i.getSerializableExtra("Core");
        bar = (Bar) i.getSerializableExtra("Bar");
    }

    private int SetElements() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new BarFragmentAdapter(getSupportFragmentManager(),
            this, core, bar));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return 0;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
