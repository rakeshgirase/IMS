package com.exuberant.ims.database;

import com.exuberant.ims.exception.FatalException;
import com.exuberant.ims.util.PropertyService;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public Connection con;
    Properties properties = new Properties();
    InputStream inputStream;
    String url;
    String user;
    String pass;
    String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
    public void loadProperties() {
        try {
            this.url = PropertyService.getInstance().getProperty("url");
            this.user = PropertyService.getInstance().getProperty("user");
            this.pass = PropertyService.getInstance().getProperty("password");
        } catch (Exception e) {
            System.out.println("DDDD");
        }
    }
    public Connection mkDataBase() throws SQLException {
        loadProperties();
        return getConnection();
    }
    public Connection getConnection() {
        try {
            loadProperties();
            this.con = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (SQLException e) {
            throw new FatalException("Unable to establish connection....");
        }
        return con;
    }
}