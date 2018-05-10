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
//public class Sprite {
//
//    //Position
//    private float x = 0;
//    private float y = 0;
//
//    //Speed
//    private int xSpeed = 0;
//    private int ySpeed = 0;
//    private int speed = 0;
//
//    private GameSurfaceView gameView;
//
//    private Bitmap bmp;
//
//    private float width;
//    private float height;
//
//    /*
//     * Constructor X Sprite
//     * */
//    public Sprite(GameSurfaceView gameView, Bitmap bmp, int speed) {
//        this.gameView = gameView;
//        this.bmp = bmp;
//        this.speed = speed;
//        this.xSpeed = speed;
//        this.ySpeed = speed;
//        this.width = bmp.getWidth();
//        this.height = bmp.getHeight();
//    }
//
//
//    /*
//        Update X
//    * */
//    private void updateBasket() {
//
////        if (x > gameView.getWidth() - bmp.getWidth() - xSpeed) {
////            xSpeed = -speed;
////            Log.d("test", "move right to left");
////        }
////
////        if (x + xSpeed < 0) {
////            Log.d("test", "move to left to right");
////            xSpeed = speed;
////        }
//
//        // x += xSpeed;
//    }
//
//    /*
//     * Update Y
//     * */
//    private void updateEgg() {
//
//        if (y > gameView.getHeight() - bmp.getHeight() - ySpeed) {
//            //ySpeed = -speed;
//            y = 0;
//            //  Log.d("test", "move right to left");
//        } else {
//            y += ySpeed;
//        }
//
////        if (y + ySpeed < 0) {
////            // Log.d("test", "move to left to right");
////            ySpeed = speed;
////        }
//
//
//    }
//
//    /*
//     * */
////    public void setPostion(int x, int y) {
////        this.x = x;
////        this.y = y;
////    }
//    public void setX(float x) {
//        this.x = x;
//    }
//
//    public void setY(float y) {
//        this.y = y;
//    }
//
//    public float getX() {
//        return this.x;
//    }
//
//    public float getY() {
//        return this.y;
//    }
//
//    public float getWidth() {
//        return width;
//    }
//
//    public float getHeight() {
//        return height;
//    }
//
//    /*
//     *
//     * */
//    public boolean isCollision(Sprite spriteBasket) {
//
//        if (y >= spriteBasket.getY() && x > spriteBasket.getX() && x < spriteBasket.getX() + spriteBasket.getWidth()) {
//            return true;
//        }
//        return false;
//
//    }
//
//    /*
//     * Ondraw X
//     * */
//    public void onDrawBasket(Canvas canvas) {
//        updateBasket();
//        setY(gameView.getHeight() - bmp.getHeight());
//        canvas.drawBitmap(bmp, x, y, null);
//    }
//
//    /*
//     * Ondraw Y
//     * */
//    public void onDrawEgg(Canvas canvas) {
//        updateEgg();
//
////        Random rand = new Random();
////        int n = rand.nextInt(canvas.getWidth()) + 10;
////
////        setX(n);
//        canvas.drawBitmap(bmp, x, y, null);
//    }
//}
