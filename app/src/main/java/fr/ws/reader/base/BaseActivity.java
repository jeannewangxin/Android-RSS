package fr.ws.reader.base;

import android.app.Dialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joanzapata.iconify.Iconify;

import fr.ws.reader.R;
import fr.ws.reader.request.QCallback;
import fr.ws.reader.util.DialogUtils;
import fr.ws.reader.util.FileUtils;
import fr.ws.reader.util.FontelloModule;
import fr.ws.reader.util.L;
import fr.ws.reader.util.OnNetConnectionReceiver;
import fr.ws.reader.util.T;
import fr.ws.reader.util.Utils;
import fr.ws.reader.view.TitleBar;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;

/**
 * 所有activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity implements QCallback.OnCallbackListener {

    protected Context mContext = BaseActivity.this;
    protected TitleBar titleBar;
    protected QCallback callback;
    public Dialog cmd;
    protected OnNetConnectionReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Iconify.with(new FontelloModule());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //StatusBarHelper.statusBarTextDark(this);
        View titleView = findViewById(R.id.title_layout);
        if (titleView != null) {
            titleBar = new TitleBar(this, titleView);
        }
        ButterKnife.bind(this);
        callback = new QCallback(this);
        callback.setOnCallbackListener(this);
        receiver = new OnNetConnectionReceiver();
        registerReceiver(receiver, ConnectivityManager.CONNECTIVITY_ACTION);
        initView();
        initData();
    }


    /**
     * Shows soft keyboard.
     *
     * @param editText EditText which has focus
     */
    public void showSoftKeyboard(EditText editText) {
        if (editText == null)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }

    /**
     * Hides soft keyboard.
     *
     * @param editText EditText which has focus
     */
    public void hideSoftKeyboard(EditText editText) {
        if (editText == null)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    /**
     * 初始化UI控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获得导航栏
     *
     * @return
     */
    public TitleBar getTitleBar() {
        return titleBar;
    }

    public Gson getGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    /**
     * 创建等待时的dialog(点击back后finish该页面)
     *
     * @return
     */
    public Dialog showLoading() {
        if (cmd == null) {
            cmd = DialogUtils.showLoading(mContext);
            cmd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    finish();
                }
            });
        } else {
            cmd.dismiss();
            cmd = null;
            cmd = DialogUtils.showLoading(mContext);
            cmd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    finish();
                }
            });
        }
        return cmd;
    }

    /**
     * 消去等待时的dialog
     */
    public void dismissLoading() {
        DialogUtils.dismiss(cmd);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    /**
     * list有数据判断
     *
     * @param dataList 数组
     * @return
     */
    public boolean isListHasData(List dataList) {
        return dataList != null && dataList.size() > 0;
    }

    /**
     * list不为空
     *
     * @param dataList 数组
     * @return
     */
    public boolean isListNotNull(List dataList) {
        return dataList != null;
    }

    /**
     * 无数据传递的跳转
     *
     * @param activity 目标activity
     */
    protected void startActivity(Class<?> activity) {
        Intent intent = new Intent(getBaseContext(), activity);
        startActivity(intent);
    }

    /**
     * 携带数据的跳转
     *
     * @param activity 目标activity
     * @param bundle   数据bundle
     */
    protected void startActivity(Class<?> activity, Bundle bundle) {
        Intent intent = new Intent(getBaseContext(), activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 请求返回结果的跳转
     *
     * @param activity    目标activity
     * @param requestCode 请求码
     */
    protected void startActivityForResult(Class<?> activity, int requestCode) {
        Intent intent = new Intent(getBaseContext(), activity);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 请求返回结果的跳转
     *
     * @param activity    目标activity
     * @param bundle      数据bundle
     * @param requestCode 请求码
     */
    protected void startActivityForResult(Class<?> activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getBaseContext(), activity);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 发送广播
     *
     * @param action 广播的类型
     */
    public void sendBroadcast(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        sendBroadcast(intent);
    }

    /**
     * 注册广播接收器
     *
     * @param action 广播的类型
     */
    public void registerReceiver(BroadcastReceiver receiver, String... action) {
        IntentFilter intentFilter = new IntentFilter();
        for (String anAction : action) {
            intentFilter.addAction(anAction);
        }
        registerReceiver(receiver, intentFilter);
    }

    public void showSuccess(View view, String message) {
        if (!isFinishing())
            if (view != null) {
                Utils.showSnackBar(view, message, 1);
                return;
            }
        T.showShort(mContext, message);
    }

    public void showError(View view, String message) {
        if (!isFinishing())
            if (view != null) {
                Utils.showSnackBar(view, message, 2);
                return;
            }
        T.showShort(mContext, message);
    }

    public void showNetError(View view) {
        if (!isFinishing())
            if (view != null) {
                Utils.showSnackBar(view, "Net Error.", 2);
                return;
            }
        T.showShort(mContext, "Net Error.");
    }

    public void showWarning(View view, String message) {
        if (!isFinishing())
            if (view != null) {
                Utils.showSnackBar(view, message, 3);
                return;
            }
        T.showShort(mContext, message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    protected void takeScreenshot(String name) {
        try {



            //String mPath = BitmapUtils.getRealFilePath(mContext, Uri.fromFile(new File(FileUtils.IMAGE_PATH + "/" + name+ ".jpg")));
            String mPath =FileUtils.FILEDIR +FileUtils.FILEIMAGE + "/" + name+ ".jpg";
            L.d(mPath);

            File dir = new File("DeliceHouse");
            if (!dir.exists()) dir.mkdirs();

            File diri = new File("DeliceHouse" +FileUtils.FILEIMAGE);
            if (!diri.exists()) diri.mkdirs();

            //String mPath = this.getExternalFilesDir(null).getPath()+ "/" + name + ".jpg";
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            //sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                final Uri contentUri = Uri.fromFile(imageFile);
                scanIntent.setData(contentUri);
                sendBroadcast(scanIntent);
            } else {
                final Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse(this.getExternalFilesDir(null).getPath()));
                sendBroadcast(intent);
            }

            MediaScannerConnection.scanFile(this, new String[] {

                            imageFile.getAbsolutePath()},

                    null, new MediaScannerConnection.OnScanCompletedListener() {

                        public void onScanCompleted(String path, Uri uri)

                        {


                        }

                    });
            //MediaScannerConnection.scanFile(this, new String[] { this.getExternalFilesDir(null).getPath() }, null, null);
            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    protected void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    /**
     * goReturn
     *
     * @param view
     */
    public void goReturn(View view) {
       finish();
    }

}