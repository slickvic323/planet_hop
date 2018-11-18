package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.lang.*;
import java.util.Timer;
import java.util.TimerTask;

import android.os.*;

public class GameplayView extends SurfaceView implements Runnable{
    volatile boolean running;
    private Thread gameplayThread = null;
    private SurfaceHolder surfaceHolder = null;
    private Paint paint;
    private Canvas canvas;

    private int screenSizeX, screenSizeY;

    private DrawableObject homePlanet;
    private DrawableObject gravity;
    private DrawableObject pilot;
    public int angle;

    GameInfo gameInfo;
    private int textOpacity = 255;
    private boolean textFadingIn = false;

    private boolean orbiting = true;


    public GameplayView(Context context, int screenSizeX, int screenSizeY) {
        super(context);

        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
        paint = new Paint();

        gameInfo = new GameInfo();

        angle=0;

        homePlanet = new DrawableObject(context, R.drawable.planet1, new int[]{screenSizeX/2, (screenSizeY*9/10)}, new int[]{300, 300});
        gravity = new DrawableObject(context, R.drawable.gravity, new int[]{screenSizeX/2, (screenSizeY*9/10)}, new int[]{550, 550});
        pilot = new DrawableObject(context, R.drawable.pilot_ship, new int[]{screenSizeX/2, (screenSizeY*9/10)}, new int[]{50, 50});


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
            update();
            draw();
        }
    }



    private void draw () {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();

            //clear screen
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            Log.i("DEBUGGIN!", String.valueOf((int)(Math.cos(Math.toRadians(angle%360))*130)));
            canvas.drawBitmap(
                homePlanet.getBitmap(),
                    homePlanet.getCoords()[0],
                    homePlanet.getCoords()[1],
                    paint

            );
            canvas.drawBitmap(
                    gravity.getBitmap(),
                    gravity.getCoords()[0],
                    gravity.getCoords()[1],
                    paint

            );

            // Changes the angle of the pilot ship
            Matrix matrix = new Matrix();
            matrix.reset();
            matrix.postScale(1, -1,pilot.getBitmap().getWidth() / 2f, pilot.getBitmap().getHeight() / 2f);
            matrix.postTranslate(-pilot.getBitmap().getWidth() / 2, -pilot.getBitmap().getHeight() / 2); // Centers image
            matrix.postRotate(angle);
            matrix.postTranslate(pilot.getBitmap().getWidth() / 2, pilot.getBitmap().getHeight() / 2); // Centers image
            matrix.postTranslate(pilot.getCoords()[0]+(int)(Math.cos(Math.toRadians(angle))*130), pilot.getCoords()[1]+(int)(Math.sin(Math.toRadians(angle))*130));
            canvas.drawBitmap(pilot.getBitmap(), matrix, paint);
            if(orbiting) {
                pilotInOrbit();
            } else {
//                float[] pts = new float[2];
//                pts[0] = (float) pilot.getCoords()[0];
//                pts[1] = (float) pilot.getCoords()[1];
//                matrix.mapPoints(pts);
//                Log.i("pointX", String.valueOf(pilot.getCoords()[0]));
//                Log.i("pointY", String.valueOf(pilot.getCoords()[1]));
            }


            if (!gameInfo.isPlaying()) {
                // Draw the "Touch anywhere to begin"
                Paint textPaint = new Paint();
                textPaint.setColor(Color.parseColor("#E6FFF2"));
                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setTextSize(screenSizeX/20);
                textPaint.setTextAlign(Paint.Align.CENTER);
                textPaint.setAlpha(100);
                if (textOpacity <= 3 && textOpacity >=0) {
                    textFadingIn = true;
                } else if (textOpacity <= 255 && textOpacity >= 252) {
                    textFadingIn = false;
                }
                if(textFadingIn) {
                    textOpacity += 3;
                } else {
                    textOpacity -= 3;
                }
                textPaint.setAlpha(textOpacity);
                canvas.drawText(
                        "Tap anywhere to launch!",
                        canvas.getWidth() / 2,
                        screenSizeY / 3,
                        textPaint
                    );

            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void update() {

    }


    @Override
    public boolean onTouchEvent (MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float touchedX, touchedY;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                touchedX = motionEvent.getX();
                touchedY = motionEvent.getY();
                orbiting = false;
                Log.i("Angle", String.valueOf(angle));
                break;
            case MotionEvent.ACTION_UP:
                touchedX = motionEvent.getX();
                touchedY = motionEvent.getY();
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

    private void pilotInOrbit () {
        angle=(angle+3)%360;
    }

    private void getCoordsFromAngle (int gravitationalRadius) {

    }

}
