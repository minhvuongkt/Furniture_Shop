package com.nhom13.dao;

import com.nhom13.driver.MySQLDriver;
import com.nhom13.interfaces.ICommonDAO;
import com.nhom13.models.Category;
import com.nhom13.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends MySQLDriver implements ICommonDAO<Product> {


    private boolean isSuccess(int[] batchResults) {
        return Arrays.stream(batchResults).allMatch(updateCount -> updateCount == 1);
    }

    @Override
    public List<Product> list() {
        List<Product> listProduct = new ArrayList<>();
        try (Connection conn = openConnection()) {
            String sql = "SELECT * FROM `products`";
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listProduct.add(new Product(rs));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    @Override
    public List<Product> list(int page, int pageSize, String searchValue) {
        List<Product> listProduct = new ArrayList<>();
        try (Connection conn = openConnection()) {
            String sql = "SELECT * FROM ("
                    + "SELECT *, ROW_NUMBER() OVER (ORDER BY Name) AS RowNumber "
                    + "FROM `products` "
                    + "WHERE (Name LIKE ?) OR (Detail LIKE ?)"
                    + ") AS t "
                    + "WHERE ? = 0 "
                    + "OR (RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?) "
                    + "ORDER BY RowNumber;";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchValue + "%");
            stmt.setString(2, "%" + searchValue + "%");
            stmt.setInt(3, pageSize);
            stmt.setInt(4, page);
            stmt.setInt(5, pageSize);
            stmt.setInt(6, page);
            stmt.setInt(7, pageSize);
            stmt.addBatch();
            var rs = stmt.executeQuery();
            while (rs.next()) {
                listProduct.add(new Product(rs));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    @Override
    public int count(String searchValue) {
        int count = 0;
        try (Connection conn = openConnection()) {
            String sql = "SELECT COUNT(*) FROM `products` WHERE (Name LIKE ?) OR (Detail LIKE ?)";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchValue + "%");
            stmt.setString(2, "%" + searchValue + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public Product get(int id) {
        Product product = null;
        try (Connection conn = openConnection()) {
            String sql = "SELECT * FROM `products` WHERE ProductID = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Product(rs);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
    public String getPhoto(int id){
        String photo = "";
        try (Connection conn = openConnection()) {
            String sql = "SELECT Photo FROM `products` WHERE ProductID = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                photo = rs.getString("Photo");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photo;
    }
    @Override
    public boolean add(Product item) {
        try (Connection conn = openConnection()) {
            String sql = "INSERT INTO `products`(`Name`, `Detail`, `Price`, `Photo`, `IsSelling`, `CategoryID`) VALUES (?,?,?,?,?,?);";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDetail());
            stmt.setInt(3, item.getPrice());
            stmt.setString(4, item.getPhoto());
            stmt.setBoolean(5, item.getIsSelling());
            stmt.setInt(6, item.getCategoryID());
            stmt.addBatch();
            int[] results = stmt.executeBatch();
            return isSuccess(results);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Product item) {
        
        boolean result = false;
        try (Connection conn = openConnection()) {
            String sql = "UPDATE `products` SET `Name`=?,`Detail`=?,`Price`=?,`Photo`=?,`IsSelling`=?,`CategoryID`=? WHERE `ProductID`=?";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDetail());
            stmt.setInt(3, item.getPrice());
            stmt.setString(4, item.getPhoto());
            stmt.setBoolean(5, item.getIsSelling());
            stmt.setInt(6, item.getCategoryID());
            stmt.setInt(7, item.getId());
            stmt.addBatch();
            int count = stmt.executeUpdate();
            result = count > 0;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection conn = openConnection()) {
            String sql = "DELETE FROM `products` WHERE `ProductID` = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int count = stmt.executeUpdate();
            result = count > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
