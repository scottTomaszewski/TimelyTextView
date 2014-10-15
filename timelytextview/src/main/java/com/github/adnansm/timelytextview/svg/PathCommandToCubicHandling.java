package com.github.adnansm.timelytextview.svg;

import android.graphics.PointF;

import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

import java.util.List;

public class PathCommandToCubicHandling extends PathCommandHandling {
    private final List<Cubic> all = Lists.newArrayList();

    @Override
    protected PointF handle_M(float endX, float endY) {
        return new PointF(endX, endY);
    }

    @Override
    protected PointF handle_m(float d_endX, float d_endY) {
        return new PointF(lastX() + d_endX, lastY() + d_endY);
    }

    @Override
    protected PointF handle_L(float endX, float endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                endX, endY, endX, endY));
        return new PointF(endX, endY);
    }

    @Override
    protected PointF handle_l(float d_endX, float d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX() + d_endX, lastY() + d_endY, lastX() + d_endX, lastY() + d_endY));
        return new PointF(lastX() + d_endX, lastY() + d_endY);
    }

    @Override
    protected PointF handle_H(float endX) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                endX, lastY(), endX, lastY()));
        return new PointF(endX, lastY());
    }

    @Override
    protected PointF handle_h(float d_endX) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX() + d_endX, lastY(), lastX() + d_endX, lastY()));
        return new PointF(lastX() + d_endX, lastY());
    }

    @Override
    protected PointF handle_V(float endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX(), endY, lastX(), endY));
        return new PointF(lastX(), endY);
    }

    @Override
    protected PointF handle_v(float d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                lastX(), lastY() + d_endY, lastX(), lastY() + d_endY));
        return new PointF(lastX(), lastY() + d_endY);
    }

    @Override
    protected PointF handle_Z() {
        all.add(new Cubic(lastX(), lastY(), lastX(), lastY(),
                firstX(), firstY(), firstX(), firstY()));
        return new PointF(firstX(), firstY());
    }

    @Override
    protected PointF handle_C(float control1X, float control1Y, float control2x, float control2Y, float endX, float endY) {
        all.add(new Cubic(lastX(), lastY(), control1X, control1Y,
                control2x, control2Y, endX, endY));
        return new PointF(control2x, control2Y);
    }

    @Override
    protected PointF handle_c(float d_control1X, float d_control1Y, float d_control2x, float d_control2Y, float d_endX, float d_endY) {
        all.add(new Cubic(lastX(), lastY(),
                lastX() + d_control1X, lastY() + d_control1Y,
                lastX() + d_control2x, lastY() + d_control2Y,
                lastX() + d_endX, lastY() + d_endY));
        return new PointF(lastX() + d_control2x, lastY() + d_control2Y);
    }

    @Override
    protected PointF handle_S(float control2x, float control2Y, float endX, float endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                control2x, control2Y, endX, endY));
        return new PointF(control2x, control2Y);
    }

    @Override
    protected PointF handle_s(float d_control2x, float d_control2Y, float d_endX, float d_endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                lastX() + d_control2x, lastY() + d_control2Y, lastX() + d_endX, lastY() + d_endY));
        return new PointF(lastX() + d_control2x, lastY() + d_control2Y);
    }

    @Override
    protected PointF handle_Q(float controlX, float controlY, float endX, float endY) {
        all.add(new Cubic(lastX(), lastY(), controlX, controlY,
                controlX, controlY, endX, endY));
        return new PointF(controlX, controlY);
    }

    @Override
    protected PointF handle_q(float d_controlX, float d_controlY, float d_endX, float d_endY) {
        all.add(new Cubic(lastX(), lastY(), lastX() + d_controlX, lastY() + d_controlY,
                lastX() + d_controlX, lastY() + d_controlY, lastX() + d_endX, lastY() + d_endY));
        return new PointF(lastX() + d_controlX, lastY() + d_controlY);
    }

    @Override
    protected PointF handle_T(float endX, float endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                nextControlX(), nextControlY(), endX, endY));
        return new PointF(nextControlX(), nextControlY());
    }

    @Override
    protected PointF handle_t(float d_endX, float d_endY) {
        all.add(new Cubic(lastX(), lastY(), nextControlX(), nextControlY(),
                nextControlX(), nextControlY(), lastX() + d_endX, lastY() + d_endY));
        return new PointF(nextControlX(), nextControlY());
    }

    public List<Cubic> getPathAsCubics() {
        return all;
    }
}
