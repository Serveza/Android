package com.serveza.lepet.serveza.Classes.Data;

import com.serveza.lepet.serveza.Utils.Converter;
import com.serveza.lepet.serveza.Utils.GetCoor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lepet on 3/4/2016.
 */
public class BarList implements Serializable {

    public List<Bar> getList() {
        return list;
    }

    public void setList(List<Bar> list) {
        this.list = list;
    }

    private List<Bar> list;

    public BarList() {
        list = new ArrayList<Bar>();
    }

    public void Add(Bar NewBar) {
        list.add(NewBar);
    }

    public void Init() {
        Add(new Bar("bar", "https://cdn0.iconfinder.com/data/icons/brewery-icons/1536/Barrel-512.png", "www.google.com", 0, 0.0, 0.0));
        Add(new Bar("bar", "https://cdn0.iconfinder.com/data/icons/brewery-icons/1536/Barrel-512.png", "www.google.com", 1, 0.0, 0.0));
        Add(new Bar("bar", "https://cdn0.iconfinder.com/data/icons/brewery-icons/1536/Barrel-512.png", "www.google.com", 2, 0.0, 0.0));
        Add(new Bar("bar", "https://cdn0.iconfinder.com/data/icons/brewery-icons/1536/Barrel-512.png", "www.google.com", 3, 0.0, 0.0));
        Add(new Bar("bar", "https://cdn0.iconfinder.com/data/icons/brewery-icons/1536/Barrel-512.png", "www.google.com", 4, 0.0, 0.0));
        Add(new Bar("bar", "https://cdn0.iconfinder.com/data/icons/brewery-icons/1536/Barrel-512.png", "www.google.com", 5, 0.0, 0.0));
        Add(new Bar("bar", "https://cdn0.iconfinder.com/data/icons/brewery-icons/1536/Barrel-512.png", "www.google.com", 6, 0.0, 0.0));
        Add(new Bar("bar", "https://cdn0.iconfinder.com/data/icons/brewery-icons/1536/Barrel-512.png", "www.google.com", 7, 0.0, 0.0));
        Add(new Bar("bar", "https://cdn0.iconfinder.com/data/icons/brewery-icons/1536/Barrel-512.png", "www.google.com", 8, 0.0, 0.0));
        Add(new Bar("bar", "https://cdn0.iconfinder.com/data/icons/brewery-icons/1536/Barrel-512.png", "www.google.com", 9, 0.0, 0.0));
    }

    static public BarList GetDebugBarList() {
        BarList bl = new BarList();
        bl.Init();
        return bl;
    }

    public BarList GetBarListByBeer(BeerList list) {
        BarList barList = new BarList();
        for (int indexBar = 0; indexBar < this.list.size(); indexBar++) {
            if (this.list.get(indexBar).ContaineThis(list))
                barList.Add(this.list.get(indexBar));
        }
        return this;
    }

    public static BarList GetBarList(JSONArray array) {
        BarList barList = new BarList();
        JSONObject tmp;
        try {
            for (int i = 0; i < array.length(); i++) {
                tmp = array.getJSONObject(i);

                barList.Add(new Bar(tmp.getString("name"), tmp.getString("image"),
                        tmp.getInt("id"), GetCoor.GetLongitude(tmp.getString("position")),
                        GetCoor.GetLatidude(tmp.getString("position"))));
                barList.getList().get(i).Carte(tmp.getJSONArray("carte"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return barList;
    }

}
