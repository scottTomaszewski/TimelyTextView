package com.github.adnansm.timelytextview.svg;

import android.util.Log;

import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public abstract class SvgPath {
    public static SvgPath from(final List<Cubic> path, final char symbol) {
        return new SvgPath() {
            @Override
            public List<Cubic> getPath() {
                return path;
            }

            @Override
            public char symbol() {
                return symbol;
            }
        };
    }

    public static SvgPath from(final String pathDescriptions, final double unitsPerEm, final char symbol) {
        return new SvgPath() {
            @Override
            public List<Cubic> getPath() {
                PathCommandToCubicHandling toCubic = new PathCommandToCubicHandling();
                Log.d("SvgPath", pathDescriptions);
                new PathParsing(pathDescriptions).parseUsing(toCubic);
                ArrayList<Cubic> normalized = Lists.newArrayList();
                for (Cubic c : toCubic.getPathAsCubics()) {
                    Log.d("SvgPath", c.toString());
                    normalized.add(c.normalizeWithMax(unitsPerEm*2));
                }
                return normalized;
            }

            @Override
            public char symbol() {
                return symbol;
            }
        };
    }

    public abstract List<Cubic> getPath();

    public abstract char symbol();
}
