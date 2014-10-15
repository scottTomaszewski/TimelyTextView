package com.github.adnansm.timelytextview.svg;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class PathParsing {
    private static final Set<Character> COMMANDS = Sets.newHashSet(
            'M', 'm', 'L', 'l', 'H', 'h', 'V', 'v', 'Z', 'z', 'C', 'c', 'S', 's', 'Q', 'q', 'T', 't');
    private static final Set<Character> SUPPORTED = Sets.newHashSet(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', ',', '-');

    private final String source;
    private final double unitsPerEm;

    public PathParsing(String pathDescriptions, double unitsPerEm) {
        this.source = pathDescriptions;
        this.unitsPerEm = unitsPerEm;
    }

    public void parseUsing(PathCommandToCubicHandling toCubic) {
        List<String> commands = breakDown(source);
        for (String command : commands) {
            switch (command.charAt(0)) {
                case 'M':
                    toCubic.process_M();
                    break;
                case 'm':
                    toCubic.process_m();
                    break;
                case 'L':
                    toCubic.process_L();
                    break;
                case 'l':
                    toCubic.process_l();
                    break;
                case 'H':
                    toCubic.process_H();
                    break;
                case 'h':
                    toCubic.process_h();
                    break;
                case 'V':
                    toCubic.process_V();
                    break;
                case 'v':
                    toCubic.process_v();
                    break;
                case 'z':
                case 'Z':
                    toCubic.process_Z();
                    break;
                case 'C':
                    toCubic.process_C();
                    break;
                case 'c':
                    toCubic.process_c();
                    break;
                case 'S':
                    toCubic.process_S();
                    break;
                case 's':
                    toCubic.process_s();
                    break;
                case 'Q':
                    toCubic.process_Q();
                    break;
                case 'q':
                    toCubic.process_q();
                    break;
                case 'T':
                    toCubic.process_T();
                    break;
                case 't':
                    toCubic.process_t();
                    break;
            }
        }
    }

    private List<String> breakDown(String source) {
        List<String> commands = Lists.newArrayList();
        String tail = source;
        while (!tail.isEmpty()) {
            if (!COMMANDS.contains(tail.charAt(0))) {
                throw new IllegalArgumentException("Unsupported command: " + tail.charAt(0));
            }
            int nextCommandIndex = indexOfNextCommandOrNegative(tail);
            if (!(nextCommandIndex < 0)) {
                // no more commands
                break;
            }
            String commandWithArgs = tail.substring(0, nextCommandIndex - 1);
            commands.add(commandWithArgs);
            tail = tail.substring(nextCommandIndex);
        }
        return commands;
    }

    private int indexOfNextCommandOrNegative(String tail) {
        for (int i = 0; i < tail.length(); i++) {
            char maybe = tail.charAt(i);
            if (COMMANDS.contains(maybe)) {
                return i;
            } else if (!SUPPORTED.contains(maybe)) {
                throw new IllegalArgumentException("Unsupported character: " + maybe);
            }
        }
        return -1;
    }
}
