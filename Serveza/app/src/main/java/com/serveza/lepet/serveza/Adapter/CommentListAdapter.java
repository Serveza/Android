package com.serveza.lepet.serveza.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.serveza.lepet.serveza.Classes.Data.CommentList;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.ImageDownloader;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

/**
 * Created by lepet on 5/4/2016.
 */
public class CommentListAdapter extends BaseAdapter {
    private Context context;
    private CommentList list;

    private static LayoutInflater inflater = null;


    public CommentListAdapter(Context context, CommentList list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return list.get_list().size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get_list().get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.comment_template, null);

        TextViewUtils.SetText((TextView) rowView.findViewById(R.id.commentTemplateText), list.get_list().get(position).getText());
        TextViewUtils.SetText((TextView) rowView.findViewById(R.id.commentTemplateNote), list.get_list().get(position).getNote() + " / 5");

        ImageDownloader.SetImage(list.get_list().get(position).getImage(),
                (ImageView) rowView.findViewById(R.id.commentTemplateImage));
        return rowView;
    }

    public static void SetAdapteur(Context context, CommentList list, ListView listView)
    {
        listView.setAdapter(new CommentListAdapter(context, list));
    }
}