package com.serveza.lepet.serveza.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.serveza.lepet.serveza.Adapter.BeerFragmentsAdapter;
import com.serveza.lepet.serveza.Adapter.CommentListAdapter;
import com.serveza.lepet.serveza.Adapter.AddCommentActivity;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.Fragments.BeerActivityFragment.BeerActivityMapFragment;
import com.serveza.lepet.serveza.Fragments.BeerActivityFragment.BeerCommentFragment;
import com.serveza.lepet.serveza.Fragments.BeerActivityFragment.BeerHomeFragment;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.ImageDownloader;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

import java.lang.ref.Reference;
import java.util.concurrent.Callable;

public class BeerActivity extends AppCompatActivity
        implements BeerHomeFragment.OnFragmentInteractionListener,
        BeerCommentFragment.OnFragmentInteractionListener,
        BeerActivityMapFragment.OnFragmentInteractionListener {

    private Core core;
    private Beer beer;
    private ViewPager mViewPager;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        context = this.getApplicationContext();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                core.network.SetFavBeer(context, core, beer.get_id());
            }
        });
        GetValueSerializable();
        core.network.GetInfoBeer(this.getApplicationContext(), core, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return SetElements();
            }
        }, beer);
    }


    private void GetValueSerializable() {
        Intent i = getIntent();
        core = (Core) i.getSerializableExtra("Core");
        beer = (Beer) i.getSerializableExtra("Beer");
    }

    private int SetElements() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new BeerFragmentsAdapter(getSupportFragmentManager(),
                this, core, beer));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


        return 0;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
