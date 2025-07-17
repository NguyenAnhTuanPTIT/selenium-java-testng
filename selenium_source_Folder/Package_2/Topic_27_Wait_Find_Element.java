package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_27_Wait_Find_Element {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        // Set total time  = 13s
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
        driver.findElement(By.cssSelector("input#FirstName"));

        // 3 - Nếu không tìm thấy element nào
    }

    @Test
    public void TC_02_FindElements() {

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
