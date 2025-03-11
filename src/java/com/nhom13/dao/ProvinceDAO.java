package com.nhom13.dao;

import com.nhom13.driver.MySQLDriver;
import com.nhom13.interfaces.*;
import com.nhom13.models.*;
import java.sql.*;
import java.util.*;

public class ProvinceDAO extends MySQLDriver implements ICommonDAO<Province>
{ 
    @Override
    public List<Province> list() {
        try(var conn = openConnection()) {
            
                var listProvinces = new ArrayList<Province>();
                String sql = "select * from `provinces`";
                var stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql);
                while(result.next()){
                    var id = result.getInt("id");
                    var name = result.getString("name");                   
                    var province = new Province(id,name);
                    listProvinces.add(province);
                }
                return listProvinces;                
            } catch (SQLException | ClassNotFoundException ex) {
                //Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
    @Override
    public Province get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Province item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Province item) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }    

    @Override
    public List<Province> list(int page, int pageSize, String searchValue) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int count(String searchValue) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
