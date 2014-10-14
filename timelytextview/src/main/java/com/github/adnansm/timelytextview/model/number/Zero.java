package com.github.adnansm.timelytextview.model.number;

import android.graphics.PointF;

import com.github.adnansm.timelytextview.model.core.Figure;

import org.apache.batik.parser.DefaultPathHandler;
import org.apache.batik.parser.DefaultPointsHandler;
import org.apache.batik.parser.ParseException;
import org.apache.batik.parser.PathArrayProducer;
import org.apache.batik.parser.PathParser;
import org.apache.batik.parser.PointsHandler;
import org.apache.batik.parser.PointsParser;

import java.util.LinkedList;
import java.util.List;

public class Zero extends Figure {
//    d="M115 735
// q0 382 115.5 566
// t351.5 184
// q231 0 352 -190.5
// t121 -559.5
// q0 -385 -117.5 -570
// t-355.5 -185
// q-229 0 -348 190.5
// t-119 564.5z
//
// M223 735
// q0 -340 89 -502.5
// t270 -162.5
// q189 0 275.5 168
// t86.5 497
// q0 324 -86.5 492
// t-275.5 168t-274 -168
// t-85 -492z


    private static final float[][] POINTS = {
        {0.056152344f, 0.358886719f},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
    };

    private static final float[][] ORIGINAL_POINTS = {
            {0.24585635359116f, 0.552486187845304f},
            {0.24585635359116f, 0.331491712707182f},
            {0.370165745856354f, 0.0994475138121547f},
            {0.552486187845304f, 0.0994475138121547f},
            {0.734806629834254f, 0.0994475138121547f},
            {0.861878453038674f, 0.331491712707182f},
            {0.861878453038674f, 0.552486187845304f},
            {0.861878453038674f, 0.773480662983425f},
            {0.734806629834254f, 0.994475138121547f},
            {0.552486187845304f, 0.994475138121547f},
            {0.370165745856354f, 0.994475138121547f},
            {0.24585635359116f, 0.773480662983425f},
            {0.24585635359116f, 0.552486187845304f}
    };

    private static Zero INSTANCE = new Zero();

    protected Zero() {
        super(POINTS);
    }

    public static Zero getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
//        System.out.println(extractPoints("M115 735q0 382 115.5 566t351.5 184q231 0 352 -190.5t121 -559.5q0 -385 -117.5 -570t-355.5 -185q-229 0 -348 190.5t-119 564.5zM223 735q0 -340 89 -502.5t270 -162.5q189 0 275.5 168t86.5 497q0 324 -86.5 492t-275.5 168t-274 -168t-85 -492z"));
        foo("M115 735q0 382 115.5 566t351.5 184q231 0 352 -190.5t121 -559.5q0 -385 -117.5 -570t-355.5 -185q-229 0 -348 190.5t-119 564.5zM223 735q0 -340 89 -502.5t270 -162.5q189 0 275.5 168t86.5 497q0 324 -86.5 492t-275.5 168t-274 -168t-85 -492z");
    }

    private static void foo(String toParse) {
        PathParser pathParser = new PathParser();
        PathArrayProducer producer = new PathArrayProducer();
        pathParser.setPathHandler(producer);
        pathParser.parse(toParse);
        short[] pathParameters = producer.getPathCommands();
        for (int i = 0; i < pathParameters.length; i++) {
            System.out.println(pathParameters[i]);
        }
    }

    public static List<PointF> extractPoints(String s) throws ParseException {
        final LinkedList<PointF> points = new LinkedList();
        PointsParser pp = new PointsParser();
        PointsHandler ph = new DefaultPointsHandler() {
            public void point(float x, float y) throws ParseException {
                System.out.println(x + " " + y);
                PointF p = new PointF(x, y);
                points.add(p);
            }
        };
        pp.setPointsHandler(ph);
        pp.parse(s);
        return points;
    }
}