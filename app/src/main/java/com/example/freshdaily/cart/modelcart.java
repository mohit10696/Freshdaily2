package com.example.freshdaily.cart;

public class modelcart {
    public modelcart(String productname, String company, String count, String quantity, String price,String image) {
        this.productname = productname;
        this.company = company;
        this.count = count;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    String productname;
    String company;
    String count;
    String quantity;
    String price;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
