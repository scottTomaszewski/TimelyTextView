package com.github.adnansm.timelytextview.svg;

import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

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
            List<Cubic> cached = null;

            @Override
            public List<Cubic> getPath() {
                if (cached == null) {
                    PathCommandToCubicHandling toCubic = new PathCommandToCubicHandling();
                    new PathParsing(pathDescriptions).parseUsing(toCubic);
                    cached = Lists.newArrayList();
                    for (Cubic c : toCubic.getPathAsCubics()) {
                        cached.add(c.normalizeWithMax(unitsPerEm * 2).flipAlongHorizontal());
                    }
                }
                return cached;
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
