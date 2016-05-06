package com.serveza.lepet.serveza.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.serveza.lepet.serveza.Adapter.BarListAdapter;
import com.serveza.lepet.serveza.Adapter.BeerListSearchAdapter;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.Classes.Data.BeerList;
import com.serveza.lepet.serveza.Classes.LocalDatas.DataBase;
import com.serveza.lepet.serveza.R;

import java.util.concurrent.Callable;

public class SearchActivity extends AppCompatActivity
implements  GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;

    private DataBase dataBaseBeer;
    private Core core;
    private ListView beerListView;
    public Context context;
    private ProgressDialog dialog;

    private BeerList beersToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent i = getIntent();
        core = (Core) i.getSerializableExtra("Core");
        beerListView = (ListView) this.findViewById(R.id.ResultListView);
        context = this;
        beersToSearch = new BeerList();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Log.e("onConnectionFailed", "Location services connection failed with code " + connectionResult.getErrorCode());

                    }
                })
                        //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ProgressDialog(context);
                dialog.setMessage("Search");
                dialog.setCancelable(false);
                dialog.setInverseBackgroundForced(false);
                dialog.show();
                core.network.GetLocalBar(context, core, new Callable<Integer>() {
                   @Override
                   public Integer call() throws Exception {
                       dialog.hide();
                       return SetValue();
                   }
               }, 20.6779049, -103.3569464);
               //
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("DataBase update");
        builder.setMessage("Do you want update the database?");

        dialog = new ProgressDialog(this);
        dialog.setMessage("Load Beer");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                DataBase db = new DataBase(context);
                db.clean();

                core.network.GetAllBeer(context, core, new Callable<Integer>() {
                    @Override

                    public Integer call() throws Exception {
                        return SearchCanStart();
                    }
                });
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SearchCanStart();
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private int SetValue() {
        beerListView.setAdapter(new BarListAdapter(context, core.localBar.GetBarListByBeer(beersToSearch), core));
        return 0;
    }

    private int SearchCanStart() {

        Log.d("Search Activity", "can start");
        dialog.hide();
        dataBaseBeer = new DataBase(context);

        SearchView searchView = (SearchView) findViewById(R.id.SearchView);
        searchView.setOnQueryTextListener(mOnQueryTextListener);
        return 0;
    }

    private void updateResult(BeerList beerList) {
        BeerListSearchAdapter.setAdapter(this.getApplicationContext(), beerList, core, beerListView, beersToSearch);
    }

    private final SearchView.OnQueryTextListener mOnQueryTextListener =
            new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    updateResult(dataBaseBeer.getBeerByName(newText));
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    updateResult(dataBaseBeer.getBeerByName(query));
                    return true;
                }
            };

    public void onConnected(Bundle bundle) {
        Log.d("onConnected", "start");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("onConnected", "permissions failed");
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            Log.e("onConnectd", "location null");

        } else {
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
            Log.d("Coor : ", "[ " + currentLatitude + " , " + currentLongitude + " ]");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e("onConnectionFailed", "Location services connection failed with code " + connectionResult.getErrorCode());
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

        Log.d("Coor : ", "[ " + currentLatitude + " , " + currentLongitude + " ]");

    }

}
