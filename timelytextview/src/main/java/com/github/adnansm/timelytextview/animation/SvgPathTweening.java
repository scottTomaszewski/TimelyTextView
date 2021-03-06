package com.github.adnansm.timelytextview.animation;

import com.github.adnansm.timelytextview.model.Cubic;
import com.github.adnansm.timelytextview.svg.SvgPath;
import com.google.common.collect.Lists;
import com.nineoldandroids.animation.TypeEvaluator;

import java.util.List;

public class SvgPathTweening implements TypeEvaluator<SvgPath> {
    @Override
    public SvgPath evaluate(float fraction, SvgPath startPath, SvgPath endPath) {
        List<Cubic> starts = startPath.getPath();
        List<Cubic> ends = endPath.getPath();

        int max = Math.max(starts.size(), ends.size());
        fill(starts, max);
        fill(ends, max);

        List<Cubic> tweened = Lists.newArrayList();
        for (int i = 0; i < max; i++) {
            tweened.add(tween(fraction, starts.get(i), ends.get(i)));
        }
        return SvgPath.from(tweened, '?');
    }

    private void fill(List<Cubic> path, int toFill) {
        Cubic last = path.get(path.size() - 1);
        Cubic lastPoint = new Cubic(last.endX, last.endY, last.endX, last.endY, last.endX, last.endY, last.endX, last.endY);
        Cubic filler = path.size() != 0 ? lastPoint : new Cubic(0, 0, 0, 0, 0, 0, 0, 0);
        while (path.size() < toFill) {
            path.add(filler);
        }
    }

    private Cubic tween(float fraction, Cubic start, Cubic end) {
        double sX = tween(fraction, start.startX, end.startX);
        double sY = tween(fraction, start.startY, end.startY);
        double c1X = tween(fraction, start.control1X, end.control1X);
        double c1Y = tween(fraction, start.control1Y, end.control1Y);
        double c2X = tween(fraction, start.control2X, end.control2X);
        double c2Y = tween(fraction, start.control2Y, end.control2Y);
        double eX = tween(fraction, start.endX, end.endX);
        double eY = tween(fraction, start.endY, end.endY);
        return new Cubic(sX, sY, c1X, c1Y, c2X, c2Y, eX, eY);
    }

    private double tween(double fraction, double start, double end) {
        return start + fraction * (end - start);
    }
}
