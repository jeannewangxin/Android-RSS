package fr.ws.reader.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.ws.reader.R;
import fr.ws.reader.base.BaseRecyclerViewAdapter;
import fr.ws.reader.base.ViewHolderHelper;
import fr.ws.reader.bean.Category;
import fr.ws.reader.bean.Feed;
import fr.ws.reader.bean.Product;

/**
 * 分类列表adapter
 */
public class FeedSimpleAdapter extends BaseRecyclerViewAdapter<Feed>{

    private Context context;
    private RecyclerView lv_product_type;
    public FeedSimpleAdapter(RecyclerView view, ArrayList<Feed> dataList, int layoutId, Context context) {
        super(view, dataList, layoutId);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderHelper helper, Feed feed, int position) {

        helper.setText(R.id.tv_Feed, feed.getTitle());
        helper.setVisible(R.id.iv_right, false);
        //helper.setVisible(R.id.iv_choose, feed.isChoose());
        lv_product_type = (RecyclerView)helper.getView(R.id.lv_product_type);
        lv_product_type.setLayoutManager(new LinearLayoutManager(context));

    }

}
