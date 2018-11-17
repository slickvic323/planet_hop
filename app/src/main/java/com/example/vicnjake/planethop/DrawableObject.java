package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DrawableObject {

    private int[] coords;
    private static int[] dimensions;
    private Bitmap bitmap;

    public DrawableObject (Context context, int[] coords, int[] dimensions) {
        this.coords = coords;
        this.dimensions = dimensions;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.planet1);
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
}
