package com.github.adnansm.timelytextview.model.chars;

import com.github.adnansm.timelytextview.svg.SvgPath;
import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

public class Horizontal {
    public static final SvgPath commands = new SvgPath(Lists.newArrayList(
            new Cubic(.1, .5, .2, .6, .8, .6, .9, .5),
            new Cubic(.5, .1, .6, .2, .6, .8, .5, .9    )
    ));
}
