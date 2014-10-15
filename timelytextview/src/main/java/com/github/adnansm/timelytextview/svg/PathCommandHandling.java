package com.github.adnansm.timelytextview.svg;

import android.graphics.Point;

import com.google.common.base.Preconditions;

public abstract class PathCommandHandling {
    private int lastX = -1;
    private int lastY = -1;
    private int firstX = -1;
    private int firstY = -1;
    private Point lastControlPoint;

    protected final int lastX() {
        return lastX;
    }

    protected final int lastY() {
        return lastY;
    }

    protected final int firstX() {
        return firstX;
    }

    protected final int firstY() {
        return firstY;
    }

    protected final int nextControlX() {
        return lastX() + (lastX() - lastControlPoint.x);
    }

    protected final int nextControlY() {
        return lastY() + (lastY() - lastControlPoint.y);
    }

    final void process_M(int endX, int endY) {
        lastControlPoint = handle_M(endX, endY);
        lastX = endX;
        lastY = endY;
        firstX = endX;
        firstY = endY;
    }

    final void process_m(int d_endX, int d_endY) {
        checkHasLast();
        lastControlPoint = handle_m(d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
        firstX = lastX + d_endX;
        firstY = lastY + d_endY;
    }

    final void process_L(int endX, int endY) {
        lastControlPoint = handle_L(endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_l(int d_endX, int d_endY) {
        checkHasLast();
        lastControlPoint = handle_l(d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_H(int endX) {
        lastControlPoint = handle_H(endX);
        lastX = endX;
    }

    final void process_h(int d_endX) {
        checkHasLast();
        lastControlPoint = handle_h(d_endX);
        lastX = lastX + d_endX;
    }

    final void process_V(int endY) {
        lastControlPoint = handle_V(endY);
        lastY = endY;
    }

    final void process_v(int d_endY) {
        checkHasLast();
        lastControlPoint = handle_v(d_endY);
        lastY = lastY + d_endY;
    }

    final void process_Z() {
        checkHasFirst();
        lastControlPoint = handle_Z();
        lastX = firstX;
        lastY = firstY;
    }

    final void process_C(int control1X, int control1Y, int control2X, int control2Y, int endX, int endY) {
        lastControlPoint = handle_C(control1X, control1Y, control2X, control2Y, endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_c(int d_control1X, int d_control1Y, int d_control2X, int d_control2Y, int d_endX, int d_endY) {
        checkHasLast();
        lastControlPoint = handle_c(d_control1X, d_control1Y, d_control2X, d_control2Y, d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_S(int control2X, int control2Y, int endX, int endY) {
        checkHasLastControlPoint();
        lastControlPoint = handle_S(control2X, control2Y, endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_s(int d_control2X, int d_control2Y, int d_endX, int d_endY) {
        checkHasLastControlPoint();
        checkHasLast();
        lastControlPoint = handle_s(d_control2X, d_control2Y, d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_Q(int controlX, int controlY, int endX, int endY) {
        lastControlPoint = handle_Q(controlX, controlY, endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_q(int d_controlX, int d_controlY, int d_endX, int d_endY) {
        checkHasLast();
        lastControlPoint = handle_q(d_controlX, d_controlY, d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_T(int endX, int endY) {
        checkHasLastControlPoint();
        lastControlPoint = handle_T(endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_t(int d_endX, int d_endY) {
        checkHasLastControlPoint();
        checkHasLast();
        lastControlPoint = handle_t(d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    private void checkHasLast() {
        Preconditions.checkArgument(lastX != -1);
        Preconditions.checkArgument(lastY != -1);
    }

    private void checkHasFirst() {
        Preconditions.checkArgument(firstX != -1);
        Preconditions.checkArgument(firstY != -1);
    }

    private void checkHasLastControlPoint() {
        Preconditions.checkNotNull(lastControlPoint);
    }

    // implementations

    protected abstract Point handle_M(int endX, int endY);

    protected abstract Point handle_m(int d_endX, int d_endY);

    protected abstract Point handle_L(int endX, int endY);

    protected abstract Point handle_l(int d_endX, int d_endY);

    protected abstract Point handle_H(int endX);

    protected abstract Point handle_h(int d_endX);

    protected abstract Point handle_V(int endY);

    protected abstract Point handle_v(int d_endY);

    protected abstract Point handle_Z();

    protected abstract Point handle_C(int control1X, int control1Y, int control2x, int control2Y, int endX, int endY);

    protected abstract Point handle_c(int d_control1X, int d_control1Y, int d_control2x, int d_control2Y, int d_endX, int d_endY);

    protected abstract Point handle_S(int control2x, int control2Y, int endX, int endY);

    protected abstract Point handle_s(int d_control2x, int d_control2Y, int d_endX, int d_endY);

    protected abstract Point handle_Q(int controlX, int controlY, int endX, int endY);

    protected abstract Point handle_q(int d_controlX, int d_controlY, int d_endX, int d_endY);

    protected abstract Point handle_T(int endX, int endY);

    protected abstract Point handle_t(int d_endX, int d_endY);
}
