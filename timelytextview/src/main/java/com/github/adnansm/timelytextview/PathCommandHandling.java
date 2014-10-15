package com.github.adnansm.timelytextview;

public interface PathCommandHandling {
    void handle_M(int startX, int startY);

    void handle_m(int d_startX, int d_startY);

    void handle_L(int endX, int endY);

    void handle_l(int d_endX, int d_endY);

    void handle_H(int endX);

    void handle_h(int d_endX);

    void handle_V(int endY);

    void handle_v(int d_endY);

    void handle_Z();

    void handle_C(int control1X, int control1Y, int control2x, int control2Y, int endX, int endY);

    void handle_c(int d_control1X, int d_control1Y, int d_control2x, int d_control2Y, int d_endX, int d_endY);

    void handle_S(int control2x, int control2Y, int endX, int endY);

    void handle_s(int d_control2x, int d_control2Y, int d_endX, int d_endY);

    void handle_Q(int controlX, int controlY, int endX, int endY);

    void handle_q(int d_controlX, int d_controlY, int d_endX, int d_endY);

    void handle_T(int endX, int endY);

    void handle_t(int d_endX, int d_endY);
}
