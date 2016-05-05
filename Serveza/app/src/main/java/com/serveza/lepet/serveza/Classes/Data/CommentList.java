package com.serveza.lepet.serveza.Classes.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lepet on 5/4/2016.
 */
public class CommentList implements Serializable {

    private List<Comment> _list;

    public CommentList()
    {
        _list = new ArrayList<Comment>();
    }

    public void Add(Comment newComment)
    {
        _list.add(newComment);
    }


    public List<Comment> get_list() {
        return _list;
    }

    public static CommentList GetCommentList(JSONArray array)
    {
        CommentList commentList = new CommentList();

        JSONObject tmp;
        JSONObject autor;
        try {
            for (int i = 0; i < array.length(); i++) {
                tmp = array.getJSONObject(i);
                autor = tmp.getJSONObject("author");
                commentList.Add(new Comment(tmp.getString("comment"), autor.getString("avatar"), autor.getString("firstname"), tmp.getInt("score")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return commentList;
    }
}

