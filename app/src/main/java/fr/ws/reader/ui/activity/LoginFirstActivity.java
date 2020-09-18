package fr.ws.reader.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.joanzapata.iconify.widget.IconTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import fr.ws.reader.R;
import fr.ws.reader.app.Config;
import fr.ws.reader.app.MainApplication;
import fr.ws.reader.base.BaseActivity;
import fr.ws.reader.bean.Account;
import fr.ws.reader.interfaces.OnLoginStatusChangeListener;
import fr.ws.reader.request.QRequest;
import fr.ws.reader.util.D;
import fr.ws.reader.util.OnMessageReceiver;
import fr.ws.reader.util.SnackbarUtil;
import fr.ws.reader.util.StringUtils;

/**
 * 第一个登录页
 */
public class LoginFirstActivity extends BaseActivity implements OnLoginStatusChangeListener, BGABanner.Adapter<ImageView, Integer> {


    @BindView(R.id.banner)
    BGABanner banner;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_password)
    EditText etPassword;


    private OnMessageReceiver receiver;
    private Account account;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_first);
    }

    @Override
    protected void initView() {
        receiver = new OnMessageReceiver(this);
        registerReceiver(receiver, Config.ACTION_LOGIN, Config.ACTION_LOGOUT);

    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    protected void initData() {
        mAuth = FirebaseAuth.getInstance();
        loadBanner();      //轮播图
        account = MainApplication.app.getAccount();
        if (account != null) {
            et_email.setText(account.getEmail());
            etPassword.setText(account.getPassword());
        }
    }

    /**
     * 初始化Banner
     */
    private void loadBanner() {
        final List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.backgroud1);
        images.add(R.mipmap.background);
        images.add(R.mipmap.background2);
        banner.setAdapter(this);
        banner.setData(images, null);
    }

    @Override
    public void onSuccess(String data, int id) throws JSONException {
    }

    @Override
    public void onFailure(String msg, String code, int id) throws JSONException {
    }

    @Override
    public void onNetError(int id) {
        SnackbarUtil.shortSnackbar(et_email, "Net Error.", SnackbarUtil.Alert).show();
        showNetError(et_email);
    }

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {
        showLoading();
        String email = et_email.getText().toString();
        final String pass = etPassword.getText().toString();
        if (email.isEmpty() || pass.isEmpty()) {
            return;
        }
        D.getInstance(this).putString("password",pass);
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("authentification", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Account acc = new Account();
                            acc.setIsActive(1);
                            acc.setEmail(user.getEmail());
                            acc.setPassword(pass);
                            MainApplication.app.setAccount(acc);
                            account = MainApplication.app.getAccount();
                            if (account.getIsActive() == 1) {
                                showSuccess(etPassword,"Authentication success!");
                                sendBroadcast(Config.ACTION_LOGIN);
                                dismissLoading();
                                finish();
                                startActivity(MainActivity.class);
                            }
                        } else {
                            dismissLoading();
                            // If sign in fails, display a message to the user.
                            Log.w("authentification", "signInWithEmail:failure", task.getException());
                            showError(etPassword,"Authentication failed!");
                            //updateUI(null);
                        }
                    }
                });
    }


    @Override
    public void onLogin() {
        if (!isFinishing()) {
            finish();
        }
    }

    @Override
    public void onLogout() {
        if (!isFinishing()) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, Integer model, int position) {
        itemView.setImageResource(model);
    }

    public void onRegister(View v){
        startActivity(RegisterActivity.class);
    }
}
