package fr.ws.reader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import fr.ws.reader.R;
import fr.ws.reader.adapter.PagerAdapter;
import fr.ws.reader.app.Constants;
import fr.ws.reader.app.MainApplication;
import fr.ws.reader.base.BaseActivity;
import fr.ws.reader.bean.Account;
import fr.ws.reader.bean.User;
import fr.ws.reader.interfaces.OnLoginStatusChangeListener;
import fr.ws.reader.request.QRequest;
import fr.ws.reader.ui.fragment.HomeFragment;
import fr.ws.reader.ui.fragment.PersonFragment;
import fr.ws.reader.ui.fragment.ReadLaterFragment;
import fr.ws.reader.ui.fragment.RecommendFragment;
import fr.ws.reader.util.OnMessageReceiver;
import fr.ws.reader.view.MyPopupWindow;
import fr.ws.reader.view.MyViewPager;

/**
 * 买家首页
 */
public class MainActivity extends BaseActivity implements OnLoginStatusChangeListener, OnTabSelectListener {

    @BindView(R.id.view_pager)
    MyViewPager viewPager;
    @BindView(R.id.navigation_bar)
    JPTabBar navigationBar;

    private ArrayList<Fragment> fragments;
    private long mTimeStampFirst = 0;
    private Account account;
    private int mPosition = 0;
    private OnMessageReceiver receiver;
    private MyPopupWindow categoriesWindow;
    @BindView(R.id.view_line)
    View view_line;
    @Titles
    private int[] title = {R.string.tab_home,R.string.readlater_sub,R.string.tab_sub,R.string.tab_person};
    @NorIcons
    private int[] iconSel = {R.mipmap.tab_home, R.mipmap.tab_heart,R.mipmap.tab_recommend, R.mipmap.tab_user};
    @SeleIcons
    private int[] iconUnSel = {R.mipmap.tab_home_sel, R.mipmap.tab_heart_sel,R.mipmap.tab_recommend_sel,R.mipmap.tab_user_sel};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
          fragments = getFragments();
          initNavigationBarAndViewPager();
          //receiver = new OnMessageReceiver(this);
          //registerReceiver(receiver, Config.ACTION_LOGIN, Config.ACTION_LOGOUT);
    }




    @Override
    protected void initData() {
//        account = MainApplication.app.getAccount();
//        if (account != null) {
//            //买家信息
//            //QRequest.userInfoMore(account.getEmail(), account.getPassword(), callback);
//        }
    }

    private void initNavigationBarAndViewPager() {
        navigationBar.setTitles(title).setNormalIcons(iconUnSel).setSelectedIcons(iconSel).generate();
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOffscreenPageLimit(4);
        navigationBar.setContainer(viewPager);
        navigationBar.setTabListener(this);
    }

    @Override
    public void onSuccess(String data, int id) throws JSONException {
        JSONObject js = new JSONObject(data);
        switch (id) {
            //个人信息
            case QRequest.USER_INFO:
                User user = getGson().fromJson(js.optString("user"), User.class);
                MainApplication.app.setUser(user);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFailure(String msg, String code, int id) throws JSONException {

    }

    @Override
    public void onNetError(int id) {

    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add (new ReadLaterFragment ());
        fragments.add(new RecommendFragment ());
        fragments.add(new PersonFragment());

        return fragments;
    }

    @Override
    public void onBackPressed() {
        long iCurTime = System.currentTimeMillis();
        if (mTimeStampFirst == 0 || iCurTime - mTimeStampFirst > 1500) {
            mTimeStampFirst = iCurTime;
            showWarning(viewPager, "Click again to exit rss reader!");
        } else if (iCurTime - mTimeStampFirst <= 1500) {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //登录返回
            case Constants.LOGIN:
                if (resultCode == RESULT_OK) {
                    //登录成功
                    account = MainApplication.app.getAccount();
                } else {
                    //登录失败
                    viewPager.setCurrentItem(mPosition, false);
                    navigationBar.setSelectTab(mPosition);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onLogin() {
        //((PersonFragment) fragments.get(1)).refreshUserInfo();
    }

    @Override
    public void onLogout() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(receiver);
    }

    @Override
    public void onTabSelect(int index) {
        if (index == 1) {
            this.mPosition = index;
        } else if (index == 2) {
           /* //个人fragment
            if (account == null) {
                startActivityForResult(LoginInsideActivity.class, Constants.LOGIN);
                return;
            }
            */
            this.mPosition = index;
        } else if(index == 3){

        } else{
            this.mPosition = index;
        }
    }

    @Override
    public void onClickMiddle(View middleBtn) {

    }
}
