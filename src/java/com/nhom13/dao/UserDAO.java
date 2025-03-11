package com.nhom13.dao;

import com.nhom13.driver.MySQLDriver;
import com.nhom13.interfaces.ICommonDAO;
import com.nhom13.models.Cart;
import com.nhom13.models.Cart.*;
import com.nhom13.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class UserDAO extends MySQLDriver implements ICommonDAO<User> {

    private boolean isSuccess(int[] batchResults) {
        return Arrays.stream(batchResults).allMatch(updateCount -> updateCount == 1);
    }

    public String cartToJson(Cart cart) {
        JSONArray jsonArray = new JSONArray();
        for (CartItem item : cart.items) {
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
    public List<User> list() {
        try (var conn = openConnection()) {
            var listUsers = new ArrayList<User>();
            String sql = "select * from `users`";
            var stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                var user = new User(result);
                listUsers.add(user);
            }
            return listUsers;
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User get(int id) {
        try (var conn = openConnection()) {
            String sql = "select * from `users` where UserID = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new User(result);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            //Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private User findUser(String emailusername) {
        try (var conn = openConnection()) {
            String sql = "select * from users where (email=? or username=?)";

            var stmt = conn.prepareStatement(sql);
            stmt.setNString(1, emailusername);
            stmt.setNString(2, emailusername);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new User(result);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkOldPass(int id, String password) {
        try (var conn = openConnection()) {
            String sql = "select * from users where (UserID=? and password=?)";

            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean changePass(int id, String password) {
        var result = false;
        try (var conn = openConnection()) {
            String sql = "UPDATE `users` SET `Password`=? WHERE `UserID` = ?";

            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setInt(2, id);
            int count = stmt.executeUpdate();
            result = count > 0;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public User login(String emailuser, String password) {
        try (var conn = openConnection()) {
            String sql = "select * from users where (Email=? or Username=?)and Password=?";

            var stmt = conn.prepareStatement(sql);
            stmt.setNString(1, emailuser);
            stmt.setNString(2, emailuser);
            stmt.setNString(3, password);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new User(result);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean inUsed(int id) {
        boolean result = false;
        try (var conn = openConnection()) {
            String sql = "SELECT CASE WHEN EXISTS (SELECT * FROM orders WHERE UserID = ?) THEN 1 ELSE 0 END";
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

    @Override
    public boolean add(User user) {
        try (var conn = openConnection()) {
            if (findUser(user.getEmail()) != null || findUser(user.getUsername()) != null) {
                return false;
            }
            String sql = "INSERT INTO `users`(`Fullname`, `Username`, `Password`, `Email`, `Phone`, `Address`, `Province`, `Role`, `Cart`) VALUES (?,?,?,?,?,?,?,?,?)";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getAddress());
            stmt.setString(7, user.getProvince());
            stmt.setBoolean(8, user.getRole());
            if (user.getCart() != null) {
                stmt.setString(9, cartToJson(user.getCart()));
            } else {
                stmt.setString(9, "[]");
            }
            stmt.addBatch();
            int[] results = stmt.executeBatch();
            return isSuccess(results);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        try (var conn = openConnection()) {
            String sql = "UPDATE `users` SET `Fullname`=?,`Email`=?,`Phone`=?,`Password`=?,`Address`=?,`Province`=?,`Role`=?, `Cart`=? WHERE `UserID` = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getProvince());
            stmt.setBoolean(7, user.getRole());
            if (user.getCart() != null) {
                stmt.setString(8, cartToJson(user.getCart()));
            } else {
                stmt.setString(8, "[]");
            }
            stmt.setInt(9, user.getId());
            int count = stmt.executeUpdate();
            result = count > 0;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        try (var conn = openConnection()) {
            String sql = "DELETE FROM `users` WHERE `UserID` = '" + id + "'";
            var stmt = conn.prepareStatement(sql);
            return stmt.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<User> list(int page, int pageSize, String searchValue) {
        List<User> listUser = new ArrayList<>();

        try (var conn = openConnection()) {
            String sql = "select * from "
                    + "( "
                    + "select *, ROW_NUMBER() over (order by Fullname) as RowNumber "
                    + "from users "
                    + "where (Fullname like ?) or (Username like ?) or (Email like ?)"
                    + ") as t where ? = 0 "
                    + "or (RowNumber between (? - 1) * ? + 1 and ? * ?) order by RowNumber;";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchValue + "%");
            stmt.setString(2, "%" + searchValue + "%");
            stmt.setString(3, "%" + searchValue + "%");
            stmt.setInt(4, pageSize);
            stmt.setInt(5, page);
            stmt.setInt(6, pageSize);
            stmt.setInt(7, page);
            stmt.setInt(8, pageSize);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Assuming a method to map ResultSet to Customer object
                listUser.add(new User(rs));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUser;
    }

    @Override
    public int count(String searchValue) {
        int count = 0;
        try (var conn = openConnection()) {

            String sql = "select count(*) from users where ((Fullname like ?) or (Username like ?) or (Email like ?)) and role = 0";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchValue + "%");
            stmt.setString(2, "%" + searchValue + "%");
            stmt.setString(3, "%" + searchValue + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
