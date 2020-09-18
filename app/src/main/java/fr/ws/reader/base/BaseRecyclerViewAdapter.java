package fr.ws.reader.base;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.joanzapata.iconify.Iconify;

import fr.ws.reader.adapter.RecommendAdapter;
import fr.ws.reader.interfaces.BaseAnimation;
import fr.ws.reader.interfaces.DataIO;
import fr.ws.reader.interfaces.OnItemClickListener;
import fr.ws.reader.util.AlphaInAnimation;
import fr.ws.reader.util.FontelloModule;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的基础父类
 *
 * @param <T>
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolderHelper> implements DataIO<T> {

    protected Context mContext;
    private int mLayoutId;
    protected RecyclerView mRecyclerView;
    protected List<T> mDataList = new ArrayList<>();
    protected OnItemClickListener mOnItemClickListener;
    //动画
    protected int mLastPosition = -1;
    private boolean mOpenAnimationEnable = true;
    private BaseAnimation mSelectAnimation = new AlphaInAnimation();
    private Interpolator mInterpolator = new LinearInterpolator();

    public BaseRecyclerViewAdapter(RecyclerView view, List<T> dataList, int layoutId) {
        if (view != null) {
            this.mContext = view.getContext();
            this.mRecyclerView = view;
            this.mLayoutId = layoutId;
            this.mDataList = dataList;
            mRecyclerView.setAdapter(this);
        }
        Iconify.with(new FontelloModule());
    }

    protected BaseRecyclerViewAdapter() {
    }

    @Override
    public ViewHolderHelper onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolderHelper viewHolder = ViewHolderHelper.get(mContext, null, parent, mLayoutId, -1);
        setListener(parent, viewHolder, viewType);
        return viewHolder;
    }

    private int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    private boolean isEnabled(int viewType) {
        return true;
    }


    protected void setListener(final ViewGroup parent, final ViewHolderHelper viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    mOnItemClickListener.onItemClick(parent, v, mDataList.get(position), position);
                }
            }
        });


        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    return mOnItemClickListener.onItemLongClick(parent, v, mDataList.get(position), position);
                }
                return false;
            }
        });
    }


    @Override
    public void onBindViewHolder(ViewHolderHelper holder, int position) {
        holder.updatePosition(position);
        //添加动画
        addAnimation(holder);
        convert(holder, mDataList.get(position), position);
    }

    public abstract void convert(ViewHolderHelper helper, T t, int position);


    /**
     * add animation when you want to show time
     *
     * @param holder
     */
    public void addAnimation(RecyclerView.ViewHolder holder) {
        if (mOpenAnimationEnable) {
            if (holder.getLayoutPosition() > mLastPosition) {
                BaseAnimation animation = null;
                if (mSelectAnimation != null) {
                    animation = mSelectAnimation;
                }
                for (Animator anim : animation.getAnimators(holder.itemView)) {
                    startAnim(anim, holder.getLayoutPosition());
                }
                mLastPosition = holder.getLayoutPosition();
            }
        }
    }

    /**
     * set anim to start when loading
     *
     * @param anim
     * @param index
     */
    protected void startAnim(Animator anim, int index) {
        anim.setDuration(300).start();
        anim.setInterpolator(mInterpolator);
    }

    /**
     * 设置动画
     *
     * @param animation ObjectAnimator
     */
    public void openLoadAnimation(BaseAnimation animation) {
        this.mOpenAnimationEnable = true;
        this.mSelectAnimation = animation;
    }

    /**
     * 关闭动画
     */
    public void closeLoadAnimation() {
        this.mOpenAnimationEnable = false;
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void add(T elem) {
        mDataList.add(elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAt(int location, T elem) {
        mDataList.add(location, elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<T> elements) {
        mDataList.addAll(elements);
        notifyItemChanged(mLastPosition, 10);
    }

    @Override
    public void addAllAt(int location, List<T> elements) {
        mDataList.addAll(location, elements);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T elem) {
        mDataList.remove(elem);
        notifyDataSetChanged();
    }

    @Override
    public void removeAt(int index) {
        mDataList.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public void removeAll(List<T> elements) {
        mDataList.removeAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        if (mDataList != null && mDataList.size() > 0) {
            mDataList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void replace(T oldElem, T newElem) {
        replaceAt(mDataList.indexOf(oldElem), newElem);
    }

    @Override
    public void replaceAt(int index, T elem) {
        mDataList.set(index, elem);
        notifyDataSetChanged();
    }

    @Override
    public void replaceAll(List<T> elements) {
        if (mDataList.size() > 0) {
            mDataList.clear();
        }
        mDataList.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public T get(int position) {
        if (position >= mDataList.size())
            return null;
        return mDataList.get(position);
    }

    @Override
    public List<T> getAll() {
        return mDataList;
    }

    @Override
    public int getSize() {
        return mDataList.size();
    }

    @Override
    public boolean contains(T elem) {
        return mDataList.contains(elem);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 无数据传递的跳转
     *
     * @param activity 目标activity
     */
    protected void startActivity(Class<?> activity) {
        Intent intent = new Intent(mContext, activity);
        mContext.startActivity(intent);
    }

    /**
     * 携带数据的跳转
     *
     * @param activity 目标activity
     * @param bundle   数据bundle
     */
    protected void startActivity(Class<?> activity, Bundle bundle) {
        Intent intent = new Intent(mContext, activity);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }
}