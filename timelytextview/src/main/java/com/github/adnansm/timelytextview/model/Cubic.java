package com.github.adnansm.timelytextview.model;

public class Cubic {
    public final double startX;
    public final double startY;

    public final double control1X;
    public final double control1Y;

    public final double control2X;
    public final double control2Y;

    public final double endX;
    public final double endY;

    public Cubic(double startX, double startY, double control1X, double control1Y, double control2X, double control2Y, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.control1X = control1X;
        this.control1Y = control1Y;
        this.control2X = control2X;
        this.control2Y = control2Y;
        this.endX = endX;
        this.endY = endY;
    }
}
