

package fr.ws.reader.bean;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Feed {

    public Feed(int entity_id, String title, String link, String description, String image, String categories_string,int if_sub) {
        this.entity_id = entity_id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.image = image;
        this.categories_string = categories_string;
        this.if_sub = if_sub;

    }

    private int entity_id;
    private String title;
    private String link;
    private String description;
    private String image;
    private String categories_string;
    private int if_sub=0;

    public int getIf_sub() {
        return if_sub;
    }

    public void setIf_sub(int if_sub) {
        this.if_sub = if_sub;
    }



    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategories_string() {
        return categories_string;
    }

    public void setCategories_string(String categories_string) {
        this.categories_string = categories_string;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", categories_string=" + categories_string +
                ", if_sub=" + if_sub +
                '}';
    }


    public static final String NAME = Feed.class.getSimpleName().toUpperCase();
    public static final String _ID = "entity_id";
    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String IMAGE = "image";
    public static final String CATEGORY = "category";
    public static final String DESCIRPTION = "description";
    public static final String IfSUB = "if_sub";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NAME +
                    " ( " +
                    _ID + " integer primary key, " +
                    TITLE + " string," +
                    LINK + " string unique," +
                    IMAGE + " string," +
                    CATEGORY + " string," +
                    DESCIRPTION + " string," +
                    IfSUB + " INTEGER" +
                    " ); ";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NAME;
    public static String[] Columns = new String[]{_ID,TITLE,LINK,IMAGE,CATEGORY,DESCIRPTION,IfSUB};


}