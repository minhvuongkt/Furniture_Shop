package com.nhom13.driver;
import java.sql.*;
import com.nhom13.utils.Constants;

public class MySQLDriver {
    protected Connection openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(Constants.DBUrl, Constants.UserDB, Constants.PassDB);
    }
}
