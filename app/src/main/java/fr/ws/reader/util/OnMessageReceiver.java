package fr.ws.reader.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.ws.reader.app.Config;
import fr.ws.reader.interfaces.OnLoginStatusChangeListener;

public class OnMessageReceiver extends BroadcastReceiver {

    public OnLoginStatusChangeListener onLoginStatusChangeListener = null;

    public OnMessageReceiver(OnLoginStatusChangeListener onLoginStatusChangeListener) {
        this.onLoginStatusChangeListener = onLoginStatusChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null)
            if (intent.getAction().equals(Config.ACTION_LOGIN)) {
                //登录广播
                if (onLoginStatusChangeListener != null)
                    onLoginStatusChangeListener.onLogin();
            } else if (intent.getAction().equals(Config.ACTION_LOGOUT)) {
                //退出登录广播
                if (onLoginStatusChangeListener != null)
                    onLoginStatusChangeListener.onLogout();
            }
    }
}