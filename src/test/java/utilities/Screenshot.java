package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Screenshot {
    WebDriver driver;
    public static void snapshot(WebDriver driver) throws IOException {
        Date date=new Date();
        String str=String.valueOf(date.getTime());
        File scrshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrshot,new File("Screenshots/"+str+".jpg"));
    }
}
