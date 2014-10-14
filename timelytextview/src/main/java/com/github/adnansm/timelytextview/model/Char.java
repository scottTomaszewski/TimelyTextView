package com.github.adnansm.timelytextview.model;

import com.github.adnansm.timelytextview.SvgPath;
import com.github.adnansm.timelytextview.model.chars.Horizontal;
import com.github.adnansm.timelytextview.model.chars.Vertical;

public class Char {
    public static SvgPath pathOf(char c) {
        return c == '|' ? Horizontal.commands : Vertical.commands;
    }
}
