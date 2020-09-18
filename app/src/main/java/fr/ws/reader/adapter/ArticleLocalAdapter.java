
package fr.ws.reader.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import fr.ws.reader.R;
import fr.ws.reader.bean.Article;
import fr.ws.reader.ui.dialog.deleteArticleDialogFragment;
import fr.ws.reader.util.DatabaseHandler;


public class ArticleLocalAdapter extends RecyclerView.Adapter<ArticleLocalAdapter.ViewHolder> {

    private ArrayList<Article> articles;
    protected deleteArticleDialogFragment deleteArticleDialogFragment_m;
    private int rowLayout;
    private Context mContext;
    private FragmentManager mfm;

    public ArticleLocalAdapter(ArrayList<Article> list, int rowLayout, Context context, FragmentManager fm) {
        this.articles = list;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.mfm=fm;
    }

    public ArticleLocalAdapter(ArrayList<Article> list, int rowLayout) {
        this.articles = list;
        this.rowLayout = rowLayout;
    }

    public void clearData() {
        if (articles != null)
            articles.clear();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

        final Article currentArticle = articles.get(position);

        Locale.setDefault(Locale.getDefault());
        Date date = currentArticle.getPubDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        final String pubDateString = sdf.format(date);
        viewHolder.title.setText(currentArticle.getTitle());
        viewHolder.pubDate.setText(pubDateString);
        viewHolder.category.setText(currentArticle.getcategories_string ());
        viewHolder.btn_download.setVisibility(View.INVISIBLE);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //show article content inside a dialog
                View v = new View(mContext);
                final TextView tv = new TextView(mContext);
                tv.setTextSize(18);
                tv.setTextColor(mContext.getResources().getColor(R.color.color_black));
                tv.setGravity(Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL);
                tv.setMovementMethod(new ScrollingMovementMethod());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tv.setText(Html.fromHtml(currentArticle.getDescription(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    tv.setText(Html.fromHtml(currentArticle.getDescription()));
                }
                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(mContext).create();
                alertDialog.setTitle(currentArticle.getTitle());
                alertDialog.setView(tv);
                alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

                ((TextView) alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }



    @Override
    public int getItemCount() {

        return articles == null ? 0 : articles.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView pubDate;
        ImageView article_image;
        TextView category;
        Button btn_download;

        public ViewHolder(View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.title);
            pubDate = itemView.findViewById(R.id.pubDate);
            article_image = itemView.findViewById(R.id.article_image);
            category = itemView.findViewById(R.id.categories);
            btn_download=itemView.findViewById(R.id.btn_download);
        }
    }
}