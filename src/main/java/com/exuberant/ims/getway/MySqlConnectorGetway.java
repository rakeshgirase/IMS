package com.exuberant.ims.getway;

import com.exuberant.ims.dal.MysqlConnector;
import com.exuberant.ims.database.SQLightConnection;
import com.exuberant.ims.util.PropertyService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlConnectorGetway {
    SQLightConnection qLightConnection = new SQLightConnection();
    Connection con = this.qLightConnection.sqliteConnection();
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");

    public void save(MysqlConnector connector) {
        try {
            this.pst = this.con.prepareStatement("insert into mysqlInfo values(?,?,?,?,?)");
            this.pst.setInt(1, 1);
            this.pst.setString(2, connector.hostName);
            this.pst.setString(3, connector.portName);
            this.pst.setString(4, connector.userName);
            this.pst.setString(5, connector.password);
            this.pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnectorGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void view(MysqlConnector connector) {
        try {
            this.pst = this.con.prepareStatement("select * from mysqlInfo where Id=1");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                connector.hostName = this.rs.getString(2);
                connector.portName = this.rs.getString(3);
                connector.userName = this.rs.getString(4);
                connector.password = this.rs.getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnectorGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
