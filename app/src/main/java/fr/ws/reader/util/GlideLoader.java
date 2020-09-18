package fr.ws.reader.util;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import fr.ws.reader.R;
import fr.ws.reader.base.BaseActivity;

/**
 * Glide网络图片加载(加载网络图片的方法)
 */
public class GlideLoader {

    /**
     * 加载图片url(长图)
     *
     * @param url       图片url
     * @param imageView 图片加载控件
     */
    public static void setImageLarge(Context context, String url, final ImageView imageView) {
        if (context != null && !((BaseActivity) context).isFinishing())
            if (url != null) {
                Glide.with(context).load(url).centerCrop().placeholder(R.mipmap.default_pic)
                        .error(R.mipmap.default_pic).into(imageView);
            }
    }

    /**
     * 加载图片url(长图)
     *
     * @param url       图片url
     * @param imageView 图片加载控件
     */
    public static void setImage(Context context, String url, final ImageView imageView) {
        if (context != null && !((BaseActivity) context).isFinishing())
            if (url != null) {
                Glide.with(context).load(url).centerCrop().placeholder(Color.parseColor("#eeeeee"))
                        .error(Color.parseColor("#eeeeee")).into(imageView);
            }
    }

    /**
     * 加载图片url(长图)
     *
     * @param url       图片url
     * @param imageView 图片加载控件
     */
    public static void setImageThumb(Context context, String url, final ImageView imageView) {
        if (context != null && !((BaseActivity) context).isFinishing())
            if (url != null) {
                Glide.with(context).load(url).thumbnail(0.8f).placeholder(R.mipmap.default_pic)
                        .error(R.mipmap.default_pic).into(imageView);
            }
    }
}