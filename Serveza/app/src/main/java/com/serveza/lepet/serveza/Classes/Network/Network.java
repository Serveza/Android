package com.serveza.lepet.serveza.Classes.Network;

import com.serveza.lepet.serveza.Classes.Data.BeerList;
import com.serveza.lepet.serveza.Classes.Data.User;

import java.io.Serializable;

/**
 * Created by lepet on 2/21/2016.
 */
public class Network implements Serializable {

    public Network()
    {

    }

    public User GetUser()
    {
        return User.GetUserLocal();
    }


}
