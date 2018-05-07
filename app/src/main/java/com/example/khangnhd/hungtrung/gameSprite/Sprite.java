package com.example.khangnhd.hungtrung.gameSprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.example.khangnhd.hungtrung.gameView.GameSurfaceView;

public class Sprite {

    //Position
    private int x;
    private int y;

    //Speed
    private int xSpeed = 0;
    private int ySpeed = 0;
    private int speed = 0;

    private GameSurfaceView gameView;

    private Bitmap bmp;

    /*
     * Constructor X Sprite
     * */
    public Sprite(GameSurfaceView gameView, Bitmap bmp, int x, int y, int speed) {
        this.gameView = gameView;
        this.bmp = bmp;

        this.x = x;
        this.y = y;

        this.speed = speed;
        this.xSpeed = speed;
        this.ySpeed = speed;
    }


    /*
        Update X
    * */
    private void updateX() {

        if (x > gameView.getWidth() - bmp.getWidth() - xSpeed) {
            xSpeed = -speed;
            Log.d("test", "move right to left");
        }

        if (x + xSpeed < 0) {
            Log.d("test", "move to left to right");
            xSpeed = speed;
        }

        // x += xSpeed;
    }

    /*
     * Update Y
     * */
    private void updateY() {

        if (y > gameView.getHeight() - bmp.getHeight() - ySpeed) {
            ySpeed = -speed;
            Log.d("test", "move right to left");
        }

        if (y + ySpeed < 0) {
            Log.d("test", "move to left to right");
            ySpeed = speed;
        }

        y += ySpeed;
    }

    /*
     * */
    public void setPostion(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /*
     * Ondraw X
     * */
    public void onDrawX(Canvas canvas) {
        updateX();
        canvas.drawBitmap(bmp, x, gameView.getHeight() - bmp.getHeight(), null);
    }

    /*
     * Ondraw Y
     * */
    public void onDrawY(Canvas canvas) {
        updateY();
        canvas.drawBitmap(bmp, 10, y, null);
    }
}
