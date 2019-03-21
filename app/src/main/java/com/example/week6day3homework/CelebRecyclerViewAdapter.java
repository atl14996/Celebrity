package com.example.week6day3homework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CelebRecyclerViewAdapter extends RecyclerView.Adapter<CelebRecyclerViewAdapter.ViewHolder> {
    ArrayList<Celebrity> celebrityArrayList;


    public CelebRecyclerViewAdapter(ArrayList<Celebrity> celebrityArrayList) {
        this.celebrityArrayList = celebrityArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return celebrityArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgCeleb;
        ImageButton btnFavorite;
        ImageButton btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCelebName);
            imgCeleb = itemView.findViewById(R.id.imgCelebPicture);
            btnFavorite = itemView.findViewById(R.id.btnfavorite);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
