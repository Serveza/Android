package com.serveza.lepet.serveza.Classes;

import com.serveza.lepet.serveza.Classes.Data.BarList;
import com.serveza.lepet.serveza.Classes.Data.BeerList;
import com.serveza.lepet.serveza.Classes.Data.EventList;
import com.serveza.lepet.serveza.Classes.Data.User;
import com.serveza.lepet.serveza.Classes.Network.Network;
import com.serveza.lepet.serveza.Classes.Settings.Settings;

import java.io.Serializable;

/**
 * Created by lepet on 2/21/2016.
 */
public class Core implements Serializable {

    public User user;
    public Network network;
    public Settings settings;

    public BeerList userBeerList;
    public BarList userBarList;
    public EventList userEventList;

    public BarList localBar;

    public Core() {
        network = new Network();

    }

    public void Init() {
        // user = User.GetUserLocal();
        userBarList = BarList.GetDebugBarList();
        userEventList = EventList.GenerateEventList(userBarList);
    }
}
