package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_02_Selenium_Locator {

    // Khai báo
    WebDriver driver;

    String fullName = "Selenium Loactor";

    @BeforeClass
    public void initialBrowser(){
        //Mở Browser lên
        driver = new FirefoxDriver();

        //Mở app lên đến màn hình Login
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() throws InterruptedException {
        // HTML source code
        // Thẻ - Thuộc tính - Giá trị thuộc tính
        // Tag name - Attribute - Value
        // Xpath: //tagname[@attribute='value']
        // Css:   //tagname[attribute='value']

        // Tương tác lên element trên web
        // 8 loại locator để tương tác với element của web
        driver.findElement(By.id("small-searchterms")).sendKeys("Airpod");

        Thread.sleep(3000); // Đây là hàm của java. ko phải của selenium

        driver.findElement(By.id("FirstName")).sendKeys("Automation");

        Thread.sleep(3000);


/*        // Sau dấu . gọi hàm/biến của thư viện đó ra

        // Tìm 1 element


        // 1 - Thao tác lên luôn (dùng 1 lần)
        driver.findElement(By.id(""));


        // 2 - Lưu dữ liệu lại (dùng nhiều lần)
        WebElement emailTextbox = driver.findElement(By.id(""));
        emailTextbox.click();
        emailTextbox.sendKeys("");
        emailTextbox.isDisplayed();
        emailTextbox.isDisplayed();

        // Tìm nhiều element giống nhau
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

        driver.findElement(By.cssSelector("")).click();

        driver.findElement(By.cssSelector("")).getText();*/

    }

    @Test
    public void TC_02_Class() throws InterruptedException {
        // Đặc thù của class là không lấy hết toàn bộ giá trị (nếu có khoảng trắng) và ngược lại
        driver.findElement(By.className("register-next-step-button")).click();

        Thread.sleep(3000);

    }

    @Test
    public void TC_03_Name() {
        // Lấy hết toàn bộ giá trị
        driver.findElement(By.name("FirstName"));
        driver.findElement(By.name("LastName"));
        driver.findElement(By.name("Email"));

    }

    @Test
    public void TC_04_LinkText() {
        // Chỉ làm việc được với element là link và có text
        // Nhận biết bằng thẻ a và có attribute là href
        // Phải lấy hết toàn bộ text ko chừa cái nào hết (tuyệt đối)
        driver.findElement(By.linkText("Register"));

        driver.findElement(By.linkText("Log in"));

        driver.findElement(By.linkText("Wishlist"));

    }

    @Test
    public void TC_05_Partial_Link_Text() {


    }

    @Test
    public void TC_06_Tagname() {


    }

    @Test
    public void TC_07_Css() {


    }

    @Test
    public void TC_08_XPath() {


    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
