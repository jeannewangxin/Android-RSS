package fr.ws.reader.ui.fragment;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import fr.ws.reader.R;
import fr.ws.reader.adapter.ArticleAdapter;
import fr.ws.reader.adapter.FeedSimpleAdapter;
import fr.ws.reader.adapter.PopupFeedsAdapter;
import fr.ws.reader.app.MainApplication;
import fr.ws.reader.base.BaseFragment;
import fr.ws.reader.bean.Article;
import fr.ws.reader.bean.Feed;
import fr.ws.reader.interfaces.OnItemClickListener;
import fr.ws.reader.util.DatabaseHandler;
import fr.ws.reader.util.Parser;
import fr.ws.reader.view.MyPopupWindow;
import fr.ws.reader.view.MySwipeRefreshLayout;

/**
 * 首页fragment
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.lv_home_type)
    RecyclerView lvHomeType;
    @BindView(R.id.refresh_layout)
    MySwipeRefreshLayout refreshLayout;
    @BindView(R.id.view_line)
    View view_line;
    @BindView(R.id.tv_Feed)
    TextView tv_Feed;
    @BindView(R.id.tv_cart)
    TextView tv_cart;
    @BindView(R.id.btn_Feed)
    LinearLayout btn_Feed;
    @BindView(R.id.btn_cart)
    RelativeLayout btn_cart;
    private MyPopupWindow feedsWindow;
    private FeedSimpleAdapter FeedListAdapter;
    private List<Feed> feeds;
    private ArrayList<Article> articles = new ArrayList<Article>();
    private DatabaseHandler handler ;
    private PopupFeedsAdapter popupFeedsAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        handler = new DatabaseHandler(getContext());
        FragmentManager fm = getFragmentManager();
        refreshLayout.setOnRefreshListener(refreshListener);
        lvHomeType.setLayoutManager(new LinearLayoutManager(getActivity()));
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontello.ttf");
        tv_Feed.setTypeface(font);
        tv_Feed.setText("\ue822");
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshArticles();

    }

    private void refreshArticles(){
        feeds=handler.getAllFeeds();
        articles.clear();
        if(feeds.size()>0) {
            for (Feed f : feeds) {
                loadFeed(f.getLink());
            }
        }

        refreshLayout.stopRefresh();
    }
    @Override
    protected void initData() {
        btn_Feed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createFeedWindow();
            }
        });
        btn_cart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        isFirstLoad = false;
    }


    public void loadFeed(String urlString) {
        //refreshLayout.startRefresh(refreshListener);
        Parser parser = new Parser();
        parser.execute(urlString);
        parser.onFinish(new Parser.OnTaskCompleted() {
            //what to do when the parsing is done
            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                //list is an Array List with all article's information
                //set the adapter to recycler view
                ArrayList<Article> tmp = new ArrayList<>();
                tmp.addAll(articles);
                articles.clear();
                articles.addAll(list);
                articles.addAll(tmp);
                ArticleAdapter articleAdapter = new ArticleAdapter(articles, R.layout.article_row, getContext(),getFragmentManager());
                lvHomeType.setAdapter(articleAdapter);
            }

            //what to do in case of error
            @Override
            public void onError() {
                        refreshLayout.stopRefresh();
                        showError(mRootView, "Unable to load data.");
                        Log.i("Unable to load ", "articles");
            }
        });
    }


    @Override
    public void onSuccess(String data, int id) throws JSONException {
        JSONObject js = new JSONObject(data);
        switch (id) {
            default:
                break;
        }
        refreshLayout.stopRefresh();
    }

    @Override
    public void onFailure(String msg, String code, int id) throws JSONException {
        showError(mRootView, msg);
        refreshLayout.stopRefresh();
    }

    @Override
    public void onNetError(int id) {
        showNetError(mRootView);
        refreshLayout.stopRefresh();
    }

    /**
     * refresh listener
     */
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshArticles();
        }
    };


    /**
     * feeds popup
     */
    private void createFeedWindow() {
        View popupView = getLayoutInflater().inflate(R.layout.layout_popup_array, null);
        RecyclerView lvArray = popupView.findViewById(R.id.lv_array);
        lvArray.setLayoutManager(new LinearLayoutManager(getContext()));
        View outside = popupView.findViewById(R.id.layout_outside);
        handler = new DatabaseHandler(getContext());
        final LinearLayoutManager llm = (LinearLayoutManager) lvHomeType.getLayoutManager();
        feeds = handler.getAllFeeds();
        popupFeedsAdapter = new PopupFeedsAdapter(lvArray, feeds, R.layout.item_popup_array);
        popupFeedsAdapter.clear();
        feeds = handler.getAllFeeds();
        popupFeedsAdapter.addAll(feeds);
        popupFeedsAdapter.notifyDataSetChanged();
        popupFeedsAdapter.setOnItemClickListener(new OnItemClickListener<Feed>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Feed Feed, int position) {
                feedsWindow.dismiss();
                loadFeed(Feed.getLink());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Feed Feed, int position) {
                return false;
            }
        });

            feedsWindow = new MyPopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            feedsWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#50000000")));
            feedsWindow.setFocusable(true);
            feedsWindow.setOutsideTouchable(true);
            feedsWindow.update();
            feedsWindow.showAsDropDown(view_line);

        //dismiss PopupWindow
        outside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (feedsWindow.isShowing())
                    feedsWindow.dismiss();
            }
        });
    }

}
