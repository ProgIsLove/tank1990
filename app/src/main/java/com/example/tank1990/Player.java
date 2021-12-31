package com.example.tank1990;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Player extends GameObject{

    private Handler handler;
    private GameConstant gameCon;

    public Player(int x, int y, ID id, int direction) {
        super(x, y, id, direction);
        this.handler = handler;
        this.gameCon = gameCon;
    }

    @Override
    public void update() {
        int tempY = getY();
        tempY += getSpeedY();
        setY(tempY);

        int tempX = getX();
        tempX += getSpeedX();
        setX(tempX);

        collision();
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public Rect getBounds() {
        return null;
    }

    public void collision() {
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Block_sea_wall || tempObject.getId() == ID.Block_steel_wall || tempObject.getId() == ID.Block_brick_wall) {
                if(getBounds().intersect(tempObject.getBounds())) {

                    int tempX = getX();
                    tempX -= getSpeedX();
                    setX(tempX);

                    int tempY = getY();
                    tempY -= getSpeedY();
                    setY(tempY);
                }
            }
        }
    }
}
