package com.nhom13.models;

import java.sql.SQLException;
import java.sql.ResultSet;

public class Province {
    int id;
    String name;

    public Province(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Province (ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
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
    
}
