package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class DrawableObject {

    private int[] coords;
    private int[] dimensions;
    private Bitmap bitmap;

    public DrawableObject (Context context, int image, int[] coords, int[] dimensions) {
        // Center the object (draw image center at coordinates given)
        this.coords = new int[]{0,0};
        this.coords[0]=coords[0]-(dimensions[0]/2);
        this.coords[1]=coords[1]-(dimensions[1]/2);
        //Log.i("DIMENSIONS", String.valueOf(coords[0]));
        this.dimensions = dimensions;

        bitmap = BitmapFactory.decodeResource(context.getResources(), image);
        bitmap = changeScale(bitmap);
    }

    public Bitmap changeScale(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, dimensions[0], dimensions[1], false);
        return newBitmap;
    }

    public int[] getCoords() {
        return this.coords;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setCoords(int[] coords) {
        this.coords = new int[]{0,0};
        this.coords[0]=coords[0]-(dimensions[0]/2);
        this.coords[1]=coords[1]-(dimensions[1]/2);
    }
}
