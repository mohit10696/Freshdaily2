package com.example.freshdaily.ui.MySubscription;

public class modelsub {
    String sid,userid,product_id,quantity,startdate,type;

    public modelsub(String sid, String userid, String product_id, String quantity, String startdate, String type) {
        this.sid = sid;
        this.userid = userid;
        this.product_id = product_id;
        this.quantity = quantity;
        this.startdate = startdate;
        this.type = type;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
