package fr.ws.reader.adapter;

import android.support.v7.widget.RecyclerView;

import fr.ws.reader.base.BaseRecyclerViewAdapter;
import fr.ws.reader.base.ViewHolderHelper;
import fr.ws.reader.bean.Category;

import java.util.List;

/**
 * 分类列表adapter
 */
public class CategoryAdapter extends BaseRecyclerViewAdapter<Category> {

    public CategoryAdapter(RecyclerView view, List<Category> dataList, int layoutId) {
        super(view, dataList, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, Category category, int position) {
       /* String base = Base64.encodeToString(cate.getBytes(), Base64.DEFAULT).replaceAll("\\r|\\n", "");
        String url = Constants.SERVER_IMAGE_ADDRESS + base + "/640/140";
        L.w("base: " + base);
        L.w("url: " + url);
        helper.setImage(R.id.iv_category, url);*/
    }
}
