package com.exuberant.ims.bll;
import com.exuberant.ims.dal.RMA;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.getway.RmaGetway;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.DBProperties;
import com.exuberant.ims.database.SQL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class RmaBLL {
    SQL sql = new SQL();
    RmaGetway rmaGetway = new RmaGetway();
    DBConnection dbCon = new DBConnection();
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
    public void save(RMA rma) {
        if (isUniqName(rma)) {
            this.rmaGetway.save(rma);
        }
    }
    public void update(RMA rma) {
        if (sameName(rma)) {
            this.rmaGetway.update(rma);
        } else if (isUniqName(rma)) {
            this.rmaGetway.update(rma);
        }
    }
    public Object delete(RMA rma) {
        if (this.rmaGetway.isNotUse(rma)) {
            this.rmaGetway.delete(rma);
        }
        return rma;
    }
    public boolean sameName(RMA rma) {
        boolean sameName = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".RMA where Id=? and RMAName=? and RMADays=?");
            this.pst.setString(1, rma.id);
            this.pst.setString(2, rma.rmaName);
            this.pst.setString(3, rma.rmaDays);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                return sameName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sameName;
    }
    public boolean isUniqName(RMA rma) {
        boolean uniqRMA = false;
        try {
            this.pst = this.con.prepareCall("select * from " + this.db + ".RMA where RMAName=? or RMADays=?");
            this.pst.setString(1, rma.rmaName);
            this.pst.setString(2, rma.rmaDays);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                System.out.println("in not uniq");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : used");
                alert.setContentText("RMA  '" + rma.rmaName + "/" + rma.rmaDays + "' Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return uniqRMA;
            }
            uniqRMA = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqRMA;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.bll\RmaBLL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */