package com.exuberant.ims.custom;
import com.exuberant.ims.controller.HistoryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
public class History {
    public void writeText(String Catagorys, String nameOrId, String userName) {
        System.out.println("Hited");
        try {
            String content = "Update By    :" + userName + "\nUpdate Date\t:" + LocalDate.now().toString() + "\n------------------------";
            File file = new File("logs/" + Catagorys + "_" + nameOrId + "_updateinfo.txt");
            if (!file.exists()) {
                file.getParentFile().mkdir();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(content);
            bw.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void viewText(String Catagoryes, String nameOrId, String userName) {
        try {
            HistoryController hc = new HistoryController();
            StringBuilder sb = new StringBuilder();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/history.fxml"));
            loader.load();
            Parent root = (Parent) loader.getRoot();
            Stage s1 = new Stage();
            HistoryController historyController = (HistoryController) loader.getController();
            try {
                BufferedReader in = new BufferedReader(new FileReader("logs/" + Catagoryes + nameOrId + "UpdateDetails.text"));
                String str;
                while ((str = in.readLine()) != null) {
                    sb.append(str).append("\n");
                }
            } catch (FileNotFoundException ex) {
                System.out.println("File Not found");
            }
            historyController.tfHistory.appendText(sb.toString());
            Scene s2 = new Scene(root);
            s1.setScene(s2);
            s1.setTitle("History");
            s1.setResizable(false);
            s1.show();
        } catch (IOException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
