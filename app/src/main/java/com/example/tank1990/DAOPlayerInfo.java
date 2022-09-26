package com.example.tank1990;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOPlayerInfo {

    private final DatabaseReference databaseReference;

    public DAOPlayerInfo() {
        FirebaseDatabase db = FirebaseDatabase.getInstance(
                "https://tank1990-debbd-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = db.getReference(PlayerInfo.class.getSimpleName());
    }

    public Task<Void> add(PlayerInfo player) {

        return databaseReference.push().setValue(player);
    }

    public Query get() {

        return databaseReference.orderByChild("score");
    }
}
