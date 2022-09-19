package com.example.tank1990;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private Performance performance;
    private final Handler handler;
    private GameConstant gameConstant;
    private final Hud hud;
    private final Map map;
    private Spawner spawner;
    private Level level;
    private final GameDisplay gameDisplay;
    private final Button buttonUp;
    private final Button buttonDown;
    private final Button buttonLeft;
    private final Button buttonRight;
    private final Button buttonShot;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
        performance = new Performance(gameLoop, context);

        handler = new Handler();
        gameConstant = new GameConstant();
        hud = new Hud();
        spawner = new Spawner(handler, gameConstant, getContext(), hud);
        level = new Level();
        map = new Map(getContext(), handler, gameConstant, spawner,  level);
        buttonUp = new Button(100, 700, gameConstant);
        buttonDown = new Button(100, 900, gameConstant);
        buttonLeft = new Button(0, 800, gameConstant);
        buttonRight = new Button(200, 800, gameConstant);
        buttonShot = new Button(1580, 950, 70);

        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, map, gameConstant);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean isShooting = false;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);

                    if (tempObject.getId() == ID.Player) {
                        if (buttonUp.rect.contains((int) event.getX(), (int) event.getY())) {
                            tempObject.setSpeedY(-gameConstant.getSpeed());
                            tempObject.setDirection(1);
                            return true;
                        }
                        if (buttonDown.rect.contains((int) event.getX(), (int) event.getY())) {
                            tempObject.setSpeedY(gameConstant.getSpeed());
                            tempObject.setDirection(4);
                            return true;
                        }
                        if (buttonLeft.rect.contains((int) event.getX(), (int) event.getY())) {
                            tempObject.setSpeedX(-gameConstant.getSpeed());
                            tempObject.setDirection(3);
                            return true;
                        }
                        if (buttonRight.rect.contains((int) event.getX(), (int) event.getY())) {
                            tempObject.setSpeedX(gameConstant.getSpeed());
                            tempObject.setDirection(2);
                            return true;
                        }
                        if (buttonShot.isCircleButtonPressed(event.getX(), event.getY()) && !isShooting) {
                            isShooting = true;
                            handler.addObject(new Bullet(getContext(), tempObject.getX() + gameConstant.getBulletSize()/2,
                                    tempObject.getY() + gameConstant.getBulletSize()/2, ID.Bullet, tempObject.getDirection(),
                                    handler, gameConstant, hud));
                            return true;
                        }
                    }
                }
            case MotionEvent.ACTION_UP:
                for (int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);

                    if (tempObject.getId() == ID.Player) {
                        if (buttonUp.rect.contains((int) event.getX(), (int) event.getY())) {
                            tempObject.setSpeedY(0);
                            return true;
                        }
                        if (buttonDown.rect.contains((int) event.getX(), (int) event.getY())) {
                            tempObject.setSpeedY(0);
                            return true;
                        }
                        if (buttonLeft.rect.contains((int) event.getX(), (int) event.getY())) {
                            tempObject.setSpeedX(0);
                            return true;
                        }
                        if (buttonRight.rect.contains((int) event.getX(), (int) event.getY())) {
                            tempObject.setSpeedX(0);
                            return true;
                        }
                        if (buttonShot.isCircleButtonPressed(event.getX(), event.getY())) {
                            isShooting = false;
                            return true;
                        }
                    }
                }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            gameLoop = new GameLoop(this, surfaceHolder);
        }

        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //performance.draw(canvas);
        handler.draw(canvas);
        hud.draw(canvas, gameDisplay, getContext());
        map.draw(gameDisplay);
        buttonUp.draw(canvas);
        buttonDown.draw(canvas);
        buttonLeft.draw(canvas);
        buttonRight.draw(canvas);
        buttonShot.drawCircle(canvas);
    }

    public void update() {
        handler.update();

        if (hud.getLive() == 0 || hud.getCrownLive() == 0) {
            Intent intent = new Intent().setClass(getContext(), GameOverActivity.class);
            intent.putExtra("Score", hud.getScore());
            getContext().startActivity(intent);
        }
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}
