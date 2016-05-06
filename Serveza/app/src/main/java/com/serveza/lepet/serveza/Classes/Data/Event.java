package com.serveza.lepet.serveza.Classes.Data;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lepet on 3/29/2016.
 */
public class Event implements Serializable {

    private String name;
    private String dateStart;
    private String dateEnd;

    private String image;
    private Bar homeBar;

    public String getImage() {
        return image;
    }

    public Boolean getShowGo() {
        return showGo;
    }

    private Boolean showGo;

    private String description;

    public Event(String name, String dateStart, String dateEnd, Bar homeBar, String description, boolean showGo) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.homeBar = homeBar;
        this.description = description;
        if (homeBar != null) {
            Log.d("event bar ", homeBar.getName());
            if (homeBar.getImage() == null)
                this.image = "";
            else
                this.image = homeBar.getImage();
        }
        else
            this.image = "";
        this.showGo = showGo;
    }

    public String GetDate() {

        if (dateStart == dateEnd)
            return dateStart;
        return dateStart + " to " + dateEnd;
    }

    public String getName() {
        return name;
    }

    public Bar getHomeBar() {
        return homeBar;
    }

    public String getDescription() {
        return description;
    }
}
