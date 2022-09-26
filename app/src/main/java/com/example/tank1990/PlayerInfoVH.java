package com.example.tank1990;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerInfoVH extends RecyclerView.ViewHolder {

    public TextView txt_name;
    public TextView txt_score;

    public PlayerInfoVH(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_score = itemView.findViewById(R.id.txt_score);
    }
}
