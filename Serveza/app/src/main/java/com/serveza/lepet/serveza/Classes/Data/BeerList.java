package com.serveza.lepet.serveza.Classes.Data;

import android.content.Context;

import com.serveza.lepet.serveza.Classes.LocalDatas.DataBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lepet on 2/21/2016.
 */
public class BeerList implements Serializable {

    private List<Beer> _list;


    public BeerList() {
        _list = new ArrayList<Beer>();
    }

    public void Add(Beer NewBeer) {
        _list.add(NewBeer);
    }

    public List<Beer> GetList() {
        return _list;
    }

    public void Init() {
        Add(new Beer(0, "Name", "http://icons.iconarchive.com/icons/flat-icons.com/flat/512/Beer-icon.png", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(1, "Name", "http://icons.iconarchive.com/icons/flat-icons.com/flat/512/Beer-icon.png", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(2, "Name", "http://icons.iconarchive.com/icons/flat-icons.com/flat/512/Beer-icon.png", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(3, "Name", "http://icons.iconarchive.com/icons/flat-icons.com/flat/512/Beer-icon.png", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(4, "Name", "http://icons.iconarchive.com/icons/flat-icons.com/flat/512/Beer-icon.png", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(5, "Name", "http://icons.iconarchive.com/icons/flat-icons.com/flat/512/Beer-icon.png", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(6, "Name", "http://icons.iconarchive.com/icons/flat-icons.com/flat/512/Beer-icon.png", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(7, "Name", "http://icons.iconarchive.com/icons/flat-icons.com/flat/512/Beer-icon.png", "Description", 5.0, "Brewer", 5.50));
    }

    static public BeerList GetDebugBeerList() {
        BeerList bl = new BeerList();
        bl.Init();
        return bl;
    }

    static public BeerList GetBeerList(JSONArray object) {
        BeerList ret = new BeerList();
        JSONObject tmp;
        try {
            for (int i = 0; i < object.length(); i++) {
                tmp = object.getJSONObject(i);
                ret.Add(new Beer(tmp.getInt("id"), tmp.getString("name")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }

    static public DataBase GetBeerListToDataBase(JSONArray object, Context context) {
        DataBase db = new DataBase(context);
        db.clean();
        JSONObject tmp;
        try {
            for (int i = 0; i < object.length(); i++) {
                tmp = object.getJSONObject(i);
                db.insertBeer(new Beer(tmp.getInt("id"), tmp.getString("name")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return db;
    }
}
