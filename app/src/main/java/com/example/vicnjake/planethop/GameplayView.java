package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameplayView extends SurfaceView {
    private SurfaceHolder surfaceHolder = null;



    public GameplayView(Context context, int screenSizeX, int screenSizeY) {
        super(context);


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
}
