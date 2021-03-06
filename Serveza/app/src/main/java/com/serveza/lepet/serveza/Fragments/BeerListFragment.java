package com.serveza.lepet.serveza.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.serveza.lepet.serveza.Adapter.BeerListAdapter;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.R;

import java.util.concurrent.Callable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BeerListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BeerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeerListFragment extends Fragment {

    private View thisView;
    private Core core;

    private OnFragmentInteractionListener mListener;

    public BeerListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param core Core.
     * @return A new instance of fragment BeerListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BeerListFragment newInstance(Core core) {
        BeerListFragment fragment = new BeerListFragment();
        Bundle args = new Bundle();
        args.putSerializable("Core", core);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            core = (Core) getArguments().getSerializable("Core");
            core.network.GetFavBeer(this.getContext(), core, new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return SetBeerList();
                }
            });
        }


    }

    private int SetBeerList() {
        ListView lv = (ListView)thisView.findViewById(R.id.ListBeerOnFragment);
        lv.setAdapter(new BeerListAdapter(this.getContext(), core.userBeerList, core));

        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_beer_list, container, false);

       return thisView;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
