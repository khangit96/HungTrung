package com.example.khangnhd.hungtrung.gameSprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.example.khangnhd.hungtrung.gameView.GameSurfaceView;

public class Sprite {

    //Position
    private float x = 0;
    private float y = 0;

    //Speed
    private int xSpeed = 0;
    private int ySpeed = 0;
    private int speed = 0;

    private GameSurfaceView gameView;

    private Bitmap bmp;

    private float width;
    private float height;

    /*
     * Constructor X Sprite
     * */
    public Sprite(GameSurfaceView gameView, Bitmap bmp, int speed) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.speed = speed;
        this.xSpeed = speed;
        this.ySpeed = speed;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
    }


    /*
        Update X
    * */
    private void updateBasket() {

//        if (x > gameView.getWidth() - bmp.getWidth() - xSpeed) {
//            xSpeed = -speed;
//            Log.d("test", "move right to left");
//        }
//
//        if (x + xSpeed < 0) {
//            Log.d("test", "move to left to right");
//            xSpeed = speed;
//        }

        // x += xSpeed;
    }

    /*
     * Update Y
     * */
    private void updateEgg() {

        if (y > gameView.getHeight() - bmp.getHeight() - ySpeed) {
            //ySpeed = -speed;
            y = 0;
            //  Log.d("test", "move right to left");
        } else {
            y += ySpeed;
        }

//        if (y + ySpeed < 0) {
//            // Log.d("test", "move to left to right");
//            ySpeed = speed;
//        }


    }

    /*
     * */
//    public void setPostion(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    /*
    *
    * */
    public boolean isCollision(float x2, float y2) {
        if (y2 >= y) {

        }
        return true;
    }

    /*
     * Ondraw X
     * */
    public void onDrawBasket(Canvas canvas) {
        updateBasket();
        setY(gameView.getHeight() - bmp.getHeight());
        canvas.drawBitmap(bmp, x, y, null);
    }

    /*
     * Ondraw Y
     * */
    public void onDrawEgg(Canvas canvas) {
        updateEgg();
        setX(150);
        canvas.drawBitmap(bmp, x, y, null);
    }
}
