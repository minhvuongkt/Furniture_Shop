package com.nhom13.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {
    int id;
    String name;


    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Category (ResultSet rs) throws SQLException 
    {
        this.id = rs.getInt("CategoryID");;
        this.name = rs.getString("Name");
    }
}
