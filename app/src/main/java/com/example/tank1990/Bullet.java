package com.example.tank1990;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Bullet extends GameObject{

    private Handler handler;
    private GameConstant gameConstant;
    private SpriteSheet spriteSheet;
    private Hud hud;


    public Bullet(Context context, int x, int y, ID id, int direction, Handler handler,
                  GameConstant gameConstant, Hud hud) {
        super(context, x, y, id, direction);
        this.handler = handler;
        this.gameConstant = gameConstant;
        this.spriteSheet = new SpriteSheet(context);
        this.hud = hud;
    }

    @Override
    public void update() {
        setSpeedY(gameConstant.getBulletSpeed());
        setSpeedX(gameConstant.getBulletSpeed());
        switch (getDirection()) {
            case 1: {
                int tempY = getY();
                tempY -= getSpeedY();
                setY(tempY);
                break;
            }
            case 2: {
                int tempY = getY();
                tempY += getSpeedY();
                setY(tempY);
                break;
            }
            case 3: {
                int tempX = getX();
                tempX -= getSpeedX();
                setX(tempX);
                break;
            }
            case 4: {
                int tempX = getX();
                tempX += getSpeedX();
                setX(tempX);
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + getDirection());
        }

        collision();

    }

    @Override
    public void draw(Canvas canvas) {
        Sprite spriteBullet = spriteSheet.getPlayerSprite(125, 0, 150, 25);
        spriteBullet.draw(canvas, getX(), getY());
    }

    @Override
    public Rect getBounds() {
        return new Rect(getX(), getY(), gameConstant.getBulletSize(), gameConstant.getBulletSize());
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Block_brick_wall) {
                if (getBounds().intersect(tempObject.getBounds())) {
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
                }
            }
            if(tempObject.getId() == ID.Enemy) {
                if (getBounds().intersect(tempObject.getBounds())) {
                    int tempCounter = hud.getEnemyCount();
                    int tempScore = hud.getScore();
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
                    tempCounter -= 1;
                    tempScore += gameConstant.getScore();
                    hud.setEnemyCount(tempCounter);
                    hud.setScore(tempScore);
                }
            }

            if(tempObject.getId() == ID.Block_steel_wall || tempObject.getId() == ID.Block_sea_wall) {
                if(getBounds().intersect(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }
    }
}
