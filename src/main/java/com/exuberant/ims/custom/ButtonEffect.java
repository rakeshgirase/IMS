package com.exuberant.ims.custom;

import com.exuberant.ims.storekeeper.URLService;
import javafx.scene.image.Image;

public class ButtonEffect {
    Image menuImage = new Image(URLService.getFileAsStream("icon/menu.png"));
    Image menuImageRed = new Image(URLService.getFileAsStream("icon/menuRed.png"));
    String defultStyle = "-fx-border-width: 0px 0px 0px 5px;-fx-border-color:none";
    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;-fx-border-color:#FF4E3C";
    String hoverStyle = "";
    Image home = new Image(URLService.getFileAsStream("icon/home.png"));
    Image homeRed = new Image(URLService.getFileAsStream("icon/homeRed.png"));
    Image stock = new Image(URLService.getFileAsStream("icon/stock.png"));
    Image stockRed = new Image(URLService.getFileAsStream("icon/stockRed.png"));
    Image sell = new Image(URLService.getFileAsStream("icon/sell2.png"));
    Image sellRed = new Image(URLService.getFileAsStream("icon/sell2Red.png"));
    Image employee = new Image(URLService.getFileAsStream("icon/employee.png"));
    Image employeeRed = new Image(URLService.getFileAsStream("icon/employeeRed.png"));
    Image setting = new Image(URLService.getFileAsStream("icon/settings.png"));
    Image settingRed = new Image(URLService.getFileAsStream("icon/settingsRed.png"));
    Image about = new Image(URLService.getFileAsStream("icon/about.png"));
    Image aboutRed = new Image(URLService.getFileAsStream("icon/aboutRed.png"));

    public void btnHomeEffect() {
    }
}
