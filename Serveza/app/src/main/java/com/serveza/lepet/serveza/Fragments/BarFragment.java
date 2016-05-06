package com.serveza.lepet.serveza.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.serveza.lepet.serveza.Adapter.BarListAdapter;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.R;

import java.util.concurrent.Callable;


public class BarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private View thisView;
    private Core core;
    private OnFragmentInteractionListener mListener;

    public BarFragment() {
        // Required empty public constructor
    }

    public static BarFragment newInstance(Core core) {
        BarFragment fragment = new BarFragment();
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
            core.network.GetFevBars(this.getContext(), core, new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return SetBarList();
                }
            });
        }
    }

    private int SetBarList() {

        ListView lv = (ListView) thisView.findViewById(R.id.ListBarOnFragment);

        lv.setAdapter(new BarListAdapter(this.getContext(), core.userBarList, core));
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisView = inflater.inflate(R.layout.fragment_bar, container, false);
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
