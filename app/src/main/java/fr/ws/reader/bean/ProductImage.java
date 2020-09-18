package fr.ws.reader.bean;

import java.io.Serializable;

/**
 * 产品图片（自定义数据）
 */
public class ProductImage implements Serializable {

    private String path;   //图片路径
    private String url;    //图片url
    private String id;    //图片的id

    public ProductImage() {
    }

    public ProductImage(String path, String url) {
        this.path = path;
        this.url = url;
    }

    public ProductImage(String path, String url, String id) {
        this.path = path;
        this.url = url;
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
