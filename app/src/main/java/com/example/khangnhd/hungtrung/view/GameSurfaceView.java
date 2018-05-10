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
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.khangnhd.hungtrung.thread.GameLoopThread;
import com.example.khangnhd.hungtrung.R;
import com.example.khangnhd.hungtrung.sprite.Sprite;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khangnhd on 02/05/2018.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder sh;
    private Bitmap bmpEgg, bmpBasket, bmpHeart, bmpBackground;

    private GameLoopThread gameLoopThread;

    //Sprite
    private Sprite spriteBasket;
    private List<Sprite> spriteEggList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameSurfaceView(Context context) {
        super(context);

        sh = getHolder();
        sh.addCallback(this);

        //thread
        gameLoopThread = new GameLoopThread(this);

        //bmp
        bmpEgg = BitmapFactory.decodeResource(getResources(), R.drawable.egg);
        bmpHeart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        bmpBasket = BitmapFactory.decodeResource(getResources(), R.drawable.basket3);
        bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        //sprite
        spriteBasket = new Sprite(this, bmpBasket, 20);
        spriteEggList = new ArrayList<>();

        Sprite spriteEgg = new Sprite(this, bmpEgg, 20);
        Sprite spriteEgg1 = new Sprite(this, bmpEgg, 20);
        spriteEgg.setX(100);
        spriteEgg1.setX(250);

        spriteEggList.add(spriteEgg);
        spriteEggList.add(spriteEgg1);

    }

    /*
     *
     * */
    public Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }

        return bitmap;
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
        paintScore.setStyle(Paint.Style.STROKE);
        paintScore.setStrokeWidth(3);
        paintScore.setTextSize(83);

        Rect boundsTextScore = new Rect();

        String textScore = "10";

        paintScore.getTextBounds(textScore, 0, textScore.length(), boundsTextScore);

        cv.drawText(textScore, cv.getWidth() - boundsTextScore.width() - 60, 100, paintScore);

        cv.drawBitmap(bmpHeart, 30, 30, null);
        cv.drawBitmap(bmpHeart, 30 + bmpHeart.getWidth(), 30, null);
        cv.drawBitmap(bmpHeart, 30 + bmpHeart.getWidth() + bmpHeart.getWidth(), 30, null);

        spriteBasket.onDrawBasket(cv);

        for (Sprite spriteEgg : spriteEggList) {
            spriteEgg.onDrawEgg(cv);

            if (spriteEgg.isCollision(spriteBasket)) {
                spriteEggList.remove(spriteEgg);
                Log.d("test", "Collision:" + spriteEgg.getX());
                break;

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