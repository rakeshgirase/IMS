package com.exuberant.ims.getway;

import com.exuberant.ims.dal.Customer;
import com.exuberant.ims.gateway.HibernateRepository;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class CustomerGetway {

    public void save(Customer customer) {
        HibernateRepository.getRepository().save(customer);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucess");
        alert.setHeaderText("Sucess : save sucess");
        alert.setContentText("Customer  '" + customer.customerName + "' Added successfully");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }

    public void view(Customer customer) {
    }

    public void selectedView(Customer customer) {
    }

    public void searchView(Customer customer) {
    }

    public void update(Customer customer) {
        HibernateRepository.getRepository().update(customer);
    }

    public void delete(Customer customer) {
        HibernateRepository.getRepository().delete(customer);
    }

    private boolean isNotUsed(Customer customer) {
        boolean isNotUsed = false;
        return isNotUsed;
    }
}
