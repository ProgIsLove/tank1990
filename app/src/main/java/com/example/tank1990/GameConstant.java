package com.example.tank1990;

public class GameConstant {

    private final int SPEED = 2;
    private final int BULLET_SPEED = 3;
    private final int BLOCK_SIZE = 50;
    private final int TANK_SIZE = 40;
    private final int BULLET_SIZE = 12;
    private final int TIMER_SHOOT = 70;
    private final int RESPAWN_TIME = 100;
    private final int SCORE = 200;
    private final int BUTTON_SIZE = 100;

    public int getSpeed() {
        return SPEED;
    }

    public int getTankSize() {
        return TANK_SIZE;
    }

    public int getBulletSpeed() {
        return BULLET_SPEED;
    }

    public int getBlockSize() {
        return BLOCK_SIZE;
    }

    public int getBulletSize() {
        return BULLET_SIZE;
    }

    public int getTimerShoot() {
        return TIMER_SHOOT;
    }

    public int getRespawnTime() {
        return RESPAWN_TIME;
    }

    public int getScore() {
        return SCORE;
    }

    public int getButtonSize() {
        return BUTTON_SIZE;
    }
}
