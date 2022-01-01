package com.example.tank1990;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

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
            Sprite spriteBrick = spriteSheet.getPlayerSprite(0, 0, 25, 25);
            spriteBrick.draw(canvas, getX(), getY());
        } else if (getId() == ID.Block_steel_wall) {
            Sprite spriteSteel = spriteSheet.getPlayerSprite(25, 0, 50, 25);
            spriteSteel.draw(canvas, getX(), getY());
        } else if (getId() == ID.Block_sea_wall) {
            Sprite spriteSea = spriteSheet.getPlayerSprite(50, 0, 75, 25);
            spriteSea.draw(canvas, getX(), getY());
        } else if (getId() == ID.Golden_crown) {
            Sprite spriteCrown = spriteSheet.getPlayerSprite(25, 50, 50, 75);
            spriteCrown.draw(canvas, getX(), getY());
        }
    }

    @Override
    public Rect getBounds() {
        return new Rect(getX(), getY(), gameConstant.getBlockSize(), gameConstant.getBlockSize());
    }
}
