package com.exuberant.ims.custom;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class TableCallBack
        implements Callback<TableColumn<Object, Object>, TableCell<Object, Object>> {
    Callback callback = new Callback() {
        @Override
        public TableCell<Object, Object> call(Object param) {
            TableCell cell = new TableCell() {
                public void updateItem(Object item, boolean empty) {
                    Text text = new Text();
                    super.updateItem(item, empty);
                    if (!isEmpty()) {
                        text = new Text(item.toString());
                        text.setWrappingWidth(200.0D);
                        text.setFill(Color.BLACK);
                        setGraphic(text);
                    } else if (isEmpty()) {
                        text.setText(null);
                        setGraphic(null);
                    }
                }
            };
            return cell;
        }
    };

    public TableCell<Object, Object> call(TableColumn<Object, Object> param) {
        return null;
    }
}