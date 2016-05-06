package com.serveza.lepet.serveza.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.serveza.lepet.serveza.Adapter.BarListAdapter;
import com.serveza.lepet.serveza.Adapter.EventListAdapter;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Bar;
import com.serveza.lepet.serveza.R;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class HomeFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    private View thisView;
    private GoogleMap mGoogleMap;
    private Core core;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MapView mMapView;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(Core core) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable("Core", core);
        fragment.setArguments(args);
        return fragment;
    }


    private void init(View rootView, Bundle savedInstanceState) {

        mMapView = (MapView) thisView.findViewById(R.id.map);


        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();//We display the map immediately

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mGoogleMap = googleMap;
                Log.d("Hello", mGoogleMap.toString());

                initMap();

            }
        });
    }

    private void initMap() {
        //we check if we have the permission to use the userPosition
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        moveCamera(currentLatitude, currentLatitude, 20);
        SetValue();
    }

    private void moveCamera(final double latitude, final double longitude, int zoom) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(zoom).build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.core = (Core) getArguments().getSerializable("Core");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (RecyclerView) thisView.findViewById(R.id.Event_RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new EventListAdapter(core.userEventList, this.getContext(), core));
        MapsInitializer.initialize(this.getContext());
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
        currentLatitude = 20.6779049;
        currentLongitude = -103.3569464;
        init(thisView, savedInstanceState);

        return thisView;
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
            SetValue();
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

    private void SetValue() {
        core.network.GetLocalBar(this.getContext(), core, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return SetBarList();
            }
        }, currentLatitude, currentLongitude);
        // DisplayBar();
        DisplayUser();
    }

    private void DisplayBar() {
        for (Bar bar : core.localBar.getList()) {
            displayMarker(bar.getName(), BitmapDescriptorFactory.HUE_AZURE, bar.getLatitude(), bar.getLongitude());
        }
    }

    private void DisplayUser() {
        displayMarker("You", BitmapDescriptorFactory.HUE_BLUE, currentLatitude, currentLongitude);
    }

    private void displayMarker(@NonNull final String markerName, final float markerColor, final double latitude, final double longitude) {
        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(markerName);

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory.defaultMarker(markerColor));

        // adding marker
        mGoogleMap.addMarker(marker);
    }

    private int SetBarList() {
        ListView lv = (ListView) thisView.findViewById(R.id.ListBarOnFragment);
        lv.setAdapter(new BarListAdapter(this.getContext(), core.localBar, core));
        return 0;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
