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

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.CardViewHolder> {

    private String[] mDataSet;
    private ArrayList<Travel> mArrayListTravel;
    private Context mcontext;

    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    // Pass in the array into the constructor
    public RecommendationAdapter(Context mcontext, ArrayList<Travel> mArrayListTravel) {

        //super(mContext, R.layout.row_home,mvehicle);
        this.mcontext = mcontext;
        this.mArrayListTravel = mArrayListTravel;

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private RecommendationAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(RecommendationAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mcontext;
    }

    @NonNull
    @Override
    public RecommendationAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_populardestination, parent, false);

        return new RecommendationAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationAdapter.CardViewHolder holder, int position) {

        holder.txtView_ProductName.setText(mArrayListTravel.get(position).getName());
        holder.imgView_PopularDest.setImageResource(mArrayListTravel.get(position).getImages());


        holder.card_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mcontext, Location_Detail_Activity.class);
                int pos = holder.getAbsoluteAdapterPosition();
                i.putExtra("Country", mArrayListTravel.get(pos).getName());
                i.putExtra("Country Image", mArrayListTravel.get(pos).getImages());
                i.putExtra("Country ID", mArrayListTravel.get(pos).getID());

                System.out.println(mArrayListTravel.get(pos).getName());
                System.out.println("Image:" + mArrayListTravel.get(pos).getImages());
                System.out.println(mArrayListTravel.get(pos).getID());

                mcontext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("Size " + mArrayListTravel.size());
        return mArrayListTravel.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        TextView txtView_ProductName, txtView_ProductId;
        ImageView imgView_PopularDest;
        CardView card_home;

        public CardViewHolder(@NonNull View v) {
            super(v);

            imgView_PopularDest = (ImageView) v.findViewById(R.id.imgView_PopularDest);
            txtView_ProductName = (TextView) v.findViewById(R.id.txtView_ProductName);
            txtView_ProductId = (TextView) v.findViewById(R.id.txtView_ProductId);
            card_home = (CardView) v.findViewById(R.id.card_home);
        }
    }
}
