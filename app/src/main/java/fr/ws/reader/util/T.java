package fr.ws.reader.util;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fr.ws.reader.R;

/**
 * Toast提示工具类<br>
 * 需要调用initWithContext()初始化
 */
public class T {

    private static Toast mToast = null;
    private static Toast nToast = null;
    private static Application mApplication = null;
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static int mY = 0;

    /**
     * 初始化
     *
     * @param application
     */
    public static void initWithApplication(Application application) {
        mApplication = application;
        try {
            mY = application.getResources().getDimensionPixelSize(Resources.getSystem().getIdentifier("toast_y_offset", "dimen", "android"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mY == 0) {
            mY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, application.getResources().getDisplayMetrics());
        }
    }

    private synchronized static void show(String message, int gravity) {
        try {
            if (mToast != null) {
                mToast.setText(message);
                mToast.setDuration(Toast.LENGTH_SHORT);
                mToast.setGravity(gravity, 0, gravity != Gravity.CENTER ? mY : 0);
            } else {
                mToast = Toast.makeText(mApplication.getApplicationContext(), message, Toast.LENGTH_SHORT);
                mToast.setGravity(gravity, 0, gravity != Gravity.CENTER ? mY : 0);
            }
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized static void show(View view, int gravity) {
        try {
            if (mToast != null) {
                mToast.setView(view);
                mToast.setDuration(Toast.LENGTH_SHORT);
                mToast.setGravity(gravity, 0, gravity != Gravity.CENTER ? mY : 0);
            } else {
                mToast = new Toast(mApplication.getApplicationContext());
                mToast.setDuration(Toast.LENGTH_SHORT);
                mToast.setView(view);
                mToast.setGravity(gravity, 0, gravity != Gravity.CENTER ? mY : 0);
            }
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * show short toast
     *
     * @param context 上下文
     * @param message 消息
     */
    public static void showShort(Context context, CharSequence message) {
        if (context != null) {
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflate.inflate(R.layout.abs_toast_layout, null);
            TextView tv = (TextView) v.findViewById(R.id.message);
            if (mToast == null) {
                mToast = new Toast(context);
                tv.setText(message);
                mToast.setView(v);
                mToast.setGravity(Gravity.CENTER, 0, Utils.dpToPx(150, context));
                mToast.setDuration(Toast.LENGTH_SHORT);
            } else {
                mToast.setView(v);
                tv.setText(message);
            }
            mToast.show();
        }
    }

    /**
     * show long toast
     *
     * @param context 上下文
     * @param message 消息
     */
    public static void showLong(Context context, CharSequence message) {
        if (context != null) {
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflate.inflate(R.layout.abs_toast_layout, null);
            TextView tv = (TextView) v.findViewById(R.id.message);
            if (mToast == null) {
                mToast = new Toast(context);
                tv.setText(message);
                mToast.setView(v);
                mToast.setGravity(Gravity.CENTER, 0, Utils.dpToPx(150, context));
                mToast.setDuration(Toast.LENGTH_LONG);
            } else {
                mToast.setView(v);
                tv.setText(message);
            }
            mToast.show();
        }
    }
}  


