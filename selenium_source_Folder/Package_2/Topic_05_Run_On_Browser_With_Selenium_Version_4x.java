package Package_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_05_Run_On_Browser_With_Selenium_Version_4x {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..
    WebDriver driver;

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Run_On_Firefox() {
        // Selenium version 4x không cần phải setup driver của trình duyệt rồi mới chạy được
        // Selenium version 4x sẽ cover các code của selenium 3x nên nếu code thuộc selenium 3x thì vẫn dùng được
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/register");
        driver.quit();
    }

    @Test
    public void TC_02_Run_On_Edge() {
        driver = new EdgeDriver();
        driver.get("https://demo.nopcommerce.com/register");
        driver.quit();

    }

    @Test
    public void TC_03_Run_On_Chorme() {
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/register");
        driver.quit();

    }
}


