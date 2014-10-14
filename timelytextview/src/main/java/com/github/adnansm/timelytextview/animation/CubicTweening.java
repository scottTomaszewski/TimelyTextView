package com.github.adnansm.timelytextview.animation;

import com.github.adnansm.timelytextview.SvgPath;
import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;
import com.nineoldandroids.animation.TypeEvaluator;

import java.util.List;

public class CubicTweening implements TypeEvaluator<SvgPath> {
    @Override
    public SvgPath evaluate(float fraction, SvgPath startPath, SvgPath endPath) {
        List<Cubic> starts = startPath.get();
        List<Cubic> ends = endPath.get();

        List<Cubic> tweened = Lists.newArrayList();
        for(int i = 0; i < starts.size(); i++) {
            tweened.add(tween(fraction, starts.get(i), ends.get(i)));
        }
        return new SvgPath(tweened);
    }

    private Cubic tween(float fraction, Cubic start, Cubic end) {
        double sX = start.startX + fraction * end.startX;
        double sY = start.startY + fraction * end.startY;
        double c1X = start.control1X + fraction * end.control1X;
        double c1Y = start.control1Y + fraction * end.control1Y;
        double c2X = start.control2X + fraction * end.control2X;
        double c2Y = start.control2Y + fraction * end.control2Y;
        double eX = start.endX+ fraction * end.endX;
        double eY = start.endY + fraction * end.endY;
        return new Cubic(sX, sY, c1X, c1Y, c2X, c2Y, eX, eY);
    }
}
