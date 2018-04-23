package utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * 动画工具类
 * Created by huyunke on 2018/4/4.
 */

public class AnimUtils {
    /**
     * view抖动
     * @param iv
     */
    public static void viewShaking(View iv) {
        ObjectAnimator animator = SeekAttentionView.tada(iv);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    /**
     * view 晃动
     * @param iv
     */
    public static void viewSloshing(View iv) {
        ObjectAnimator nopeAnimator = SeekAttentionView.nope(iv);
        nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
        nopeAnimator.start();
    }

    /**
     * 闪耀的view
     * @param context
     */
    public static void flickerText(View context) {
        ObjectAnimator animator = SeekAttentionView.flicker(context);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    /**
     *
     * @param view
     * @param startRotation
     * @param endRotation
     * @param duration
     * @param alwaysRuning
     * @param listener
     * @return
     */
    @SuppressLint("WrongConstant")
    public static ObjectAnimator rotation(View view, float startRotation, float endRotation, int duration, boolean alwaysRuning, OnEGAnimListener listener){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", startRotation, endRotation);
        animator.setDuration(duration);
        if(alwaysRuning){
            animator.setRepeatCount(ObjectAnimator.INFINITE);
            animator.setRepeatMode(ObjectAnimator.INFINITE);
        }
        checkObjectAnimListener(animator, listener);
        animator.start();
        return animator;
    }

    /**
     *  x轴上移动
     * @param view
     * @param startX
     * @param endX
     * @param duration
     * @param listener
     * @return
     */
    public static ObjectAnimator moveX(View view, float startX, float endX, int duration, OnEGAnimListener listener){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "x", startX, endX);
        animator.setDuration(duration);
        checkObjectAnimListener(animator, listener);
        animator.start();
        return animator;
    }
    /** 源Y轴移动 */
    public static ObjectAnimator moveY(View view, float startY, float endY, int duration, Interpolator interpolator, OnEGAnimListener listener){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "y", startY, endY);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        checkObjectAnimListener(animator, listener);
        animator.start();
        return animator;
    }
    /** 缩小退出 */
    public static AnimatorSet scaleOut(View view, int duration, OnEGAnimListener listener){
        AnimatorSet scaleSet = new AnimatorSet();
        scaleSet.playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f));
        Interpolator interpolator = new AccelerateDecelerateInterpolator();
        scaleSet.setInterpolator(interpolator);
        scaleSet.setDuration(duration);
        checkObjectAnimListener(scaleSet, listener);
        scaleSet.start();
        return scaleSet;
    }
    /** 放大进入 */
    public static AnimatorSet scaleIn(View view, int duration){
        AnimatorSet scaleSet = new AnimatorSet();
        scaleSet.playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f));
        Interpolator interpolator = new AccelerateDecelerateInterpolator();
        scaleSet.setInterpolator(interpolator);
        scaleSet.setDuration(duration);
        scaleSet.start();
        return scaleSet;
    }
    /**
     * 监听动画开始-结束
     */
    public static interface OnEGAnimListener{
        void onAnimEnd(Animator animator);
    }
    /*************************************************************************************************************************************************************************/
    /**
     *  监听动画开始-结束
     * @param animator
     * @param listener
     */
    private static void checkObjectAnimListener(ObjectAnimator animator, final OnEGAnimListener listener){
        checkObjectAnimListener(null, animator, listener);
    }

    private static void checkObjectAnimListener(final View view, ObjectAnimator animator, final OnEGAnimListener listener){

        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                if(view != null){
                    view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                if(listener != null){
                    listener.onAnimEnd(arg0);
                }

            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                if(listener != null){
                    listener.onAnimEnd(arg0);
                }
            }
        });

    }
    private static void checkObjectAnimListener(AnimatorSet animator, final OnEGAnimListener listener){
        if(listener != null){
            animator.addListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator arg0) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationRepeat(Animator arg0) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationEnd(Animator arg0) {
                    listener.onAnimEnd(arg0);
                }

                @Override
                public void onAnimationCancel(Animator arg0) {
                    listener.onAnimEnd(arg0);
                }
            });
        }
    }
}
