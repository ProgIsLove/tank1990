package com.example.tank1990;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private Performance performance;
    private Player player;
    private Handler handler;
    private GameConstant gameConstant;
    private Enemy enemy;

    public Game(Context context) {
        super(context);


        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
        performance = new Performance(gameLoop, context);

        handler = new Handler();
        gameConstant = new GameConstant();

        player = new Player(getContext(),500, 500, ID.Player, 4, handler, gameConstant);
        enemy = new Enemy(getContext(), 525, 525, ID.Enemy, 1, handler, gameConstant);

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

        performance.draw(canvas);
        handler.draw(canvas);
        player.draw(canvas);
        enemy.draw(canvas);
    }

    public void update() {

        handler.update();
        player.update();
        enemy.update();

    }

    public void pause() {
        gameLoop.stopLoop();
    }
}
