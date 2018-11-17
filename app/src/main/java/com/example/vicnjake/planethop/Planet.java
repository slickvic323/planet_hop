package com.example.vicnjake.planethop;

public class Planet {
    private int percentX, percentY;
    private int radius;
    private int gravitationalRadius;

    public Planet(int percentX, int percentY, int radius, int gravitationalRadius) {
        this.percentX = percentX;
        this.percentY = percentY;
        this.radius = radius;
        this.gravitationalRadius = gravitationalRadius;
    }

    public int getPercentX() {
        return percentX;
    }

    public void setPercentX(int percentX) {
        this.percentX = percentX;
    }

    public int getPercentY() {
        return percentY;
    }

    public void setPercentY(int percentY) {
        this.percentY = percentY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getGravitationalRadius() {
        return gravitationalRadius;
    }

    public void setGravitationalRadius(int gravitationalRadius) {
        this.gravitationalRadius = gravitationalRadius;
    }

}
