package com.github.adnansm.timelytextview.model.chars;

import com.github.adnansm.timelytextview.SvgPath;
import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

public class Vertical {
    // 1 (one)
    //M199 1165
    // l397 297
    // h86
    // v-1462
    // h-98
    // v1065
    // q0 145 12 301
    // q-15 -15 -31 -29
    // t-309 -243
    // z
    private static final double max = 2048;
    public static final SvgPath commands = new SvgPath(Lists.newArrayList(
            new Cubic(0, 0,
                    0, 0,
                    199 / max, 1165 / max,
                    199 / max, 1165 / max),
            new Cubic(199 / max, 1165 / max,
                    199 / max, 1165 / max,
                    (199 + 397) / max, (1165f + 297) / max,
                    (199 + 397) / max, (1165f + 297) / max),
            new Cubic((199 + 397) / max, (1165 + 297) / max,
                    (199 + 397) / max, (1165 + 297) / max,
                    (199 + 397 + 86) / max, (1165 + 297) / max,
                    (199 + 397 + 86) / max, (1165 + 297) / max),
            new Cubic((199 + 397 + 86) / max, (1165 + 297) / max,
                    (199 + 397 + 86) / max, (1165 + 297) / max,
                    (199 + 397 + 86) / max, (1165 + 297 - 1462) / max,
                    (199 + 397 + 86) / max, (1165 + 297 - 1462) / max)
            ));
}
