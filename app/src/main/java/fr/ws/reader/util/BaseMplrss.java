package fr.ws.reader.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fr.ws.reader.bean.Article;
import fr.ws.reader.bean.Feed;

public class BaseMplrss extends SQLiteOpenHelper {

    public final static int VERSION = 1;
    public final static String DB_NAME = "base_mplrss";


    private static BaseMplrss ourInstance;

    public static BaseMplrss getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new BaseMplrss(context);
        return ourInstance;
    }

    private BaseMplrss(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Feed.CREATE_TABLE);
        db.execSQL(Article.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {

            db.execSQL(Feed.DROP_TABLE);
            db.execSQL(Article.DROP_TABLE);
            onCreate(db);
        }
    }
}
