package com.github.adnansm.timelytextview.model;

import com.google.common.base.MoreObjects;

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

    public Cubic normalizeWithMax(double maxValue) {
        return new Cubic(startX / maxValue, startY / maxValue,
                control1X / maxValue, control1Y / maxValue,
                control2X / maxValue, control2Y / maxValue,
                endX / maxValue, endY / maxValue);
    }

    public Cubic flipAlongVertical() {
        return new Cubic(1 - startX, startY,
                1 - control1X, control1Y,
                1 - control2X, control2Y,
                1 - endX, endY);
    }

    public Cubic flipAlongHorizontal() {
        return new Cubic(startX, 1 - startY,
                control1X, 1 - control1Y,
                control2X, 1 - control2Y,
                endX, 1 - endY);
    }

    public Cubic swap() {
        return new Cubic(endX, endY,
                control2X, control2Y,
                control1X, control1Y,
                startX, startY);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("startX", startX)
                .add("startY", startY)
                .add("control1X", control1X)
                .add("control1Y", control1Y)
                .add("control2X", control2X)
                .add("control2Y", control2Y)
                .add("endX", endX)
                .add("endY", endY)
                .toString();
    }
}
