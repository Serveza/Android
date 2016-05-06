package com.serveza.lepet.serveza.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.serveza.lepet.serveza.Activity.BarActivity;
import com.serveza.lepet.serveza.Classes.Core;
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
    private Core core;
    private Context context;

    public EventListAdapter(EventList eventList, Context context, Core core) {
        this.eventList = eventList;
        this.context = context;
        this.core = core;
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
        v.setPadding(0, 5, 0, 0);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        TextViewUtils.SetText((TextView) holder.view.findViewById(R.id.EventNameTextView), eventList.getList().get(position).getName());
        TextViewUtils.SetText((TextView) holder.view.findViewById(R.id.EventDescriptionTextView), eventList.getList().get(position).getDescription());
        TextViewUtils.SetText((TextView) holder.view.findViewById(R.id.EventTimeTextView), eventList.getList().get(position).GetDate());
        ImageDownloader.SetImage(eventList.getList().get(position).getImage(), (ImageView) holder.view.findViewById(R.id.EventImage));
        ButtonUtils.SetActionLister((Button) holder.view.findViewById(R.id.GoEventButton), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "On click", Toast.LENGTH_SHORT);
                Log.d("EventListAdapter : ", "On Click");
            }
        });

        if (eventList.getList().get(position).getShowGo() == false) {
            Button tmp = (Button) (holder.view.findViewById(R.id.ShowBarEventButton));
            tmp.setVisibility(View.INVISIBLE);
        }

        ButtonUtils.SetActionLister((Button) holder.view.findViewById(R.id.ShowBarEventButton), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BarActivity.class);
                intent.putExtra("Bar", eventList.getList().get(position).getHomeBar());
                intent.putExtra("Core", core);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.getList().size();
    }
}
