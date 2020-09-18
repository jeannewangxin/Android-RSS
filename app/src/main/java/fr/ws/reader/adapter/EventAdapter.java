package fr.ws.reader.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import fr.ws.reader.R;
import fr.ws.reader.base.BaseRecyclerViewAdapter;
import fr.ws.reader.base.ViewHolderHelper;
import fr.ws.reader.bean.Event;
import fr.ws.reader.util.Utils;

/**
 * EVENT adapter
 */
public class EventAdapter extends BaseRecyclerViewAdapter<Event> {
    private Context context;
    public EventAdapter(RecyclerView view, List<Event> dataList, int layoutId, Context context) {
        super(view, dataList, layoutId);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderHelper helper, Event event, int position) {
        if (event != null) {
            TextView iv_card=helper.getView(R.id.iv_card);
            TextView tv_total=helper.getView(R.id.tv_total);
            Typeface font = Typeface.createFromAsset(context.getAssets(), "fontello.ttf");
            iv_card.setTypeface(font);
            iv_card.setText("\ue8bc");
            String pre="";
            if(Integer.parseInt(event.getActions())==1){
                pre="- ";
                tv_total.setTextColor(Color.RED);
            }else{
                pre="+ ";
                tv_total.setTextColor(Color.GREEN);
            }
            helper.setText(R.id.tv_date, event.getCreated()).setText(R.id.tv_total, pre+Utils.getPrice(event.getTotal()))
                    .setText(R.id.tv_remark, event.getRemark());
        }
    }
}
