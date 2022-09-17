package com.example.tank1990;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

public class Button {

    private int positionX;
    private int positionY;
    private GameConstant gameConstant;
    public Rect rect;
    private Boolean isPressed = false;

    public Button(int positionX, int positionY, GameConstant gameConstant) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.gameConstant = gameConstant;

        rect = new Rect(positionX, positionY, positionX + 100, positionY + 100);
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(rect, paint);
    }

    public Boolean getPressed() {
        return isPressed;
    }

    public void setPressed(Boolean pressed) {
        isPressed = pressed;
    }
}
