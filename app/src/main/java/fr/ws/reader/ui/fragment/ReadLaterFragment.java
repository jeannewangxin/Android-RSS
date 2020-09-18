package fr.ws.reader.ui.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.nfc.Tag;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fr.ws.reader.R;
import fr.ws.reader.adapter.ArticleAdapter;
import fr.ws.reader.adapter.ArticleLocalAdapter;
import fr.ws.reader.base.BaseFragment;
import fr.ws.reader.bean.Article;;
import fr.ws.reader.ui.dialog.deleteArticleDialogFragment;
import fr.ws.reader.util.DatabaseHandler;
import fr.ws.reader.util.mplrssProvider;
import fr.ws.reader.view.MySwipeRefreshLayout;


/**
 * 首页fragment
 */
public class ReadLaterFragment extends BaseFragment implements deleteArticleDialogFragment.DeleteArticleDialogListener, DeleteArticlesByDateDialogFragment.DeleteArticlesByDateDialogListener {

    private static final String TAG = "ReadLaterFragment";
    @BindView(R.id.lv_readerlater)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    MySwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.view_line)
    View view_line;
    private List<Article> articles;
    private ArticleLocalAdapter articleAdapter;
    private Context mcontext;
    protected DeleteArticlesByDateDialogFragment deleteArticlesByDateDialogFragment_m;
    private DatabaseHandler handler;
    private FragmentManager fm ;
    private ArrayList<Article> list;
    /**
     * 初始化layout的id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_readlater;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout.setOnRefreshListener(refreshListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private int getItemsCountLocal() {
        int itemsCount = 0;

        Cursor query = mcontext.getContentResolver().query(mplrssProvider.urlForItems(Article.NAME, 0), null, null, null, null);
        if (query != null) {
            itemsCount = query.getCount();
            query.close();
        }
        return itemsCount;
    }


    @Override
    protected void initData() {
        mcontext = getContext();
        fm= getFragmentManager();
        handler = new DatabaseHandler(mcontext);
        list = handler.getAllArticles();
        articleAdapter = new ArticleLocalAdapter(list, R.layout.article_row, mcontext, fm);
        mRecyclerView.setAdapter(articleAdapter);
        mSwipeRefreshLayout.stopRefresh();
    }

    private void refreshData(){
        if(list!=null && list.size()>0){list.clear();}
        if(list!=null && handler!=null && handler.getAllArticles().size()>0)list.addAll(handler.getAllArticles());
        Log.d(TAG,"data here:"+list.toArray().length);
        articleAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.stopRefresh();
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();
        }
    };


    @Override
    public void onFinishEditDialog(int article_id) {
        if (article_id > 0) {
            String[] arguments = new String[1];
            arguments[0] = article_id + "";
            String selectionclause = "_ID = ?";
            mcontext.getContentResolver().delete(mplrssProvider.urlForItems(Article.NAME, 0), selectionclause, arguments);
            Log.d(TAG, "Supression of article id : " + article_id);
//                restart();
        }

    }

    public void deleteArticleByDate(String date_b, String date_e) throws ParseException {
        String[] arguments = new String[2];
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        SimpleDateFormat Date_begin = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat Date_end = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        String date_b_m = sdfDate.format(Date_begin.parse(date_b));
        String date_e_m = sdfDate.format(sdfDate.parse(date_e + " 23:59:59"));
        arguments[0] = date_b_m;
        arguments[1] = date_e_m;
        String selectionclause = "date > ? AND date < ?";
        mcontext.getContentResolver().delete(mplrssProvider.urlForItems(Article.NAME, 0), selectionclause, arguments);
        Log.d(TAG, "Supression of article date between : " + date_b + " and " + date_b);
    }

    public void cancelEditDate(View v) {
        deleteArticlesByDateDialogFragment_m.dismiss();
    }

    public void finishEditDate(View v) throws ParseException {
        EditText date_b = (EditText) deleteArticlesByDateDialogFragment_m.getDialog().findViewById(R.id.date_b);
        EditText date_e = (EditText) deleteArticlesByDateDialogFragment_m.getDialog().findViewById(R.id.date_e);
        if (date_b != null && date_e != null) {
            deleteArticleByDate(date_b.getText().toString(), date_e.getText().toString());
        }
        deleteArticlesByDateDialogFragment_m.dismiss();
//            restart();
    }

    @Override
    public void onFinishEditDateByDateDialog(String inputText) {

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
