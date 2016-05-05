package com.serveza.lepet.serveza.Fragments.BeerActivityFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.serveza.lepet.serveza.Adapter.AddCommentActivity;
import com.serveza.lepet.serveza.Adapter.CommentListAdapter;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.R;

import java.util.concurrent.Callable;


public class BeerCommentFragment extends Fragment {

    private Core core;
    private Beer beer;
    private View view;
    private Context context;
    private OnFragmentInteractionListener mListener;


    public BeerCommentFragment() {
        // Required empty public constructor
    }

    public static BeerCommentFragment newInstance(Core coreparam, Beer beerparam) {
        BeerCommentFragment fragment = new BeerCommentFragment();
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
            core = (Core) getArguments().getSerializable("Core");
            beer = (Beer) getArguments().getSerializable("Beer");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_beer_comment, container, false);
        SetValue();
        return view;
    }

    private void SetValue() {
        context = this.getContext();
        Button button = (Button) view.findViewById(R.id.AddCommentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddCommentActivity.class);
                intent.putExtra("Core", core);
                intent.putExtra("link", "/api/beers/" + beer.get_id() + "/comments");
                intent.putExtra("image", beer.get_image());
                intent.putExtra("name", beer.get_name());
                startActivity(intent);
            }
        });

        core.network.GetBeerComment(this.getContext(), core, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return SetComment();
            }
        }, beer);
    }

    private int SetComment() {
        CommentListAdapter.SetAdapteur(this.getContext(), beer.commentList, (ListView) view.findViewById(R.id.CommentBeerListView));
        return 0;
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
        void onFragmentInteraction(Uri uri);
    }

    public void AddCommentClick(View v) {


    }
}
