package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_30_Mix_ExplicitWait_ImplicitWait {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();


    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....

    @Test
    // TH có tìm thấy element
    public void TC_01_Element_Found() {
        // Set timeout cho implicitWait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Set timeout cho explicitWait
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Wait element với explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

        // Wait element với implicit
        driver.findElement(By.cssSelector("input#email"));

        // ==> Việc tìm thấy element thì dùng wait implicit hoặc explicit cũng sẽ như nhau, ko ảnh hưởng gì hết

    }

    @Test
    // TH không tìm thấy element đối với Implicit wait
    public void TC_02_Not_Found_Element_Only_Implicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Locator "input#email_1" không tồn tại trong DOM
        driver.findElement(By.cssSelector("input#email_1"));

        // Nếu không tìm thấy element nào
        // Mới đầu vào findElement nhưng không thấy:
        // Tìm lại mà thấy thì không cần chờ hết tổng time còn lại
        // Tìm lại và không thấy hết tổng time 13s thì đánh fail testcase
        // Show lỗi: NoSuchElementException

    }

    @Test
    // TH không tìm thấy element đối với Explicit wait, dùng kiểu tham số là By
    public void TC_03_Not_Found_Element_Only_Explicit_Params_By() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Define ra 1 biến để lưu lại locator
        By emailTextboxBy = By.cssSelector("input#email_1");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailTextboxBy));
        // Hàm trên sẽ log ra lỗi TimeoutException : lỗi này thuộc về explicitWait
        // Nguyên nhân : truyền tham số kiểu By, trong hàm explicitWait có hàm đang dùng kiểu tham số này

    }

    @Test
    // TH không tìm thấy element đối với Explicit wait, dùng kiểu tham số là WebElement
    public void TC_03_Not_Found_Element_Only_Explicit_Params_WebElement() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#email_1"))));
        // Hàm trên sẽ log ra lỗi NoSuchElement : lỗi không tìm thấy element
        // Nguyên nhân : thứ tự code sẽ chạy bắt đầu từ driver.findElement(By.cssSelector("input#email_1")))
        // ==> Hàm findElement lại liên quan đến implicit, mà implicit lại chưa set (implicitWait = 0) nên sẽ fail

    }



    @Test
    public void TC_04_Not_Found_Element_Mix_Explicit_Implicit() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Viết hàm để lấy ra thời gian bắt đầu và thời gian kết thúc khi thực thi hàm explicit ở dưới
        // Lấy thời gian bắt đầu thực thi hàm explicitWait ở dưới
        System.out.println("Start : " + getDateTimeNow());

        // Locator "input#email_1" không tồn tại trong DOM
        // Mục đích sử dụng try catch nhằm trong T/H fail thì lệnh  System.out.println ở dưới vẫn được thực thi
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email_1")));
        } catch (Exception e) {
            // Lấy thời gian kết thúc thực thi hàm explicitWait ở trên
            System.out.println("End : " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    // Hàm để lấy thời gian hiện tại
    public String getDateTimeNow() {
        Date date = new Date();
        return date.toString();
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
