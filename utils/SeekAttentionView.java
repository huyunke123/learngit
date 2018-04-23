package utils;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;



/**
 * 对控件的抖动
 * 对view的x轴和y轴进行0.9倍到1.1倍的缩放，同时对view进行一定角度的上下旋转。
 * Created by Administrator on 2017/11/13 0013.
 */

public class SeekAttentionView {
    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static ObjectAnimator tada(View view) {
        return tada(view, 1f);
    }

    /**
     * 控件抖动的方法
     * @param view
     * @param shakeFactor
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static ObjectAnimator tada(View view, float shakeFactor) {

        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.1f, -3f * shakeFactor),
                Keyframe.ofFloat(.2f, -3f * shakeFactor),
                Keyframe.ofFloat(.3f, 3f * shakeFactor),
                Keyframe.ofFloat(.4f, -3f * shakeFactor),
                Keyframe.ofFloat(.5f, 3f * shakeFactor),
                Keyframe.ofFloat(.6f, -3f * shakeFactor),
                Keyframe.ofFloat(.7f, 3f * shakeFactor),
                Keyframe.ofFloat(.8f, -3f * shakeFactor),
                Keyframe.ofFloat(.9f, 3f * shakeFactor),
                Keyframe.ofFloat(1f, 0)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY, pvhRotate).
                setDuration(1000);
    }

    /**
     * 左右摇晃的效果
     * @param view
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public  static ObjectAnimator nope(View view) {
        int delta =8;//Utils.px2dip(context,8)

        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, -delta),
                Keyframe.ofFloat(.26f, delta),
                Keyframe.ofFloat(.42f, -delta),
                Keyframe.ofFloat(.58f, delta),
                Keyframe.ofFloat(.74f, -delta),
                Keyframe.ofFloat(.90f, delta),
                Keyframe.ofFloat(1f, 0f)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX).
                setDuration(800);
    }

    /**
     * 闪烁方法
     */
    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static ObjectAnimator flicker(View context) {
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.3f, .9f),
                Keyframe.ofFloat(.4f, .9f),
                Keyframe.ofFloat(.5f, 1.3f),
                Keyframe.ofFloat(.6f, 1.3f),
                Keyframe.ofFloat(.7f, 1.3f),
                Keyframe.ofFloat(.8f, 1.3f),
                Keyframe.ofFloat(.9f, 1.3f),
                Keyframe.ofFloat(1f, 1.3f),
                Keyframe.ofFloat(1.2f, 1.3f),
                Keyframe.ofFloat(1f, 1.3f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.3f, .9f),
                Keyframe.ofFloat(.4f, .9f),
                Keyframe.ofFloat(.5f, 1.3f),
                Keyframe.ofFloat(.6f, 1.3f),
                Keyframe.ofFloat(.7f, 1.3f),
                Keyframe.ofFloat(.8f, 1.3f),
                Keyframe.ofFloat(.9f, 1.3f),
                Keyframe.ofFloat(1f, 1.3f),
                Keyframe.ofFloat(1.2f, 1.3f),
                Keyframe.ofFloat(1f, 1.3f)
        );
        return ObjectAnimator.ofPropertyValuesHolder(context, pvhScaleX, pvhScaleY).
                setDuration(600);
    }
}
