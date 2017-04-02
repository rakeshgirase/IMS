package com.exuberant.ims.getway;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.list.ListEmployee;
import com.exuberant.ims.database.DBConnection;

import com.exuberant.ims.util.PropertyService;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
public class UsersGetway {
    DBConnection dbConnection = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");

    public void save(Users users) {
        if (isUniqName(users)) {
            this.con = this.dbConnection.getConnection();
            try {
                this.pst = this.con.prepareStatement("insert into " + this.db + ".User values(?,?,?,?,?,?,?,?,?,?,?,?)");
                this.pst.setString(1, null);
                this.pst.setString(2, users.userName);
                this.pst.setString(3, users.fullName);
                this.pst.setString(4, users.emailAddress);
                this.pst.setString(5, users.contactNumber);
                this.pst.setString(6, users.salary);
                this.pst.setString(7, users.address);
                this.pst.setString(8, users.password);
                this.pst.setString(9, "1");
                if (users.imagePath != null) {
                    InputStream is = new FileInputStream(new File(users.imagePath));
                    this.pst.setBlob(10, is);
                } else {
                    this.pst.setBlob(10, (Blob) null);
                }
                this.pst.setString(11, LocalDate.now().toString());
                this.pst.setString(12, users.creatorId);
                this.pst.executeUpdate();
                this.pst.close();
                this.con.close();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sucess :");
                alert.setHeaderText("Sucess");
                alert.setContentText("User " + users.userName + " Added sucessfuly");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void view(Users users) {
        this.con = this.dbConnection.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".User");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                users.id = this.rs.getString(1);
                users.userName = this.rs.getString(2);
                users.employeeLists.addAll(new ListEmployee[]{new ListEmployee(users.id, users.userName)});
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void selectedView(Users users) {
        this.con = this.dbConnection.getConnection();
        try {
            this.pst = this.con.prepareCall("select * from " + this.db + ".User where id=?");
            this.pst.setString(1, users.id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                users.id = this.rs.getString(1);
                users.userName = this.rs.getString(2);
                users.fullName = this.rs.getString(3);
                users.emailAddress = this.rs.getString(4);
                users.contactNumber = this.rs.getString(5);
                users.salary = this.rs.getString(6);
                users.address = this.rs.getString(7);
                users.password = this.rs.getString(8);
                users.status = this.rs.getString(9);
                users.userImage = this.rs.getBlob(10);
                if (users.userImage != null) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(users.userImage.getBytes(1L, (int) users.userImage.length()));
                    users.image = new Image(byteArrayInputStream);
                } else {
                    users.image = new Image("/com/exuberant/ims/image/rifat.jpg");
                }
                users.date = this.rs.getString(11);
                users.creatorId = this.rs.getString(12);
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(Users users) {
        this.con = this.dbConnection.getConnection();
        try {
            this.pst = this.con.prepareStatement("UPDATE " + this.db + ".User SET FullName=?, EmailAddress=?,ContactNumber=?,Salary=COALESCE(?, Salary),Address=?,Password=COALESCE(?, Password), Status=COALESCE(?, Status), UserImage=COALESCE(?, UserImage) WHERE UsrName=?");
            this.pst.setString(1, users.fullName);
            this.pst.setString(2, users.emailAddress);
            this.pst.setString(3, users.contactNumber);
            this.pst.setString(4, users.salary);
            this.pst.setString(5, users.address);
            this.pst.setString(6, users.password);
            this.pst.setString(7, users.status);
            if (users.imagePath != null) {
                InputStream is = new FileInputStream(new File(users.imagePath));
                this.pst.setBlob(8, is);
            } else if (users.imagePath == null) {
                this.pst.setBlob(8, (Blob) null);
            }
            this.pst.setString(9, users.userName);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess :");
            alert.setHeaderText("Updated !!");
            alert.setContentText("User " + users.userName + " Updated Sucessfuly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void delete(Users users) {
        this.con = this.dbConnection.getConnection();
        try {
            this.pst = this.con.prepareStatement("delete from " + this.db + ".User where Id=?");
            this.pst.setString(1, users.id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isUniqName(Users users) {
        this.con = this.dbConnection.getConnection();
        boolean isUniqName = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".User where UsrName=?");
            this.pst.setString(1, users.userName);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR :");
                alert.setHeaderText("ERROR : Name Exist");
                alert.setContentText("User name " + users.userName + " Already Used");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isUniqName;
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
            isUniqName = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUniqName;
    }
}
