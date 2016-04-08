package com.serveza.lepet.serveza.Classes.Data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by lepet on 2/21/2016.
 */
public class User implements Serializable {


    private String _firstName;
    private String _lastName;
    private String _imageURL;
    private String _mailAdrress;


    public User(String firstName, String lastName, String imageUrl, String mailAdrress)
    {
        _firstName = firstName;
        _lastName = lastName;
        _imageURL = imageUrl;
        _mailAdrress = mailAdrress;
    }

    public static User GetUserLocal(){
        return new User("Thomas", "Caron",
                "https://scontent-lax3-1.xx.fbcdn.net/hphotos-xap1/v/t1.0-9/10922803_10208019930037378_4351704227098544776_n.jpg?_nc_eui=ARgto1Sb1gJo5cmXbO1Y-gSwKBQT6TzpZHO4cc4u5Tjp76ChjqszwA&oh=52aee323d241906b9c041bf7f1cd795f&oe=572885D7", "thomas.caron@epitech.eu");
    }

    public static User GetUser(JSONObject object, String mail){
        try
        {
            String firstName = object.getString("firstname");
            String lastName = object.getString("lastname");
            String image = object.getString("avatar");
            return new User(firstName, lastName, image,mail);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Get Set

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String get_imageURL() {
        return _imageURL;
    }

    public void set_imageURL(String _imageURL) {
        this._imageURL = _imageURL;
    }

    public String get_mailAdrress() {
        return _mailAdrress;
    }

    public void set_mailAdrress(String _mailAdrress) {
        this._mailAdrress = _mailAdrress;
    }


}
