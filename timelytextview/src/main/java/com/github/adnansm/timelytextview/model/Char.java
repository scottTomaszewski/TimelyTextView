package com.github.adnansm.timelytextview.model;

import com.github.adnansm.timelytextview.svg.SvgPath;
import com.github.adnansm.timelytextview.model.chars.Vertical;
import com.github.adnansm.timelytextview.model.chars.Vertical2;

public class Char {
    public static SvgPath pathOf(char c) {
        return c == '|' ? Vertical2.commands : Vertical.commands;
    }
}
