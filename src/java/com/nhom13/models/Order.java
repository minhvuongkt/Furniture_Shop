package com.nhom13.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import org.json.JSONArray;

public class Order {

    int id, userid, status, totalprice;
    Cart productitems;
    InfoOrder infoorder;
    Date timeorder;

    public int getTotalprice() {
        return totalprice;
    }

    public Cart getProductitems() {
        return productitems;
    }

    public InfoOrder getInfoorder() {
        return infoorder;
    }

    public Date getTimeorder() {
        return timeorder;
    }

    public int getUserid() {
        return userid;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimeorder(Date time) {
        this.timeorder = time;
    }

    public void setProductitems(Cart productitems) {
        this.productitems = productitems;
    }

    public void setInfoorder(InfoOrder infoorder) {
        this.infoorder = infoorder;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Order(int id, Cart productitems, InfoOrder infoorder, int userid, int totalprice, Date timeorder, int status) {
        this.id = id;
        this.productitems = productitems;
        this.infoorder = infoorder;
        this.userid = userid;
        this.status = status;
        this.totalprice = totalprice;
        this.timeorder = timeorder;
    }

    public Order(Cart productitems, InfoOrder infoorder, int userid, int totalprice, int status) {
        this.productitems = productitems;
        this.infoorder = infoorder;
        this.userid = userid;
        this.status = status;
        this.totalprice = totalprice;
    }

    public Order(ResultSet rs) throws SQLException {
        this.id = rs.getInt("ID");
        var productItems = rs.getString("ProductItems");
        var infoData = rs.getString("InfoOrder");
        this.userid = rs.getInt("UserID");
        this.totalprice = rs.getInt("TotalPrice");
        this.timeorder = rs.getDate("TimeOrder");
        this.status = rs.getInt("Status");

        this.productitems = new Cart();
        if (productItems != null && !productItems.isEmpty() && productItems != "[]") {
            // Assuming cartData is in the format you provided
            JSONArray dataa = new JSONArray(productItems);
            for (int i = 0; i < dataa.length(); i++) {
                var itemArray = dataa.getJSONObject(i);
                int id = itemArray.getInt("productId");
                String productName = itemArray.getString("productName");
                int quantity = itemArray.getInt("quantity");
                int price = itemArray.getInt("price");
                this.productitems.items.add(new Cart.CartItem(id, productName, quantity, price));
            }
        }

        if (infoData != null && !infoData.isEmpty() && infoData != "[]") {
            JSONArray jsonArray = new JSONArray(infoData);
            for (int i = 0; i < jsonArray.length(); i++) {
                var itemArray = jsonArray.getJSONObject(i);
                String Name = itemArray.getString("Name");
                String Email = itemArray.getString("Email");
                String Phone = itemArray.getString("Phone");
                String Address = itemArray.getString("Address");
                String Province = itemArray.getString("Province");
                this.infoorder = new InfoOrder(Name, Email, Phone, Address, Province);
            }
        }
    }

}
