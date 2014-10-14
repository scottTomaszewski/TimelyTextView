package com.github.adnansm.timelytextview.model.chars;

import com.github.adnansm.timelytextview.SvgPath;
import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

import java.util.List;

public class Horizontal {
    public static final SvgPath commands = new SvgPath(Lists.newArrayList(
            new Cubic(.1, .5, .2, .6, .8, .6, .9, .5)
    ));
}
