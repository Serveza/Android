package com.serveza.lepet.serveza.Classes.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lepet on 2/21/2016.
 */
public class BeerList implements Serializable {

    private List<Beer> _list;


    public BeerList()
    {
        _list = new ArrayList<Beer>();
    }

    public void Add(Beer NewBeer)
    {
        _list.add(NewBeer);
    }

    public  List<Beer> GetList()
    {
        return _list;
    }

    public void Init()
    {
        Add(new Beer(0, "Name", "Image", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(1, "Name", "Image", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(2, "Name", "Image", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(3, "Name", "Image", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(4, "Name", "Image", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(5, "Name", "Image", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(6, "Name", "Image", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(7, "Name", "Image", "Description", 5.0, "Brewer", 5.50));
    }
}