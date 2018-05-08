package com.example.khangnhd.hungtrung.gameView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.khangnhd.hungtrung.GameLoopThread;
import com.example.khangnhd.hungtrung.R;
import com.example.khangnhd.hungtrung.gameSprite.Sprite;

/**
 * Created by khangnhd on 02/05/2018.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder sh;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bmpEgg, bmpBasket;

    private GameLoopThread gameLoopThread;

    //Sprite
    private Sprite spriteBasket, spriteEgg;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameSurfaceView(Context context) {
        super(context);
        sh = getHolder();
        sh.addCallback(this);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        gameLoopThread = new GameLoopThread(this);

        bmpEgg = BitmapFactory.decodeResource(getResources(), R.drawable.egg);
        bmpBasket = BitmapFactory.decodeResource(getResources(), R.drawable.basket3);
        spriteBasket = new Sprite(this, bmpBasket, 20);
        spriteEgg = new Sprite(this, bmpEgg, 20);

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
    public void onDraw(Canvas cv) {
        super.onDraw(cv);

        cv.drawColor(Color.BLACK);

        spriteBasket.onDrawBasket(cv);
        spriteEgg.onDrawEgg(cv);

        // Log.d("test", "width:" + spriteBasket.getWidth() + "height:" + spriteBasket.getHeight());

        if (spriteEgg.getY() >= spriteBasket.getY() && spriteEgg.getX() > spriteBasket.getX() && spriteEgg.getX() < spriteBasket.getX() + spriteBasket.getWidth()) {
            Log.d("test", "Ok");
        }

        // Log.d("test", "basket-X:" + spriteBasket.getX() + "basket-Y:" + spriteBasket.getY());
        // Log.d("test", "egg-X:" + spriteEgg.getX() + "basket-X:" + spriteBasket.getX());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        spriteBasket.setX(event.getX());
        // Toast.makeText(getContext(), "touch:" + event.getX() + "," + event.getY(), Toast.LENGTH_LONG).show();
        return true;
    }
}
