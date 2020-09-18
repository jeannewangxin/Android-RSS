package fr.ws.reader.util;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import fr.ws.reader.bean.Article;
import fr.ws.reader.bean.Feed;

public class mplrssProvider extends ContentProvider {

    private static final String TAG = "mplrssProvider";
    private static final String LOG = "mplrssProvider";
    private SQLiteOpenHelper helper;
    private static  String authority="fr.reader.mplrssProvider";


    private static final int SOUSCRIPTION=1;
    private static final int ARTICLE=2;

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static Uri urlForItems(String table,int limit) {
        return Uri.parse("content://" + authority + "/" + table + "/offset/" + limit);
    }
    static {
        matcher.addURI(authority, Feed.NAME + "/offset/" + "#", SOUSCRIPTION);
        matcher.addURI(authority, Article.NAME + "/offset/" + "#", ARTICLE);
    }

    public mplrssProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.

        SQLiteDatabase db=helper.getWritableDatabase();
        int code =  matcher.match(uri);
        Log.d(LOG,"Uri="+ uri.toString());
        String path;
        long id;

        switch (code){
            case SOUSCRIPTION:
                id=db.delete(Feed.NAME,selection,selectionArgs);
                path = Feed.NAME;
                break;
            case ARTICLE:
                id=db.delete(Article.NAME,selection,selectionArgs);
                path = Article.NAME;
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        Uri.Builder builder = (new Uri.Builder()).authority(authority).appendPath(path);
        return 1;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.


         throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        SQLiteDatabase db=helper.getWritableDatabase();
        int code =  matcher.match(uri);
        //Log.d(LOG,"Uri="+ uri.toString());
        String path;
        long id;

        switch (code){
            case SOUSCRIPTION: {
                id=db.insert(Feed.NAME,null,values);
                path = Feed.NAME;
                break;
            }
            case ARTICLE: {
                id=db.insert(Article.NAME,null,values);
                path = Article.NAME;
                break;
            }
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        Uri.Builder builder = (new Uri.Builder()).authority(authority).appendPath(path);

        return ContentUris.appendId(builder,id).build();

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        this.helper=BaseMplrss.getInstance(getContext());
        return true;
    }

//    tableColumns, whereClause, whereArgs, groupBy, having, orderBy
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        SQLiteDatabase db = helper.getReadableDatabase();
        SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
        Cursor c = null;
        String offset;

        switch (matcher.match(uri)) {
            case SOUSCRIPTION: {
                sqb.setTables(Feed.NAME);
                offset = uri.getLastPathSegment();
                break;
            }
             case ARTICLE: {
                sqb.setTables(Article.NAME);
                offset = uri.getLastPathSegment();
                break;
            }
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }

        int intOffset = Integer.parseInt(offset);

        String limitArg = intOffset + ", " + 30;
        Log.d(TAG, "query: " + limitArg);
        c = sqb.query(db, projection, selection, selectionArgs, null, null, sortOrder, limitArg);

        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;



        /*
        SQLiteDatabase db=helper.getWritableDatabase();
        int code =  matcher.match(uri);
        Log.d(LOG,"Query Uri="+ uri.toString());

        Cursor cursor;
        switch (code) {
            case ALLFEEDS:
                cursor = db.query("feeds", null, null, null, null, null, null, null);
                return cursor;
            case ALLRSS:
                cursor = db.query("feeds", null, null, null, null, null, null, null);
                return cursor;
        }

        throw new UnsupportedOperationException("Not yet implemented");*/
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
