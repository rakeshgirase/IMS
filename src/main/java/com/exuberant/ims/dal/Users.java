package com.exuberant.ims.dal;
import com.exuberant.ims.list.ListEmployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import java.sql.Blob;
public class Users {
    public ObservableList<String> allUser = FXCollections.observableArrayList();
    public String id;
    public String userName;
    public String fullName;
    public String emailAddress;
    public String contactNumber;
    public String salary;
    public String address;
    public String password;
    public String status;
    public String imagePath;
    public Blob userImage;
    public String date;
    public String creatorId;
    public Image image;
    public ObservableList<ListEmployee> employeeLists = FXCollections.observableArrayList();
}