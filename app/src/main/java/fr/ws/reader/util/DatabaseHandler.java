package fr.ws.reader.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.ws.reader.bean.Article;
import fr.ws.reader.bean.Feed;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "READER_DB";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Feed.CREATE_TABLE);
        db.execSQL(Article.CREATE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(Feed.DROP_TABLE);
            db.execSQL(Article.DROP_TABLE);
            onCreate(db);
        }
    }

    // DELETE DB
    public void deleteDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(Feed.DROP_TABLE);
        db.execSQL(Article.DROP_TABLE);
        //onCreate(db);
        db.close();
    }
    // Getting article Count
    public int getProductsCount() {
        String countQuery = "SELECT  * FROM " + Article.NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }


    // code to add the new article
    public long saveArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println ("============================================saveArticle");
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        String pubDateString = dateFormat.format(article.getPubDate());
        values.put(Article.TITLE, article.getTitle());
        values.put(Article.DESCIRPTION, article.getDescription());
        values.put(Article.DATE, pubDateString);
        values.put(Article.LINK, article.getLink());
        values.put(Article.IMAGE, article.getImage());
        // Inserting Row
        long id = db.insert(Article.NAME, null, values);
        db.close(); // Closing database connection
        return id;
    }

    // code to add the new article
    public long SubFeed(Feed feed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Feed._ID, feed.getEntity_id());
        values.put(Feed.TITLE, feed.getTitle());
        values.put(Feed.LINK, feed.getLink());
        values.put(Feed.IMAGE, feed.getImage());
        values.put(Feed.DESCIRPTION, feed.getDescription());
        values.put(Feed.CATEGORY, feed.getCategories_string());
        values.put(Feed.IfSUB, feed.getIf_sub());
        // Inserting Row
        long id = db.insert(Feed.NAME, null, values);
        db.close(); // Closing database connection
        return id;
    }
    public  boolean desFeed(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean i = db.delete(Feed.NAME , Feed._ID + "=" + id, null) > 0;
        db.close();
        return i;
    }

    public  ArrayList<Article> getAllArticles() {
        ArrayList<Article> contact_list = new  ArrayList<Article>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String selectQuery = "SELECT  * FROM " + Article.NAME;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Article contact = new Article();
                    contact.setId(Integer.parseInt((cursor.getString(0))));
                    contact.setTitle((cursor.getString(1)));
                    contact.setLink(cursor.getString(2));
                    contact.setImage(cursor.getString(3));
                    contact.setDescription(cursor.getString(5));
                    contact.setPubDate(new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa").parse(cursor.getString(6)));

                    contact_list.add(contact);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
        } catch (Exception e) {
           System.out.println("Erreur inconnnue!"+e.getMessage());
        }

        return contact_list;
    }


    public  ArrayList<Feed> getAllFeeds() {
        ArrayList<Feed> feed_list = new  ArrayList<Feed>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String selectQuery = "SELECT  * FROM " + Feed.NAME;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Feed feed = new Feed(Integer.parseInt((cursor.getString(0)))
                    ,cursor.getString(1)
                    ,cursor.getString(2)
                    ,cursor.getString(3)
                    ,cursor.getString(4)
                    ,cursor.getString(5),
                    Integer.parseInt((cursor.getString(6)))
                    );

                    feed_list.add(feed);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
        } catch (Exception e) {
            System.out.println("Erreur inconnnue!"+e.getMessage());
        }

        return feed_list;
    }


}
