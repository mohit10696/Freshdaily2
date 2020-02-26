package com.example.freshdaily.ui.dashboard.ProductList;

public class modelProduct {
    private String image;
    private String name;
    private String price;
    private String quntity;
    private String Company;

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getQuntity() {
        return quntity;
    }

    public void setQuntity(String quntity) {
        this.quntity = quntity;
    }

    public modelProduct(String image, String name, String price, String quntity, String company) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.quntity = quntity;
        Company = company;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
