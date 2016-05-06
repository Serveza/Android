package com.serveza.lepet.serveza.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.serveza.lepet.serveza.Activity.BeerActivity;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.BeerList;
import com.serveza.lepet.serveza.Classes.Data.CommentList;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.ImageDownloader;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

/**
 * Created by lepet on 2/21/2016.
 */
public class BeerListAdapter extends BaseAdapter {
    private Context context;
    private BeerList beerList;
    private static LayoutInflater inflater = null;
    private Core core;

    private int TemplateID;

    public BeerListAdapter(Context context, BeerList beerList, Core core) {
        this.beerList = beerList;
        this.context = context;
        this.core = core;
        this.TemplateID = R.layout.barlisttemplate;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public BeerListAdapter(Context context, BeerList beerList, Core core, int TemplateID) {
        this.beerList = beerList;
        this.context = context;
        this.core = core;
        this.TemplateID = TemplateID;
        inflater = (LayoutInflater) context.
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
        View rowView = inflater.inflate( R.layout.beerlisttemplate, null);

        TextViewUtils.SetText((TextView) rowView.findViewById(R.id.beerListTemplateName), beerList.GetList().get(position).get_name());
        TextViewUtils.SetText((TextView) rowView.findViewById(R.id.beerListTemplateBrower), beerList.GetList().get(position).get_product());
        ImageDownloader.SetImage(beerList.GetList().get(position).get_image(), (ImageView) rowView.findViewById(R.id.beerListTemplateImage));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BeerActivity.class);
                intent.putExtra("Beer", beerList.GetList().get(position));
                intent.putExtra("Core", core);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                //Toast.makeText(context, "You Clicked " + beerList.GetList().get(position).get_id(), Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

    public static void SetAdapteur(Context context, BeerList list, ListView listView, Core core)
    {
        listView.setAdapter(new BeerListAdapter(context, list, core));
    }

}
