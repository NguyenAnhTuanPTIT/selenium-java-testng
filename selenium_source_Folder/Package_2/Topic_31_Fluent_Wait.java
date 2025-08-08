package Package_2;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_31_Fluent_Wait {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    // FluentWait <T> : với T là data type. Ví dụ như bên dưới
    FluentWait<WebDriver> driverFluentWait;
    // FluentWait<WebElement> elementFluentWait;
    // FluentWait<String> stringFluentWait;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        // Khi khai báo là data type gì thì khi khởi tạo sẽ đi kèm kiểu dữ liệu đó. VD:
        // driverFluentWait = new FluentWait<>(driver);

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Dùng findElement đã khai báo ở dưới thay cho hàm findElement thông thường hay dùng
        findElement(10, 100, By.id("email"));


    }

    @Test
    public void TC_02_() {

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }

    // Viết 1 hàm để tìm element với timeout và polling tự set
    // Điều kiện của hàm là findElement : kiểu trả về của hàm apply
    // findElement cần có driver => driver sẽ làm tham số của hàm apply

    public WebElement findElement(long timeout, long polling, By by) {
        driverFluentWait = new FluentWait<>(driver);

        // Set timeout
        driverFluentWait.withTimeout(Duration.ofSeconds(timeout));

        // Set polling time
        driverFluentWait.pollingEvery(Duration.ofMillis(polling));

        // Ignore exception trong quá trình chạy
        driverFluentWait.ignoring(NoSuchElementException.class);

        // Hoặc có thể viết gộp lại các hàm set trên dưới dạng chaining, 1 chuỗi các hoạt động liên tiếp. Giống như bên user interaction
        // driverFluentWait.withTimeout(Duration.ofSeconds(timeout))
        //        .pollingEvery(Duration.ofMillis(polling))
        //        .ignoring(NoSuchElementException.class);

        
        // T là tham số, R là kiểu trả về
        // Ở đây T = WebDriver. R = WebElement
        return driverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    }

}
