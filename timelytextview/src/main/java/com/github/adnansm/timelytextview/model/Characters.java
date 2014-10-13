package com.github.adnansm.timelytextview.model;

import com.github.adnansm.timelytextview.model.number.Eight;
import com.github.adnansm.timelytextview.model.number.Five;
import com.github.adnansm.timelytextview.model.number.Four;
import com.github.adnansm.timelytextview.model.number.Nine;
import com.github.adnansm.timelytextview.model.number.One;
import com.github.adnansm.timelytextview.model.number.Seven;
import com.github.adnansm.timelytextview.model.number.Six;
import com.github.adnansm.timelytextview.model.number.Three;
import com.github.adnansm.timelytextview.model.number.Two;
import com.github.adnansm.timelytextview.model.number.Zero;

import java.security.InvalidParameterException;

public class Characters {
    public static float[][] getControlPointsFor(char start) {
        switch (start) {
            case 0:
                return Zero.getInstance().getControlPoints();
            case 1:
                return One.getInstance().getControlPoints();
            case 2:
                return Two.getInstance().getControlPoints();
            case 3:
                return Three.getInstance().getControlPoints();
            case 4:
                return Four.getInstance().getControlPoints();
            case 5:
                return Five.getInstance().getControlPoints();
            case 6:
                return Six.getInstance().getControlPoints();
            case 7:
                return Seven.getInstance().getControlPoints();
            case 8:
                return Eight.getInstance().getControlPoints();
            case 9:
                return Nine.getInstance().getControlPoints();
            default:
                throw new InvalidParameterException("Unsupported character requested");
        }
    }

    public static char charAt(int position) {
        if (position == -1) {
            return 0;
        }
        return position < 10 ? (char) position : 'l';
    }
}
