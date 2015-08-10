# Animated Counting TextView
Animated counting textview component for Android. With countingtextview you can enumerate a textview with given start and end values. You can play with duration of the animation and the interpolator which is playing the counting animation. 

![Image](http://i.imgur.com/noXwKG7.gif)

#How to?

After importing library to your project add a countingtextview to your XML layout.

####XML definition
    <com.uguratar.countingtextview.countingTextView
        android:id="@+id/countingText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textIsSelectable="false"
        android:textSize="22sp"
        android:textStyle="bold"/>
        
After adding your countingtextview you can use the methods to animate your textview with the values of your choice.

    private countingTextView points;


    points = (countingTextView) findViewById(R.id.countingText);
    //Animate from 0 to 250
    points.animateText(0,250);
    //Animate from 0 to 1233 in 2 seconds
    points.animateFromZero(1233,2000);
    //Animate from 0 to 7233 in 1.2 seconds
    points.animateFromZero(7233);
    //set end value and play animation from zero
    points.setEndValue(3422);
    points.animateFromZerotoCurrentValue();
    //set start end value and play animation in 5 seconds
    points.setStartValue(-1000);
    points.setEndValue(3444);
    points.animateText(5000);
    //change interpolator and animate from 0 to 2233 in 10 seconds
    points.setInterpolator(new LinearInterpolator());
    points.animateFromZero(7233, 10000);
    
    
####Note: animation sequencing is not implemented yet, so the library can't play animations in order. So you should use one of the methods above to animate your textview.

Feel free to contribute by sending a PR, creating an issue or tweeting me @uuratar.

And thanks for the inspiration;
http://stackoverflow.com/a/24389011/3399234

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-countingtextview-green.svg?style=flat)](https://android-arsenal.com/details/1/2277)
