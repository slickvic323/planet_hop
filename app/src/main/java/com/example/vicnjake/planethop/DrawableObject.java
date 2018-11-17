package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DrawableObject {

    private int[] coords;
    private Bitmap bitmap;

    public DrawableObject (Context context, int[] coords) {
        this.coords = coords;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.planet1);
    }

    public int[] getCoords() {
        return coords;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
