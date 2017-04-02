package com.exuberant.ims.custom;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
public class CustomPf {
    public void clearPassFieldByButton(final PasswordField value, final Button btn) {
        btn.setVisible(false);
        value.setOnKeyReleased(event -> {
            if ((((String) value.textProperty().get()).length() < 0) || (((String) value.textProperty().get()).equals(""))) {
                btn.setVisible(false);
            } else if ((((String) value.textProperty().get()).length() > -1) || (!((String) value.textProperty().get()).equals(""))) {
                btn.setVisible(true);
            }
        });
        btn.setOnAction(event -> {
            value.clear();
            btn.setVisible(false);
            value.requestFocus();
        });
    }
}
