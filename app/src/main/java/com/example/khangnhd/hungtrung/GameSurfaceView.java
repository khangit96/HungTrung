package com.example.khangnhd.hungtrung;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by khangnhd on 02/05/2018.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder sh;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bmp;

    private GameLoopThread gameLoopThread;
    private int x = 0;
    private int xSpeed = 10;

    private int y=0;
    private int ySpeed=10;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameSurfaceView(Context context) {
        super(context);
        sh = getHolder();
        sh.addCallback(this);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        gameLoopThread = new GameLoopThread(this);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dot);
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

    @Override
    protected void onDraw(Canvas cv) {
        super.onDraw(cv);

        //Move from right to left
//        if (x == getWidth() - bmp.getWidth()) {
//            xSpeed = -10;
//            Log.d("test","Move from right to left");
//        }
//
//        //Move from left to right
//        if (x == 0) {
//            xSpeed = 10;
//            Log.d("test","Move left to right");
//        }
//
//        x = x + xSpeed;
        if (y == getHeight() - bmp.getHeight()) {
            ySpeed = -10;
            Log.d("test","Move from right to left");
        }

        //Move from left to right
        if (y == 0) {
            ySpeed = 10;
            Log.d("test","Move left to right");
        }

        y = y + ySpeed;

        cv.drawColor(Color.BLACK);
        cv.drawBitmap(bmp, 10, y, null);
    }
}
