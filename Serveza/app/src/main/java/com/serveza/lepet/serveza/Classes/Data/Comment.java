package com.serveza.lepet.serveza.Classes.Data;

import java.io.Serializable;

/**
 * Created by lepet on 5/4/2016.
 */
public class Comment implements Serializable {

    private String text;
    private String Image;
    private String userName;
    private int note;

    public Comment(String text, String image, String userName, int note) {
        this.text = text;
        Image = image;
        this.userName = userName;
        this.note = note;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return Image;
    }

    public String getUserName() {
        return userName;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNote() {

        return note;
    }
}
