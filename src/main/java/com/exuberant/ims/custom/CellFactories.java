package com.exuberant.ims.custom;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
public class CellFactories {
    public Callback<TableColumn, TableCell> cellFactoryCheckBox = new Callback<TableColumn, TableCell>() {
        public TableCell call(TableColumn param) {
            final CheckBox checkBox = new CheckBox();
            TableView tableView = new TableView();
            TableCell cell = new TableCell() {
                public void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {
                        checkBox.setVisible(false);
                    } else {
                        checkBox.setVisible(true);
                        checkBox.setOnMouseClicked(event -> {

                        });
                    }
                }
            };
            cell.setGraphic(checkBox);
            return cell;
        }
    };
}
