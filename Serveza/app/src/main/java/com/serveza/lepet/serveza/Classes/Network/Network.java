package com.serveza.lepet.serveza.Classes.Network;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.telecom.Call;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.Classes.Data.BeerList;
import com.serveza.lepet.serveza.Classes.Data.User;
import com.serveza.lepet.serveza.Classes.LocalDatas.DataBase;
import com.serveza.lepet.serveza.Classes.Network.request.GetBeerInfo;
import com.serveza.lepet.serveza.Classes.Network.request.Login;
import com.serveza.lepet.serveza.Classes.Network.request.Register;
import com.serveza.lepet.serveza.Classes.Network.request.getAllBeer;
import com.serveza.lepet.serveza.Classes.Network.request.getBeerComment;
import com.serveza.lepet.serveza.Classes.Network.request.getFavBeer;
import com.serveza.lepet.serveza.Classes.Network.request.getLocalBar;
import com.serveza.lepet.serveza.Classes.Network.request.sendComment;
import com.serveza.lepet.serveza.Classes.Network.request.setFavBeer;
import com.serveza.lepet.serveza.Classes.Settings.Settings;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.util.concurrent.Callable;

/**
 * Created by lepet on 2/21/2016.
 */
public class Network implements Serializable {

    public String Token;

    public Network() {

    }


    public void GetInfoBeer(Context context, Core core, Callable<Integer> callable, Beer beer) {
        GetBeerInfo getBeerInfo = new GetBeerInfo(context, core, callable, beer);
        getBeerInfo.SetParam();
        getBeerInfo.execute();
    }

    public void GetAllBeer(Context context, Core core, Callable<Integer> callable) {

        getAllBeer getAllBeer = new getAllBeer(context, core, callable);
        getAllBeer.execute();
    }

    public void Connection(Context context, Core core, String mail, String passworld, Callable<Integer> callable) {
        Login login = new Login(context, core, callable);
        login.SetParam(mail, passworld);
        login.execute();
    }

    public void Register(Context context, Core core, String mail, String firstname, String lastname, String passworld, String image, Callable<Integer> callable) {
        Register register = new Register(context, core, callable);
        register.SetParam(mail, firstname, lastname, passworld, image);
        register.execute();
    }

    public void SetFavBeer(Context context, Core core, int ID) {
        setFavBeer sfb = new setFavBeer(context, core, null);
        sfb.SetParma(ID, Token);

        sfb.execute();
    }

    public void GetFavBeer(Context context, Core core, Callable<Integer> callback) {
        getFavBeer gfb = new getFavBeer(context, core, callback);
        gfb.SetParam(Token);
        gfb.execute();
    }

    public void GetBeerComment(Context context, Core core, Callable<Integer> callable, Beer beer) {
        getBeerComment getBeerComment = new getBeerComment(context, core, callable, beer);
        getBeerComment.execute();
    }

    public void SendComment(Context context, Core core, Callable<Integer> callable, String link, String comment, int note) {
        sendComment sendComment = new sendComment(context, core, link, callable);
        sendComment.SetParam(Token, comment, note);
        sendComment.execute();
    }

    public void GetLocalBar(Context context, Core core, Callable<Integer> callable, double longitude, double latitude) {
        getLocalBar getLocalBar = new getLocalBar(context, core, callable);
        getLocalBar.SetParam(longitude, latitude, Settings.GetRange(context));
        Log.d("GetLocalBar", "execute");
        getLocalBar.execute();
    }
}
