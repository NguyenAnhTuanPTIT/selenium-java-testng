package Package_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Topic_06_WebBrowser_Commands {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    // Hàm thao tác với browser sẽ thông qua biến driver
    WebDriver driver;

    @BeforeClass
    public void initalBrowser() throws MalformedURLException {
        // Run with browser (local)
        driver = new FirefoxDriver();
        driver = new EdgeDriver();
        driver = new FirefoxDriver();
        driver = new SafariDriver();
        driver = new InternetExplorerDriver();

        // Run with Remote (Grid/ Docker / Cloud Testing)
        // Networking (LAN / WAN / IP / Port)
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_() {

        // Mở ra 1 URL bất kỳ (phải bắt đầu bằng http hoặc https, nếu không sẽ báo lỗi)
        driver.get("https://demo.nopcommerce.com");

        // Đóng browser (chỉ đóng cái nào driver đang đứng, hoặc trong TH nhiều tab => đóng tab đang active)
        driver.close();

        // Đóng browser (bao gồm các tab và windows)
        driver.quit();
        // ==> driver.close(); và driver.quit(); chỉ giống nhau khi có 1 cửa sổ/1 tab duy nhất

        // Lấy ra title của page hiện tại (tên của page đó, nằm trên tab của browser)
        // 1 - Lưu dữ liệu lại ri kiểm tra sau
        //String homePageTitle = driver.getTitle("");
        // Dùng hàm mà thư viện cung cấp để kiểm tra: TestNG, JUnit,...
        //Assert.assertEquals();
    }

    @Test
    public void TC_02_Login() {

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
