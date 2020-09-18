package fr.ws.reader.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.ws.reader.adapter.RecommendAdapter;
import fr.ws.reader.interfaces.MultiItemTypeSupport;
import fr.ws.reader.interfaces.OnClickLoadMoreListener;

import java.util.List;

/**
 * 多种ItemType类型布局的RecyclerView
 *
 * @param <T> 实体类
 */
public abstract class MultiItemRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;
    protected static final int LIST_DATA = 0;
    protected static final int LOAD_MORE = -1;
    private OnClickLoadMoreListener onClickLoadMoreListener;
    private TextView textView;
    private LinearLayout layoutFooter;
    private boolean noData = false;
    private List moreList;


    public MultiItemRecyclerViewAdapter(RecyclerView view, List<T> dataList, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(view, dataList, -1);
        mMultiItemTypeSupport = multiItemTypeSupport;
        if (mMultiItemTypeSupport == null)
            throw new IllegalArgumentException("the mMultiItemTypeSupport can not be null.");
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getItemViewType(position, mDataList.get(position));
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolderHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mMultiItemTypeSupport == null)
            return super.onCreateViewHolder(parent, viewType);
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolderHelper holder = ViewHolderHelper.get(mContext, null, parent, layoutId, -1);
        setListener(parent, holder, viewType);
        return holder;
    }

    protected boolean finishLoad(List dataList) {
        return dataList != null && dataList.size() > 0 && dataList.size() < 10;
    }

    public void setOnClickLoadMoreListener(OnClickLoadMoreListener onClickLoadMoreListener) {
        this.onClickLoadMoreListener = onClickLoadMoreListener;
    }

    /**
     * 加载更多数据
     *
     * @param entityList
     * @param t
     */
    public void loadMoreList(List<T> entityList, T t) {
        this.moreList = entityList;
        if (entityList.size() == 0)
            noData = true;
        removeAt(getItemCount() - 1);
        addAll(entityList);
        add(t);
    }

}