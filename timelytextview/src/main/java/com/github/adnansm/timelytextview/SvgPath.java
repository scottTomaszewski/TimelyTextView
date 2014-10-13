package com.github.adnansm.timelytextview;

public class SvgPath {
    private final float[][] controlPoints;

    public SvgPath(float[][] controlPoints) {
        this.controlPoints = controlPoints;
    }

    public float[][] get() {
        return controlPoints;
    }
}
