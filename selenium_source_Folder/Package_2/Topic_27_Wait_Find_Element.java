package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_27_Wait_Find_Element {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        // Set total time  = 13s
        // Nếu set total time = 0s -> thì vào findElement sẽ fail ngay
        // Nếu không set implicitWait thì findElement sẽ nhận time = 0 -> Vào find thì fail luôn
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_FindElement() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        // 1 - Nếu Tìm thấy duy nhất element
        // Output: trả về đúng element đó
        // Không cần chờ hết total time của implicit
        driver.findElement(By.cssSelector("input#FirstName"));

        // 2 - Nếu tìm thấy nhiều hơn 1 element
        // -> Chỉ thao tác với element đầu tiên
        // -> Lưu ý là khi lấy locator nên check trước
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Testing");

        // 3 - Nếu không tìm thấy element nào
        // Mới đầu vào findElement nhưng không thấy:
        // Tìm lại mà thấy thì không cần chờ hết tổng time còn lại
        // Tìm lại và không thấy hết tổng time 13s thì đánh fail testcase
        // Show lỗi: NoSuchElementException

        //---------------------------------------- CHÚ Ý -----------------------------------------------------------------------
            /*
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
            // Nếu set impclicitWait ngay tại đây thì sẽ ảnh hưởng các hàm findElement ở phía sau
            // Sẽ ăn vào cả các testcase phía sau luôn, VD: nếu set implicitWait ở đây sẽ ảnh hưởng qua TC_02_FindElements


            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
            // Còn nếu tiếp tục set implicitWait thì lúc này các hàm findElement phía sau sẽ chịu ảnh hưởng bởi giá trị set mới này

            driver.findElement(By.cssSelector("input#FirstName")); // Hàm này sẽ dùng implicitWait = 8s

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            // Nếu tiếp tục set lại implicitWait thì sẽ ảnh hưởng đến các hàm phía sau, không ảnh hưởng đến hàm findElement ở trên
            */

        // -----------------------------------------------------------------------------------------------------------------------

    }

    @Test
    public void TC_02_FindElements() {
        List<WebElement> elements = null;

        // 1 - Nếu Tìm thấy duy nhất element
        // Trả về đúng 1 cái
        elements = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println(elements.size());

        // 2 - Nếu tìm thấy nhiều hơn 1 element
        // Trả về hết toàn bộ element matching
        elements = driver.findElements(By.cssSelector("input[type='text']"));
        System.out.println(elements.size());

        // 3 - Nếu không tìm thấy element nào
        // Trả về list Element = 0
        // Mới đầu vào findElement nhưng không thấy:
        // Tìm lại mà thấy thì không cần chờ hết tổng time còn lại
        // Tìm lại và không thấy hết tổng time 13s thì:
        //   + 1. Trả về list element = 0
        //   + 2. KHÔNG ĐÁNH FAIL TESTCASE
        elements = driver.findElements(By.cssSelector("input#RememberMe"));
        System.out.println(elements.size());
        Assert.assertEquals(elements.size(),0);

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
