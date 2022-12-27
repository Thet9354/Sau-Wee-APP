package com.example.p2103174_assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    Addhelper db;

    ArrayList<String> historyDate,historyCountry,historyLat,historyLong,historyID;

    ArrayList<Bitmap> historyPhoto;
    Customadapter customadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView historyview = (RecyclerView) findViewById(R.id.historyview);
        Button mapbtn = (Button) findViewById(R.id.Mapbtn);

        db = new Addhelper(this);

        historyDate = new ArrayList<>();
        historyCountry = new ArrayList<>();
        historyLat = new ArrayList<>();
        historyLong = new ArrayList<>();
        historyPhoto = new ArrayList<>();
        historyID = new ArrayList<>();
        storeDatainArray();

        customadapter = new Customadapter(HistoryActivity.this,historyID, historyDate,historyCountry,historyLat,historyLong, historyPhoto);
        historyview.setAdapter(customadapter);
        historyview.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));

        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, MapActivity3.class);
                intent.putStringArrayListExtra("userDateA",historyDate);
                intent.putStringArrayListExtra("userLatA",historyLat);
                intent.putStringArrayListExtra("userLongA",historyLong);
                startActivity(intent);
            }
        });

            customadapter.setOnItemClickListener(new Customadapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                    builder.setMessage("Record on " + historyDate.get(position));
                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Boolean deleted = db.delete(historyID.get(position));

                            if (deleted == true) {

                                Log.d("LOG",deleted.toString());

                                storeDatainArray();

                                customadapter.notifyDataSetChanged();

                            }
                        }
                    });
                    builder.setNegativeButton("See on Google Map", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent(HistoryActivity.this, MapActivity.class);
                            intent.putExtra("userDate", historyDate.get(position));
                            intent.putExtra("userLat", historyLat.get(position));
                            intent.putExtra("userLong", historyLong.get(position));
                            startActivity(intent);


                        }
                    });
                    builder.setCancelable(true);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
    }

    void storeDatainArray(){

        db.close();
        Cursor cursor = db.getdata();

        historyID.clear();
        historyDate.clear();
        historyCountry.clear();
        historyLat.clear();
        historyLong.clear();
        historyPhoto.clear();

        int i = 0;

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                historyID.add(cursor.getString(0));
                historyDate.add(cursor.getString(1));
                historyCountry.add(cursor.getString(2));
                historyLat.add(cursor.getString(3));
                historyLong.add(cursor.getString(4));

                i++;


                if(cursor.getBlob(5) !=  null) {
                    byte[] BArray = cursor.getBlob(5);
                    Bitmap bm = BitmapFactory.decodeByteArray(BArray, 0, BArray.length);
                    historyPhoto.add(bm);

                }else{
                    db.delete(historyID.get(i-1));
                }

                }
                Log.d("Actlife", String.valueOf(historyDate));
        db.close();
            }
        }
}