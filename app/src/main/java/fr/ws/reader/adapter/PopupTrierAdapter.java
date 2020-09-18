package fr.ws.reader.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import fr.ws.reader.R;
import fr.ws.reader.base.BaseRecyclerViewAdapter;
import fr.ws.reader.base.ViewHolderHelper;
import fr.ws.reader.bean.Trier;

/**
 * 筛选adapter
 */
public class PopupTrierAdapter extends BaseRecyclerViewAdapter<Trier> {

    public PopupTrierAdapter(RecyclerView view, List<Trier> dataList, int layoutId) {
        super(view, dataList, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, Trier trier, int position) {
        helper.setText(R.id.tv_content, trier.getName());
    }
}
