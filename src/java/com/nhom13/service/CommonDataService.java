package com.nhom13.service;

import com.nhom13.dao.*;
import com.nhom13.interfaces.*;
import com.nhom13.models.*;
import java.util.*;
import java.util.stream.Collectors;

public class CommonDataService {

    private static final ICommonDAO<Province> provinceDB;
    private static final ICommonDAO<User> userDB;
    private static final ICommonDAO<Product> productDB;
    private static final ICommonDAO<Category> categoryDB;
    private static final ICommonDAO<Order> orderDB;

    static {
        provinceDB = new ProvinceDAO();
        userDB = new UserDAO();
        productDB = new ProductDAO();
        categoryDB = new CategoryDAO();
        orderDB = new OrderDAO();
    }

    /**
     * *--/START PROVINCE/--**
     */
    public static List<Province> listProvinces() {
        return new ArrayList<>(provinceDB.list());
    }

    /**
     * *--/END PROVINCE/--**
     */
    /**
     * *--/START USER/--**
     */
    public static List<User> listCustomers(int page, int pageSize, String searchValue) {
        return userDB.list(page, pageSize, searchValue).stream()
                .filter(user -> !user.isRole()) // Assuming 'isRole' returns a boolean
                .collect(Collectors.toList());
    }

    public static int rowCountUsers(String searchValue) {
        return userDB.count(searchValue);
    }

    public static List<User> listCustomers() {
        var list = new ArrayList<User>();
        var listUser = userDB.list();
        for (var item : listUser) {
            if (item.getRole() == false) {
                list.add(item);
            }
        }
        return list;
    }

    public static List<User> listAdmins() {
        var list = new ArrayList<User>();
        var listUser = userDB.list();
        if (listUser != null) {
            for (var item : listUser) {
                if (item.getRole()) {
                    list.add(item);
                }
            }
        }
        return list;
    }

    public static User getUser(int id) {
        return userDB.get(id);
    }

    public static User login(String useremail, String password) {
        if (useremail.isEmpty() || password.isEmpty()) {
            return null;
        }
        var userdb = new UserDAO();
        return userdb.login(useremail, password);
    }

    public static boolean isUsedUser(int id) {
        if (id < 1) {
            return false;
        }
        var userdb = new UserDAO();
        return userdb.inUsed(id);
    }
    
    public static boolean registor(User user) {
        return userDB.add(user);
    }

    public static boolean updateUser(User user) {
        return userDB.update(user);
    }

    public static boolean deleteUser(int id) {
        return userDB.delete(id);
    }

    public static boolean checkOldPass(int id, String password) {
        if (id < 1 || password.isEmpty()) {
            return false;
        }
        var userdb = new UserDAO();
        return userdb.checkOldPass(id, password);
    }

    public static boolean changePass(int id, String newPass) {
        if (id < 1 || newPass.isEmpty()) {
            return false;
        }
        var userdb = new UserDAO();
        return userdb.changePass(id, newPass);
    }

    /**
     * *--/END USER/--**
     */

    /**
     * *--/START PRODUCT/--**
     */
    public static List<Product> listProducts(int page, int pageSize, String searchValue) {
        return productDB.list(page, pageSize, searchValue);
    }

    public static int rowCountProducts(String searchValue) {
        return productDB.count(searchValue);
    }

    public static List<Product> listProducts() {
        return productDB.list();
    }

    public static Product getProduct(int id) {
        return productDB.get(id);
    }

    public static boolean addProduct(Product prd) {
        return productDB.add(prd);
    }

    public static boolean updateProduct(Product prd) {
        return productDB.update(prd);
    }

    public static boolean deleteProduct(int id) {
        return productDB.delete(id);
    }
    public static String getPhotoProduct(int id ){
        if(id < 1){
            return "";
        }
        var prdDB = new ProductDAO();
        return prdDB.getPhoto(id);
    }
    /**
     * *--/END PRODUCT/--**
     */
    /**
     * *--/START CATEGORY/--**
     */
    public static List<Category> listCategories(int page, int pageSize, String searchValue) {
        return categoryDB.list(page, pageSize, searchValue);
    }

    public static int rowCountCategories(String searchValue) {
        return categoryDB.count(searchValue);
    }

    public static List<Category> listCategories() {
        return categoryDB.list();
    }

    public static Category getCategory(int id) {
        return categoryDB.get(id);
    }

    public static boolean addCategory(Category item) {
        return categoryDB.add(item);
    }

    public static boolean updateCategory(Category item) {
        return categoryDB.update(item);
    }

    public static boolean deleteCategory(int id) {
        return categoryDB.delete(id);
    }

    public static boolean isUsedCategory(int id) {
        var categorydb = new CategoryDAO();
        return categorydb.inUsed(id);
    }

    /**
     * *--/END CATEGORY/--**
     */
    /**
     * *--/START ORDER/--**
     */
    public static List<Order> listOrders(int page, int pageSize, String searchValue) {
        return orderDB.list(page, pageSize, searchValue);
    }

    public static int rowCountOrders(String searchValue) {
        return orderDB.count(searchValue);
    }

    public static List<Order> listOrders() {
        return orderDB.list();
    }

    public static Order getOrder(int id) {
        return orderDB.get(id);
    }

    public static boolean addOrder(Order item) {
        return orderDB.add(item);
    }

    public static boolean updateOrder(Order item) {
        return orderDB.update(item);
    }

    public static boolean updateOrderStatus(int status, int id) {
        var orderdb = new OrderDAO();
        return orderdb.updateStatus(status, id);
    }

    public static boolean deleteOrder(int id) {
        return orderDB.delete(id);
    }
    public static boolean isUsedOrder(int id) {
        if (id < 1) {
            return false;
        }
        var orderDb = new OrderDAO();
        return orderDb.inUsed(id);
    }
    /**
     * *--/END ORDER/--**
     */
}
