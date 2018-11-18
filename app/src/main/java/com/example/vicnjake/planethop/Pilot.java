package com.example.vicnjake.planethop;

import android.content.Context;
import android.graphics.Bitmap;

public class Pilot {
    private DrawableObject drawablePilot;
    private Planet orbitingPlanet = null;

    //always want to construct the pilot orbiting the home planet so no constructor without it.
    private int[] planetCoords;
    Pilot(Context context, int[] planetCoords, Planet orbitingPlanet) {
        this.orbitingPlanet = orbitingPlanet;
        this.planetCoords = new int[]{planetCoords[0], planetCoords[1]};
        this.drawablePilot = new DrawableObject(context, R.drawable.pilot_ship, planetCoords, new int[]{50, 50});
    }

    public void removeOrbitingPlanet(){
        this.orbitingPlanet = null;
    }

    public void setOrbitingPlanet(Planet orbitingPlanet){
        this.orbitingPlanet = orbitingPlanet;
    }

    public int[] getPilotCoords() {
        return this.drawablePilot.getCoords();
    }

    public int[] getOrbitingCoords(){

        return new int[]{orbitingPlanet.getOriginalCoords()[0]-25, orbitingPlanet.getOriginalCoords()[1]-25};
    }

    public Bitmap getBitmap() {
        return drawablePilot.getBitmap();
    }
}
