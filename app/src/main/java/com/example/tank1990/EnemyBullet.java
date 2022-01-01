package com.example.tank1990;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

public class EnemyBullet extends GameObject {

    private Handler handler;
    private GameConstant gameConstant;
    private SpriteSheet spriteSheet;


    public EnemyBullet(Context context, int x, int y, ID id, int direction, Handler handler,
                       GameConstant gameConstant) {
        super(context, x, y, id, direction);
        this.handler = handler;
        this.gameConstant = gameConstant;
        this.spriteSheet = new SpriteSheet(context);
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
        Sprite spriteBullet = spriteSheet.getPlayerSprite(100, 0, 125, 25);
        spriteBullet.draw(canvas, getX(), getY());
    }

    @Override
    public Rect getBounds() {
        return new Rect(getX(), getY(), gameConstant.getBulletSize(), gameConstant.getBulletSize());
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
//            int tempLive = hud.getLive();
//            int tempCrownLive = hud.getCrownLive();

            if (tempObject.getId() == ID.Bullet || tempObject.getId() == ID.Block_brick_wall) {
                if (getBounds().intersect(tempObject.getBounds())) {
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
                }
            }
            if (tempObject.getId() == ID.Player) {
                if (getBounds().intersect(tempObject.getBounds())) {
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
//                    tempLive -= 1;
//                    hud.setLive(tempLive);
//                    if(hud.getLive() != 0 || hud.getCrownLive() != 0) {
//                        spawner.nextLive();
//                    }
                }
            }
            if (tempObject.getId() == ID.Golden_crown) {
                if (getBounds().intersect(tempObject.getBounds())) {
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
//                    tempCrownLive -= 1;
//                    tempLive -= 1;
//                    hud.setCrownLive(tempCrownLive);
//                    hud.setLive(tempLive);
                }
            }
            if (tempObject.getId() == ID.Block_steel_wall || tempObject.getId() == ID.Block_sea_wall) {
                if (getBounds().intersect(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }
    }
}
