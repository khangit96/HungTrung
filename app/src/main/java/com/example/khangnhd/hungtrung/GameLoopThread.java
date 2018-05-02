package com.example.khangnhd.hungtrung;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.logging.Handler;

/**
 * Created by khangnhd on 02/05/2018.
 */

class GameLoopThread extends Thread {
    private MySurfaceView view;

    private boolean running = false;


    public GameLoopThread(MySurfaceView view) {

        this.view = view;

    }


    public void setRunning(boolean run) {

        running = run;

    }


    @Override

    public void run() {

        while (running) {

            Canvas c = null;

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

        }

    }
}