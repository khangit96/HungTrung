package com.example.khangnhd.hungtrung.thread;

import android.graphics.Canvas;

import com.example.khangnhd.hungtrung.sprite.SpriteEgg;
import com.example.khangnhd.hungtrung.view.GameSurfaceView;

import java.util.Random;

/**
 * Created by khangnhd on 02/05/2018.
 */

public class GameLoopThread extends Thread {
    private GameSurfaceView view;

    private boolean running = false;

    static final long FPS = 100;

    public GameLoopThread(GameSurfaceView view) {

        this.view = view;

    }


    public void setRunning(boolean run) {

        running = run;

    }


    @Override

    public void run() {

        while (running) {
            long ticksPS = 1000 / FPS;
            long startTime;
            long sleepTime;

            Canvas c = null;

            startTime = System.currentTimeMillis();

            try {

                c = view.getHolder().lockCanvas();

                synchronized (view.getHolder()) {

                    view.onDraw(c);
//                    Random rand = new Random();
//
//                    int n = rand.nextInt((int) 300 - 50) + 10;
//                    GameSurfaceView.spriteEggList.add(new SpriteEgg(view, GameSurfaceView.bmpEgg, 10, n));


                }

            } finally {

                if (c != null) {

                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

            try {

                if (sleepTime > 0)

                    sleep(sleepTime);

                else

                    sleep(10);

            } catch (Exception e) {
            }

        }

    }
}