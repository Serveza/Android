package com.serveza.lepet.serveza.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.serveza.lepet.serveza.Classes.Data.EventList;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.ButtonUtils;
import com.serveza.lepet.serveza.Utils.ImageDownloader;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

/**
 * Created by lepet on 3/29/2016.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    private EventList eventList;

    public EventListAdapter(EventList eventList)
    {
        this.eventList = eventList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }


    }
    public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eventcardtemplate, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...
        v.setPadding(0, 5, 0, 0);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // holder.mTextView.setText(mDataset[position]);

        TextViewUtils.SetText((TextView) holder.view.findViewById(R.id.EventNameTextView), eventList.getList().get(position).getName());
        TextViewUtils.SetText((TextView) holder.view.findViewById(R.id.EventDescriptionTextView), eventList.getList().get(position).getDescription());
        TextViewUtils.SetText((TextView) holder.view.findViewById(R.id.EventTimeTextView), eventList.getList().get(position).GetDate());
        ImageDownloader.SetImage(eventList.getList().get(position).getHomeBar().getImage(), (ImageView) holder.view.findViewById(R.id.EventImage));
        ButtonUtils.SetActionLister((Button) holder.view.findViewById(R.id.GoEventButton), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "On click", Toast.LENGTH_SHORT);
                Log.d("EventListAdapter : " , "On Click");
            }
        });

        ButtonUtils.SetActionLister((Button) holder.view.findViewById(R.id.ShowBarEventButton), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Show Bar", Toast.LENGTH_SHORT);
                Log.d("EventListAdapter : ", "Show Bar");

            }
        });



    }

    @Override
    public int getItemCount() {
        return eventList.getList().size();
    }
}
