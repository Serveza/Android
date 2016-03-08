package com.serveza.lepet.serveza.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.serveza.lepet.serveza.Activity.BeerActivity;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.BarList;
import com.serveza.lepet.serveza.Classes.Data.BeerList;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.ImageDownloader;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

/**
 * Created by lepet on 3/4/2016.
 */
public class BarListAdapter extends BaseAdapter {
    private Context context;
    private BarList barList;
    private static LayoutInflater inflater = null;
    private Core core;

    private int TemplateID;

    public BarListAdapter(Context context, BarList barList, Core core) {
        this.barList = barList;
        this.context = context;
        this.core = core;
        this.TemplateID = R.layout.barlisttemplate;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public BarListAdapter(Context context, BarList barList, Core core, int TemplateID) {
        this.barList = barList;
        this.context = context;
        this.core = core;
        this.TemplateID = TemplateID;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return barList.getList().size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(TemplateID, null);


        TextViewUtils.SetText((TextView) rowView.findViewById(R.id.BarListTemplateName), barList.getList().get(position).getName());
        TextViewUtils.SetText((TextView) rowView.findViewById(R.id.BarListTemplateDistance),
                String.valueOf(barList.getList().get(position).GetDistanceFrom()) + "km");
        ImageDownloader.SetImage(barList.getList().get(position).getImage(), (ImageView) rowView.findViewById(R.id.BarListTemplateImage));



        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BeerActivity.class);
                intent.putExtra("Bar", barList.getList().get(position));
                intent.putExtra("Core", core);
            //    context.startActivity(intent);
            }
        });
        return rowView;
    }

}
