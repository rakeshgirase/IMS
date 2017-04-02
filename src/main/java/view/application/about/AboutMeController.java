package view.application.about;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
public class AboutMeController
        implements Initializable {
    Image image = new Image("/com/exuberant/ims/image/myPic2.jpg");
    @FXML
    private ImageView imgMyImg;
    public void initialize(URL url, ResourceBundle rb) {
        this.imgMyImg.setImage(this.image);
    }
}
