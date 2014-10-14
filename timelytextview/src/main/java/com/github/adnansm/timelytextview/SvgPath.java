package com.github.adnansm.timelytextview;

import com.github.adnansm.timelytextview.model.Cubic;

import java.util.List;

public class SvgPath {
    private final List<Cubic> path;

    public SvgPath(List<Cubic> path) {
        this.path = path;
    }

    public List<Cubic> get() {
        return path;
    }
}
