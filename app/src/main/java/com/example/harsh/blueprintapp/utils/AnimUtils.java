package com.example.harsh.blueprintapp.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;


public class AnimUtils {

    public static Animation getFadeInAnimation(Animation.AnimationListener listener) {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(200);
        animation.setAnimationListener(listener);
        return animation;
    }

    public static Animation getFadeOutAnimation(Animation.AnimationListener listener) {
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(200);
        animation.setAnimationListener(listener);
        return animation;
    }

    public static Animation getAnimFromResource(Context context, int resource, Animation.AnimationListener listener) {
        Animation animation = AnimationUtils.loadAnimation(context, resource);
        animation.setAnimationListener(listener);
        return animation;
    }

    public static Animation.AnimationListener getAnimationListener(final View view, final int visibility) {
        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(visibility);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
        return listener;
    }
}
