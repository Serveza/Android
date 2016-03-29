package com.serveza.lepet.serveza.Classes.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lepet on 3/29/2016.
 */
public class Event implements Serializable {

    private String name;
    private Date dateStart;
    private Date dateEnd;

    private Bar homeBar;

    private String description;

    public Event(String name, Date dateStart, Date dateEnd, Bar homeBar, String description) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.homeBar = homeBar;
        this.description = description;
    }

    public String GetDate() {
        SimpleDateFormat ft =
                new SimpleDateFormat("E yyyy MM dd 'at' hh:mm");

        if (dateStart == dateEnd)
            return ft.format(dateStart);
        return ft.format(dateStart) + " to " + ft.format(dateEnd);
    }

    public String getName() {
        return name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public Bar getHomeBar() {
        return homeBar;
    }

    public String getDescription() {
        return description;
    }
}
