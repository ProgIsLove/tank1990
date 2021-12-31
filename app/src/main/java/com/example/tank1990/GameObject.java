package com.example.tank1990;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class GameObject {

    private int x;
    private int y;
    private ID id;
    private int speedX;
    private int speedY;
    private int direction;

    public GameObject(Context context, int x, int y, ID id, int direction) {
        this(x,y,id);
        this.direction = direction;
    }

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void update();
    public abstract void draw(Canvas canvas);
    public abstract Rect getBounds();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
