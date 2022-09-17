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

    public GameDisplay(int widthPixels, int heightPixels, Map centerObject, GameConstant gameConstant) {
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        this.centerObject = centerObject;
        this.gameConstant = gameConstant;

        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;

        update();
    }

    public void update() {
        gameToDisplayCoordinatesOffsetX = displayCenterX;
        gameToDisplayCoordinatesOffsetY = displayCenterY;
    }

    public double mapToDisplayCoordinatesX(double x) {
        double totalBlockWidth = WIDTH_MAP_TILE * gameConstant.getBlockSize();
        return x + gameToDisplayCoordinatesOffsetX - totalBlockWidth/2.0;
    }

    public double mapToDisplayCoordinatesY(double y) {
        double totalBlockHeight = HEIGHT_MAP_TILE * gameConstant.getBlockSize();
        return y + gameToDisplayCoordinatesOffsetY - totalBlockHeight/2.0;
    }

    public double HUDToDisplayCoordinatesX(double x) {
        return x + gameToDisplayCoordinatesOffsetX;
    }

    public double HUDToDisplayCoordinatesY(double y) {
        return y + gameToDisplayCoordinatesOffsetX;
    }
}
