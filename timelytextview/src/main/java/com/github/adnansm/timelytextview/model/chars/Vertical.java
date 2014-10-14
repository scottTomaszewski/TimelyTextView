package com.github.adnansm.timelytextview.model.chars;

import com.github.adnansm.timelytextview.SvgPath;
import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

public class Vertical {
    public static final SvgPath commands = new SvgPath(Lists.newArrayList(
            new Cubic(.5, .1, .6, .2, .6, .8, .5, .9)
    ));
}
