package com.serveza.lepet.serveza.Classes.LocalDatas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.Classes.Data.BeerList;

import java.io.Serializable;

/**
 * Created by lepet on 4/7/2016.
 */
public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "beers.db";
    public static final String TABLE_NAME = "beer";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE = "image";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME +
                        "( " + COLUMN_ID + " integer , " + COLUMN_NAME + " text, " + COLUMN_IMAGE + " text)"
        );
    }

    public void clean()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public boolean insertBeer(Beer beer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, beer.get_id());
        contentValues.put(COLUMN_NAME, beer.get_name());
        contentValues.put(COLUMN_IMAGE, beer.get_image());

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public BeerList getBeerByName(String name) {
        BeerList list = new BeerList();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_NAME + " LIKE \"%" + name + "%\"", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            list.Add(new Beer(res.getInt(0), res.getString(1), res.getString(2)));
            res.moveToNext();
        }
        return list;
    }

}
