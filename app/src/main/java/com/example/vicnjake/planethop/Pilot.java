package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Bitmap;

public class Pilot {
    private DrawableObject drawablePilot;

    private int[] planetCoords;
    Pilot(Context context, int[] planetCoords){
        this.planetCoords = new int[]{planetCoords[0], planetCoords[1]};
        this.drawablePilot = new DrawableObject(context, R.drawable.pilot_ship, planetCoords, new int[]{50, 50});
    }

    public int[] getPlanetCoords() {
        return this.drawablePilot.getCoords();
    }

    public Bitmap getBitmap() {
        return drawablePilot.getBitmap();
    }
}
