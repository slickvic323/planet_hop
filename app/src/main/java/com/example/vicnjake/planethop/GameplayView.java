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
import java.util.ArrayList;

public class GameplayView extends SurfaceView implements Runnable{
    volatile boolean running;
    private Thread gameplayThread = null;
    private SurfaceHolder surfaceHolder = null;
    private Paint paint;
    private Canvas canvas;

    private int screenSizeX, screenSizeY;

//    private DrawableObject homePlanet;
//    private DrawableObject gravity;
//    private DrawableObject pilot;
    private Pilot pilot;
    public int angle;

    GameInfo gameInfo;
    private int textOpacity = 255;
    private boolean textFadingIn = false;

    private boolean orbiting = true;

    Matrix matrix = new Matrix();

    public ArrayList<Planet> planetList;

    public GameplayView(Context context, int screenSizeX, int screenSizeY) {
        super(context);

        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
        paint = new Paint();

        gameInfo = new GameInfo();

        angle=0;

        //homePlanet = new DrawableObject(context, R.drawable.planet1, new int[]{screenSizeX/2, (screenSizeY*9/10)}, new int[]{200, 200});
        //gravity = new DrawableObject(context, R.drawable.gravity, new int[]{screenSizeX/2, (screenSizeY*9/10)}, new int[]{260, 260});
        //pilot = new DrawableObject(context, R.drawable.pilot_ship, new int[]{screenSizeX/2, (screenSizeY*9/10)}, new int[]{50, 50});


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

        //Create original planet list
        planetList = new ArrayList<Planet>();

        //generate home planet
        planetList.add(new Planet(context,new int[]{screenSizeX/2, (screenSizeY*9/10)}, new int[]{200, 200},60));

//        for(int i = 0;i<2;i++){
//            //planetList.add(new Planet())
//        }

        pilot = new Pilot(context, new int[]{screenSizeX/2, (screenSizeY*9/10)}, planetList.get(0));

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

            //clear screen
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            //Log.i("DEBUGGIN!", String.valueOf((int)(Math.cos(Math.toRadians(angle%360))*130)));

            //Draw all planets in list
            drawPlanets(planetList);


            // Changes the angle of the pilot ship
            drawPilot();
//            Matrix matrix = new Matrix();
//            matrix.reset();
//            matrix.postScale(1, -1,pilot.getBitmap().getWidth() / 2f, pilot.getBitmap().getHeight() / 2f);
//            matrix.postRotate(angle,pilot.getBitmap().getWidth() / 2f, pilot.getBitmap().getHeight() / 2f);
//            matrix.postTranslate(pilot.getOrbitingCoords()[0]+(int)(Math.cos(Math.toRadians(angle))*130), pilot.getOrbitingCoords()[1]+(int)(Math.sin(Math.toRadians(angle))*130));
//            canvas.drawBitmap(pilot.getBitmap(), matrix, paint);
//            if(orbiting) {
//                pilotInOrbit();
//            } else {
//                // pilot.setCoords(new int[]{pilot.getCoords()[0], pilot.getCoords()[1]-5});
//            }


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


    private void drawPlanets(ArrayList<Planet> planets){
        for(Planet planet: planets){
            //Log.i("planet coords:", String.valueOf(planet.getCoords()[0]));
            canvas.drawBitmap(
                    planet.getBitmap(),
                    planet.getCoords()[0],
                    planet.getCoords()[1],
                    paint

            );
            //Log.i("Gravity coords:", String.valueOf(planet.getGravityCoords()[0]));
            canvas.drawBitmap(
                    planet.getGravity(),
                    planet.getGravityCoords()[0],
                    planet.getGravityCoords()[1],
                    paint
            );
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
                if (orbiting) {
                    orbiting = false;
                    float[] launchingPoint = new float[2];
                    matrix.mapPoints(launchingPoint);
                    pilot.setPilotCoords(new int[]{(int)launchingPoint[0], (int)launchingPoint[1]});
                } else {
                    orbiting = true;
                }
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


    private void drawPilot(){

        matrix.reset();

        matrix.postScale(1, -1,pilot.getBitmap().getWidth() / 2f, pilot.getBitmap().getHeight() / 2f);
        matrix.postRotate(angle,pilot.getBitmap().getWidth() / 2f, pilot.getBitmap().getHeight() / 2f);



        //Decide what actions to perform on pilot
        if(orbiting) {
            matrix.postTranslate(pilot.getOrbitingCoords()[0]+(int)(Math.cos(Math.toRadians(angle))*130), pilot.getOrbitingCoords()[1]+(int)(Math.sin(Math.toRadians(angle))*130));
            angle=(angle+3)%360;
        } else {
            matrix.postTranslate(pilot.getPilotCoords()[0], pilot.getPilotCoords()[1]);
        }

        //Draw pilot
        canvas.drawBitmap(pilot.getBitmap(), matrix, paint);
    }

}
