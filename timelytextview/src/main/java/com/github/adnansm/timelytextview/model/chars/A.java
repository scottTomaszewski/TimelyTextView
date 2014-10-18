package com.github.adnansm.timelytextview.model.chars;

import com.github.adnansm.timelytextview.svg.SvgPath;

public class A {
    // 602, 1462
    private static final String ORIGINAL = "M207 0v1462h395C952 1462 952 1462 1134.5 1279C1317 1096 1317 1096 1317 745z";
//    private static final String ORIGINAL = "M207 0v1462h395q350 0 532.5 -183t182.5 -534z";
//    private static final String ORIGINAL = "M0 0l588 1468h65l576 -1468h-115l-203 516h-594l-204 -516h-113zM354 608h523l-199 527q-25 62 -60 172q-27 -96 -59 -174z";
    public static final SvgPath PATH = SvgPath.from(ORIGINAL, 1024, 'A');
}
