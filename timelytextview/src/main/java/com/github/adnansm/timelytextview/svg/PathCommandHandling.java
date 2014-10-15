package com.github.adnansm.timelytextview.svg;

public abstract class PathCommandHandling {
    private int lastX;
    private int lastY;
    private int firstX;
    private int firstY;

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

    final void process_M(int endX, int endY) {
        handle_M(endX, endY);
        lastX = endX;
        lastY = endY;
        firstX = endX;
        firstY = endY;
    }

    final void process_m(int d_endX, int d_endY) {
        handle_m(d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_L(int endX, int endY) {
        handle_L(endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_l(int d_endX, int d_endY) {
        handle_l(d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_H(int endX) {
        handle_H(endX);
        lastX = endX;
    }

    final void process_h(int d_endX) {
        handle_h(d_endX);
        lastX = lastX + d_endX;
    }

    final void process_V(int endY) {
        handle_V(endY);
        lastY = endY;
    }

    final void process_v(int d_endY) {
        handle_v(d_endY);
        lastY = lastY + d_endY;
    }

    final void process_Z() {
        handle_Z();
        lastX = firstX;
        lastY = firstY;
    }

    final void process_C(int control1X, int control1Y, int control2x, int control2Y, int endX, int endY) {
        handle_C(control1X, control1Y, control2x, control2Y, endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_c(int d_control1X, int d_control1Y, int d_control2x, int d_control2Y, int d_endX, int d_endY) {
        handle_c(d_control1X, d_control1Y, d_control2x, d_control2Y, d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_S(int control2x, int control2Y, int endX, int endY) {
        handle_S(control2x, control2Y, endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_s(int d_control2x, int d_control2Y, int d_endX, int d_endY) {
        handle_s(d_control2x, d_control2Y, d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_Q(int controlX, int controlY, int endX, int endY) {
        handle_Q(controlX, controlY, endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_q(int d_controlX, int d_controlY, int d_endX, int d_endY) {
        handle_q(d_controlX, d_controlY, d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    final void process_T(int endX, int endY) {
        handle_T(endX, endY);
        lastX = endX;
        lastY = endY;
    }

    final void process_t(int d_endX, int d_endY) {
        handle_t(d_endX, d_endY);
        lastX = lastX + d_endX;
        lastY = lastY + d_endY;
    }

    // implementations

    protected abstract void handle_M(int endX, int endY);

    protected abstract void handle_m(int d_endX, int d_endY);

    protected abstract void handle_L(int endX, int endY);

    protected abstract void handle_l(int d_endX, int d_endY);

    protected abstract void handle_H(int endX);

    protected abstract void handle_h(int d_endX);

    protected abstract void handle_V(int endY);

    protected abstract void handle_v(int d_endY);

    protected abstract void handle_Z();

    protected abstract void handle_C(int control1X, int control1Y, int control2x, int control2Y, int endX, int endY);

    protected abstract void handle_c(int d_control1X, int d_control1Y, int d_control2x, int d_control2Y, int d_endX, int d_endY);

    protected abstract void handle_S(int control2x, int control2Y, int endX, int endY);

    protected abstract void handle_s(int d_control2x, int d_control2Y, int d_endX, int d_endY);

    protected abstract void handle_Q(int controlX, int controlY, int endX, int endY);

    protected abstract void handle_q(int d_controlX, int d_controlY, int d_endX, int d_endY);

    protected abstract void handle_T(int endX, int endY);

    protected abstract void handle_t(int d_endX, int d_endY);
}
