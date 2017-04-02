package com.exuberant.ims.database;
import com.exuberant.ims.storekeeper.StoreKeeper;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBProperties {
    Properties properties = new Properties();
    InputStream inputStream;
    OutputStream output = null;
    public void mkDbProperties() {
        try {
            this.output = new FileOutputStream("database.properties");
            this.properties.setProperty("host", "localhost");
            this.properties.setProperty("port", "3306");
            this.properties.setProperty("db", "storekeeper");
            this.properties.setProperty("user", "root");
            this.properties.setProperty("password", "");
            this.properties.store(this.output, null);
            this.output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String loadPropertiesFile() {
        try {
            this.inputStream = new FileInputStream("database.properties");
            this.properties.load(this.inputStream);
            return this.properties.getProperty("db");
        } catch (IOException e) {
            System.out.println("DDDD");
        }
        return "";
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.database\DBProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */