package fr.ws.reader.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import fr.ws.reader.R;
import fr.ws.reader.base.BaseRecyclerViewAdapter;
import fr.ws.reader.base.ViewHolderHelper;
import fr.ws.reader.bean.Feed;

/**
 * 首页排序下拉菜单adapter
 * tv_content
 * iv_choose
 */
public class PopupFeedsAdapter extends BaseRecyclerViewAdapter<Feed> {

    public PopupFeedsAdapter(RecyclerView view, List<Feed> dataList, int layoutId) {
        super(view, dataList, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, Feed category, int position) {
        helper.setText(R.id.tv_content, category.getTitle());
    }
}
