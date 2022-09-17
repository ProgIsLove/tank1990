package com.example.tank1990;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
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

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
        performance = new Performance(gameLoop, context);

        handler = new Handler();
        gameConstant = new GameConstant();
        hud = new Hud(getContext());
        spawner = new Spawner(handler, gameConstant, getContext(), hud);
        level = new Level();
        map = new Map(getContext(), handler, gameConstant, spawner,  level);
        buttonUp = new Button(100, 700, gameConstant);
        buttonDown = new Button(100, 900, gameConstant);
        buttonLeft = new Button(0, 800, gameConstant);
        buttonRight = new Button(200, 800, gameConstant);

        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, map, gameConstant);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
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
        hud.draw(canvas, gameDisplay);
        map.draw(gameDisplay);
        buttonUp.draw(canvas);
        buttonDown.draw(canvas);
        buttonLeft.draw(canvas);
        buttonRight.draw(canvas);
    }

    public void update() {
        handler.update();
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}
