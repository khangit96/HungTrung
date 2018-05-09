package com.example.khangnhd.hungtrung.gameView;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khangnhd on 02/05/2018.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder sh;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bmpEgg, bmpBasket, bmpBackground;

    private GameLoopThread gameLoopThread;

    //Sprite
    private Sprite spriteBasket;
    private List<Sprite> spriteEggList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameSurfaceView(Context context) {
        super(context);
        //   this.setBackgroundResource(R.drawable.background);
        sh = getHolder();
        sh.addCallback(this);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        gameLoopThread = new GameLoopThread(this);

        //bmp
        bmpEgg = BitmapFactory.decodeResource(getResources(), R.drawable.egg);
        bmpBasket = BitmapFactory.decodeResource(getResources(), R.drawable.basket3);
        bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        //sprite
        spriteBasket = new Sprite(this, bmpBasket, 20);
        spriteEggList = new ArrayList<>();

        Sprite spriteEgg = new Sprite(this, bmpEgg, 20);
        Sprite spriteEgg1 = new Sprite(this, bmpEgg, 20);
        spriteEgg.setX(100);
        spriteEgg1.setX(400);

        spriteEggList.add(spriteEgg);
        spriteEggList.add(spriteEgg1);


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

        // cv.drawColor(Color.BLACK);
//        Paint paint = new Paint();
//        paint.setColor(Color.WHITE);
//        paint.setStyle(Paint.Style.FILL);
//        cv.drawPaint(paint);
//
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(20);
//        cv.drawText("Some Text", 10, 25, paint);
        cv.drawBitmap(bmpBackground, 0, 0, null);
        //     Log.d("test", "" + this.getWidth());

        spriteBasket.onDrawBasket(cv);

        for (Sprite spriteEgg : spriteEggList) {
            spriteEgg.onDrawEgg(cv);

            if (spriteEgg.isCollision(spriteBasket)) {
                // spriteEggList.remove(spriteEgg);
                //  Log.d("test", "Collision:" + spriteEgg.getX());
            }
        }


//        if (spriteEgg.getX() == 0) {
//            spriteEgg.setX(150);
//            Log.d("test", "O");
//        }
//        if (spriteEgg.isCollision(spriteBasket)) {
//            Log.d("test", "Collision");
//        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        spriteBasket.setX(event.getX());
        return true;
    }

}
