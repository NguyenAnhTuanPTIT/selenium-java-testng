package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
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
import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
        // Dùng hàm mà thư viện cung cấp để kiểm tra: TestNG, JUnit,...
        //Assert.assertEquals();
        // 1 - Lưu dữ liệu lại rồi kiểm tra sau
        String homePageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle,"nopCommerce demo store");
        Assert.assertTrue(homePageTitle.contains("demo store"));

        // 2 - Kiểm tra trực tiếp, ít tốn bộ nhớ
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");

        // ==> Khi dữ liệu dùng từ 2 ần trở lên thì mới khai báo biến để tái sử dụng lại, còn dùng 1 lần thì không nên

        // Lấy ra URL của page hiện tại
        driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/cart");

        // Lấy ra Page Source Code, toàn bộ code HTML,CSS, JS, jQuerry
        // Chỉ để verify tương đối, không verify tuyệt đối được. Vì page source chứa rất nhiều giá trị
        Assert.assertTrue(driver.getPageSource().contains("Conditions of Use"));

        // Lấy ra ID của tab/ window đang active (đang đứng hiện tại)
        driver.getWindowHandle();

        // Lấy ra tất cả ID của tất cả các tab/ window đang có
        driver.getWindowHandles();

        // Đi tìm 1 Element
        driver.findElement(By.xpath(""));

        // Đi tìm nhiều Element
        driver.findElements(By.xpath(""));

        // Selenium Version 3x - sẽ có dấu gạch ở popup gợi ý hàm, cho biết hàm đã lỗi thời
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.DAYS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MINUTES);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.HOURS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.NANOSECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MICROSECONDS);

        // Selenium Version 4x
        // Dùng để chờ cho việc tìm element (findElement / findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofDays(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofHours(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); // chờ hết trong thời gian để tìm Element, vượt thời gian đó sẽ fail
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofNanos(15));


        // Dùng để chờ cho việc page được load xong
        // Thực tế không cần dùng hàm này, vì thực tế driver.get("") đã có wait rồi
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        // Dùng để chờ cho 1 đoạn script được thực thi xong
        // JavaScriptExcutor -js
        // Thực tế cũng sẽ không cần đến hàm này
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

        // Thu nhỏ browser về Taskbar để chạy
        driver.manage().window().minimize();

        // Phóng to browser (vẫn còn thấy taskbar)
        driver.manage().window().maximize();

        // Phóng to màn hình hết cỡ (không thấy taskbar)
        driver.manage().window().fullscreen();

        // Dùng để set kích thước của browser
        // Thực tế đùng để test Responsive bên manual / Test GUI (font, color, size, position, location)
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().window().getSize();


        // Đưa browser đến vị trí mong muốn trên màn hình
        // Liên quan đến độ phân giải màn hình đang sử dụng
        // Thưc tế sẽ ít dùng
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();


;
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
