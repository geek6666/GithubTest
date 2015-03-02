package com.zy.frame.utils;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Administrator on 2015/3/2.
 */
public class AnimatorUtils {
    public static final int DEFAULT_ANIMTOR_DURATION = 300;

    private static AnimatorUtils animatorUtils;

    private AnimatorUtils() {
    }

    public static AnimatorUtils newInstance() {
        if (animatorUtils == null) {
            synchronized (AnimatorUtils.class) {
                if (animatorUtils == null) {
                    animatorUtils = new AnimatorUtils();
                }
            }
        }
        return animatorUtils;
    }


    public void shakeAnimator(View view, int duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0);
        objectAnimator.setDuration(duration == 0 ? DEFAULT_ANIMTOR_DURATION:duration);
        objectAnimator.start();
    }

}
