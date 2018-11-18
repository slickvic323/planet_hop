package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class Planet {



    private int[] originalCoords;
    private int gravitationalRadius;
    private DrawableObject drawableObject;
    private DrawableObject drawableGravity;

    public Planet(Context context, int[] myCoords, int[] dimensions, int gravitationalRadius) {

        this.originalCoords = new int[]{myCoords[0], myCoords[1]};
        this.gravitationalRadius = gravitationalRadius;
        //TODO: random planet image, rotation, and color
        this.drawableObject = new DrawableObject(context, R.drawable.planet1 ,myCoords, dimensions);
        this.drawableGravity = new DrawableObject(context, R.drawable.gravity, myCoords, new int[]{dimensions[0]+gravitationalRadius, dimensions[1]+gravitationalRadius});
    }

    public int[] getCoords() {
        //Log.i("GETTING COORDS", drawableObject.getCoords()[0] + " " + drawableObject.getCoords()[1]);
        return drawableObject.getCoords();
    }


    public int[] getGravityCoords(){
        //Log.i("GETTING COORDS", drawableGravity.getCoords()[0] + " " + drawableGravity.getCoords()[1]);
        return drawableGravity.getCoords();
    }

    public Bitmap getBitmap() {
        return drawableObject.getBitmap();
    }

    public Bitmap getGravity() {
        return drawableGravity.getBitmap();
    }

    public int[] getOriginalCoords() {
        return originalCoords;
    }

    public int getGravitationalRadius() {
        return gravitationalRadius;
    }

    public void setGravitationalRadius(int gravitationalRadius) {
        this.gravitationalRadius = gravitationalRadius;
    }

}
