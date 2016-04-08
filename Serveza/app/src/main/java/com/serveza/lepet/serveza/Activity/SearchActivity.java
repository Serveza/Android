package com.serveza.lepet.serveza.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.serveza.lepet.serveza.Adapter.BeerListSearchAdapter;
import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.BeerList;
import com.serveza.lepet.serveza.Classes.LocalDatas.DataBase;
import com.serveza.lepet.serveza.R;

import java.util.concurrent.Callable;

public class SearchActivity extends AppCompatActivity {

    private DataBase dataBaseBeer;
    private Core core;
    private ListView beerListView;
    public Context context;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent i = getIntent();
        core = (Core) i.getSerializableExtra("Core");
        beerListView = (ListView) this.findViewById(R.id.ResultListView);
        context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("DataBase update");
        builder.setMessage("Do you want update the database?");
        dialog = new ProgressDialog(this);
        dialog.setMessage("Load Beer");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                DataBase db = new DataBase(context);
                db.clean();

                core.network.GetAllBeer(context, core, new Callable<Integer>() {
                    @Override

                    public Integer call() throws Exception {
                        return SearchCanStart();
                    }
                });
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SearchCanStart();
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private int SearchCanStart() {

        Log.d("Search Activity", "can start");
        dialog.hide();
        dataBaseBeer = new DataBase(context);

        SearchView searchView = (SearchView) findViewById(R.id.SearchView);
        searchView.setOnQueryTextListener(mOnQueryTextListener);
        return 0;
    }

    private void updateResult(BeerList beerList) {
        BeerListSearchAdapter.setAdapter(this.getApplicationContext(), beerList, core, beerListView);
    }

    private final SearchView.OnQueryTextListener mOnQueryTextListener =
            new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {

                    Log.d("search ", newText);
                    updateResult(dataBaseBeer.getBeerByName(newText));

                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    updateResult(dataBaseBeer.getBeerByName(query));
                    return true;
                }
            };
}
