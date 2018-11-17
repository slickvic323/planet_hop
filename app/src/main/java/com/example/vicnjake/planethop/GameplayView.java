package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.lang.*;

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
    public int pilotx, piloty;
    public int angle;


    public GameplayView(Context context, int screenSizeX, int screenSizeY) {
        super(context);

        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
        paint = new Paint();

        angle=0;

        homePlanet = new DrawableObject(context, R.drawable.planet1, new int[]{screenSizeX/2, (screenSizeY*9/10)}, new int[]{200, 200});
        gravity = new DrawableObject(context, R.drawable.gravity, new int[]{screenSizeX/2, (screenSizeY*9/10)}, new int[]{260, 260});

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

    private void update () {
        updatePilotPosition();
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
//            Matrix matrix = new Matrix();
//            matrix.reset();
//            matrix.postTranslate(-pilot.getBitmap().getWidth() / 2, -pilot.getBitmap().getHeight() / 2); // Centers image
//            matrix.postRotate(angle);
//            matrix.postTranslate(pilot.getCoords()[0]+(int)(Math.cos(Math.toRadians(angle))*130), pilot.getCoords()[1]+(int)(Math.sin(Math.toRadians(angle))*130));
//            canvas.drawBitmap(pilot.getBitmap(), matrix, paint);
            canvas.drawBitmap(
                    pilot.getBitmap(),
                    pilot.getCoords()[0]+(int)(Math.cos(Math.toRadians(angle))*130),
                    pilot.getCoords()[1]+(int)(Math.sin(Math.toRadians(angle))*130),
                    paint

            );
            angle=(angle+2)%360;
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


    private void updatePilotPosition () {
        pilotx += (int)(Math.cos(Math.toRadians(angle%360))*125);
        piloty += (int)(Math.sin(Math.toRadians(angle%360))*125);
        pilot.setCoords(new int[]{pilotx, piloty});
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
