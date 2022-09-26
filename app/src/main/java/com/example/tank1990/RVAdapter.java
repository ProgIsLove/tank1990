package com.example.tank1990;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    List<PlayerInfo> players = new ArrayList<>();

    public RVAdapter(Context ctx) {
        this.context = ctx;
    }

    public void setItems(List<PlayerInfo> pl) {
        players.addAll(pl);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_records, parent,false);
        return new PlayerInfoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        PlayerInfoVH vh = (PlayerInfoVH) holder;
        PlayerInfo playerInfo = players.get(position);
        vh.txt_name.setText(playerInfo.getName());
        vh.txt_score.setText(playerInfo.getScore().toString());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
