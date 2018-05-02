package com.example.khangnhd.hungtrung;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    int width = 200, height = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameSurfaceView(this));

//        surfaceView = (SurfaceView) findViewById(R.id.imagesurface);
//        surfaceHolder = surfaceView.getHolder();
//        surfaceHolder.addCallback(this);
//        surfaceView.setWillNotDraw(false);
//
//        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        mCanvas = new Canvas(mBitmap);
    }

//    protected void onDraw(Canvas canvas) {
//        canvas.drawRGB(255, 0, 255);
//    }


//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        Toast.makeText(getApplicationContext(), "Suface created", Toast.LENGTH_LONG).show();
////        Canvas canvas = null;
////        try {
////            canvas = holder.lockCanvas();
////            synchronized (holder) {
////                onDraw(canvas);
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (canvas != null) {
////                holder.unlockCanvasAndPost(canvas);
////            }
////        }
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//
//    }
}
