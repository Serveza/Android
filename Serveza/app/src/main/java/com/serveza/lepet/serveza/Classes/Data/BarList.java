package com.serveza.lepet.serveza.Classes.Data;

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
}
