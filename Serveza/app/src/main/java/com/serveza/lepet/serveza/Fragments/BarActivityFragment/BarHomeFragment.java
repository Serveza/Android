package com.serveza.lepet.serveza.Fragments.BarActivityFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.serveza.lepet.serveza.Adapter.BeerListAdapter;
import com.serveza.lepet.serveza.Adapter.EventListAdapter;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Bar;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.ButtonUtils;
import com.serveza.lepet.serveza.Utils.ImageDownloader;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

import java.util.concurrent.Callable;


public class BarHomeFragment extends Fragment {

    private Core core;
    private Bar bar;
    private View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;

    public BarHomeFragment() {
        // Required empty public constructor
    }

    public static BarHomeFragment newInstance(Core param1, Bar param2) {
        BarHomeFragment fragment = new BarHomeFragment();
        Bundle args = new Bundle();
        args.putSerializable("Core", param1);
        args.putSerializable("Bar", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            core = (Core) getArguments().getSerializable("Core");
            bar = (Bar) getArguments().getSerializable("Bar");


        }
    }

    private void SetValue() {
        BeerListAdapter.SetAdapteur(this.getContext(), bar.getBeerList(), (ListView) view.findViewById(R.id.beerListView), core);

        TextViewUtils.SetText((TextView) view.findViewById(R.id.BarName), bar.getName());
        ImageDownloader.SetImage(bar.getImage(), (ImageView) view.findViewById(R.id.BarImage));
        ButtonUtils.SetActionLister((Button) view.findViewById(R.id.buttonGoBar), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:" + bar.getLatitude() + "," + bar.getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bar_home, container, false);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
