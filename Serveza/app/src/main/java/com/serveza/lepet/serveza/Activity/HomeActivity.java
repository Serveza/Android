package com.serveza.lepet.serveza.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.LocalDatas.KeyValue;
import com.serveza.lepet.serveza.Fragments.BarFragment;
import com.serveza.lepet.serveza.Fragments.BeerFragment;
import com.serveza.lepet.serveza.Fragments.BeerListFragment;
import com.serveza.lepet.serveza.Fragments.HistoryFragment;
import com.serveza.lepet.serveza.Fragments.HomeFragment;
import com.serveza.lepet.serveza.Fragments.ManageFragment;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Services.ServezaNotificationService;
import com.serveza.lepet.serveza.Utils.ImageDownloader;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        BarFragment.OnFragmentInteractionListener,
        BeerFragment.OnFragmentInteractionListener,
        HistoryFragment.OnFragmentInteractionListener,
        ManageFragment.OnFragmentInteractionListener,
        BeerListFragment.OnFragmentInteractionListener {

    private FrameLayout Fragment_Contener;

    private NavigationView navigationView;

    private Core core;
    private Context context;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        count = 0;
        context = this.getApplicationContext();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SearchActivity.class);
                i.putExtra("Core", core);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
        setFragment(HomeFragment.newInstance(core));
        navigationView.setNavigationItemSelectedListener(this);

        Fragment_Contener = (FrameLayout) findViewById(R.id.fragment_container);
        Init();
        SetElement();
    }

    private void Init() {
        Intent i = getIntent();
        core = (Core) i.getSerializableExtra("Core");
        Intent intent = new Intent(this, ServezaNotificationService.class);
        intent.putExtra("Core", core);
        startService(intent);
    }

    private void SetElement() {
        TextView userNameText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.UserNameText);
        userNameText.setText(core.user.get_firstName() + " " + core.user.get_lastName());
        TextView userMailText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.UserMailText);
        userMailText.setText(core.user.get_mailAdrress());

        ImageDownloader.SetImage(core.user.get_imageURL(), (ImageView) navigationView.getHeaderView(0).findViewById(R.id.UserImage));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setFragment(Fragment framgement) {

        framgement.setArguments(getIntent().getExtras());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, framgement);
        transaction.addToBackStack(null);
        count++;
        transaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setFragment(HomeFragment.newInstance(core));
        } else if (id == R.id.nav_bar) {
            setFragment(BarFragment.newInstance(core));

        } else if (id == R.id.nav_beer) {
            setFragment(BeerListFragment.newInstance(core));

        } else if (id == R.id.nav_manage) {
            setFragment(new ManageFragment());

        } else if (id == R.id.nav_logout) {
            KeyValue.putValue(this.getApplicationContext(), "api_token", "");
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
