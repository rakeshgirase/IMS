package com.exuberant.ims.database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SQLightConnection {
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    String table = "CREATE TABLE if not exists mysqlInfo (ID INT PRIMARY KEY     NOT NULL, hostName          VARCHAR(30) ,  portName          VARCHAR(30) ,  userName        VARCHAR(30) ,  password         VARCHAR(30) )";
    String demoValue = "insert into mysqlInof values(?,?,?,?,?)";
    public Connection sqliteConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.con = DriverManager.getConnection("jdbc:sqlite:storekeeperConnection.db");
            System.out.println("SQLIGHT DATABASE OPENED SUCESSFULY");
            this.pst = this.con.prepareStatement(this.table);
            this.pst.execute();
            System.out.println("SUcessfuly crrate table");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLightConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLightConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.con;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.database\SQLightConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */