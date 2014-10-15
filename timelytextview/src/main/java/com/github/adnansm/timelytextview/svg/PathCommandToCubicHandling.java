package com.github.adnansm.timelytextview.svg;

import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

import java.util.List;

public class PathCommandToCubicHandling extends PathCommandHandling {
    private final List<Cubic> all = Lists.newArrayList();

    @Override
    protected void handle_M(int endX, int endY) {
        // do nothing
    }

    @Override
    protected void handle_m(int d_endX, int d_endY) {
        // do nothing
    }

    @Override
    protected void handle_L(int endX, int endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                endX, endY, endX, endY));
    }

    @Override
    protected void handle_l(int d_endX, int d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX() + d_endX, lastY() + d_endY, lastX() + d_endX, lastY() + d_endY));
    }

    @Override
    protected void handle_H(int endX) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                endX, lastY(), endX, lastY()));
    }

    @Override
    protected void handle_h(int d_endX) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX() + d_endX, lastY(), lastX() + d_endX, lastY()));
    }

    @Override
    protected void handle_V(int endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX(), endY, lastX(), endY));
    }

    @Override
    protected void handle_v(int d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX(), lastY() + d_endY, lastX(), lastY() + d_endY));
    }

    @Override
    protected void handle_Z() {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                firstX(), firstY(), firstX(), firstY()));
    }

    @Override
    protected void handle_C(int control1X, int control1Y, int control2x, int control2Y, int endX, int endY) {
        all.add(new Cubic(lastX(), lastY(), control1X, control1Y,
                control2x, control2Y, endX, endY));
    }

    @Override
    protected void handle_c(int d_control1X, int d_control1Y, int d_control2x, int d_control2Y, int d_endX, int d_endY) {
        all.add(new Cubic(lastX(), lastY(),
                lastX() + d_control1X, lastY() + d_control1Y,
                lastX() + d_control2x, lastY() + d_control2Y,
                lastX() + d_endX, lastY() + d_endY));
    }

    @Override
    protected void handle_S(int control2x, int control2Y, int endX, int endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                control2x, control2Y, endX, endY));
    }

    @Override
    protected void handle_s(int d_control2x, int d_control2Y, int d_endX, int d_endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                lastX() + d_control2x, lastY() + d_control2Y, lastX() + d_endX, lastY() + d_endY));
    }

    @Override
    protected void handle_Q(int controlX, int controlY, int endX, int endY) {
        all.add(new Cubic(lastX(), lastY(), controlX, controlY,
                controlX, controlY, endX, endY));
    }

    @Override
    protected void handle_q(int d_controlX, int d_controlY, int d_endX, int d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX() + d_controlX, lastY() + d_controlY,
                lastX() + d_controlX, lastY() + d_controlY, lastX() + d_endX, lastY() + d_endY));
    }

    @Override
    protected void handle_T(int endX, int endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                nextControlX(), nextControlY(), endX, endY));
    }

    @Override
    protected void handle_t(int d_endX, int d_endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                nextControlX(), nextControlY(), lastX() + d_endX, lastY() + d_endY));
    }

    public List<Cubic> getPathAsCubics() {
        return all;
    }
}
