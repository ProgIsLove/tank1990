package com.example.tank1990;

import android.content.Context;
import android.graphics.Canvas;

import java.util.Random;

public class Map {

    private Handler handler;
    private GameConstant gameCon;
    private Spawner spawner;
    private Level level;
    private Random rnd = new Random();
    private int blockSpaceX = 0;
    private int blockSpaceY = 0;
    private int blockValue;
    private int[][] gameField;
    private boolean isDraw = false;
    private Context context;

    public Map(Context context, Handler handler, GameConstant gameCon, Spawner spawner, Level level) {
        this.context = context;
        this.handler = handler;
        this.gameCon = gameCon;
        this.spawner = spawner;
        this.level = level;
    }

    public int getBlockX() {
        return blockSpaceX;
    }

    public int getBlockY() {
        return blockSpaceY;
    }

    public void setBlockX(int blockSpaceX) {
        this.blockSpaceX = blockSpaceX;
    }

    public void setBlockY(int blockSpaceY) {
        this.blockSpaceY = blockSpaceY;
    }

    public void setIsDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }

    public void draw(GameDisplay gameDisplay) {
        fillGameLvl(
                (int) gameDisplay.gameToDisplayCoordinatesX((float) blockSpaceX),
                (int) gameDisplay.gameToDisplayCoordinatesY((float) blockSpaceY),
                blockValue, gameDisplay);
        spawnEnemy(blockValue, gameDisplay);
    }

    private void fillGameLvl(int blockSpaceX, int blockSpaceY, int blockValue, GameDisplay gameDisplay) {

        gameField = level.levelOne();

        if (!isDraw) {
            for (int row = 0; row < gameField.length; row++) {
                for (int column = 0; column < gameField[0].length; column++) {

                    blockValue = gameField[row][column];

                    switch (blockValue) {
                        case 1: {
                            handler.addObject(new Block(context,blockSpaceX, blockSpaceY, ID.Block_brick_wall, gameCon));
                            break;
                        }
                        case 2: {
                            handler.addObject(new Block(context,blockSpaceX, blockSpaceY, ID.Block_steel_wall, gameCon));
                            break;
                        }
                        case 3: {
                            handler.addObject(new Block(context,0 + blockSpaceX, 0 + blockSpaceY, ID.Golden_crown, gameCon));
                            break;
                        }
                        case 4: {
                            handler.addObject(new Player(context,0 + blockSpaceX, 0 + blockSpaceY, ID.Player, 1, handler, gameCon));
                            break;
                        }
                        case 5: {
                            handler.addObject(new Block(context,0 + blockSpaceX, 0 + blockSpaceY, ID.Block_sea_wall, gameCon));
                            break;
                        }
                    }
                    setBlockX(gameCon.getBlockSize());
                    blockSpaceX += getBlockX();
                }
                setBlockY(gameCon.getBlockSize());
                blockSpaceY += getBlockY();
                blockSpaceX = (int) gameDisplay.gameToDisplayCoordinatesX((float) 0);
            }
            isDraw = true;
        }
    }

    private void spawnEnemy(int blockValue, GameDisplay gameDisplay) {
        gameField = level.levelOne();
        int key;

        key = rnd.nextInt(2);
        switch (key) {
            case 0: {
                blockValue = gameField[2][5];
                break;
            }
            case 1: {
                blockValue = gameField[2][15];
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + key);
        }

        if(blockValue == 7) {
            spawner.nextEnemy((int) gameDisplay.gameToDisplayCoordinatesX((float) 775),
                    (int) gameDisplay.gameToDisplayCoordinatesY((float) 50));
        }else
            spawner.nextEnemy((int) gameDisplay.gameToDisplayCoordinatesX((float) 225),
                    (int) gameDisplay.gameToDisplayCoordinatesY((float) 50));
    }
}
