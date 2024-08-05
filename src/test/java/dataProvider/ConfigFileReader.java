package dataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private static FileReader fr;
    private static Properties prop;
    static {
        try {
            fr=new FileReader("config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        prop=new Properties();
        try {
            prop.load(fr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getUrl() {

        String url = prop.getProperty("url");
        if (url != null)
            return url;
        else
            throw new RuntimeException("url is not specified in the config.properties file");
    }
    public static String getBrowser(){
        String browser= prop.getProperty("browser");
        if(browser!=null)
            return browser;
        else
            throw new RuntimeException("browser not specified in the config.properties file");
    }
    public static String getUsername(){
        String username= prop.getProperty("username");
        if (username!=null)
            return username;
        else
            throw new RuntimeException("username not specified in the config.properties file");
    }
    public static String getpassword(){
        String password= prop.getProperty("username");
        if (password!=null)
            return password;
        else
            throw new RuntimeException("password not specified in the config.properties file");
    }
    public static  String getExcelPath(){
        String path= prop.getProperty("excelPath");
        if(path!=null)
        return path;
        else
            throw new RuntimeException("excel path not specified in the config.properties file");
    }
}
