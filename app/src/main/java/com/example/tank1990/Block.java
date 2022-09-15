package com.example.tank1990;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

public class Block extends GameObject{

    private GameConstant gameConstant;
    private SpriteSheet spriteSheet;

    public Block(Context context, int x, int y, ID id, GameConstant gameConstant) {
        super(context, x, y, id);
        this.gameConstant = gameConstant;
        this.spriteSheet = new SpriteSheet(context);

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        if (getId() == ID.Block_brick_wall) {
            //Sprite spriteBrick = spriteSheet.getSprite(0, 0, 25, 25);
            //spriteBrick.draw(canvas, getX(), getY());
            Rect rect = new Rect(getX(), getY(), getX() + gameConstant.getBlockSize(), getY() + gameConstant.getBlockSize());
            Paint p = new Paint();
            int color = ContextCompat.getColor(getContext(), R.color.brick);
            p.setColor(color);
            canvas.drawRect(rect, p);
        } else if (getId() == ID.Block_steel_wall) {
            //Sprite spriteSteel = spriteSheet.getSprite(25, 0, 50, 25);
            //spriteSteel.draw(canvas, getX(), getY());
            Rect rect = new Rect(getX(), getY(), getX() + gameConstant.getBlockSize(), getY() + gameConstant.getBlockSize());
            Paint p = new Paint();
            int color = ContextCompat.getColor(getContext(), R.color.white);
            p.setColor(color);
            canvas.drawRect(rect, p);
        } else if (getId() == ID.Block_sea_wall) {
            //Sprite spriteSea = spriteSheet.getSprite(50, 0, 75, 25);
            //spriteSea.draw(canvas, getX(), getY());
            Rect rect = new Rect(getX(), getY(), getX() + gameConstant.getBlockSize(), getY() + gameConstant.getBlockSize());
            Paint p = new Paint();
            int color = ContextCompat.getColor(getContext(), R.color.teal_200);
            p.setColor(color);
            canvas.drawRect(rect, p);
        } else if (getId() == ID.Golden_crown) {
            Sprite spriteCrown = spriteSheet.getSprite(25, 50, 50, 75);
            spriteCrown.draw(canvas, getX(), getY());
        }
    }

    @Override
    public Rect getBounds() {
        return new Rect(getX(), getY(), getX() + gameConstant.getBlockSize(), getY() + gameConstant.getBlockSize());
    }
}
