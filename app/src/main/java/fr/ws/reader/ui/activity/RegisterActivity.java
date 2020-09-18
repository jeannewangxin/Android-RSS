package fr.ws.reader.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import fr.ws.reader.R;
import fr.ws.reader.app.Config;
import fr.ws.reader.app.MainApplication;
import fr.ws.reader.base.BaseActivity;
import fr.ws.reader.bean.Account;
import fr.ws.reader.request.QRequest;
import fr.ws.reader.util.EditTextWatcher;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_repassword)
    EditText etRepassword;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.tv_return)
    TextView tv_return;
    private String email = "";
    private String password = "";
    private String repassword = "";
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        new EditTextWatcher(et_email);
        new EditTextWatcher(etPassword);
        new EditTextWatcher(etRepassword);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontello.ttf");
        tv_return.setTypeface(font);
        tv_return.setText("\ue806");
    }

    @Override
    protected void initData() {
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onSuccess(String data, int id) throws JSONException {
        switch (id) {
            //普通用户注册提交
            case QRequest.REGISTER:
                JSONObject js = new JSONObject(data);
                if(!js.isNull("user")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("email", email);
                    bundle.putString("password", password);
                    //startActivity(LoginFirstActivity.class, bundle);
                    finish();
                }else if(!js.isNull("error")){
                    showError(etPassword,js.optString("error"));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onFailure(String msg, String code, int id) throws JSONException {
        Integer error_code = Integer.parseInt(msg);
        String message="";
        switch (id) {
            //event list
            case QRequest.REGISTER:
                switch (error_code) {
                    case 1:
                        message = getString(R.string.register_user_error1);
                        break;
                    case 2:
                        message = getString(R.string.register_user_error2);
                        break;
                    case 3:
                        message = getString(R.string.register_user_error3);
                        break;
                    case 4:
                        message = getString(R.string.error4);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        showError(etPassword, message);
    }

    @Override
    public void onNetError(int id) {
        showNetError(etPassword);
    }


    /**
     * 注册提交
     *
     * @param view
     */
    public void onRegister(View view) {
        showLoading();
        if (checkContent()) {
            //普通用户注册提交接口
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        showSuccess(et_email, "SignUp unsuccessful!");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Account acc = new Account();
                        acc.setIsActive(1);
                        acc.setEmail(user.getEmail());
                        acc.setPassword(password);
                        MainApplication.app.setAccount(acc);
                        Account account = MainApplication.app.getAccount();
                        if (account.getIsActive() == 1) {
                            showSuccess(et_email,"Registration sucess!");
                            sendBroadcast(Config.ACTION_LOGIN);
                            dismissLoading();
                            startActivity(MainActivity.class);
                            finish();
                        }

                    } else {
                        dismissLoading();
                        showError(et_email,"Something went wrong!");
                        startActivity(MainActivity.class);
                    }
                }
            });
            showLoading().show();
        }
    }

    /**
     * 检查文案
     *
     * @return
     */
    public boolean checkContent() {
        email = et_email.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        repassword = etRepassword.getText().toString().trim();
        //判断是否为空
        if (email.isEmpty())
            return false;
        if (password.isEmpty())
            return false;
        if (repassword.isEmpty())
            return false;
        //密码位数
        if (password.length() < 6)
            return false;
        if (repassword.length() < 6)
            return false;
        return true;
    }
}
