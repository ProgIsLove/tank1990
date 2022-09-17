package com.example.tank1990;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.core.content.ContextCompat;

public class Hud {

    private int enemyCount = 0;
    private int live = 3;
    private int crownLive = 1;
    private int score = 0;
    private final int space = 110;
    private final Context context;

    public Hud(Context context) {
        this.context = context;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setColor(color);
        paint.setTextSize(60);
        canvas.drawText("Lives ", (float) gameDisplay.HUDToDisplayCoordinatesX(-800),
                (float) gameDisplay.HUDToDisplayCoordinatesY(-800), paint);
        canvas.drawText("Score " + score, (float) gameDisplay.HUDToDisplayCoordinatesX(-200),
                (float) gameDisplay.HUDToDisplayCoordinatesY(-800), paint);
        canvas.drawText("Enemy ", (float) gameDisplay.HUDToDisplayCoordinatesX(600),
                (float) gameDisplay.HUDToDisplayCoordinatesY(-800), paint);

        RectF oval;
        for(int i = 0; i < enemyCount; i++) {
            oval = new RectF(1630, 250 + (i * space), 1530, 150 + (i * space));
            color = ContextCompat.getColor(context, R.color.enemy);
            paint.setColor(color);
            canvas.drawOval(oval, paint);
        }

        for(int i = 0; i < live; i++) {
            oval = new RectF(100, 250 + (i * space), 200, 150 + (i * space));
            color = ContextCompat.getColor(context, R.color.live);
            paint.setColor(color);
            canvas.drawOval(oval, paint);
        }
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public int getCrownLive() {
        return crownLive;
    }

    public void setCrownLive(int crownLive) {
        this.crownLive = crownLive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
