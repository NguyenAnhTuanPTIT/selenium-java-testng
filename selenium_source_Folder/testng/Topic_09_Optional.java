package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_09_Optional {


    WebDriver driver;
    By emailTextbox = By.cssSelector("input#email");
    By passwordTextbox = By.cssSelector("input#pass");
    By loginButton = By.cssSelector("button#send2");

    String userName = "selenium_11_01@gmail.com";
    String password = "111111";

    // Khai báo một biến đại diện cho  các environment
    String domainURL;


    @BeforeClass
    @Parameters({"server","browser"})
    // Khai báo @Optional ở đây nghĩa là nếu như bên file XML không sử dụng thẻ parameter thì
    // mặc định sẽ truyền value = Firefox, default sẽ mở browser Firefox
    public void beforeClass(String serverName,@Optional("Firefox") String browserName) {
        if(serverName.equalsIgnoreCase("Dev")){
            domainURL = "http://dev.techpanda.org";
        } else if (serverName.equalsIgnoreCase("Testing")) {
            domainURL = "http://testing.techpanda.org";
        }
        else if (serverName.equalsIgnoreCase("Staging")) {
            domainURL = "http://staging.techpanda.org";
        }
        else if (serverName.equalsIgnoreCase("Production")) {
            domainURL = "http://live.techpanda.org";
        }
        // Nếu truyền vào không đúng tên 3 browser trên (Vd như truyền vào opera, safari,...), sẽ văng ra 1 ngoại lệ
        else {
            throw new RuntimeException("Server name is not valid");
        }

        // Dùng switchcase cho tham  số truyền vào là  browserName
        switch (browserName){
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            default:
                // Trong switch-case không dùng throw, vì  phải return dữ liệu
                new RuntimeException("Browser name is not valid");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }


    @Test
    // Cũng có thể khai báo @Paramters ở đây
    public void loginOnMultipleBrowser() throws InterruptedException {
        // Khi chạy bên file XML sẽ chỉ chạy được duy nhất 1 môi trường tại 1 thời điểm
        driver.get(domainURL + "/index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys(userName);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));

    }


    @AfterClass
    // Cũng có thể khai báo @Paramters ở đây
    public void afterClass() {

        driver.quit();
    }

    // TH beforeSuite và afterSuite sẽ không dùng được annotation @Parameters

}
