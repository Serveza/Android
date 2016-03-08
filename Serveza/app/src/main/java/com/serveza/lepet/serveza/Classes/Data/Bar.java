package com.serveza.lepet.serveza.Classes.Data;

import java.io.Serializable;

/**
 * Created by lepet on 3/4/2016.
 */
public class Bar  implements Serializable {

    private String name;
    private String image;
    private String webSiteURL;
    private int ID;
    private double longitude;
    private double latitude;
    private BeerList beerList;


    public Bar(String name, String image, String webSiteURL, int ID, double longitude, double latitude) {
        this.name = name;
        this.image = image;
        this.webSiteURL = webSiteURL;
        this.ID = ID;
        this.longitude = longitude;
        this.latitude = latitude;

        beerList = BeerList.GetDebugBeerList();
    }

    public Double GetDistanceFrom()
    {
        return 0.0;
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
