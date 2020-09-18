package fr.ws.reader.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import fr.ws.reader.R;
import fr.ws.reader.base.BaseRecyclerViewAdapter;
import fr.ws.reader.base.ViewHolderHelper;
import fr.ws.reader.bean.User;

/**
 * 产品收藏者列表adapter
 * iv_favoriter_head  tv_favoriter_name
 */
public class ProductFavoriterAdapter extends BaseRecyclerViewAdapter<User> {

    public ProductFavoriterAdapter(RecyclerView view, List<User> dataList, int layoutId) {
        super(view, dataList, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, User user, int position) {
        //helper.setText(R.id.tv_favoriter_name, user.getFirstname());
        //helper.setImageResource(R.id.iv_favoriter_head, R.drawable.default_portrait);
    }
}
