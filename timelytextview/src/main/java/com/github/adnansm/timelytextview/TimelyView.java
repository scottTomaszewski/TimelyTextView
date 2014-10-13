package com.github.adnansm.timelytextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.github.adnansm.timelytextview.animation.TimelyEvaluator;
import com.github.adnansm.timelytextview.model.Characters;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.util.Property;

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
        SvgPath startPoints = Characters.getControlPointsFor(start);
        SvgPath endPoints = Characters.getControlPointsFor(end);

        return ObjectAnimator.ofObject(this, PATH_POINTS, new TimelyEvaluator(), startPoints, endPoints);
    }

    public ObjectAnimator animate(char end) {
        SvgPath startPoints = Characters.getControlPointsFor('0');
        SvgPath endPoints = Characters.getControlPointsFor(end);

        return ObjectAnimator.ofObject(this, PATH_POINTS, new TimelyEvaluator(), startPoints, endPoints);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (path == null) return;
        float[][] controlPoints = path.get();

        int length = controlPoints.length;

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        float minDimen = height > width ? width : height;

        mPath.reset();
        mPath.moveTo(minDimen * controlPoints[0][0], minDimen * controlPoints[0][1]);
        for (int i = 1; i < length; i += 3) {
            mPath.cubicTo(minDimen * controlPoints[i][0], minDimen * controlPoints[i][1],
                    minDimen * controlPoints[i + 1][0], minDimen * controlPoints[i + 1][1],
                    minDimen * controlPoints[i + 2][0], minDimen * controlPoints[i + 2][1]);
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
}
