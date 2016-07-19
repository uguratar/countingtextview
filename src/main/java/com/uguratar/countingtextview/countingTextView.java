package com.uguratar.countingtextview;

import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

public class countingTextView extends TextView {

    private int startValue = 0;

    private int endValue = 0;

    private int duration = 1200;

    private String format = "%d";

    private TimeInterpolator interpolator;

    private ValueAnimator animator;


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

    /**
     * Initial method that sets default duration and default interpolator
     */
    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = null;
        try {
            a =
                    getContext().obtainStyledAttributes(
                            attrs, R.styleable.countingTextView, defStyle, 0);
            duration = a.getInt(
                    R.styleable.countingTextView_duration, 1200);
        } finally {
            if (a != null) {
                a.recycle();
            }
        }

        interpolator = new AccelerateDecelerateInterpolator();

    }

    /**
     * Animates from zero to current endValue, with duration
     *
     * @param duration animation duration
     */
    public void animateFromZerotoCurrentValue(Integer duration) {
        setDuration(duration);
        animateText(0, getEndValue());
    }

    /**
     * Animates from zero to current endValue
     */
    public void animateFromZerotoCurrentValue() {
        animateText(0, getEndValue());
    }

    /**
     * Animates from zero to given endValue with given duration
     *
     * @param endValue value animate to
     * @param duration value animate to
     */
    public void animateFromZero(Integer endValue, Integer duration) {
        setDuration(duration);
        animateText(0, endValue);
    }

    /**
     * Animates from zero to given endValue
     *
     * @param endValue value animate to
     */
    public void animateFromZero(Integer endValue) {
        animateText(0, endValue);
    }

    /**
     * Animates from startValue to endValue with given duration
     *
     * @param duration value animate to
     */
    public void animateText(Integer duration) {
        setDuration(duration);
        animateText(getStartValue(), getEndValue());
    }

    /**
     * Animates from startValue to endValue
     */
    public void animateText() {
        animateText(getStartValue(), getEndValue());
    }

    /**
     * Actual method that plays the animation with given values
     *
     * @param startValue value animate from
     * @param endValue   value animate to
     */
    public void animateText(Integer startValue, Integer endValue) {
        setStartValue(startValue);
        setEndValue(endValue);

        ValueAnimator animator = ValueAnimator.ofInt(startValue, endValue);
        animator.setInterpolator(getInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                setText(String.format(getFormat(), animation.getAnimatedValue()));
            }
        });

        animator.setEvaluator(new TypeEvaluator<Integer>() {
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return Math.round(startValue + (endValue - startValue) * fraction);
            }
        });

        animator.setDuration(getDuration());

        if (this.animator != null) {
            this.animator.cancel();
        }
        this.animator = animator;
        animator.start();
    }

    /**
     * Gets the startValue
     */
    public int getStartValue() {
        return startValue;
    }

    /**
     * Sets the startValue
     */
    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    /**
     * Gets the endValue
     */
    public int getEndValue() {
        return endValue;
    }

    /**
     * Sets the endValue
     *
     * @param endValue value animate to
     */
    public void setEndValue(int endValue) {
        this.endValue = endValue;
    }

    /**
     * Gets the duration
     *
     * @return animation duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration
     *
     * @param duration animation duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the format, default is %s
     *
     * @return current format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the string format of your choice See: http://developer.android.com/reference/java/util/Formatter.html
     *
     * @param format fromat to text displaying
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Gets the interpolator
     *
     * @return current time interpolator
     */
    public TimeInterpolator getInterpolator() {
        return interpolator;
    }

    /**
     * Sets the interpolator See: http://developer.android.com/reference/android/view/animation/Interpolator.html
     *
     * @param interpolator set interpolator for counting animation
     */
    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }

}
