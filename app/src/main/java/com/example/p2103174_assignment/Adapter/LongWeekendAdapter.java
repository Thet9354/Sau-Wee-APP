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

public class LongWeekendAdapter extends RecyclerView.Adapter<LongWeekendAdapter.CardViewHolder> {

    private String[] mDataSet;
    private ArrayList<Travel> mArrayListLongWeekend;
    private Context mContext;


    // Allows to remember the last item shown on screen
    private int lastPosition = -1;


    // Pass in the array into the constructor
    public LongWeekendAdapter(Context mcontext, ArrayList<Travel> mLongWeekendAdapter) {

        //super(mContext, R.layout.row_home,mvehicle);
        this.mContext = mcontext;
        this.mArrayListLongWeekend = mLongWeekendAdapter;

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private LongWeekendAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(LongWeekendAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }


    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }


    @NonNull
    @Override
    public LongWeekendAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_longweekend, parent, false);
        return new LongWeekendAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LongWeekendAdapter.CardViewHolder holder, int position) {

        holder.txtView_ProductName.setText(mArrayListLongWeekend.get(position).getName());
        holder.imgView_LongWeekend.setImageResource(mArrayListLongWeekend.get(position).getImages());

        holder.card_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, Location_Detail_Activity.class);
                int pos = holder.getAbsoluteAdapterPosition();
                i.putExtra("Country", mArrayListLongWeekend.get(pos).getName());
                i.putExtra("Country Image", mArrayListLongWeekend.get(pos).getImages());
                i.putExtra("Country ID", mArrayListLongWeekend.get(pos).getID());

                System.out.println(mArrayListLongWeekend.get(pos).getName());
                System.out.println(mArrayListLongWeekend.get(pos).getImages());
                System.out.println(mArrayListLongWeekend.get(pos).getID());

                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrayListLongWeekend.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        CardView card_home;
        ImageView imgView_LongWeekend;
        TextView txtView_ProductName, txtView_ProductId;

        public CardViewHolder(View v) {
            super(v);


            imgView_LongWeekend = (ImageView) v.findViewById(R.id.imgView_LongWeekend);
            txtView_ProductName = (TextView) v.findViewById(R.id.txtView_ProductName);
            txtView_ProductId = (TextView) v.findViewById(R.id.txtView_ProductId);
            card_home = (CardView) v.findViewById(R.id.card_home);
        }
    }
}
