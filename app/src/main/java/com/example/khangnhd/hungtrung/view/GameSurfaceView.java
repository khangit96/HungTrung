package com.example.khangnhd.hungtrung.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khangnhd.hungtrung.sprite.SpriteBasket;
import com.example.khangnhd.hungtrung.sprite.SpriteEgg;
import com.example.khangnhd.hungtrung.thread.GameLoopThread;
import com.example.khangnhd.hungtrung.R;
import com.example.khangnhd.hungtrung.sprite.Sprite;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by khangnhd on 02/05/2018.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder sh;
    private Bitmap bmpBasket, bmpHeart, bmpBackground, bmpBrokenEgg;
    private Bitmap bmpEgg;

    private GameLoopThread gameLoopThread;

    //Sprite
    private SpriteBasket spriteBasket;
    private List<SpriteEgg> spriteEggList;
    private List<SpriteEgg> spriteEggBroken;

    //Time
    private Timer timerPlaygame;
    private TimerTask timerTaskPlayGame;
    private float timeEscape;
    private Handler mTimerHandler = new Handler();
    int countTime = 0;
    long startTime;

    //
    int score = 0;
    int countCheckBrokenEgg = 0;
    boolean checkGameOver = false;
    boolean checkBrokenEggOne = false;
    boolean checkBrokenEggTwo = false;
    boolean checkBrokenEggThree = false;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameSurfaceView(Context context) {
        super(context);

        sh = getHolder();
        sh.addCallback(this);

        //thread
        gameLoopThread = new GameLoopThread(this);

        //bmp
        bmpEgg = BitmapFactory.decodeResource(getResources(), R.drawable.egg);
        bmpBrokenEgg = BitmapFactory.decodeResource(getResources(), R.drawable.broken_egg);
        bmpHeart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        bmpBasket = BitmapFactory.decodeResource(getResources(), R.drawable.basket3);
        bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        spriteBasket = new SpriteBasket(this, bmpBasket);
        spriteEggList = new ArrayList<>();
        spriteEggList.add(new SpriteEgg(this, bmpEgg, 10, 10));

        if (countCheckBrokenEgg == 1) {

        }
        //start timer
        startTimer();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);

        while (retry) {

            try {

                gameLoopThread.join();

                retry = false;

            } catch (InterruptedException e) {

            }
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onDraw(Canvas cv) {
        super.onDraw(cv);

        cv.drawBitmap(bmpBackground, 0, 0, null);

        //PaintScore
        Paint paintScore = new Paint();
        paintScore.setColor(Color.RED);
        paintScore.setStyle(Paint.Style.FILL_AND_STROKE);
        paintScore.setStrokeWidth(3);
        paintScore.setTextSize(83);
        Rect boundsTextScore = new Rect();

        String textScore = "" + score;
        paintScore.getTextBounds(textScore, 0, textScore.length(), boundsTextScore);

        //PaintGameOver
        Paint paintGameOver = new Paint();
        paintGameOver.setColor(Color.RED);
        paintGameOver.setStyle(Paint.Style.STROKE);
        paintGameOver.setStrokeWidth(3);
        paintGameOver.setTextSize(90);


        Rect boundsTextGameOver = new Rect();
        String textGameOver = "Game Over";


        paintGameOver.getTextBounds(textGameOver, 0, textGameOver.length(), boundsTextGameOver);

        cv.drawText(textScore, cv.getWidth() - boundsTextScore.width() - 60, 100, paintScore);
        cv.drawBitmap(bmpHeart, 30, 30, null);
        cv.drawBitmap(bmpHeart, 30 + bmpHeart.getWidth(), 30, null);
        cv.drawBitmap(bmpHeart, 30 + bmpHeart.getWidth() + bmpHeart.getWidth(), 30, null);

        //Check game Over
        if (checkGameOver) {
            cv.drawText(textGameOver, (this.spriteBasket.getGameViewWidth() / 2) - (boundsTextGameOver.width() / 2), (this.spriteBasket.getGameViewHeigh() / 2), paintGameOver);
        }

        //Draw Broken Egg
        if (checkBrokenEggOne) {
            cv.drawBitmap(bmpBrokenEgg, 300, this.spriteBasket.getGameViewHeigh() - this.bmpBrokenEgg.getHeight(), null);
        }
        if (checkBrokenEggTwo) {
            cv.drawBitmap(bmpBrokenEgg, 400, this.spriteBasket.getGameViewHeigh() - this.bmpBrokenEgg.getHeight(), null);

        }
        if (checkBrokenEggThree) {
            cv.drawBitmap(bmpBrokenEgg, 500, this.spriteBasket.getGameViewHeigh() - this.bmpBrokenEgg.getHeight(), null);
        }

        spriteBasket.onDraw(cv);

        synchronized (getHolder()) {
            for (SpriteEgg spriteEgg : spriteEggList) {
                if (spriteEgg != null) {
                    spriteEgg.onDraw(cv);
                }
            }
        }

        for (SpriteEgg spriteEgg : spriteEggList) {
            if (spriteEgg.isCollision(spriteBasket)) {
                score += 5;
                cv.drawBitmap(bmpBrokenEgg, spriteEgg.getX(), spriteEgg.getY(), null);
                spriteEggList.remove(spriteEgg);
                break;

            } else {
                if (spriteEgg.isEggEnd()) {
                    countCheckBrokenEgg++;
                    if (countCheckBrokenEgg == 1) {
                        checkBrokenEggOne = true;
                    } else if (countCheckBrokenEgg == 2) {
                        checkBrokenEggTwo = true;
                    } else if (countCheckBrokenEgg == 3) {
                        checkBrokenEggThree = true;
                        Log.d("test", "End");
                        spriteEggList.clear();
                        stopTimer(timerPlaygame);
                        checkGameOver = true;

                        break;
                    }
                    break;
                }
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        spriteBasket.setX(event.getX());
        return true;
    }

    /*
    Start timer
     * */
    private void startTimer() {
        startTime = System.currentTimeMillis();
        timerPlaygame = new Timer();
        timerTaskPlayGame = new TimerTask() {
            public void run() {
                mTimerHandler.post(new Runnable() {
                    public void run() {
                        countTime++;
                        Random rand = new Random();
                        if (spriteBasket.getGameViewWidth() > 0) {
                            int n = rand.nextInt((int) spriteBasket.getGameViewWidth() - 100) + 30;
                            spriteEggList.add(new SpriteEgg(spriteBasket.getGameView(), bmpEgg, 10, n));
                        }


//                        if (countTime == 6) {
//                            long estimatedTime = System.currentTimeMillis() - startTime;
//                            //  Log.d("test", "" + estimatedTime);
//                            stopTimer(timerPlaygame);
//                        }

                    }
                });
            }
        };

        timerPlaygame.schedule(timerTaskPlayGame, 10, 3000);
    }

    /*
     *Stop timer
     * */
    private void stopTimer(Timer timer) {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

}
