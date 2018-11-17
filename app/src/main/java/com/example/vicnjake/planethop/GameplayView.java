package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameplayView extends SurfaceView implements Runnable{
    volatile boolean running;
    private Thread gameplayThread = null;
    private SurfaceHolder surfaceHolder = null;
    private Paint paint;
    private Canvas canvas;

    private int screenSizeX, screenSizeY;

    private DrawableObject homePlanet;


    public GameplayView(Context context, int screenSizeX, int screenSizeY) {
        super(context);

        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
        paint = new Paint();

        homePlanet = new DrawableObject(context, R.drawable.planet1, new int[]{0, 0}, new int[]{100, 100});

        setFocusable(true);

        if(surfaceHolder == null) {
            // Get surfaceHolder object.
            surfaceHolder = getHolder();
        }

        // Set the parent view background color. This can not set surfaceview background color.
        this.setBackgroundColor(Color.parseColor("#2F5063"));

        // Set current surfaceview at top of the view tree.
        this.setZOrderOnTop(true);

        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);

    }

    @Override
    public void run () {
        while (running) {
            draw();
        }
    }

    private void draw () {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawBitmap(
                homePlanet.getBitmap(),
                    homePlanet.getCoords()[0],
                    homePlanet.getCoords()[1],
                    paint

            );
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }


    @Override
    public boolean onTouchEvent (MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float touchedX, touchedY;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                touchedX = motionEvent.getX();
                touchedY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                touchedX = motionEvent.getX();
                touchedY = motionEvent.getY();
                System.out.println(touchedX);
                System.out.println(touchedY);
                break;
            default:
                break;
        }
        return true;
    }



    public void resume () {
        running = true;
        gameplayThread = new Thread(this);
        gameplayThread.start();
    }

    public void pause () {
        running = false;
        try {
            //stopping the thread
            gameplayThread.join();
        } catch (InterruptedException e) {
        }
    }
}
