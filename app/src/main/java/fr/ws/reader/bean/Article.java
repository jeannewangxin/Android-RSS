/*
*   Copyright 2016 Marco Gomiero
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*
*/

package fr.ws.reader.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Marco Gomiero on 12/02/2015.
 */
public class Article implements java.io.Serializable {

    private int _id;
    private String title;
    private String author;
    private String link;
    private Date pubDate;
    private String description;
    private String content;
    private String image;
    private String categories_string;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getLink() {
        return link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getcategories_string() {
        return categories_string;
    }

    public void setcategories_string(String categories_string) {
        this.categories_string = categories_string;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", pubDate=" + pubDate +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


    public static final String NAME = Article.class.getSimpleName().toUpperCase();
    public static final String _ID = "entity_id";
    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String IMAGE = "image";
    public static final String CATEGORY = "category";
    public static final String DESCIRPTION = "description";
    public static final String DATE = "date";
    public static final String PUBDATE = "pubdate";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NAME +
                    " ( " +
                    _ID + " integer primary key autoincrement," +
                    TITLE + " string," +
                    LINK + " string unique," +
                    IMAGE + " string," +
                    CATEGORY + " string," +
                    DESCIRPTION + " text," +
                    DATE + " string," +
                    PUBDATE + " string " +
                    " ); ";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NAME;
    public static String[] Columns = new String[]{_ID,TITLE,LINK,IMAGE,CATEGORY,DESCIRPTION,DATE,PUBDATE};
}