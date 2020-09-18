package fr.ws.reader.request;

import android.content.Context;
import android.content.Intent;

import fr.ws.reader.base.BaseActivity;
import fr.ws.reader.ui.activity.NetErrorActivity;
import fr.ws.reader.util.L;

import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 接口回调方法Callback
 */
public class QCallback extends StringCallback {

    private static final String TAG = "QCallback";

    private OnCallbackListener onCallbackListener = null;
    private OnPaginationListener onPaginationListener = null;
    private OnLogoutListener onLogoutListener = null;
    private Context mContext;

    public QCallback(Context context) {
        this.mContext = context;
    }

    public void setOnCallbackListener(OnCallbackListener listener) {
        this.onCallbackListener = listener;
    }

    public void setOnPaginationListener(OnPaginationListener onPaginationListener) {
        this.onPaginationListener = onPaginationListener;
    }

    public void setOnLogoutListener(OnLogoutListener listener) {
        this.onLogoutListener = listener;
    }

    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
        return super.parseNetworkResponse(response, id);
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        //网络错误，发起网络错误的回调方法
        if (onCallbackListener != null) {
            onCallbackListener.onNetError(id);
        }
        Intent intent = new Intent(mContext, NetErrorActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        if (mContext instanceof BaseActivity)
            ((BaseActivity) mContext).dismissLoading();
    }

    @Override
    public void onResponse(String response, int id) throws JSONException {
        L.i(TAG, id + "\n" + response);
        if (response.isEmpty()) {
            if (onCallbackListener != null)
                onCallbackListener.onSuccess(response, id);
            if (mContext instanceof BaseActivity)
                ((BaseActivity) mContext).dismissLoading();
            return;
        }
        JSONObject js = new JSONObject(response);
        //String data = js.optString("out");
        JSONObject out = js;
        if (out.isNull("error")) {
            //请求成功，发起成功的回调方法(data)
            if (onCallbackListener != null)
                onCallbackListener.onSuccess(response, id);
        } else {
            //请求发生错误，发起请求失败的回调方法(msg)
            if (onCallbackListener != null)
                onCallbackListener.onFailure(out.optString("error"), "", id);
        }
        if (mContext instanceof BaseActivity)
            ((BaseActivity) mContext).dismissLoading();
    }

    /**
     * 接口返回回调封装interface
     */
    public interface OnCallbackListener {
        //接口返回成功 200
        void onSuccess(String data, int id) throws JSONException;

        //接口返回失败
        void onFailure(String msg, String code, int id) throws JSONException;

        //网络错误
        void onNetError(int id);
    }

    public interface OnPaginationListener {
        //列表分页信息
        void onPageInfo(String pageInfo, int id);
    }

    public interface OnLogoutListener {
        //用户未登录
        void onLogout();
    }
}
