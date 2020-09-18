package fr.ws.reader.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import fr.ws.reader.R;
import fr.ws.reader.base.BaseActivity;

/**
 * Dialog的工具类
 */
public class DialogUtils {

    /**
     * 创建加载中Dialog
     *
     * @param context
     * @return
     */
    public static Dialog showLoading(final Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.abs_dialog_loading, null);// 得到加载view
        LinearLayout layout = v.findViewById(R.id.dialog_loading_view);// 加载布局
        Dialog loadingDialog = new Dialog(context, R.style.Loading);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        //将显示Dialog的方法封装在这里面
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnim);
        return loadingDialog;
    }

    /**
     * 显示dialog
     *
     * @param context
     * @param dialog
     */
    public static void show(Context context, Dialog dialog) {
        if (dialog != null && context != null && !((BaseActivity) context).isFinishing()) {
            dialog.show();
        }
    }

    /**
     * 关闭加载中dialog
     *
     * @param mDialogUtils
     */
    public static void dismiss(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }

    /**
     * 显示AlertDialog 提示框
     *
     * @param context
     * @param title
     * @param message
     * @param listener
     * @return
     */
    public static AlertDialog showAlert(Context context, String title, String message, String nButton, String pButton, DialogInterface.OnClickListener listener) {
        final AlertDialog builder = new AlertDialog.Builder(context, R.style.AlertDialog).create();
        if (!title.isEmpty())
            builder.setTitle(title);
        builder.setMessage(message);
        if (!nButton.isEmpty()) {
            builder.setButton(DialogInterface.BUTTON_NEGATIVE, nButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }
        builder.setButton(DialogInterface.BUTTON_POSITIVE, pButton, listener);
        builder.setCancelable(false);
        builder.setCanceledOnTouchOutside(false);
        return builder;
    }

    /**
     * 显示AlertDialog 提示框
     *
     * @param context
     * @param title
     * @param message
     * @param positiveButtonText
     * @param listener
     * @return
     */
    public static AlertDialog showAlertCustom(Context context, String title, String message, String positiveButtonText,
                                              DialogInterface.OnClickListener listener) {
        final AlertDialog builder = new AlertDialog.Builder(context, R.style.AlertDialog).create();
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setButton(DialogInterface.BUTTON_POSITIVE, positiveButtonText, listener);
        builder.setCancelable(true);
        return builder;
    }
}