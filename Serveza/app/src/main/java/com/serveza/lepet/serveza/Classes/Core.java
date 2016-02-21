package com.serveza.lepet.serveza.Classes;

import com.serveza.lepet.serveza.Classes.Data.BeerList;
import com.serveza.lepet.serveza.Classes.Data.User;
import com.serveza.lepet.serveza.Classes.Network.Network;

import java.io.Serializable;

/**
 * Created by lepet on 2/21/2016.
 */
public class Core implements Serializable {

    public User user;
    public Network network;


    public BeerList userBeerList;
    public Core()
    {
          network = new Network();

    }

    public void Init()
    {
        user = User.GetUserLocal();
        userBeerList = new BeerList();
        userBeerList.Init();
    }
}
