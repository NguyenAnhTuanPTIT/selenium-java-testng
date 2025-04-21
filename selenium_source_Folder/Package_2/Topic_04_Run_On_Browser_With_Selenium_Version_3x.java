package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Run_On_Browser_With_Selenium_Version_3x {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..
    WebDriver driver;

    // Lấy đường dẫn của thư mục project hiện tại
    String projectPath = System.getProperty("user.dir");


    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Run_On_Firefox() {
        // Selenium version 3x phải setup driver của trình duyệt rồi mới chạy được
        // Set đường dẫn driver theo hướng linh động, để nếu chạy ở máy khác thì vẫn chạy được
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/register");
        driver.quit();
    }

    @Test
    public void TC_02_Run_On_Edge() {
        System.setProperty("webdriver.edge.driver", projectPath + "\\browserDriver\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://demo.nopcommerce.com/register");
        driver.quit();

    }

    @Test
    public void TC_03_Run_On_Chorme() {
        System.setProperty("webdriver.chorme.driver", projectPath + "\\browserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/register");
        driver.quit();

    }
}


