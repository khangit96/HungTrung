package com.example.khangnhd.hungtrung.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.khangnhd.hungtrung.view.GameSurfaceView;

public class SpriteEgg extends Sprite {
    private int ySpeed = 0;

    public SpriteEgg(GameSurfaceView gameView, Bitmap bmp, int speed) {
        super(gameView, bmp);
        this.ySpeed = speed;
        this.setX(30);
        this.setY(0);
    }

    /*
     *
     * */
    public boolean isCollision(Sprite spriteBasket) {

        if (getY() >= spriteBasket.getY() && getX() > spriteBasket.getX() && getX() < spriteBasket.getX() + spriteBasket.getBmp().getWidth()) {
            return true;
        }
        return false;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (this.getY() > this.getGameViewHeigh() - this.getBmp().getHeight() - ySpeed) {
            this.setY(0);
        } else {
            float s = ySpeed + this.getY();
            this.setY(s);
        }

        canvas.drawBitmap(this.getBmp(), this.getX(), getY(), null);
    }

}
