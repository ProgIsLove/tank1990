package com.example.tank1990;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player extends GameObject{

    private Handler handler;
    private GameConstant gameCon;
    private SpriteSheet spriteSheet;

    public Player(Context context, int x, int y, ID id, int direction, Handler handler,
                  GameConstant gameCon) {
        super(context, x, y, id, direction);

        this.handler = handler;
        this.gameCon = gameCon;

        this.spriteSheet = new SpriteSheet(context);
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

        if(getDirection() == 1) {
            Sprite spriteUp = spriteSheet.getPlayerSprite(125, 0, 150, 25);
            spriteUp.draw(canvas, getX(), getY());
        } else if(getDirection() == 2) {
            Sprite spriteRight = spriteSheet.getPlayerSprite(25, 25, 50, 50);
            spriteRight.draw(canvas, getX(), getY());
        } else if(getDirection() == 3) {
            Sprite spriteLeft = spriteSheet.getPlayerSprite(0, 25, 25, 50);
            spriteLeft.draw(canvas, getX(), getY());
        } else if(getDirection() == 4) {
            Sprite spriteDown = spriteSheet.getPlayerSprite(50, 25, 75, 50);
            spriteDown.draw(canvas, getX(), getY());
        }
    }

    @Override
    public Rect getBounds() {
        return new Rect(getX(), getY(), gameCon.getTankSize(), gameCon.getTankSize());
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
