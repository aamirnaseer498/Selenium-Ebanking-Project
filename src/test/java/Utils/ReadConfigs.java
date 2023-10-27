package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigs {

    Properties properties;

    public ReadConfigs(){
        File file= new File("./Configs/config.properties");
        try {
            FileInputStream fileInputStream= new FileInputStream(file);
            properties= new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBrowser(){
        return properties.getProperty("browser");
    }

    public String getHomeURL(){
        return properties.getProperty("homeURL");
    }

    public String getUserID(){
        return properties.getProperty("userID");
    }

    public String getPassword(){
        return properties.getProperty("password");
    }

    public String getHomeTitle(){
        return properties.getProperty("homeTitle");
    }

}
