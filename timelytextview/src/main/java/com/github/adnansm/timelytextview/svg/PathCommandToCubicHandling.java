package com.github.adnansm.timelytextview.svg;

import android.util.Log;

import com.github.adnansm.timelytextview.model.Cubic;
import com.github.adnansm.timelytextview.model.PointD;
import com.google.common.collect.Lists;

import java.util.List;

public class PathCommandToCubicHandling extends PathCommandHandling {
    private final List<Cubic> all = Lists.newArrayList();

    @Override
    protected void handle_M(double endX, double endY) {
    }

    @Override
    protected void handle_m(double d_endX, double d_endY) {
    }

    @Override
    protected void handle_L(double endX, double endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                endX, endY, endX, endY));
    }

    @Override
    protected void handle_l(double d_endX, double d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX() + d_endX, lastY() + d_endY, lastX() + d_endX, lastY() + d_endY));
    }

    @Override
    protected void handle_H(double endX) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                endX, lastY(), endX, lastY()));
    }

    @Override
    protected void handle_h(double d_endX) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX() + d_endX, lastY(), lastX() + d_endX, lastY()));
    }

    @Override
    protected void handle_V(double endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX(), endY, lastX(), endY));
    }

    @Override
    protected void handle_v(double d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX(), lastY() + d_endY, lastX(), lastY() + d_endY));
    }

    @Override
    protected void handle_Z() {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                firstX(), firstY(), firstX(), firstY()));
    }

    @Override
    protected void handle_C(double control1X, double control1Y, double control2X, double control2Y, double endX, double endY) {
        Log.d("Handle", control1X + " " + control1Y + " " + control2X + " " + control2Y + " " + endX + " " + endY);
        all.add(new Cubic(lastX(), lastY(), control1X, control1Y,
                control2X, control2Y, endX, endY));
    }

    @Override
    protected void handle_c(double d_control1X, double d_control1Y, double d_control2X, double d_control2Y, double d_endX, double d_endY) {
        all.add(new Cubic(lastX(), lastY(),
                lastX() + d_control1X, lastY() + d_control1Y,
                lastX() + d_control2X, lastY() + d_control2Y,
                lastX() + d_endX, lastY() + d_endY));
    }

    @Override
    protected void handle_S(double control2X, double control2Y, double endX, double endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                control2X, control2Y, endX, endY));
    }

    @Override
    protected void handle_s(double d_control2X, double d_control2Y, double d_endX, double d_endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                lastX() + d_control2X, lastY() + d_control2Y, lastX() + d_endX, lastY() + d_endY));
    }

    @Override
    protected void handle_Q(double controlX, double controlY, double endX, double endY) {
        double c1X = lastX() + (2.0 / 3.0) * (controlX - lastX());
        double c1Y = lastY() + (2.0 / 3.0) * (controlY - lastY());
        double c2X = endX + (2.0 / 3.0) * (controlX - endX);
        double c2Y = endY + (2.0 / 3.0) * (controlY - endY);
        all.add(new Cubic(lastX(), lastY(), c1X, c1Y,
                c2X, c2Y, endX, endY));
    }

    @Override
    protected void handle_q(double d_controlX, double d_controlY, double d_endX, double d_endY) {
        double controlX = lastX() + d_controlX;
        double controlY = lastY() + d_controlY;
        double endX = lastX() + d_endX;
        double endY = lastY() + d_endY;

        double c1X = lastX() + (2.0 / 3.0) * (controlX - lastX());
        double c1Y = lastY() + (2.0 / 3.0) * (controlY - lastY());
        double c2X = endX + (2.0 / 3.0) * (controlX - endX);
        double c2Y = endY + (2.0 / 3.0) * (controlY - endY);

        all.add(new Cubic(lastX(), lastY(), c1X, c1Y,
                c2X, c2Y, endX, endY));
    }

    @Override
    protected void handle_T(double endX, double endY) {
//        Log.d("Handle", "" + (lastX()*2 - lastControlX())/2);
//        Log.d("Handle", "" + (lastY()*2 - lastControlY())/2);
        Log.d("Handle", "end: " + endX + ", " + endY);
        handle_Q(nextControlX(), nextControlY(), endX, endY);
    }

    @Override
    protected void handle_t(double d_endX, double d_endY) {
        handle_T(lastX() + d_endX, lastY() + d_endY);
    }

    public List<Cubic> getPathAsCubics() {
        Log.d("Handle", "gotten");
        return all;
    }
}
