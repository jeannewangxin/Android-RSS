package fr.ws.reader.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fr.ws.reader.R;
import fr.ws.reader.adapter.ArticleAdapter;
import fr.ws.reader.adapter.FeedAdapter;
import fr.ws.reader.adapter.FeedSimpleAdapter;
import fr.ws.reader.base.BaseActivity;
import fr.ws.reader.bean.Article;
import fr.ws.reader.bean.Feed;
import fr.ws.reader.util.Parser;
import fr.ws.reader.view.MyPopupWindow;
import fr.ws.reader.view.MySwipeRefreshLayout;

public class FeedsArticleActivity extends BaseActivity{

    @BindView (R.id.main_title)
    TextView title;
    @BindView(R.id.lv_home_type)
    RecyclerView lvHomeType;
    @BindView(R.id.refresh_layout)
    MySwipeRefreshLayout refreshLayout;
    @BindView(R.id.view_line)
    View view_line;
    @BindView(R.id.tv_Feed)
    TextView tv_Feed;
    @BindView(R.id.btn_Feed)
    LinearLayout btn_Feed;

    private MyPopupWindow feedsWindow;
    private FeedSimpleAdapter FeedListAdapter;
    private List<Feed> feeds;
    private FeedAdapter feedAdapter;
    private ArticleAdapter articleAdapter;
    private String ti;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds_article);
        Intent intent = getIntent();
         ti= intent.getStringExtra("title");
        title.setText (ti);
         url = intent.getStringExtra("url");
        loadFeed (url);
    }


    /**
     * 初始化UI控件
     */
    @Override
    protected void initView() {
        refreshLayout.setOnRefreshListener(refreshListener);
        lvHomeType.setLayoutManager(new LinearLayoutManager (this));
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fontello.ttf");
        tv_Feed.setTypeface(font);
        tv_Feed.setText("\ue819");
    }

    public void goBack(View v){
        finish();
    }
    /**
     * refresh listener
     */
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadFeed(url);
        }
    };

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
                articleAdapter = new ArticleAdapter(list, R.layout.article_row);
                lvHomeType.setAdapter(articleAdapter);
                refreshLayout.stopRefresh();
            }

            //what to do in case of error
            @Override
            public void onError() {
                refreshLayout.stopRefresh();
//                showError(mRootView, "Unable to load data.");
                Log.i("Unable to load ", "articles");
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
