package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_09_Multi_Browser {


    WebDriver driver;
    By emailTextbox = By.cssSelector("input#email");
    By passwordTextbox = By.cssSelector("input#pass");
    By loginButton = By.cssSelector("button#send2");

    String userName = "selenium_11_01@gmail.com";
    String password = "111111";

    String projectPath = System.getProperty("user.dir");

    /*
        @BeforeClass
        @Parameters("server") // Khai báo annotation dùng parameter bên xml có name = "server"
        public void beforeClass(String serverName) { // Ánh xạ vào 1 tham số, thường sẽ là kiểu String, ở đây sẽ truyền value = "Dev"
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
    */

    /*
        // T/h nếu dùng nhiều parameters, dùng kiểu mảng {..}
        @Parameters({"server","browser"})
        // Ánh xạ vào 2 tham số do khai báo dùng 2 parameter, thường sẽ là kiểu String, ở đây sẽ truyền
        // value = "Dev" tương ứng với paramter = "server", value = "browser" tương ứng với parameter = "browser"
        public void beforeClass(String serverName,String browserName) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
    */

    @BeforeClass
    @Parameters("browser") // Nếu run trực tiếp ở đây sẽ không được vì nó sẽ không hiểu Parameter này lấy từ đâu, phải run bên file xml
    public void beforeClass(String browserName) {
        // Tương đối: contains, startWith, endWith
        // Tuyệt đối:   equals - bằng giá trị (Hoa/thường)
        //              equalsIgnoreCase - bằng (giá trị/ko phân biệt hoa thường)
        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            // WebDriver đang không tự động tải về driver mới  nhất cho edge, nên sẽ dùng tạm cách set thủ công này
            // System.setProperty("webdriver.edge.driver", projectPath + "\\browserDriver\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        // Nếu truyền vào không đúng tên 3 browser trên (Vd như truyền vào opera, safari,...), sẽ văng ra 1 ngoại lệ
        else {
            throw new RuntimeException("Browser name is not valid");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }


    @Test
    // Cũng có thể khai báo @Paramters ở đây
    public void loginOnMultipleBrowser() throws InterruptedException {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        Thread.sleep(5000);

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
