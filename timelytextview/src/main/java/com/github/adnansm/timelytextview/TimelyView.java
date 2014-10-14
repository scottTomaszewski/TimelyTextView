package com.github.adnansm.timelytextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.github.adnansm.timelytextview.animation.CubicTweening;
import com.github.adnansm.timelytextview.model.Char;
import com.github.adnansm.timelytextview.model.Cubic;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.util.Property;

import java.util.List;

public class TimelyView extends View {
    private static final float RATIO = 1f;

    private static final Property<TimelyView, SvgPath> PATH_POINTS =
            new Property<TimelyView, SvgPath>(SvgPath.class, "pathPoints") {
                @Override
                public SvgPath get(TimelyView object) {
                    return object.getPath();
                }

                @Override
                public void set(TimelyView object, SvgPath value) {
                    object.setPath(value);
                }
            };

    private Paint mPaint = null;
    private Path mPath = null;
    private SvgPath path = null;

    public TimelyView(Context context) {
        super(context);
        init();
    }

    public TimelyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimelyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private SvgPath getPath() {
        return path;
    }

    private void setPath(SvgPath value) {
        this.path = value;
        invalidate();
    }

    public ObjectAnimator animate(char start, char end) {
        SvgPath startPoints = Char.pathOf(start);
        SvgPath endPoints = Char.pathOf(end);

        return ObjectAnimator.ofObject(this, PATH_POINTS, new CubicTweening(), startPoints, endPoints);
    }

    public ObjectAnimator animate(char end) {
        return animate('|', end);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (path == null) return;
        List<Cubic> controlPoints = path.get();

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        float minDimen = height > width ? width : height;
        Adjustment adjust = new Adjustment(minDimen);

        mPath.reset();
        mPath.moveTo(adjust.d(controlPoints.get(0).startX), adjust.d(controlPoints.get(0).startY));

        for (Cubic c : controlPoints) {
            mPath.cubicTo(
                    adjust.d(c.control1X), adjust.d(c.control1Y),
                    adjust.d(c.control2X), adjust.d(c.control2Y),
                    adjust.d(c.endX), adjust.d(c.endY));
        }
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
        int heightWithoutPadding = height - getPaddingTop() - getPaddingBottom();

        int maxWidth = (int) (heightWithoutPadding * RATIO);
        int maxHeight = (int) (widthWithoutPadding / RATIO);

        if (widthWithoutPadding > maxWidth) {
            width = maxWidth + getPaddingLeft() + getPaddingRight();
        } else {
            height = maxHeight + getPaddingTop() + getPaddingBottom();
        }

        setMeasuredDimension(width, height);
    }

    private void init() {
        // A new paint with the style as stroke.
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5.0f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
    }

    private static final class Adjustment {
        private final float minDimension;

        private Adjustment(float minDimension) {
            this.minDimension = minDimension;
        }

        private float d(double toAdjust) {
            return (float) (minDimension * toAdjust);
        }
    }
}
