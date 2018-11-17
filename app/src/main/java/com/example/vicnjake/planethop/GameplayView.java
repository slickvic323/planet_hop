package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameplayView extends SurfaceView implements Runnable {
    private boolean running;
    private Thread titleThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private int[] screenDims;
    private Context context;



    public GameplayView (Context context, int[] screenDims) {
        super(context);
        this.context = context;
        this.screenDims = screenDims;

        surfaceHolder = getHolder();
        paint = new Paint();

    }

    @Override
    public void run () {
        while (running) {

        }
    }

    public void draw () {

    }

    public void update() {

    }
}
