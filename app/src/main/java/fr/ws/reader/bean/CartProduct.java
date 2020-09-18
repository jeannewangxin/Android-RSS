package fr.ws.reader.bean;

import java.io.Serializable;

/**
 * 产品 数据体
 */
public class CartProduct implements Serializable {

    private Integer product_id=0;
    private String name;
    private String price;
    private String img;
    private Integer qty=0;

    public CartProduct(Integer product_id, String name, String price, String img, Integer qty){

        this.product_id=product_id;
        this.name=name;
        this.price=price;
        this.img=img;
        this.qty=qty;
    }
    public CartProduct(){

    }
    public Integer getProductId() {
        return product_id;
    }

    public void setProductId(Integer product_id) {
        this.product_id = product_id;
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


    @Override
    public String toString() {
        return "Product{" +
                "product_id='" + product_id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", img='" + img + '\'' +
                ", qty='" + qty + '\'' +


                '}';
    }
}
