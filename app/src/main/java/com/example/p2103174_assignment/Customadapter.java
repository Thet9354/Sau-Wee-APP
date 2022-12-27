package com.example.p2103174_assignment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Customadapter extends RecyclerView.Adapter<Customadapter.MyViewHolder>{

    Context context;
    ArrayList historyDate,historyCountry,historyLat,historyLong,historyID;
    ArrayList historyPhotoa;

    Addhelper db;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public Customadapter(Context context, ArrayList historyID, ArrayList historyDate, ArrayList historyCountry, ArrayList historyLat, ArrayList historyLong, ArrayList historyPhotoa){
        this.context = context;
        this.historyID = historyID;
        this.historyDate = historyDate;
        this.historyCountry = historyCountry;
        this.historyLat = historyLat;
        this.historyLong = historyLong;
        this.historyPhotoa = historyPhotoa;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        MyViewHolder evh = new MyViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.historyDatetxt.setText("Time and Date: " + historyDate.get(position));
        holder.historyCountrytxt.setText("Place : " + historyCountry.get(position));
        holder.historyLattxt.setText("Lat : " + historyLat.get(position));
        holder.historyLongtxt.setText("Long : " + historyLong.get(position));

        if((position < historyPhotoa.size()) && (historyPhotoa.size() == historyDate.size())) {
            holder.historyPhoto.setImageBitmap((Bitmap) historyPhotoa.get(position));
        }else if (historyPhotoa.size() < historyDate.size()){
            Bitmap bm = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.toolarge);
            historyPhotoa.add(bm);
            holder.historyPhoto.setImageBitmap((Bitmap) historyPhotoa.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return historyDate.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView historyDatetxt,historyCountrytxt,historyLattxt,historyLongtxt;
        ImageView historyPhoto;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            historyDatetxt = itemView.findViewById(R.id.userDate);
            historyCountrytxt = itemView.findViewById(R.id.userCountry);
            historyLattxt = itemView.findViewById(R.id.userLat);
            historyLongtxt = itemView.findViewById(R.id.userLong);
            historyPhoto = itemView.findViewById(R.id.userprofile);

            db = new Addhelper(context);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
