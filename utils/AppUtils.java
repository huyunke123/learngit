package utils;

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

/**
 * 系统工具类
 * Created by huyunke on 2018/4/4.
 */

public class AppUtils {
    /**
     * 打开软键盘将光标取来
     *
     * @param editText
     */
    public static void openEditText(EditText editText) {
        editText.setFocusable(true);
        String str = editText.getText().toString();
        if (TextUtils.isEmpty(str)) {
            editText.setSelection(0);
        } else {
            editText.setSelection(str.length());
        }
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 关闭软键盘
     *
     * @param context
     * @param view
     */
    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    /**
     * 打电话
     *
     * @param context
     * @param phone
     */
    public static void callPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    /**
     * 拍照（兼容7.0）
     */
    public static void photo(Activity activity, int requestCode, String fileProvice, String imageName){
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getPath(activity,fileProvice,imageName));
        openCameraIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        openCameraIntent.putExtra("noFaceDetection", true);
        openCameraIntent.putExtra("return-data", true);
        activity.startActivityForResult(openCameraIntent, requestCode);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dp值转成px值
     *
     * @param context
     * @param dip
     * @return
     */
    public static int dip2Px(Context context, float dip) {
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics()));
    /*	float desity=context.getResources().getDisplayMetrics().density;
        return (int)(dip*desity+0.5f);*/
    }

/*****************************************************************************************************************************************************************************/
    /**
     * 6.0以上要设置FileProvicer
     * @param fileProvice：FileProvicer地址
     * @param imageName：图片名称
     * @param context
     * @return
     */
    private static Uri getPath(Context context,String fileProvice,String imageName) {
        Uri uri = Uri.fromFile(createImageFile(imageName));
        if (android.os.Build.VERSION.SDK_INT > 23) {
            return FileProvider.getUriForFile(context, fileProvice, createImageFile(imageName));
        } else {
            return uri;

        }

    }

    /**
     * 放图片的地址
     * imageName：图片名称
     * @return
     */
    private static File createImageFile(String imageName) {
        // Create an image file name
        String imageFileName = imageName;
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        if (!storageDir.exists()) {
            if (!storageDir.mkdir()) {
                Log.e("TAG", "Throwing Errors....");
            }
        }
        File image = new File(storageDir, imageFileName);
        String mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
