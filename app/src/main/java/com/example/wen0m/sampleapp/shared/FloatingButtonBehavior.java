package com.example.wen0m.sampleapp.shared;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.example.wen0m.sampleapp.R;
import java.util.List;


public class FloatingButtonBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;
    private int toolbarHeight;

    public FloatingButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        if (dependency instanceof AppBarLayout) {
//            ((AppBarLayout) dependency).addOnOffsetChangedListener(new FloatingButtonListener());
        }
        return dependency instanceof AppBarLayout || super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, final FloatingActionButton child, View dependency) {
        if (dependency instanceof AppBarLayout) {
            return true;
        }
        return super.onDependentViewChanged(parent, child, dependency);
    }


    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child, final View target,
                               final int dxConsumed, final int dyConsumed, final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d("TAG666", "onNestedScroll.dyConsumed: " + dyConsumed);

        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
            // прокрутка вниз, кнопка видна
//            animateOut(child);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            // прокрутка вверх, кнопка не видна
//            animateIn(child);
        }
    }

//    @Override
//    public boolean onInterceptTouchEvent(CoordinatorLayout parent, final FloatingActionButton child, MotionEvent ev) {
//        child.animate()
//                .scaleX(1.4f)
//                .scaleY(1.4f)
//                .setInterpolator(new LinearOutSlowInInterpolator())
//                .withEndAction(new Runnable() {
//                    @Override
//                    public void run() {
//                        child.animate().scaleX(1).scaleY(1).setDuration(500);
//                    }
//                })
//                .setDuration(300);
//        return false;
//    }

    private void animateOut(final FloatingActionButton button) {
            Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.fab_shrink);
            anim.setInterpolator(INTERPOLATOR);
            anim.setDuration(300);
            anim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    FloatingButtonBehavior.this.mIsAnimatingOut = true;
                }

                public void onAnimationEnd(Animation animation) {
                    FloatingButtonBehavior.this.mIsAnimatingOut = false;
                    button.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(final Animation animation) {
                }

            });
            button.startAnimation(anim);
    }

    private void animateIn(FloatingActionButton button) {
        button.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.fab_grow);
            anim.setDuration(300);
            anim.setInterpolator(INTERPOLATOR);
            button.startAnimation(anim);
        }

}
