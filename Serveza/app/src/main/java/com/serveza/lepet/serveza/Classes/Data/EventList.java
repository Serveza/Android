package com.serveza.lepet.serveza.Classes.Data;

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
        Random random = new Random();

        for (int i = 0; i< 10; i++ )
        {
            eventList.Add(new Event("Event "+ String.valueOf(i), new Date(), new Date(),
                    barList.getList().get(random.nextInt(barList.getList().size())), "description"));
        }
        return eventList;
    }
}
