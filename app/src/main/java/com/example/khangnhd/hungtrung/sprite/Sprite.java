package com.example.khangnhd.hungtrung.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.khangnhd.hungtrung.view.GameSurfaceView;


public abstract class Sprite {

    //postion
    private float x;
    private float y;

    //bitmap
    private Bitmap bmp;

    //
    private GameSurfaceView gameView;

    public Sprite(GameSurfaceView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
    }

    public GameSurfaceView getGameView() {
        return gameView;
    }

    /*
     * */
    public float getGameViewWidth() {
        return this.gameView.getWidth();
    }

    /*
     * */
    public float getGameViewHeigh() {
        return this.gameView.getHeight();
    }

    /*
     * */
    public void setX(float x) {
        this.x = x;
    }

    /*
     * */
    public void setY(float y) {
        this.y = y;
    }

    /*
     * */
    public float getX() {
        return this.x;
    }

    /*
     *
     * */
    public float getY() {
        return this.y;
    }

    /*
     *
     * */

    public Bitmap getBmp() {
        return this.bmp;
    }
    /*
     *
     * */

    public abstract void onDraw(Canvas canvas);
}
