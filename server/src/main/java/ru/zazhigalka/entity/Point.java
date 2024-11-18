package ru.zazhigalka.entity;

import ru.zazhigalka.util.PointChecker;

public class Point {
    public final double x;
    public final int y;
    public final double r;
    public final boolean inArea;

    public Point(double x, int y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.inArea = PointChecker.inArea(this);
    }

    public double getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    private boolean inArea() {
        return inArea;
    }

}

