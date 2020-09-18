package fr.ws.reader.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import fr.ws.reader.R;
import fr.ws.reader.app.Config;
import fr.ws.reader.app.MainApplication;
import fr.ws.reader.base.BaseFragment;
import fr.ws.reader.bean.Account;
import fr.ws.reader.bean.User;
import fr.ws.reader.request.QRequest;
import fr.ws.reader.ui.activity.HistoryActivity;
import fr.ws.reader.ui.activity.LoginFirstActivity;
import fr.ws.reader.ui.activity.PersonDataActivity;
import fr.ws.reader.util.Utils;

/**
 * 个人fragment
 */
public class PersonFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.layout_person_data)
    RelativeLayout layoutPersonData;
    @BindView(R.id.layout_logout)
    LinearLayout layoutLogout;
    @BindView(R.id.layout_person)
    LinearLayout layoutPerson;
    private Account account;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    protected void initView() {
        getTitleBar().setCustomTitle(getString(R.string.title_person), false, null);
        layoutPersonData.setOnClickListener(this);
        layoutLogout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        account = MainApplication.app.getAccount();
        if (account != null) {
            //QRequest.getWallet(String.valueOf(account.getEntityId()),account.getTokenPay(),callback);
           // showLoading().show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        //QRequest.getWallet(String.valueOf(account.getEntityId()),account.getTokenPay(),callback);
        //showLoading().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_person_data:
                startActivity(PersonDataActivity.class);
                break;
            case R.id.layout_logout:
                Utils.clearInfo(getActivity());
                getBaseActivity().sendBroadcast(Config.ACTION_LOGOUT);
                startActivity(LoginFirstActivity.class);
                getBaseActivity().finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void lazyLoad() {
      // refreshUserInfo();
    }

    @Override
    protected void userVisible() {

    }

    @Override
    public void onSuccess(String data, int id) throws JSONException {

    }

    @Override
    public void onFailure(String msg, String code, int id) throws JSONException {

    }

    @Override
    public void onNetError(int id) {

    }

}
