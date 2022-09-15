package com.example.tank1990;

public class GameDisplay {
    private final int widthPixels;
    private final int heightPixels;
    private final Map centerObject;
    private final GameConstant gameConstant;
    private final double displayCenterX;
    private final double displayCenterY;
    private final int WIDTH_MAP_TILE = 21;
    private final int HEIGHT_MAP_TILE = 15;
    private double gameToDisplayCoordinatesOffsetX;
    private double gameToDisplayCoordinatesOffsetY;
    private double gameCenterX;
    private double gameCenterY;

    public GameDisplay(int widthPixels, int heightPixels, Map centerObject, GameConstant gameConstant) {
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        this.centerObject = centerObject;
        this.gameConstant = gameConstant;

        double totalBlockWidth = WIDTH_MAP_TILE * gameConstant.getBlockSize();
        double totalBlockHeight = HEIGHT_MAP_TILE * gameConstant.getBlockSize();

        displayCenterX = widthPixels/2.0 - totalBlockWidth/2.0;
        displayCenterY = heightPixels/2.0 - totalBlockHeight/2.0;

        update();
    }

    public void update() {
        gameCenterX = centerObject.getBlockX();
        gameCenterY = centerObject.getBlockY();

        gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY;
    }

    public double gameToDisplayCoordinatesX(double x) {
        return x + gameToDisplayCoordinatesOffsetX;
    }

    public double gameToDisplayCoordinatesY(double y) {
        return y + gameToDisplayCoordinatesOffsetY;
    }
}
