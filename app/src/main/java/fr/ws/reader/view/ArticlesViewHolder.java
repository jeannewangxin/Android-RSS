package fr.ws.reader.view;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.ws.reader.R;
import fr.ws.reader.bean.Article;
import fr.ws.reader.ui.fragment.deleteArticleDialogFragment;

public class ArticlesViewHolder extends RecyclerView.ViewHolder  {
    protected deleteArticleDialogFragment deleteArticleDialogFragment_m;
    public TextView title;
    public TextView categories;
    public TextView pubdate;

    private Context mContext;
    private FragmentManager mfm;
    private Cursor mc;
    private int _id;
    public ArticlesViewHolder(View itemView, FragmentManager fm, Context context) {
        super(itemView);
        mContext=context;
        mfm=fm;
        title = (TextView) itemView.findViewById(R.id.title);
        categories = (TextView) itemView.findViewById(R.id.categories);
        pubdate = (TextView) itemView.findViewById(R.id.pubDate);

//        Button delete=(Button)itemView.findViewById(R.id.btn_delete);
//        delete.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                deleteArticleDialogFragment_m = deleteArticleDialogFragment.newInstance(_id);
//                deleteArticleDialogFragment_m.show(mfm, "fragment_delete_link");
//            }
//        });

    }



    public void setData(Cursor c) {
        mc=c;
        title.setText(mc.getString(mc.getColumnIndex(Article.TITLE)));
        categories.setText(mc.getString(mc.getColumnIndex(Article.CATEGORY)));
        pubdate.setText(mc.getString(mc.getColumnIndex(Article.PUBDATE)));
        _id=mc.getInt(mc.getColumnIndex(Article._ID));


    }
}