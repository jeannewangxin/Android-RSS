package fr.ws.reader.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.List;

/**
 * 产品分类
 */
public class Category implements Serializable {

    private String entity_id;
    private String name;
    private List<Product> list;
    //额外属性
    private boolean isChoose;   //是否选中

    public Category() {
    }

    public Category(String entity_id) {
        this.entity_id = entity_id;
    }

    public Category(String entity_id,String name) {
        this.entity_id = entity_id;
        this.name = name;
    }



    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(String list) {
            this.list = getGson().fromJson(
                    list, new TypeToken<List<Product>>() {
                    }.getType());
    }

    public Gson getGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
    @Override
    public boolean equals(Object obj) {
        return entity_id.equals(((Category) obj).getEntity_id());
    }

    @Override
    public String toString() {
        return "Category{" +
                "entity_id='" + entity_id + '\'' +
                ", name='" + name + '\'' +
                ", list='" + list.toString() + '\'' +
                ", isChoose=" + isChoose +
                '}';
    }

    public String test(){
        return list.get(0).getName();
    }
}
