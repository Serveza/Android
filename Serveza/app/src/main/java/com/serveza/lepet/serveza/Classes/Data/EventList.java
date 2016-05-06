package com.serveza.lepet.serveza.Classes.Data;

import com.serveza.lepet.serveza.Utils.Converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by lepet on 3/29/2016.
 */
public class EventList implements Serializable {

    public List<Event> getList() {
        return list;
    }

    public void setList(List<Event> list) {
        this.list = list;
    }

    private List<Event> list;

    public EventList() {
        list = new ArrayList<Event>();
    }

    public void Add(Event NewEvent) {
        list.add(NewEvent);
    }

    public static EventList GenerateEventList(BarList barList) {
        EventList eventList = new EventList();

        return eventList;
    }

     public static EventList GetEventList(JSONArray array, Bar bar) {
        EventList eventList = new EventList();
        JSONObject tmp;

        try {
            for (int i = 0; i < array.length(); i++) {
                tmp = array.getJSONObject(i);

                eventList.Add(new Event(tmp.getString("name"),
                        tmp.getString("start"),
                        tmp.getString("end"),
                        bar,
                        tmp.getString("description"),
                        false));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return eventList;
    }

    public static EventList GetEventList(JSONArray array, BarList barList) {
        EventList eventList = new EventList();
        JSONObject tmp;

        try {
            for (int i = 0; i < array.length(); i++) {
                tmp = array.getJSONObject(i);

                eventList.Add(new Event(tmp.getString("name"),
                        tmp.getString("start"),
                        tmp.getString("end"),
                        barList.GetBarByUrl(tmp.getString("bar")),
                        tmp.getString("description"),
                        true));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return eventList;
    }

}
