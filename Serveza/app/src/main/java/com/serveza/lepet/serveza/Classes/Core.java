package com.serveza.lepet.serveza.Classes;

import android.content.Intent;

import com.serveza.lepet.serveza.Activity.HomeActivity;
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

    public boolean isConnect;
    public User user;
    public Network network;

    public BeerList userBeerList;
    public BarList userBarList;
    public EventList userEventList;

    public BarList localBar;

    public Core() {
        network = new Network();

    }

    public void Init() {
        userBarList = null;

    }
}
