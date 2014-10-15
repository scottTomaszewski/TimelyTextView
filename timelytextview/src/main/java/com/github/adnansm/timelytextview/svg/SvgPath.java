package com.github.adnansm.timelytextview.svg;

import com.github.adnansm.timelytextview.model.Cubic;

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
                new PathParsing(pathDescriptions, unitsPerEm).parseUsing(toCubic);
                return toCubic.getPathAsCubics();
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
