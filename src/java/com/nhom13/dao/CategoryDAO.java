package com.nhom13.dao;

import com.nhom13.driver.MySQLDriver;
import com.nhom13.interfaces.ICommonDAO;
import com.nhom13.models.Category;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO extends MySQLDriver implements ICommonDAO<Category> {

    private boolean isSuccess(int[] batchResults) {
        return Arrays.stream(batchResults).allMatch(updateCount -> updateCount == 1);
    }

    @Override
    public List<Category> list() {
        List<Category> listCategory = new ArrayList<>();
        try (var conn = openConnection()) {
            String sql = "SELECT * FROM `categories`";
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listCategory.add(new Category(rs));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategory;
    }

    @Override
    public List<Category> list(int page, int pageSize, String searchValue) {
        List<Category> listCategory = new ArrayList<>();
        try (var conn = openConnection()) {
            var sql = "SELECT * FROM ("
                    + "SELECT *, ROW_NUMBER() OVER (ORDER BY Name) AS RowNumber "
                    + "FROM `categories` "
                    + "WHERE Name LIKE ? ) AS t "
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
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listCategory.add(new Category(rs));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategory;
    }

    @Override
    public int count(String searchValue) {
        int count = 0;
        try (var conn = openConnection()) {
            String sql = "SELECT COUNT(*) FROM categories WHERE (Name LIKE ?)";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchValue + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public Category get(int id) {
        Category category = null;
        try (var conn = openConnection()) {
            String sql = "SELECT * FROM categories WHERE CategoryID = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                category = new Category(rs);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }

    @Override
    public boolean add(Category item) {
        try (var conn = openConnection()) {
            var sql = "INSERT INTO `categories`(`Name`) VALUES (?)";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getName());
            stmt.addBatch();
            int[] results = stmt.executeBatch();
            return isSuccess(results);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Category item) {
        boolean result = false;
        try (var conn = openConnection()) {
            String sql = "UPDATE `categories` SET `Name` = ? WHERE `CategoryID` = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getName() != null ? item.getName() : "");
            stmt.setInt(2, item.getId());
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
        try (var conn = openConnection()) {
            String sql = "DELETE FROM `categories` WHERE `CategoryID` = ?";
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
            String sql = "SELECT CASE WHEN EXISTS (SELECT * FROM products WHERE CategoryID = ?) THEN 1 ELSE 0 END";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getInt(1) == 1;  // Nếu tồn tại, giá trị sẽ là 1
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
