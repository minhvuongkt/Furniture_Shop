package com.nhom13.models;

import com.nhom13.models.Cart.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.json.JSONArray;

public class User {

    int Id;
    String Name, Username, Password, Email, Phone, Address, Province;
    boolean Role;
    Cart Cart;

    public User(ResultSet rs) throws SQLException {
        this.Id = rs.getInt("UserID");
        this.Name = rs.getString("Fullname");
        this.Username = rs.getString("Username");
        this.Email = rs.getString("Email");
        this.Phone = rs.getString("Phone");
        this.Address = rs.getString("Address");
        this.Province = rs.getString("Province");
        this.Password = rs.getString("password");
        this.Role = rs.getBoolean("Role");

        String cartData = rs.getString("Cart");

        this.Cart = new Cart();
        if (cartData != null && !cartData.isEmpty() && cartData != "[]") {
            // Assuming cartData is in the format you provided
            var jsonArray = new JSONArray(cartData);
            for (int i = 0; i < jsonArray.length(); i++) {
                var itemArray = jsonArray.getJSONObject(i);
                int id = itemArray.getInt("productId");
                String productName = itemArray.getString("productName");
                int quantity = itemArray.getInt("quantity");
                int price = itemArray.getInt("price");
                this.Cart.items.add(new CartItem(id, productName, quantity, price));
            }
        }
    }

    public int getId() {
        return Id;
    }

    public boolean isRole() {
        return Role;
    }

    public String getName() {
        return Name;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getProvince() {
        return Province;
    }

    public Boolean getRole() {
        return Role;
    }

    public Cart getCart() {
        return Cart;
    }

    public void setRole(boolean Role) {
        this.Role = Role;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public void setCart(Cart cart) {
        this.Cart = cart;
    }
    public User(String Name, String Username, String Password, String Email, String Phone, String Address, String Province, Cart cart) {
        this.Name = Name;
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.Phone = Phone;
        this.Address = Address;
        this.Province = Province;
        if(cart == null) this.Cart = new Cart();
        else this.Cart = cart;
    }

    public User(String Name, String Username, String Password, String Email, String Phone, String Address, String Province, boolean Role, Cart cart) {
        this.Name = Name;
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.Phone = Phone;
        this.Address = Address;
        this.Province = Province;
        this.Role = Role;
        this.Cart = cart;
    }
    public User(int id, String Name, String Username, String Password, String Email, String Phone, String Address, String Province, boolean Role, Cart cart) {
        this.Id = id;
        this.Name = Name;
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.Phone = Phone;
        this.Address = Address;
        this.Province = Province;
        this.Role = Role;
        this.Cart = cart;
    }
}
