package com.exuberant.ims.gateway;

import com.exuberant.ims.dal.User;
import com.exuberant.ims.list.ListEmployee;
import com.exuberant.ims.persistence.HibernateRepository;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class UserGateway {

    public void save(User user) {
        if (isUniqName(user)) {
            HibernateRepository.getRepository().save(user);
        }
    }

    public void view(User user) {

    }

    public void selectedView(User user) {
    }

    public void update(User user) {
        HibernateRepository.getRepository().update(user);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success :");
        alert.setHeaderText("Updated !!");
        alert.setContentText("User " + user.getUserName() + " Updated Successfully");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();

    }

    public void delete(User user) {
        HibernateRepository.getRepository().delete(user);
    }

    public boolean isUniqName(User user) {
        boolean isUniqName = true;
        //TODO: Implement Logic To Validate the User created
        return isUniqName;
    }

    public ObservableList<ListEmployee> getAll() {
        return null;
    }
}
