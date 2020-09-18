package fr.ws.reader.view;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fr.ws.reader.R;

/**
 * 全局标题栏自定义View
 */
public class TitleBar {

    private final Activity mContext;
    private View mView, viewLine;
    private TextView tvMainTitle, tvSubTitle;
    private RelativeLayout btnLeft, titleLayout;
    private ImageView ivBack;

    public TitleBar(Activity context, View view) {
        this.mContext = context;
        this.mView = view;
        if (view != null) {
            //整体布局
            titleLayout = view.findViewById(R.id.title_layout);
            tvMainTitle = view.findViewById(R.id.tv_main_title);
            tvSubTitle = view.findViewById(R.id.tv_sub_title);
            btnLeft = view.findViewById(R.id.btn_title_left);
            ivBack = view.findViewById(R.id.iv_back);
            viewLine = view.findViewById(R.id.view_line);
        }
    }

    /**
     * 设置主标题
     *
     * @param title        标题
     * @param showBackIcon 返回按钮的显示
     * @param listener     返回监听
     * @return
     */
    public TitleBar setCustomTitle(String title, boolean showBackIcon, OnClickListener listener) {
        titleLayout.setBackgroundColor(mContext.getResources().getColor(R.color.title_background));
        tvMainTitle.setText(title);
        if (!showBackIcon) {
            btnLeft.setVisibility(View.GONE);
        }
        if (listener == null) {
            this.btnLeft.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.finish();
                }
            });
        } else {
            this.btnLeft.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置副标题
     *
     * @param title 标题
     * @return
     */
    public TitleBar setSubTitle(String title) {
        tvSubTitle.setVisibility(View.VISIBLE);
        tvSubTitle.setText(title);
        return this;
    }

    /**
     * 设置左侧点击按钮（图片）
     *
     * @param id
     * @returnR
     */
    public TitleBar setBackButton(int id) {
        ivBack.setImageResource(id);
        return this;
    }

    /**
     * 设置标题颜色
     *
     * @return CustomTitle
     */
    public TitleBar setTitleTextColor(int colorResource) {
        tvMainTitle.setTextColor(mContext.getResources().getColor(colorResource));
        return this;
    }

    /**
     * 设置标题栏背景透明
     *
     * @return CustomTitle
     */
    public TitleBar setBackgroundTrans() {
        mView.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
        viewLine.setVisibility(View.GONE);
        return this;
    }

    /**
     * 设置标题栏背景颜色
     *
     * @param colorId 颜色的id
     * @return
     */
    public TitleBar setBackgroundColor(int colorId) {
        mView.setBackgroundColor(mContext.getResources().getColor(colorId));
        return this;
    }

    /**
     * 设置标题栏背景资源id
     *
     * @param resourceId 资源id
     * @return
     */
    public TitleBar setBackgroundResource(int resourceId) {
        mView.setBackgroundResource(resourceId);
        return this;
    }

    /**
     * 设置标题栏背景透明度
     *
     * @param alpha
     * @return
     */
    public TitleBar setBackgroundAlpha(float alpha) {
        mView.getBackground().setAlpha((int) alpha);
        return this;
    }

    /**
     * 显示/隐藏标题栏布局
     *
     * @param visible
     * @return
     */
    public TitleBar setTitleLayoutVisible(boolean visible) {
        if (visible) {
            titleLayout.setVisibility(View.VISIBLE);
        } else {
            titleLayout.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    /**
     * 设置标题栏最下面的线
     *
     * @param visible
     * @return
     */
    public TitleBar setViewLine(boolean visible) {
        if (visible)
            viewLine.setVisibility(View.VISIBLE);
        else
            viewLine.setVisibility(View.GONE);
        return this;
    }

    /**
     * 设置标题字母是否为大写
     *
     * @param isCaps
     * @return
     */
    public TitleBar setTitleAllCaps(boolean isCaps) {
        tvMainTitle.setAllCaps(isCaps);
        return this;
    }
}