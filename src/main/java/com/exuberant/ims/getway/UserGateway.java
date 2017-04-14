package com.exuberant.ims.getway;

import com.exuberant.ims.dal.Users;
import com.exuberant.ims.gateway.HibernateRepository;
import com.exuberant.ims.list.ListEmployee;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class UserGateway {

    public void save(Users users) {
        if (isUniqName(users)) {
            System.out.println("Creating Users" + users);
            HibernateRepository.getRepository().save(users);
            System.out.println("Created Users: " + users);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success :");
            alert.setHeaderText("Success");
            alert.setContentText("Users " + users.getUserName() + " Added successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
    }

    public void view(Users users) {

    }

    public void selectedView(Users users) {
    }

    public void update(Users users) {
        HibernateRepository.getRepository().update(users);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success :");
        alert.setHeaderText("Updated !!");
        alert.setContentText("Users " + users.getUserName() + " Updated Successfully");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();

    }

    public void delete(Users users) {
        HibernateRepository.getRepository().delete(users);
    }

    public boolean isUniqName(Users users) {
        boolean isUniqName = true;
        return isUniqName;
    }

    public ObservableList<ListEmployee> getAll() {
        return null;
    }
}
