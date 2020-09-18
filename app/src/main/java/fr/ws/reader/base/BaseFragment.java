package fr.ws.reader.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.ws.reader.R;
import fr.ws.reader.ui.activity.MainActivity;
import fr.ws.reader.app.MainApplication;
import fr.ws.reader.request.QCallback;
import fr.ws.reader.util.DialogUtils;
import fr.ws.reader.util.T;
import fr.ws.reader.util.Utils;
import fr.ws.reader.view.TitleBar;

import java.util.List;

import butterknife.ButterKnife;

/**
 * 所有fragment的基类
 */
public abstract class BaseFragment extends Fragment implements QCallback.OnCallbackListener {

    protected View mRootView, titleView;
    protected TitleBar titleBar;
    protected QCallback callback;
    protected boolean isFirstLoad = true;
    protected int page = 1;
    public Dialog cmd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        titleView = mRootView.findViewById(R.id.title_layout);
        if (titleView != null) {
            titleBar = new TitleBar(getActivity(), titleView);
        }
        ButterKnife.bind(this, mRootView);
        callback = new QCallback(getBaseActivity());
        callback.setOnCallbackListener(this);
        initView();
        initData();
        return mRootView;
    }

    /**
     * 初始化layout的id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化UI控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 自定义导航栏
     *
     * @return
     */
    protected TitleBar getTitleBar() {
        return titleBar;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible()) {
            if (isFirstLoad) {
                lazyLoad();
                isFirstLoad = false;
            } else {
                userVisible();
            }
        } else {
            if (!isFirstLoad)
                userBehind();
        }
    }

    /**
     * 数据缓加载
     */
    protected void lazyLoad() {

    }

    protected void userVisible() {

    }

    protected void userBehind() {

    }

    /**
     * 获取BaseActivity实例
     *
     * @return
     */
    public BaseActivity getBaseActivity() {
        if (getActivity() != null && !getActivity().isFinishing())
            return (BaseActivity) getActivity();
        else
            return null;
    }

    public Gson getGson() {
        if (getBaseActivity() != null)
            return getBaseActivity().getGson();
        else
            return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    /**
     * 获取MainActivity实例
     *
     * @return
     */
    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
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

    public MainApplication getApplication() {
        return (MainApplication) super.getActivity().getApplicationContext();
    }

    /**
     * 无数据传递的跳转
     *
     * @param activity 目标activity
     */
    protected void startActivity(Class<?> activity) {
        Intent intent = new Intent(getBaseActivity(), activity);
        startActivity(intent);
    }

    /**
     * 携带数据的跳转
     *
     * @param activity 目标activity
     * @param bundle   数据bundle
     */
    protected void startActivity(Class<?> activity, Bundle bundle) {
        Intent intent = new Intent(getBaseActivity(), activity);
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
        Intent intent = new Intent(getBaseActivity(), activity);
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
        Intent intent = new Intent(getBaseActivity(), activity);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public MainApplication getAppInstance() {
        return (MainApplication) super.getActivity().getApplicationContext();
    }

    public void showSuccess(View view, String message) {
        if (getBaseActivity() != null)
            if (view != null) {
                Utils.showSnackBar(view, message, 1);
                return;
            }
        T.showShort(getBaseActivity(), message);
    }

    public void showError(View view, String message) {
        if (getBaseActivity() != null)
            if (view != null) {
                Utils.showSnackBar(view, message, 2);
                return;
            }
        T.showShort(getBaseActivity(), message);
    }

    public void showNetError(View view) {
        if (getBaseActivity() != null)
            if (view != null) {
                Utils.showSnackBar(view, "Net Error.", 2);
                return;
            }
        T.showShort(getBaseActivity(), "Net Error.");
    }


    /**
     * 创建等待时的dialog(点击back后finish该页面)
     *
     * @return
     */
    public Dialog showLoading() {
        if (cmd == null) {
            cmd = DialogUtils.showLoading(getContext());
            cmd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {

                }
            });
        } else {
            cmd.dismiss();
            cmd = null;
            cmd = DialogUtils.showLoading(getContext());
            cmd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {

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
}