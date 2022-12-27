package com.example.p2103174_assignment.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.p2103174_assignment.Addhelper;
import com.example.p2103174_assignment.Customadapter;
import com.example.p2103174_assignment.MapActivity;
import com.example.p2103174_assignment.MapActivity3;
import com.example.p2103174_assignment.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PastVisits extends Fragment {

    private Context mContext;
    Addhelper db;
    ArrayList<String> historyDate, historyCountry, historyLat, historyLong, historyID;
    ArrayList<Bitmap> historyPhoto;
    Customadapter customadapter;

    @BindView(R.id.recView_UpcomingBooking)
    androidx.recyclerview.widget.RecyclerView rvUpcomingBooking;

    @BindView(R.id.pgb_UpcomingBooking)
    ProgressBar pgbUpcomingBooking;

    @BindView(R.id.imgView_settings)
    androidx.appcompat.widget.AppCompatImageView btnSettings;

    @BindView(R.id.btn_mapView)
    androidx.appcompat.widget.AppCompatButton btn_mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_past_visits, container, false);


        mContext = getActivity();

        ButterKnife.bind(this, rootView);

        initUi(rootView);

        pageDirectories(rootView);

        return rootView;
    }

    private void pageDirectories(View rootView) {

        btn_mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MapActivity3.class);
                intent.putStringArrayListExtra("userDateA",historyDate);
                intent.putStringArrayListExtra("userLatA",historyLat);
                intent.putStringArrayListExtra("userLongA",historyLong);
                startActivity(intent);
            }
        });

        customadapter.setOnItemClickListener(new Customadapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
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

                        Intent intent = new Intent(mContext, MapActivity.class);
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

    private void storeDatainArray() {
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
            Toast.makeText(mContext, "No data", Toast.LENGTH_SHORT).show();
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

    private void initUi(View rootView) {

        db = new Addhelper(mContext);

        historyDate = new ArrayList<>();
        historyCountry = new ArrayList<>();
        historyLat = new ArrayList<>();
        historyLong = new ArrayList<>();
        historyPhoto = new ArrayList<>();
        historyID = new ArrayList<>();
        storeDatainArray();

        customadapter = new Customadapter(mContext,historyID, historyDate,historyCountry,historyLat,historyLong, historyPhoto);
        rvUpcomingBooking.setAdapter(customadapter);
        rvUpcomingBooking.setLayoutManager(new LinearLayoutManager(mContext));
    }
}