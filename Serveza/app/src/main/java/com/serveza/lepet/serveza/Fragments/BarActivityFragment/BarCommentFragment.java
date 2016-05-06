package com.serveza.lepet.serveza.Fragments.BarActivityFragment;

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
import com.serveza.lepet.serveza.Classes.Data.Bar;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.R;

import java.util.concurrent.Callable;

public class BarCommentFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private Core core;
    private Bar bar;
    private View view;
    private Context context;

    public BarCommentFragment() {
        // Required empty public constructor
    }

    public static BarCommentFragment newInstance(Core coreparam, Bar barparam) {
        BarCommentFragment fragment = new BarCommentFragment();
        Bundle args = new Bundle();
        args.putSerializable("Core", coreparam);
        args.putSerializable("Bar", barparam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            core = (Core) getArguments().getSerializable("Core");
            bar = (Bar) getArguments().getSerializable("Bar");
            core.network.GetBarComment(this.getContext(), core, new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return SetComment();
                }
            }, bar);
        }
    }
    private int SetComment() {
        CommentListAdapter.SetAdapteur(this.getContext(), bar.commentList, (ListView) view.findViewById(R.id.CommentBeerListView));
        return 0;
    }
    private int SetValue() {
        context = this.getContext();
        Button button = (Button) view.findViewById(R.id.AddCommentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddCommentActivity.class);
                intent.putExtra("Core", core);
                intent.putExtra("link", "/api/bars/" + bar.getID() + "/comments");
                intent.putExtra("image", bar.getImage());
                intent.putExtra("name", bar.getName());
                startActivity(intent);
            }
        });
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bar_comment, container, false);
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
