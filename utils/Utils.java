package utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/4.
 */

public class Utils {

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
/*********************************************************************************************************************************************************************************************/



}
