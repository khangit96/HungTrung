package com.example.khangnhd.hungtrung;

import android.graphics.Canvas;

/**
 * Created by khangnhd on 02/05/2018.
 */

class GameLoopThread extends Thread {
    private GameSurfaceView view;

    private boolean running = false;

    static final long FPS = 10;

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