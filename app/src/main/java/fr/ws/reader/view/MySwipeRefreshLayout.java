package fr.ws.reader.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import fr.ws.reader.R;

/**
 * 刷新控件
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {

    private int mTouchSlop;
    private float mPrevX;

    public MySwipeRefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setColorSchemeResources(R.color.button_color, R.color.button_color, R.color.button_color, R.color.button_color);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 开始刷新
     */
    public void startRefresh(SwipeRefreshLayout.OnRefreshListener listener) {
        setRefreshing(true);
        listener.onRefresh();
    }

    /**
     * 停止刷新
     */
    public void stopRefresh() {
        setRefreshing(false);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevX = MotionEvent.obtain(event).getX();
                break;
            case MotionEvent.ACTION_MOVE:
                final float eventX = event.getX();
                float xDiff = Math.abs(eventX - mPrevX);
                if (xDiff > mTouchSlop) {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(event);
    }
}
