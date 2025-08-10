package Package_2;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
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
    public void TC_01_First_Example() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        // Dùng findElement đã khai báo ở dưới thay cho hàm findElement thông thường hay dùng
        findElement(10, 100, By.id("email"));

    }

    @Test
    public void TC_02_Second_Example() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // Dùng hàm findElement() đã viết bên dưới để click vào Button Start
        findElement(10, 100, By.cssSelector("div#start button")).click();

        // Dùng hàm getElementText() để verify text mong muốn
        Assert.assertEquals(getElementText(By.cssSelector("div#finish h4")),"Hello World!");
    }

    @Test
    public void TC_03_Second_Example() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        // Dùng hàm findElement() đã viết bên dưới để click vào Button Start
        findElement(10, 100, By.cssSelector("div#start button")).click();

        // Dùng hàm isElementDisplayed() để verify text mong muốn hiển thị
        Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
    }

    @Test
    public void TC_04_Third_Example() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        // Dùng hàm findElement() đã viết bên dưới để click vào Button Start
        WebElement counDownTime = findElement(10, 100, By.cssSelector("div#javascript_countdown_time"));

        // Dùng hàm isElementDisplayed() để verify text mong muốn hiển thị
        Assert.assertTrue(isSecondMatching(counDownTime));
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

    // Kiểm tra 1 element hiển thị
    // Điều kiện : isDisplayed => trả về boolean
    public boolean isElementDisplayed(By by) {
        driverFluentWait = new FluentWait<>(driver);

        // Set timeout
        driverFluentWait.withTimeout(Duration.ofSeconds(10));

        // Set polling time
        driverFluentWait.pollingEvery(Duration.ofMillis(500));

        // Ignore exception trong quá trình chạy
        driverFluentWait.ignoring(NoSuchElementException.class);

        // Dùng trong java generic sẽ dùng Boolean (kiểu object) thay vì boolean (kiểu nguyên thủy)
        return driverFluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });
    }

    // Trường hợp có sẵn element rồi, không cần đi tìm nữa
    public boolean isElementDisplayed_new(WebElement element) {
        FluentWait<WebElement> driverFluentWait_new = new FluentWait<>(element);

        // Set timeout
        driverFluentWait_new.withTimeout(Duration.ofSeconds(10));

        // Set polling time
        driverFluentWait_new.pollingEvery(Duration.ofMillis(500));

        // Ignore exception trong quá trình chạy
        driverFluentWait_new.ignoring(NoSuchElementException.class);

        // Dùng trong java generic sẽ dùng Boolean (kiểu object) thay vì boolean (kiểu nguyên thủy)
        return driverFluentWait_new.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                return element.isDisplayed();
            }
        });
    }

    // Lấy ra 1 text của element
    // getText trả về kiểu String => R
    public String getElementText(By by) {
        driverFluentWait = new FluentWait<>(driver);

        // Set timeout
        driverFluentWait.withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofMillis(500))
                        .ignoring(NoSuchElementException.class);

        // Dùng trong java generic sẽ dùng Boolean (kiểu object) thay vì boolean (kiểu nguyên thủy)
        return driverFluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(by).getText();
            }
        });
    }

    public boolean isSecondMatching(WebElement element) {
        FluentWait<WebElement> driverFluentWait_new = new FluentWait<>(element);

        // Set timeout
        driverFluentWait_new.withTimeout(Duration.ofSeconds(13))
                            .pollingEvery(Duration.ofMillis(200)) // 1s kiểm tra được 5 lần
                            .ignoring(NoSuchElementException.class); // Có thể bỏ hàm này vì khi truyền vào là element, nghĩa
                            // là đã tìm thấy element trước đó rồi, nếu throw lỗi thì sẽ throw ở hàm findElement()

        // Dùng trong java generic sẽ dùng Boolean (kiểu object) thay vì boolean (kiểu nguyên thủy)
        return driverFluentWait_new.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                return element.getText().endsWith("00");
            }
        });
    }
}
