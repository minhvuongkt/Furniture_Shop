package com.nhom13.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {

    int id;
    String name, detail;
    int price;
    String photo;
    boolean isSelling;
    int categoryID;

    public Product(int id, String name, String detail, int price, String photo, boolean isSelling, int categoryID) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.photo = photo;
        this.isSelling = isSelling;
        this.categoryID = categoryID;
    }
    public Product(String name, String detail, int price, String photo, boolean isSelling, int categoryID) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.photo = photo;
        this.isSelling = isSelling;
        this.categoryID = categoryID;
    }
    public Product(ResultSet rs) throws SQLException {
        this.id = rs.getInt("ProductID");
        this.name = rs.getString("Name");
        this.detail = rs.getString("Detail");
        this.price = rs.getInt("Price");
        this.photo = rs.getString("Photo");
        this.isSelling = rs.getBoolean("IsSelling");
        this.categoryID = rs.getInt("CategoryID");
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setIsSelling(boolean isSelling) {
        this.isSelling = isSelling;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public int getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public boolean getIsSelling() {
        return isSelling;
    }

    public int getCategoryID() {
        return categoryID;
    }
}
