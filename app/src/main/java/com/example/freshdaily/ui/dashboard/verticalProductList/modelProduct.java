package com.example.freshdaily.ui.dashboard.verticalProductList;

public class modelProduct {
        private String id;
        private String image;
        private String name;
        private String price;
        private String quntity;
        private String Company;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        private String desc;

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

        public modelProduct(String id,String image, String name, String price, String quntity, String company,String desc) {
            this.id = id;
            this.image = image;
            this.name = name;
            this.price = price;
            this.quntity = quntity;
            this.Company = company;
            this.desc = desc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
