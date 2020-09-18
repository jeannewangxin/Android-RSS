package fr.ws.reader.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 产品 数据体
 */
public class Product implements Serializable {

    private Integer entity_id=0;
    private String name;
    private String price;
    private String img;
    private Integer qty=0;
    private List<Images> images;

    public Product(Integer entity_id,String name,String price,String img,Integer qty){

        this.entity_id=entity_id;
        this.name=name;
        this.price=price;
        this.img=img;
        this.qty=qty;
    }
    public Product(){

    }
    public Integer getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(Integer entity_id) {
        this.entity_id = entity_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public static class Images implements Serializable {

        private String entity_id;
        private String store_id;
        private String attribute_set_id;
        private String type_id;
        private String product_id;
        private String value;
        private String position;
        private Object lable;

        public String getEntity_id() {
            return entity_id;
        }

        public void setEntity_id(String entity_id) {
            this.entity_id = entity_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getAttribute_set_id() {
            return attribute_set_id;
        }

        public void setAttribute_set_id(String attribute_set_id) {
            this.attribute_set_id = attribute_set_id;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public Object getLable() {
            return lable;
        }

        public void setLable(Object lable) {
            this.lable = lable;
        }

        @Override
        public String toString() {
            return "Images{" +
                    "entity_id='" + entity_id + '\'' +
                    ", store_id='" + store_id + '\'' +
                    ", attribute_set_id='" + attribute_set_id + '\'' +
                    ", type_id='" + type_id + '\'' +
                    ", product_id='" + product_id + '\'' +
                    ", value='" + value + '\'' +
                    ", position='" + position + '\'' +
                    ", lable=" + lable +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "entity_id='" + entity_id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", img='" + img + '\'' +
                ", qty='" + qty + '\'' +


                '}';
    }
}
