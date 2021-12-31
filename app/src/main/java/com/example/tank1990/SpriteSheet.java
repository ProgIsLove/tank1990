package com.example.tank1990;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class SpriteSheet {
    private Bitmap bitmap;

    public SpriteSheet(Context context) {

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite, bitmapOptions);

    }

    public Sprite getPlayerSprite(int left, int top, int right, int bottom) {
        return new Sprite(this, new Rect(left, top, right, bottom));
    }

    public Bitmap getBitMap() {
        return bitmap;
    }
}
