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
        Add(new Beer(0, "Name", "https://pixabay.com/static/uploads/photo/2015/10/01/21/39/background-image-967820_960_720.jpg", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(1, "Name", "https://pixabay.com/static/uploads/photo/2015/10/01/21/39/background-image-967820_960_720.jpg", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(2, "Name", "https://pixabay.com/static/uploads/photo/2015/10/01/21/39/background-image-967820_960_720.jpg", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(3, "Name", "https://pixabay.com/static/uploads/photo/2015/10/01/21/39/background-image-967820_960_720.jpg", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(4, "Name", "https://pixabay.com/static/uploads/photo/2015/10/01/21/39/background-image-967820_960_720.jpg", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(5, "Name", "https://pixabay.com/static/uploads/photo/2015/10/01/21/39/background-image-967820_960_720.jpg", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(6, "Name", "https://pixabay.com/static/uploads/photo/2015/10/01/21/39/background-image-967820_960_720.jpg", "Description", 5.0, "Brewer", 5.50));
        Add(new Beer(7, "Name", "https://pixabay.com/static/uploads/photo/2015/10/01/21/39/background-image-967820_960_720.jpg", "Description", 5.0, "Brewer", 5.50));
    }
}
