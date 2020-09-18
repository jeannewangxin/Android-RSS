package fr.ws.reader.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import fr.ws.reader.R;
import fr.ws.reader.app.MainApplication;
import fr.ws.reader.base.BaseActivity;
import fr.ws.reader.bean.Account;
import fr.ws.reader.bean.User;
import fr.ws.reader.request.QRequest;
import fr.ws.reader.util.EditTextWatcher;
import fr.ws.reader.util.StringUtils;
import fr.ws.reader.view.ObservableScrollView;

/**
 * 个人资料页
 */
public class PersonDataActivity extends BaseActivity {

    private static final String TAG = "PersonDataActivity";

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.scroll_view)
    ObservableScrollView scrollView;
    @BindView(R.id.tv_return)
    TextView tv_return;
    private User user;


    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_data);
    }

    @Override
    protected void initView() {

        new EditTextWatcher(etEmail, EditTextWatcher.WATCHER_EMAIL);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontello.ttf");
        tv_return.setTypeface(font);
        tv_return.setText("\ue819");
    }

    @Override
    protected void initData() {
        Account account = MainApplication.app.getAccount();
        if (account != null) {
            etEmail.setText(account.getEmail());
            scrollView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onSuccess(String data, int id) throws JSONException {

    }

    @Override
    public void onFailure(String msg, String code, int id) throws JSONException {
        showError(etEmail, msg);
    }

    @Override
    public void onNetError(int id) {
        showNetError(etEmail);
    }



}
