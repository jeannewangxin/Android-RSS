package fr.ws.reader.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.ws.reader.R;
import fr.ws.reader.app.MainApplication;
import fr.ws.reader.bean.Category;
import fr.ws.reader.bean.Feed;
import fr.ws.reader.ui.activity.FeedsArticleActivity;
import fr.ws.reader.ui.fragment.deleteFeedDialogFragment;
import fr.ws.reader.util.DatabaseHandler;
import fr.ws.reader.util.mplrssProvider;



public class RecommendAdapter extends RecyclerView.Adapter<fr.ws.reader.adapter.RecommendAdapter.ViewHolder> {

    private ArrayList<Feed> Feeds;
    protected deleteFeedDialogFragment deleteFeedDialogFragment_m;
    private int rowLayout;
    private Context mContext;
    private WebView FeedView;
    private FragmentManager mfm;

    public RecommendAdapter(ArrayList<Feed> list, int rowLayout, Context context, FragmentManager fm) {
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
    public fr.ws.reader.adapter.RecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new fr.ws.reader.adapter.RecommendAdapter.ViewHolder (v);
    }


    @Override
    public void onBindViewHolder(@NonNull final fr.ws.reader.adapter.RecommendAdapter.ViewHolder viewHolder, int position) {
        final DatabaseHandler handler = new DatabaseHandler(mContext);
        List<Feed> subs = handler.getAllFeeds();
        final Feed currentFeed = Feeds.get(position);

        Locale.setDefault(Locale.getDefault());

        viewHolder.title.setText(currentFeed.getTitle());
        for(Feed f : subs){
            if(f.getEntity_id()==currentFeed.getEntity_id()){
                viewHolder.btn_subscripted.setBackgroundResource(R.drawable.ic_file_liked);
                break;
            }
        }
        Picasso.get()
                .load(currentFeed.getImage())
                //.centerInside()
                .into(viewHolder.Feed_image);

        List<Category> cats = MainApplication.app.getCategories();
        String cat = "";
        for (Category c : cats){
            if(c.getEntity_id()==currentFeed.getCategories_string())cat=c.getName();
        }
        viewHolder.category.setText(cat);

        viewHolder.btn_subscripted.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Boolean sub = true;

                int id = Feeds.get(viewHolder.getAdapterPosition()).getEntity_id();
                String title = Feeds.get(viewHolder.getAdapterPosition()).getTitle();
                String link = Feeds.get(viewHolder.getAdapterPosition ()).getLink ();
                String des = Feeds.get(viewHolder.getAdapterPosition()).getDescription();
                String image = Feeds.get(viewHolder.getAdapterPosition()).getImage();
                String category = Feeds.get(viewHolder.getAdapterPosition()).getCategories_string();
                List<Feed> subs = handler.getAllFeeds();
                for(Feed f : subs){
                    if(f.getEntity_id()==id){
                        sub = false;
                        break;
                    }
                }
                if(sub) {
                    if(handler.SubFeed(new Feed(id,title,link,des,image,category,1))>0) {
                        viewHolder.btn_subscripted.setBackgroundResource(R.drawable.ic_file_liked);
                    }else{
                        return;
                    }
                }else{
                    if(handler.desFeed(id)) {
                        viewHolder.btn_subscripted.setBackgroundResource(R.drawable.ic_readlater_24);
                    }
                }

                //Toast.makeText (mContext,"Is subscripted",Toast.LENGTH_LONG).show ();
            }
        });


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FeedsArticleActivity.class);
                intent.putExtra ("url",Feeds.get(viewHolder.getAdapterPosition()).getLink());
                intent.putExtra ("title",Feeds.get(viewHolder.getAdapterPosition ()).getTitle ());
                mContext.startActivity (intent);

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
        Button btn_subscripted;

        public ViewHolder(View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.title);
            pubDate = itemView.findViewById(R.id.pubDate);
            Feed_image = itemView.findViewById(R.id.feed_image);
            category = itemView.findViewById(R.id.categories);
            btn_subscripted=itemView.findViewById(R.id.btn_subs);
        }
    }
}