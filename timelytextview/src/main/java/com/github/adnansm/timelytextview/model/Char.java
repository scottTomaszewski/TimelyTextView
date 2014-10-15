package com.github.adnansm.timelytextview.model;

import android.util.Log;

import com.github.adnansm.timelytextview.model.chars.A;
import com.github.adnansm.timelytextview.model.chars.Vertical;
import com.github.adnansm.timelytextview.svg.SvgPath;

public class Char {
    public static SvgPath pathOf(char c) {
        Log.d("foo", "foo");
        return c == '|' ? A.PATH : Vertical.commands;
    }
}
