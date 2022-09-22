package com.example.tank1990;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

import java.util.Random;

public class Enemy extends GameObject{

    private Random rnd = new Random();
    private Handler handler;
    private GameConstant gameConstant;
    private SpriteSheet spriteSheet;
    private Spawner spawner;
    private Hud hud;
    private int timerShoot;

    public Enemy(Context context, int x, int y, ID id, int direction, Handler handler,
                 GameConstant gameConstant, Spawner spawner, Hud hud) {
        super(context, x, y, id, direction);

        this.handler = handler;
        this.gameConstant = gameConstant;
        this.spawner = spawner;
        this.hud = hud;
        this.spriteSheet = new SpriteSheet(context);

        timerShoot = rnd.nextInt(gameConstant.getTimerShoot());
    }

    @Override
    public void update() {
        setSpeedX(gameConstant.getSpeed());
        setSpeedY(gameConstant.getSpeed());

        int tempY = getY();
        int tempX = getX();

        switch(getDirection()) {
            case 1:{
                tempY -= getSpeedY();
                setY(tempY);
                break;
            }
            case 2:{
                tempY += getSpeedY();
                setY(tempY);
                break;
            }
            case 3:{
                tempX -= getSpeedX();
                setX(tempX);
                break;
            }
            case 4:{
                tempX += getSpeedX();
                setX(tempX);
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + getDirection());
        }
        if (timerShoot <= 0) {
            handler.addObject(new EnemyBullet(getContext(), getX() + gameConstant.getBulletSize() / 2,
                    getY() + gameConstant.getBulletSize() / 2, ID.EnemyBullet, getDirection(),
                    handler, gameConstant, spawner, hud));
            timerShoot = rnd.nextInt(gameConstant.getTimerShoot());
        } else
            timerShoot--;

        collision();
    }

    @Override
    public void draw(Canvas canvas) {
        if (getDirection() == 1) {
            Sprite spriteUp = spriteSheet.getSprite(150, 50, 200, 100);
            spriteUp.draw(canvas, getX(), getY());
        } else if (getDirection() == 2) {
            Sprite spriteDown = spriteSheet.getSprite(0, 100, 50, 150);
            spriteDown.draw(canvas, getX(), getY());
        } else if (getDirection() == 3) {
            Sprite spriteLeft = spriteSheet.getSprite(200, 50, 250, 100);
            spriteLeft.draw(canvas, getX(), getY());
        } else if (getDirection() == 4) {
            Sprite spriteRight = spriteSheet.getSprite(250, 50, 300, 100);
            spriteRight.draw(canvas, getX(), getY());
        }
    }

    @Override
    public Rect getBounds() {
        return new Rect(getX(), getY(), getX() + gameConstant.getTankSize(), getY() + gameConstant.getTankSize());
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Block_sea_wall || tempObject.getId() == ID.Block_steel_wall
                    || tempObject.getId() == ID.Block_brick_wall) {
                if (getBounds().intersect(tempObject.getBounds())) {

                    int tempY = getY();
                    int tempX = getX();
                    int move;

                    if(getDirection() == 1) {
                        move = rnd.nextInt(4 + 1 - 1) + 1;
                        tempY += getSpeedY();
                        setY(tempY);
                        setDirection(move);
                        break;
                    }

                    if(getDirection() == 2) {
                        move = rnd.nextInt(4 + 1 - 1) + 1;
                        tempY -= getSpeedY();
                        setY(tempY);
                        setDirection(move);
                        break;
                    }

                    if(getDirection() == 3) {
                        move = rnd.nextInt(4 + 1 - 1) + 1;
                        tempX += getSpeedX();
                        setX(tempX);
                        setDirection(move);
                        break;
                    }

                    if(getDirection() == 4) {
                        move = rnd.nextInt(4 + 1 - 1) + 1;
                        tempX -= getSpeedX();
                        setX(tempX);
                        setDirection(move);
                        break;
                    }
                }
            }
        }
    }
}
