package com.github.adnansm.timelytextview.svg;

import android.graphics.Point;

import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

import java.util.List;

public class PathCommandToCubicHandling extends PathCommandHandling {
    private final List<Cubic> all = Lists.newArrayList();

    @Override
    protected Point handle_M(int endX, int endY) {
        return new Point(endX, endY);
    }

    @Override
    protected Point handle_m(int d_endX, int d_endY) {
        return new Point(lastX() + d_endX, lastY() + d_endY);
    }

    @Override
    protected Point handle_L(int endX, int endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                endX, endY, endX, endY));
        return new Point(endX, endY);
    }

    @Override
    protected Point handle_l(int d_endX, int d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX() + d_endX, lastY() + d_endY, lastX() + d_endX, lastY() + d_endY));
        return new Point(lastX() + d_endX, lastY() + d_endY);
    }

    @Override
    protected Point handle_H(int endX) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                endX, lastY(), endX, lastY()));
        return new Point(endX, lastY());
    }

    @Override
    protected Point handle_h(int d_endX) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX() + d_endX, lastY(), lastX() + d_endX, lastY()));
        return new Point(lastX() + d_endX, lastY());
    }

    @Override
    protected Point handle_V(int endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX(), endY, lastX(), endY));
        return new Point(lastX(), endY);
    }

    @Override
    protected Point handle_v(int d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX(), lastY() + d_endY, lastX(), lastY() + d_endY));
        return new Point(lastX(), lastY() + d_endY);
    }

    @Override
    protected Point handle_Z() {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                firstX(), firstY(), firstX(), firstY()));
        return new Point(firstX(), firstY());
    }

    @Override
    protected Point handle_C(int control1X, int control1Y, int control2x, int control2Y, int endX, int endY) {
        all.add(new Cubic(lastX(), lastY(), control1X, control1Y,
                control2x, control2Y, endX, endY));
        return new Point(control2x, control2Y);
    }

    @Override
    protected Point handle_c(int d_control1X, int d_control1Y, int d_control2x, int d_control2Y, int d_endX, int d_endY) {
        all.add(new Cubic(lastX(), lastY(),
                lastX() + d_control1X, lastY() + d_control1Y,
                lastX() + d_control2x, lastY() + d_control2Y,
                lastX() + d_endX, lastY() + d_endY));
        return new Point(lastX() + d_control2x, lastY() + d_control2Y);
    }

    @Override
    protected Point handle_S(int control2x, int control2Y, int endX, int endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                control2x, control2Y, endX, endY));
        return new Point(control2x, control2Y);
    }

    @Override
    protected Point handle_s(int d_control2x, int d_control2Y, int d_endX, int d_endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                lastX() + d_control2x, lastY() + d_control2Y, lastX() + d_endX, lastY() + d_endY));
        return new Point(lastX() + d_control2x, lastY() + d_control2Y);
    }

    @Override
    protected Point handle_Q(int controlX, int controlY, int endX, int endY) {
        all.add(new Cubic(lastX(), lastY(), controlX, controlY,
                controlX, controlY, endX, endY));
        return new Point(controlX, controlY);
    }

    @Override
    protected Point handle_q(int d_controlX, int d_controlY, int d_endX, int d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX() + d_controlX, lastY() + d_controlY,
                lastX() + d_controlX, lastY() + d_controlY, lastX() + d_endX, lastY() + d_endY));
        return new Point(lastX() + d_controlX, lastY() + d_controlY);
    }

    @Override
    protected Point handle_T(int endX, int endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                nextControlX(), nextControlY(), endX, endY));
        return new Point(nextControlX(), nextControlY());
    }

    @Override
    protected Point handle_t(int d_endX, int d_endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                nextControlX(), nextControlY(), lastX() + d_endX, lastY() + d_endY));
        return new Point(nextControlX(), nextControlY());
    }

    public List<Cubic> getPathAsCubics() {
        return all;
    }
}
