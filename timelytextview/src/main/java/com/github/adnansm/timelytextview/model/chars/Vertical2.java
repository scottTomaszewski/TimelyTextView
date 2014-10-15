package com.github.adnansm.timelytextview.model.chars;

import com.github.adnansm.timelytextview.svg.SvgPath;
import com.github.adnansm.timelytextview.model.Cubic;
import com.google.common.collect.Lists;

import java.util.ArrayList;

public class Vertical2 {
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
    private static final double max = 1600;
    public static final SvgPath commands = path();

    private static SvgPath path() {
        ArrayList<Cubic> cubics = Lists.newArrayList(
                new Cubic(199, 1165,
                        199, 1165,
                        (199 + 397), (1165f + 297),
                        (199 + 397), (1165f + 297)),
                new Cubic((199 + 397), (1165 + 297),
                        (199 + 397), (1165 + 297),
                        (199 + 397 + 86), (1165 + 297),
                        (199 + 397 + 86), (1165 + 297)),
                new Cubic((199 + 397 + 86), (1165 + 297),
                        (199 + 397 + 86), (1165 + 297),
                        (199 + 397 + 86), (1165 + 297 - 1462),
                        (199 + 397 + 86), (1165 + 297 - 1462)),
                new Cubic((199 + 397 + 86), (1165 + 297 - 1462),
                        (199 + 397 + 86), (1165 + 297 - 1462),
                        (199 + 397 + 86 - 98), (1165 + 297 - 1462),
                        (199 + 397 + 86 - 98), (1165 + 297 - 1462)),
                new Cubic((199 + 397 + 86 - 98), (1165 + 297 - 1462),
                        (199 + 397 + 86 - 98), (1165 + 297 - 1462),
                        (199 + 397 + 86 - 98), (1165 + 297 - 1462 + 1065),
                        (199 + 397 + 86 - 98), (1165 + 297 - 1462 + 1065)),
                new Cubic(584, 1065,
                        584, 1065 + 145,
                        584, 1065 + 145,
                        584 + 12, 1065 + 301),
                new Cubic(596, 1366,
                        596 - 15, 1366 - 15,
                        596 - 15, 1366 - 15,
                        596 - 31, 1366 - 29),
                new Cubic(565, 1337,
                        565 - 15, 1337 - 15,
                        565 - 15, 1337 - 15,
                        565 - 309, 1337 - 243),
                new Cubic(256, 1094,
                        256, 1094,
                        199, 1165,
                        199, 1165)
        );

        ArrayList<Cubic> normalized = Lists.newArrayList();
        for (Cubic c : cubics) {
            normalized.add(c.normalizeWithMax(max).flipAlongVertical());
        }
        return new SvgPath(normalized);
    }
}
