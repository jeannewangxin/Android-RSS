package fr.ws.reader.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import fr.ws.reader.ui.activity.NetErrorActivity;

/**
 * 监听网络变化的广播
 */
public class OnNetConnectionReceiver extends BroadcastReceiver {

    private ConnectivityManager connectivityManager;
    private NetworkInfo info;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null)
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager != null) {
                    info = connectivityManager.getActiveNetworkInfo();
                }
                if (info != null && info.isAvailable()) {
                    if (NetErrorActivity.instance != null)
                        NetErrorActivity.instance.finish();
                } else {
                    Intent intent1 = new Intent(context, NetErrorActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
                }
            }
    }
}
