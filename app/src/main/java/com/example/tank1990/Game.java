package com.example.tank1990;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
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

        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, map, gameConstant);

        setFocusable(true);
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
    }

    public void update() {
        handler.update();
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}
