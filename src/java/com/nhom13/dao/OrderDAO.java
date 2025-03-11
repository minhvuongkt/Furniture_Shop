/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom13.dao;

import com.nhom13.driver.MySQLDriver;
import com.nhom13.interfaces.ICommonDAO;
import com.nhom13.models.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class OrderDAO extends MySQLDriver implements ICommonDAO<Order> {

    private boolean isSuccess(int[] batchResults) {
        return Arrays.stream(batchResults).allMatch(updateCount -> updateCount == 1);
    }

    public String infoToJson(InfoOrder info) {
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", info.getName());
        jsonObject.put("Email", info.getEmail());
        jsonObject.put("Phone", info.getPhone());
        jsonObject.put("Address", info.getAddress());
        jsonObject.put("Province", info.getProvince());

        jsonArray.put(jsonObject);

        return jsonArray.toString();
    }

    public String cartToJson(Cart cart) {
        JSONArray jsonArray = new JSONArray();
        for (Cart.CartItem item : cart.items) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productId", item.getId());
            jsonObject.put("productName", item.getName());
            jsonObject.put("quantity", item.getQuantity());
            jsonObject.put("price", item.getPrice());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @Override
    public List<Order> list() {
        List<Order> listOrder = new ArrayList<>();
        try (Connection conn = openConnection()) {
            String sql = "SELECT * FROM `Orders`";
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listOrder.add(new Order(rs));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrder;
    }

    @Override
    public List<Order> list(int page, int pageSize, String searchValue) {
        List<Order> listOrder = new ArrayList<>();
        try (Connection conn = openConnection()) {
            String sql = "SELECT * FROM ("
                    + "SELECT *, ROW_NUMBER() OVER (ORDER BY ID) AS RowNumber "
                    + "FROM `Orders` "
                    + "WHERE (ID LIKE ?)"
                    + ") AS t "
                    + "WHERE ? = 0 "
                    + "OR (RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?) "
                    + "ORDER BY RowNumber;";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchValue + "%");
            stmt.setInt(2, pageSize);
            stmt.setInt(3, page);
            stmt.setInt(4, pageSize);
            stmt.setInt(5, page);
            stmt.setInt(6, pageSize);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                listOrder.add(new Order(rs));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrder;
    }

    @Override
    public int count(String searchValue) {
        int count = 0;
        try (Connection conn = openConnection()) {
            String sql = "SELECT COUNT(*) FROM `Orders` WHERE (ID LIKE ?);";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchValue + "%");
            var rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public Order get(int id) {
        Order orders = null;
        try (Connection conn = openConnection()) {
            String sql = "SELECT * FROM `Orders` WHERE ID = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                orders = new Order(rs);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    @Override
    public boolean add(Order item) {
        try (Connection conn = openConnection()) {
            String sql = "INSERT INTO `orders`(`ProductItems`, `InfoOrder`, `UserID`, `TotalPrice`, `Status`) VALUES (?,?,?,?,?)";
            var stmt = conn.prepareStatement(sql);

            stmt.setString(1, cartToJson(item.getProductitems()));
            stmt.setString(2, infoToJson(item.getInfoorder()));
            stmt.setInt(3, item.getUserid());
            stmt.setInt(4, item.getTotalprice());
            stmt.setInt(5, item.getStatus());
            stmt.addBatch();
            int[] results = stmt.executeBatch();
            return isSuccess(results);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Order item) {

        boolean result = false;
        try (Connection conn = openConnection()) {
            String sql = "UPDATE `orders` SET `ProductItems`=?,`InfoOrder`=?,`UserID`=?, `Status`=? WHERE `ID`=?";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, cartToJson(item.getProductitems()));
            stmt.setString(2, infoToJson(item.getInfoorder()));
            stmt.setInt(3, item.getUserid());
            stmt.setInt(4, item.getStatus());
            stmt.setInt(5, item.getId());

            int count = stmt.executeUpdate();
            result = count > 0;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean updateStatus(int status, int id) {

        boolean result = false;
        try (Connection conn = openConnection()) {
            String sql = "UPDATE `orders` SET `Status`=? WHERE `ID`=?";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, status);
            stmt.setInt(2, id);

            int count = stmt.executeUpdate();
            result = count > 0;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection conn = openConnection()) {
            String sql = "DELETE FROM `Orders` WHERE `ID` = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int count = stmt.executeUpdate();
            result = count > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean inUsed(int id) {
        boolean result = false;
        try (var conn = openConnection()) {
            String sql = "SELECT CASE WHEN EXISTS (SELECT * FROM users WHERE UserID = ?) THEN 1 ELSE 0 END";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getInt(1) == 1;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
