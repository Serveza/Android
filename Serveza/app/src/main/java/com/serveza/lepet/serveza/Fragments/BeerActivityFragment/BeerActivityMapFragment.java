package com.serveza.lepet.serveza.Fragments.BeerActivityFragment;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;

import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.serveza.lepet.serveza.Adapter.BarListAdapter;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.Classes.Data.BeerList;

import com.serveza.lepet.serveza.R;

import java.util.concurrent.Callable;

public class BeerActivityMapFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;


    private Core core;
    private Beer beer;
    private View view;
    private OnFragmentInteractionListener mListener;

    public BeerActivityMapFragment() {
    }

    public static BeerActivityMapFragment newInstance(Core coreparam, Beer beerparam) {
        BeerActivityMapFragment fragment = new BeerActivityMapFragment();
        Bundle args = new Bundle();
        Log.d("BeerMap ", "newInstance");
        args.putSerializable("Core", coreparam);
        args.putSerializable("Beer", beerparam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BeerMap ", "onCreate");
        if (getArguments() != null) {
            core = (Core) getArguments().getSerializable("Core");
            beer = (Beer) getArguments().getSerializable("Beer");
            mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("BeerMap ", "onCreateView");
        view = inflater.inflate(R.layout.fragment_beer_activity_map, container, false);
        SetValue();
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void SetValue() {
        Log.d("BeerMap ", "SetValue");
        core.network.GetLocalBar(this.getContext(), core, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return SetBarList();
            }
        }, 20.6779049, -103.3569464);
    }

    private int SetBarList() {
        ListView lv = (ListView) view.findViewById(R.id.ListBarOnFragment);

        BeerList beerList = new BeerList();
        beerList.Add(beer);

        lv.setAdapter(new BarListAdapter(this.getContext(), core.localBar.GetBarListByBeer(beerList), core));

        return 0;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("onConnected", "start");
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                connectionResult.startResolutionForResult(this.getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
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
        SetValue();
        Log.d("Coor : ", "[ " + currentLatitude + " , " + currentLongitude + " ]");

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
