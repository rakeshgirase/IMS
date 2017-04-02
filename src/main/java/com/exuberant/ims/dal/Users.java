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
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.dal\Users.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */