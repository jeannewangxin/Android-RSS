
package fr.ws.reader.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import fr.ws.reader.R;
import fr.ws.reader.ui.dialog.deleteArticleDialogFragment;
import fr.ws.reader.bean.Article;
import fr.ws.reader.util.DatabaseHandler;
import fr.ws.reader.util.mplrssProvider;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private ArrayList<Article> articles;
    protected deleteArticleDialogFragment deleteArticleDialogFragment_m;
    private int rowLayout;
    private Context mContext;
    private WebView articleView;
    private FragmentManager mfm;

    public ArticleAdapter(ArrayList<Article> list, int rowLayout, Context context, FragmentManager fm) {
        this.articles = list;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.mfm=fm;
    }

    public ArticleAdapter(ArrayList<Article> list, int rowLayout) {
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

        Picasso.get()
                .load(currentArticle.getImage())
                //.centerInside()
                .into(viewHolder.article_image);

        viewHolder.pubDate.setText(pubDateString);
        viewHolder.category.setText(currentArticle.getcategories_string ());

        viewHolder.btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = articles.get(viewHolder.getAdapterPosition()).getTitle();
                String description = articles.get(viewHolder.getAdapterPosition()).getDescription();
                String link = articles.get(viewHolder.getAdapterPosition()).getLink();
                Date date = articles.get(viewHolder.getAdapterPosition()).getPubDate();
                String image = articles.get(viewHolder.getAdapterPosition()).getImage();
                ArrayList<Article> list = new ArrayList<Article>();
                Article newArticle = new Article();
                newArticle.setTitle(title);
                newArticle.setDescription(description);
                newArticle.setLink(link);
                newArticle.setImage(image);
                newArticle.setPubDate(date);
                newArticle.getcategories_string ();
                DatabaseHandler handler = new DatabaseHandler(mContext);
                if(handler.saveArticle(newArticle)>0){
                    Toast.makeText(mContext,"Save Success!",Toast.LENGTH_SHORT).show();
                    viewHolder.btn_download.setVisibility(View.INVISIBLE);
                    updaterss(list);
                }else{
                    Toast.makeText(mContext,"Save failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //show article content inside a dialog
                articleView = new WebView(mContext);

                articleView.getSettings().setLoadWithOverviewMode(true);

                String title = articles.get(viewHolder.getAdapterPosition()).getTitle();
                String content = articles.get(viewHolder.getAdapterPosition()).getDescription();
                String link = articles.get(viewHolder.getAdapterPosition()).getLink();
                articleView.getSettings().setJavaScriptEnabled(true);
                articleView.setHorizontalScrollBarEnabled(false);
                articleView.setWebChromeClient(new WebChromeClient());
                articleView.loadDataWithBaseURL(null, "<style>img{display: inline; height: auto; max-width: 100%;} " +

                        "</style>\n" + "<style>iframe{ height: auto; width: auto;}" + "</style>\n" + "</br><a href="+link +">Lire l'article sur une page web</a></br>"+content, null, "utf-8", null);

                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(mContext).create();
                alertDialog.setTitle(title);
                alertDialog.setView(articleView);
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


    public void updaterss(ArrayList<Article> list){
        ContentValues[] cvArray = new ContentValues[1];
        int i=0;
        for(Article article : list) {
            ContentValues values = new ContentValues();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
            String pubDateString = dateFormat.format(article.getPubDate());
            values.put(Article.TITLE, article.getTitle());
            values.put(Article.LINK, article.getLink());
            values.put(Article.IMAGE, article.getImage());
            values.put(Article.DESCIRPTION, article.getDescription());
            values.put(Article.PUBDATE,pubDateString);

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
            Date now = new Date();
            String strDate = sdfDate.format(now);
            values.put(Article.DATE, strDate);
            values.put(Article.CATEGORY, article.getcategories_string ());
            cvArray[i] = values;
            i++;
        }
        //mContext.getContentResolver().bulkInsert(mplrssProvider.urlForItems(Article.NAME,0), cvArray);

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