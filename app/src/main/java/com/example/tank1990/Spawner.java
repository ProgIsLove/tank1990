package com.example.tank1990;

import android.content.Context;

public class Spawner {

    private Handler handler;
    private GameConstant gameCon;
    private Hud hud;
    private int counter = 0;
    private int respawnTime;
    private Context context;

    public Spawner(Handler handler, GameConstant gameCon, Context context) {
        this.handler = handler;
        this.gameCon = gameCon;
        this.context = context;
    }

    public void nextEnemy(int blockSpaceX, int blockSpaceY) {
        if(respawnTime <= 0 && hud.getEnemyCount() <= 5) {
            handler.addObject(new Enemy(context, 0 + blockSpaceX, 0 + blockSpaceY,
                    ID.Enemy, 1,handler, gameCon, this, hud));
            counter = hud.getEnemyCount();
            counter += 1;
            hud.setEnemyCount(counter);
            respawnTime = gameCon.getRespawnTime();
        } else
            respawnTime--;
    }

    public void nextLive() {
        handler.addObject(new Player(context, 175, 325,
                ID.Player, 1, handler, gameCon));
    }
}
