package com.example.p2103174_assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p2103174_assignment.Location_Detail_Activity;
import com.example.p2103174_assignment.Model.Travel;
import com.example.p2103174_assignment.R;

import java.util.ArrayList;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.CardViewHolder>{

    private String[] mDataSet;
    private ArrayList<Travel> mArrayListDeals;
    private Context mContext;


    // Allows to remember the last item shown on screen
    private int lastPosition = -1;


    // Pass in the array into the constructor
    public DealsAdapter(Context mcontext, ArrayList<Travel> thalidetails) {

        //super(mContext, R.layout.row_home,mvehicle);
        mArrayListDeals = thalidetails;
        mContext = mcontext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private DealsAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(DealsAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }


    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public DealsAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_deals, parent, false);
        return new DealsAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DealsAdapter.CardViewHolder holder, int position) {

        holder.imgView_Deals.setImageResource(mArrayListDeals.get(position).getImages());

        holder.txtView_ProductName.setText(mArrayListDeals.get(position).getName());
        holder.txtView_productDesc.setText(mArrayListDeals.get(position).getDesc());
        holder.txtView_expDate.setText(mArrayListDeals.get(position).getExpDate());

        holder.card_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, Location_Detail_Activity.class);
                int pos = holder.getAbsoluteAdapterPosition();
                i.putExtra("Country", mArrayListDeals.get(pos).getName());
                i.putExtra("Deal Image", mArrayListDeals.get(pos).getImages());
                i.putExtra("Deal Desc", mArrayListDeals.get(pos).getDesc());

                System.out.println(mArrayListDeals.get(pos).getName());
                System.out.println(mArrayListDeals.get(pos).getImages());
                System.out.println(mArrayListDeals.get(pos).getDesc());

                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrayListDeals.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        CardView card_home;
        ImageView imgView_Deals;
        TextView txtView_ProductName, txtView_productDesc, txtView_expDate;

        public CardViewHolder(View v) {
            super(v);

            imgView_Deals = (ImageView) v.findViewById(R.id.imgView_Deals);

            txtView_ProductName = (TextView) v.findViewById(R.id.txtView_ProductName);
            txtView_productDesc = (TextView) v.findViewById(R.id.txtView_productDesc);
            txtView_expDate = (TextView) v.findViewById(R.id.txtView_expDate);
            card_home = (CardView) v.findViewById(R.id.card_home);

        }
    }
}
