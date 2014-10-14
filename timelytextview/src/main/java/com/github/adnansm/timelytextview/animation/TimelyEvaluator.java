package com.github.adnansm.timelytextview.animation;

import android.graphics.Path;

import com.github.adnansm.timelytextview.SvgPath;
import com.nineoldandroids.animation.TypeEvaluator;

public class TimelyEvaluator implements TypeEvaluator<SvgPath> {
    private float[][] _cachedPoints = null;

    @Override
    public SvgPath evaluate(float fraction, SvgPath start, SvgPath end) {

        float[][] startValue = start.get();
        float[][] endValue = end.get();

        int pointsCount = startValue.length;
        initCache(pointsCount);

        new Path();


        for (int i = 0; i < pointsCount; i++) {
            _cachedPoints[i][0] = startValue[i][0] + fraction * (endValue[i][0] - startValue[i][0]);
            _cachedPoints[i][1] = startValue[i][1] + fraction * (endValue[i][1] - startValue[i][1]);
        }

        return new SvgPath(_cachedPoints);
    }

    private void initCache(int pointsCount) {
        if (_cachedPoints == null || _cachedPoints.length != pointsCount) {
            _cachedPoints = new float[pointsCount][2];
        }
    }
}
