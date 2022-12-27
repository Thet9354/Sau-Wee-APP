package com.example.p2103174_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Addhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HistoryList.db";
    private static final int SCHEMA_VERSION = 2;

    public Addhelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE History_table( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "historyDate TEXT, historyCountry TEXT, historyLat TEXT, historyLong TEXT, historyNote TEXT, photo BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String historyDate, String historyCountry,
                       String historyLat, String historyLong, String historyNote, byte[] photo){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put("historyDate",historyDate);
        cv.put("historyCountry",historyCountry);
        cv.put("historyLat",historyLat);
        cv.put("historyLong",historyLong);
        cv.put("historyNote",historyNote);
        cv.put("photo", photo);

        db.insert("History_table", "historyDate", cv);
        Log.d("Actlife","db inserted");

    }

    public Cursor getdata() {
        String query = "Select * " + " From History_table";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }

    public Boolean delete(String historyID) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select *from History_table where _id = ?", new String[]{historyID});
        if (cursor.getCount() > 0) {
            long result = db.delete("History_table", "_id=?", new String[]{historyID});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
