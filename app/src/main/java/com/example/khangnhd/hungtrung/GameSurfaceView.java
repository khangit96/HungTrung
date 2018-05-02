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
    private int xSpeed = 1;

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
        if (x == getWidth() - bmp.getWidth()) {
            xSpeed = -1;
            Log.d("test","Stop move");
        }

        if (x == 0) {
            xSpeed = 1;
            Log.d("test","Started move");
        }

        x = x + xSpeed;
        cv.drawColor(Color.BLACK);
        cv.drawBitmap(bmp, x, 10, null);
    }
}
