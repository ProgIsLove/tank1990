package com.example.tank1990;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Button {

    private int positionX;
    private int positionY;
    private GameConstant gameConstant;
    private float radius;
    private double circleButtonTouchDistance;
    public Rect rect;
    private Boolean isPressed = false;

    public Button(int positionX, int positionY, GameConstant gameConstant) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.gameConstant = gameConstant;

        rect = new Rect(positionX, positionY, positionX + gameConstant.getButtonSize(),
                positionY + gameConstant.getButtonSize());
    }

    public Button(int positionX, int positionY, float radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(rect, paint);
    }

    public void drawCircle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((float) positionX, (float) positionY, radius, paint);
    }

    public Boolean isCircleButtonPressed(double touchX, double touchY) {
        circleButtonTouchDistance = Math.sqrt(
                Math.pow(positionX - touchX, 2) + Math.pow(positionY - touchY, 2)
        );

        return circleButtonTouchDistance < radius;
    }

    public Boolean getPressed() {
        return isPressed;
    }

    public void setPressed(Boolean pressed) {
        isPressed = pressed;
    }
}
