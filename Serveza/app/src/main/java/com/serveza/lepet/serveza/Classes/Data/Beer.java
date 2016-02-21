package com.serveza.lepet.serveza.Classes.Data;

import java.io.Serializable;

/**
 * Created by lepet on 2/21/2016.
 */
public class Beer implements Serializable {



    public int _id;
    public String _name;
    public String _image;
    public String _desc;
    public double _degre;
    public String _product;
    public double _price;

    public Beer(int _id, String _name, String _image, String _desc, double _degre, String _product, double _price) {
        this._id = _id;
        this._name = _name;
        this._image = _image;
        this._desc = _desc;
        this._degre = _degre;
        this._product = _product;
        this._price = _price;
    }

    //get set

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_image() {
        return _image;
    }

    public void set_image(String _image) {
        this._image = _image;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    public double get_degre() {
        return _degre;
    }

    public void set_degre(double _degre) {
        this._degre = _degre;
    }

    public String get_product() {
        return _product;
    }

    public void set_product(String _product) {
        this._product = _product;
    }

    public double get_price() {
        return _price;
    }

    public void set_price(double _price) {
        this._price = _price;
    }



}
