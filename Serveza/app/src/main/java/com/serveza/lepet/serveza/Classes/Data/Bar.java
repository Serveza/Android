package com.serveza.lepet.serveza.Classes.Data;

import com.serveza.lepet.serveza.Utils.Converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by lepet on 3/4/2016.
 */
public class Bar  implements Serializable {

    private String name;
    private String image;
    private String address;
    private String webSiteURL;
    private int ID;
    private double longitude;
    private double latitude;
    private BeerList beerList;

    public EventList eventList;

    public CommentList commentList;

    public Bar(String name, String image, int ID, double longitude, double latitude) {
        this.name = name;
        if (image == null)
            this.image = "";
        else
            this.image = image;
        this.ID = ID;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Bar(String name, String image, String webSiteURL, int ID, double longitude, double latitude) {
        this.name = name;
        this.image = image;
        this.webSiteURL = webSiteURL;
        this.ID = ID;
        this.longitude = longitude;
        this.latitude = latitude;

        beerList = BeerList.GetDebugBeerList();
    }

    public boolean ContaineThis(BeerList list)
    {
        for (int i = 0; i < list.GetList().size(); i++)
        {
            if (!this.beerList.contain(list.GetList().get(i)))
                return false;
        }
        return true;
    }

    public void Carte(JSONArray array) {
        JSONObject tmp;
        beerList = new BeerList();
        try {
            for (int i = 0; i < array.length(); i++) {
                tmp = array.getJSONObject(i);

                beerList.Add(new Beer(tmp.getInt("id"), tmp.getString("name"), tmp.getString("image")
                        , Converter.GetPrice(tmp.getString("price"))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString()
    {
        String string = "";

        for (int i = 0; i < beerList.GetList().size(); i++)
        {
            string += beerList.GetList().get(i).get_name() + " ";
        }
        return  string;
    }

    public Double GetDistanceFrom()
    {
        return 0.0;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BeerList getBeerList() {
        return beerList;
    }

    public void setBeerList(BeerList beerList) {
        this.beerList = beerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWebSiteURL() {
        return webSiteURL;
    }

    public void setWebSiteURL(String webSiteURL) {
        this.webSiteURL = webSiteURL;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
