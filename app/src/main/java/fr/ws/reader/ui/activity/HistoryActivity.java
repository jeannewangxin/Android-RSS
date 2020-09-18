package fr.ws.reader.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fr.ws.reader.R;
import fr.ws.reader.adapter.EventAdapter;
import fr.ws.reader.app.MainApplication;
import fr.ws.reader.base.BaseActivity;
import fr.ws.reader.bean.Account;
import fr.ws.reader.bean.Event;
import fr.ws.reader.interfaces.OnItemClickListener;
import fr.ws.reader.request.QRequest;

/**
 * 分类列表页
 * Constants.CATEGORIES_INFO--------------------分类列表（可能是二级分类）
 * Constants.CATEGORY---------------------------标题
 */
public class HistoryActivity extends BaseActivity implements OnItemClickListener<Event> {

    private static final String TAG = "HistoryActivity";

    @BindView(R.id.lv_history)
    RecyclerView lv_history;
    @BindView(R.id.tv_return)
    TextView tv_return;
    private List<Event> events;
    private EventAdapter adapter;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    @Override
    protected void initView() {
        account = MainApplication.app.getAccount();
        if (account != null) {
            QRequest.getListEvents(String.valueOf(account.getEntityId()),account.getPassword(),callback);
            showLoading().show();
        }
        lv_history.setLayoutManager(new LinearLayoutManager(mContext));
        Typeface font = Typeface.createFromAsset(getAssets(), "fontello.ttf");
        tv_return.setTypeface(font);
        tv_return.setText("\ue819");
    }

    @Override
    protected void initData() {
        adapter = new EventAdapter(lv_history, new ArrayList<Event>(), R.layout.item_event,this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onSuccess(String data, int id) throws JSONException {
        JSONObject js = new JSONObject(data);
        switch (id) {
            //event list
            case QRequest.LIST_EVENTS:
                events = getGson().fromJson(
                        js.optString("list"), new TypeToken<List<Event>>() {
                        }.getType());
                if (isListNotNull(events)) {
                    adapter.replaceAll(events);
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
            case QRequest.LIST_EVENTS:
                switch (error_code) {
                    case 1:
                        message = getString(R.string.event_list_error1);
                        break;
                    case 2:
                        message = getString(R.string.event_list_error1);
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
        showError(lv_history, message);
    }

    @Override
    public void onNetError(int id) {
        showNetError(lv_history);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Event event, int position) {

    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Event event, int position) {
        return false;
    }
}
