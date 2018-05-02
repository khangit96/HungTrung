package com.example.khangnhd.hungtrung;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by khangnhd on 02/05/2018.
 */

public class MyCanvasView extends View {
    private Paint mPaint;
    private Path mPath;
    private int mDrawColor;
    private int mBackgroundColor;
    private Canvas mExtraCanvas;
    private Bitmap mExtraBitmap;

    public MyCanvasView(Context context) {
        super(context);
    }

    public MyCanvasView(Context context, AttributeSet attributeSet) {
        super(context);

        mBackgroundColor = ResourcesCompat.getColor(getResources(),
                R.color.colorPrimary, null);
        mDrawColor = ResourcesCompat.getColor(getResources(),
                R.color.colorPrimary, null);

        // Holds the path we are currently drawing.
        mPath = new Path();
        // Set up the paint with which to draw.
        mPaint = new Paint();
        mPaint.setColor(mDrawColor);
        // Smoothes out edges of what is drawn without affecting shape.
        mPaint.setAntiAlias(true);
        // Dithering affects how colors with higher-precision device
        // than the are down-sampled.
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE); // default: FILL
        mPaint.setStrokeJoin(Paint.Join.ROUND); // default: MITER
        mPaint.setStrokeCap(Paint.Cap.ROUND); // default: BUTT
        mPaint.setStrokeWidth(12); // default: Hairline-width (really thin)
    }

    @Override
    protected void onSizeChanged(int width, int height,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
// Create bitmap, create canvas with bitmap, fill canvas with color.
        mExtraBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        mExtraCanvas = new Canvas(mExtraBitmap);
// Fill the Bitmap with the background color.
        mExtraCanvas.drawColor(mBackgroundColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
// Draw the bitmap that stores the path the user has drawn.
// Initially the user has not drawn anything
// so we see only the colored bitmap.
        canvas.drawBitmap(mExtraBitmap, 0, 0, null);
    }
}
