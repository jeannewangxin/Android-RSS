package fr.ws.reader.bean;

import java.io.Serializable;

/**
 * 产品 数据体
 */
public class Event implements Serializable {

    private Integer entity_id=0;
    private String customer_id;
    private String total;

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    private String actions;
    private String remark;
    private String created;

    public Event(Integer entity_id, String customer_id, String total, String remark, String created){

        this.entity_id=entity_id;
        this.customer_id=customer_id;
        this.total=total;
        this.remark=remark;
        this.created=created;
    }
    public Event(){

    }

    public Integer getEntityId() {
        return entity_id;
    }

    public void setEntityId(Integer entity_id) {
        this.entity_id = entity_id;
    }

    public String getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Product{" +
                "entity_id='" + entity_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", total='" + total + '\'' +
                ", remark='" + remark + '\'' +
                ", created='" + created + '\'' +


                '}';
    }
}
