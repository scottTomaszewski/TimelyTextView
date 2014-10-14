package com.github.adnansm.timelytextview.model.chars;

import com.github.adnansm.timelytextview.SvgPath;
import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

public class Vertical {
    public static final SvgPath commands = new SvgPath(Lists.newArrayList(
            new Cubic(.5, .1, .7, .2, .7, .8, .5, .9)
    ));
}
