package fr.ws.reader.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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

import fr.ws.reader.R;
import fr.ws.reader.bean.Feed;
import fr.ws.reader.ui.fragment.deleteFeedDialogFragment;
import fr.ws.reader.util.mplrssProvider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;



public class FeedAdapter extends RecyclerView.Adapter<fr.ws.reader.adapter.FeedAdapter.ViewHolder> {

    private ArrayList<Feed> Feeds;
    protected deleteFeedDialogFragment deleteFeedDialogFragment_m;
    private int rowLayout;
    private Context mContext;
    private WebView FeedView;
    private FragmentManager mfm;

    public FeedAdapter(ArrayList<Feed> list, int rowLayout, Context context, FragmentManager fm) {
        this.Feeds = list;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.mfm=fm;
    }

    public void clearData() {
        if (Feeds != null)
            Feeds.clear();
    }

    @NonNull
    @Override
    public fr.ws.reader.adapter.FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new fr.ws.reader.adapter.FeedAdapter.ViewHolder (v);
    }


    @Override
    public void onBindViewHolder(@NonNull final fr.ws.reader.adapter.FeedAdapter.ViewHolder viewHolder, int position) {

        final Feed currentFeed = Feeds.get(position);

        Locale.setDefault(Locale.getDefault());

        viewHolder.title.setText(currentFeed.getTitle());

        Picasso.get()
                .load(currentFeed.getImage())
                //.centerInside()
                .into(viewHolder.Feed_image);

        viewHolder.category.setText(currentFeed.getCategories_string());


        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteFeedDialogFragment_m = deleteFeedDialogFragment.newInstance(currentFeed.getEntity_id ());
                deleteFeedDialogFragment_m.show(mfm, "fragment_delete_link");
            }
        });


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //show Feed content inside a dialog
                FeedView = new WebView(mContext);

                FeedView.getSettings().setLoadWithOverviewMode(true);

                String title = Feeds.get(viewHolder.getAdapterPosition()).getTitle();
                String content = Feeds.get(viewHolder.getAdapterPosition()).getDescription();
                String link = Feeds.get(viewHolder.getAdapterPosition()).getLink();
                FeedView.getSettings().setJavaScriptEnabled(true);
                FeedView.setHorizontalScrollBarEnabled(false);
                FeedView.setWebChromeClient(new WebChromeClient());
                FeedView.loadDataWithBaseURL(null, "<style>img{display: inline; height: auto; max-width: 100%;} " +

                        "</style>\n" + "<style>iframe{ height: auto; width: auto;}" + "</style>\n" + "</br><a href="+link +">Lire l'Feed sur une page web</a></br>"+content, null, "utf-8", null);

                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(mContext).create();
                alertDialog.setTitle(title);
                alertDialog.setView(FeedView);
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

        return Feeds == null ? 0 : Feeds.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView pubDate;
        ImageView Feed_image;
        TextView category;
        Button btn_delete;

        public ViewHolder(View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.title);
            pubDate = itemView.findViewById(R.id.pubDate);
            Feed_image = itemView.findViewById(R.id.feed_image);
            category = itemView.findViewById(R.id.categories);
        }
    }
}