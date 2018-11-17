package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class DrawableObject {

    private int[] coords;
    private static int[] dimensions;
    private Bitmap bitmap;

    public DrawableObject (Context context, int image, int[] coords, int[] dimensions) {
        // Center the object (draw image center at coordinates given)
        this.coords = coords;
        this.coords[0]-=(dimensions[0]/2);
        this.coords[1]-=(dimensions[1]/2);
        this.dimensions = dimensions;

        bitmap = BitmapFactory.decodeResource(context.getResources(), image);
        bitmap = changeScale(bitmap);
    }

    public static Bitmap changeScale(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, dimensions[0], dimensions[1], false);
        return newBitmap;
    }

    public int[] getCoords() {
        return coords;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }
}
