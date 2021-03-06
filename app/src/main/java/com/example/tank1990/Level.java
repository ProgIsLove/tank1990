package com.example.tank1990;

public class Level {

    private final int x = 21;
    private final int y = 15;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int[][] levelOne(){
        return new int[][]
                {
                        {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                        {2,0,0,2,0,0,2,0,0,5,5,5,0,0,2,0,0,2,0,0,2},
                        {2,0,1,1,1,6,2,0,1,1,2,1,1,0,2,7,1,1,1,0,2},
                        {2,0,2,2,1,2,2,2,1,5,5,5,1,2,2,2,1,2,2,0,2},
                        {2,0,2,2,1,0,1,1,5,1,1,1,5,1,1,0,1,2,2,0,2},
                        {2,0,0,0,0,1,0,0,0,1,1,1,0,0,0,1,0,0,0,0,2},
                        {2,0,0,1,0,2,1,2,2,2,2,2,2,2,1,2,0,1,0,0,2},
                        {2,0,1,1,1,0,0,2,5,5,5,5,5,2,0,0,1,1,1,0,2},
                        {2,2,2,5,5,0,0,2,2,2,2,2,2,2,0,0,5,5,2,2,2},
                        {2,5,5,2,2,0,0,1,5,2,2,2,5,1,0,0,2,2,5,5,2},
                        {2,5,2,5,5,0,1,1,1,5,5,5,1,1,1,0,5,5,2,5,2},
                        {2,5,2,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,2,5,2},
                        {2,5,2,0,1,2,2,1,1,0,0,0,1,1,2,2,1,0,2,5,2},
                        {2,2,2,0,0,0,0,4,1,0,3,0,1,0,0,0,0,0,2,2,2},
                        {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
                };
    }
}
