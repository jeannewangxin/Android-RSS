package fr.ws.reader.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
public class HomeType implements Serializable {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
