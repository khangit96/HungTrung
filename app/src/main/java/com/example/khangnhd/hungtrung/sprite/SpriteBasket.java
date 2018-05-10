package com.example.khangnhd.hungtrung.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.example.khangnhd.hungtrung.view.GameSurfaceView;

public class SpriteBasket extends Sprite {
    public SpriteBasket(GameSurfaceView gameView, Bitmap bmp) {

        super(gameView, bmp);
        this.setX(30);

    }

    @Override
    public void onDraw(Canvas canvas) {
        this.setY(getGameViewHeigh() - getBmp().getHeight());
        canvas.drawBitmap(this.getBmp(), this.getX(), this.getY(), null);
    }

}
