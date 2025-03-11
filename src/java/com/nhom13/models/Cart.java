package com.nhom13.models;

import java.util.*;

public class Cart {

    public List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }
    public List<CartItem> getItems() {
        return items;
    }

    public static class CartItem {

        int id, quantity, price;
        String name;

        public CartItem(String name, int quantity, int price) {
            this.quantity = quantity;
            this.price = price;
            this.name = name;
        }

        public CartItem(int id, String name, int quantity, int price) {
            this.id = id;
            this.quantity = quantity;
            this.price = price;
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }
    }
}
