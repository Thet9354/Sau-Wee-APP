package com.example.p2103174_assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p2103174_assignment.Model.Travel;
import com.example.p2103174_assignment.R;

import java.util.ArrayList;

public class CityGuidesAdapter extends RecyclerView.Adapter<CityGuidesAdapter.CardViewHolder> {

    private String[] mDataSet;
    private ArrayList<Travel> mArraymCityGuide;
    private Context mContext;


    // Allows to remember the last item shown on screen
    private int lastPosition = -1;


    // Pass in the array into the constructor
    public CityGuidesAdapter(Context mcontext, ArrayList<Travel> thalidetails) {

        //super(mContext, R.layout.row_home,mvehicle);
        mArraymCityGuide = thalidetails;
        mContext = mcontext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private CityGuidesAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(CityGuidesAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }


    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public CityGuidesAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_cityguides, parent, false);
        return new CityGuidesAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityGuidesAdapter.CardViewHolder holder, int position) {

        holder.imgView_cityGuide.setImageResource(mArraymCityGuide.get(position).getImages());
        holder.txtView_ProductName.setText(mArraymCityGuide.get(position).getName());
        holder.txtView_productDesc.setText(mArraymCityGuide.get(position).getDesc());

        holder.card_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAbsoluteAdapterPosition();

                switch (mArraymCityGuide.get(pos).getName())
                {
                    case "Bon Appetit":
                        String url = "https://www.bonappetit.com/city-guides";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        mContext.startActivity(intent);
                        break;

                    case "Landmarks":
                        String url2 = "https://www.tiqets.com/blog/city-guides/";
                        Intent intent2 = new Intent(Intent.ACTION_VIEW);
                        intent2.setData(Uri.parse(url2));
                        mContext.startActivity(intent2);
                        break;

                    case "Airbnb":
                        String url3 = "https://www.etsy.com/sg-en/market/airbnb_city_guide";
                        Intent intent3 = new Intent(Intent.ACTION_VIEW);
                        intent3.setData(Uri.parse(url3));
                        mContext.startActivity(intent3);
                        break;

                    case "Hotels":
                        String url4 = "https://luxecityguides.com/";
                        Intent intent4 = new Intent(Intent.ACTION_VIEW);
                        intent4.setData(Uri.parse(url4));
                        mContext.startActivity(intent4);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArraymCityGuide.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        CardView card_home;
        ImageView imgView_cityGuide;
        TextView txtView_ProductName, txtView_productDesc;

        public CardViewHolder(View v) {
            super(v);

            imgView_cityGuide = (ImageView) v.findViewById(R.id.imgView_cityGuide);
            txtView_ProductName = (TextView) v.findViewById(R.id.txtView_ProductName);
            txtView_productDesc = (TextView) v.findViewById(R.id.txtView_productDesc);
            card_home = (CardView) v.findViewById(R.id.card_home);

        }
    }
}
