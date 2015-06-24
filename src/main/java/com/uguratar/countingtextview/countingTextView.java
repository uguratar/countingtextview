package com.uguratar.countingtextview;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class countingTextView extends TextView {

    private int startValue = 0;

    private int endValue = 0;

    private int duration = 1200;

    private String format = "%d";


    public countingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (isInEditMode()) {
            setText(getText());
        }
        init(attrs, defStyle);
    }

    public countingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) {
            setText(getText());
        }
        init(attrs, 0);
    }

    public countingTextView(Context context) {
        this(context, null);
        init(null, 0);
    }


    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.countingTextView, defStyle, 0);

        duration = a.getInt(
                R.styleable.countingTextView_duration, 1200);

    }

    public void animateFromZerotoCurrentValue(Integer duration) {
        setDuration(duration);
        animateText(0, getEndValue());
    }

    public void animateFromZerotoCurrentValue() {
        animateText(0, getEndValue());
    }

    public void animateFromZero(Integer endValue, Integer duration) {
        setDuration(duration);
        animateText(0, endValue);
    }

    public void animateFromZero(Integer endValue) {
        animateText(0, endValue);
    }

    public void animateText(Integer duration) {
        setDuration(duration);
        animateText(getStartValue(), getEndValue());
    }

    public void animateText() {
        animateText(getStartValue(), getEndValue());
    }

    public void animateText(Integer startValue, Integer endValue) {
        setStartValue(startValue);
        setEndValue(endValue);

        ValueAnimator animator = ValueAnimator.ofInt(startValue, endValue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setText(String.format(getFormat(), animation.getAnimatedValue()));
            }
        });

        animator.setEvaluator(new TypeEvaluator<Integer>() {
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return Math.round(endValue * fraction);
            }
        });

        animator.setDuration(getDuration());
        animator.start();
    }


    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public int getEndValue() {
        return endValue;
    }

    public void setEndValue(int endValue) {
        this.endValue = endValue;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
