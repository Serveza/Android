package com.serveza.lepet.serveza.Adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.serveza.lepet.serveza.Classes.Data.BeerList;
import com.serveza.lepet.serveza.R;

/**
 * Created by lepet on 2/21/2016.
 */
public class BeerListAdapter extends BaseAdapter {
    private Context context;
    private BeerList beerList;
    private static LayoutInflater inflater = null;

    public BeerListAdapter(Context context, BeerList beerList) {
        this.beerList = beerList;
        this.context = context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return beerList.GetList().size();
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
        View rowView = inflater.inflate(R.layout.beerlisttemplate, null);

        TextView tv = (TextView) rowView.findViewById(R.id.beerListTemplateName);
        ImageView iw = (ImageView) rowView.findViewById(R.id.beerListTemplateImage);

        tv.setText(beerList.GetList().get(position).get_name());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Clicked " + beerList.GetList().get(position).get_id(), Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}
