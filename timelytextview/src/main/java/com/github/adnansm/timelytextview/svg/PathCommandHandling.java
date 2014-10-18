package com.github.adnansm.timelytextview.svg;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

import com.google.common.base.Preconditions;

public abstract class PathCommandHandling {
    private float lastX = -1;
    private float lastY = -1;
    private float firstX = -1;
    private float firstY = -1;
    private PointF lastControlPoint;

    protected final float lastX() {
        return lastX;
    }

    protected final float lastY() {
        return lastY;
    }

    protected final float firstX() {
        return firstX;
    }

    protected final float firstY() {
        return firstY;
    }

    protected final float nextControlX() {
        return lastX() + (lastX() - lastControlPoint.x);
    }

    protected final float nextControlY() {
        return lastY() + (lastY() - lastControlPoint.y);
    }

    final void process_M(float endX, float endY) {
        lastControlPoint = handle_M(endX, endY);
        lastX = endX;
        lastY = endY;
        firstX = endX;
        firstY = endY;
    }

    final void process_m(float d_endX, float d_endY) {
        checkHasLast();
        lastControlPoint = handle_m(d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
        firstX = lastX + d_endX;
        firstY = lastY + d_endY;
    }

    final void process_L(float endX, float endY) {
        lastControlPoint = handle_L(endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_l(float d_endX, float d_endY) {
        checkHasLast();
        lastControlPoint = handle_l(d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_H(float endX) {
        lastControlPoint = handle_H(endX);
        lastX = endX;
    }

    final void process_h(float d_endX) {
        checkHasLast();
        lastControlPoint = handle_h(d_endX);
        lastX = lastX + d_endX;
    }

    final void process_V(float endY) {
        lastControlPoint = handle_V(endY);
        lastY = endY;
    }

    final void process_v(float d_endY) {
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

    final void process_C(float control1X, float control1Y, float control2X, float control2Y, float endX, float endY) {
        Log.d("HandleParent", control1X + " " + control1Y + " " + control2X + " " + control2Y + " " + endX + " " + endY);
        lastControlPoint = handle_C(control1X, control1Y, control2X, control2Y, endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_c(float d_control1X, float d_control1Y, float d_control2X, float d_control2Y, float d_endX, float d_endY) {
        checkHasLast();
        lastControlPoint = handle_c(d_control1X, d_control1Y, d_control2X, d_control2Y, d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_S(float control2X, float control2Y, float endX, float endY) {
        checkHasLastControlPoint();
        lastControlPoint = handle_S(control2X, control2Y, endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_s(float d_control2X, float d_control2Y, float d_endX, float d_endY) {
        checkHasLastControlPoint();
        checkHasLast();
        lastControlPoint = handle_s(d_control2X, d_control2Y, d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_Q(float controlX, float controlY, float endX, float endY) {
        lastControlPoint = handle_Q(controlX, controlY, endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_q(float d_controlX, float d_controlY, float d_endX, float d_endY) {
        checkHasLast();
        lastControlPoint = handle_q(d_controlX, d_controlY, d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_T(float endX, float endY) {
        checkHasLastControlPoint();
        lastControlPoint = handle_T(endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_t(float d_endX, float d_endY) {
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

    protected abstract PointF handle_M(float endX, float endY);

    protected abstract PointF handle_m(float d_endX, float d_endY);

    protected abstract PointF handle_L(float endX, float endY);

    protected abstract PointF handle_l(float d_endX, float d_endY);

    protected abstract PointF handle_H(float endX);

    protected abstract PointF handle_h(float d_endX);

    protected abstract PointF handle_V(float endY);

    protected abstract PointF handle_v(float d_endY);

    protected abstract PointF handle_Z();

    protected abstract PointF handle_C(float control1X, float control1Y, float control2x, float control2Y, float endX, float endY);

    protected abstract PointF handle_c(float d_control1X, float d_control1Y, float d_control2x, float d_control2Y, float d_endX, float d_endY);

    protected abstract PointF handle_S(float control2x, float control2Y, float endX, float endY);

    protected abstract PointF handle_s(float d_control2x, float d_control2Y, float d_endX, float d_endY);

    protected abstract PointF handle_Q(float controlX, float controlY, float endX, float endY);

    protected abstract PointF handle_q(float d_controlX, float d_controlY, float d_endX, float d_endY);

    protected abstract PointF handle_T(float endX, float endY);

    protected abstract PointF handle_t(float d_endX, float d_endY);
}
