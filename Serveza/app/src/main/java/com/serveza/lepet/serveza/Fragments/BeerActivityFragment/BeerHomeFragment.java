package com.serveza.lepet.serveza.Fragments.BeerActivityFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.ImageDownloader;
import com.serveza.lepet.serveza.Utils.TextViewUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BeerHomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BeerHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeerHomeFragment extends Fragment {

    private Core core;
    private Beer beer;
    private View view;
    private OnFragmentInteractionListener mListener;

    public BeerHomeFragment() {
        // Required empty public constructor
    }

     public static BeerHomeFragment newInstance(Core coreparam, Beer beerparam) {
        BeerHomeFragment fragment = new BeerHomeFragment();
        Bundle args = new Bundle();
        args.putSerializable("Core", coreparam);
        args.putSerializable("Beer", beerparam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            core = (Core)getArguments().getSerializable("Core");
            beer = (Beer)getArguments().getSerializable("Beer");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_beer_home, container, false);
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

    private void SetValue()
    {
        TextViewUtils.SetText((TextView) (view.findViewById(R.id.BeerName)), beer.get_name());
        TextViewUtils.SetText((TextView) (view.findViewById(R.id.BeerBrowerName)), beer.get_product());
        TextViewUtils.SetText((TextView) (view.findViewById(R.id.BeerDegreName)), String.valueOf(beer.get_degre()) + "%");

        TextViewUtils.SetText((TextView) (view.findViewById(R.id.BeerInformation)), beer.get_desc());

        ImageDownloader.SetImage(beer.get_image(), (ImageView) (view.findViewById(R.id.BeerImage)));

    }
}
