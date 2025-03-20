package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
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
    // Chỉ làm việc với element là link
    // Có thể lấy hết toàn bộ text hoặc 1 phần (hay dùng)

        driver.findElement(By.partialLinkText("Digital")); //Linktext hoàn chỉnh là Digital downloads

        driver.findElement(By.partialLinkText("downloads"));
    }

    @Test
    public void TC_06_Tagname() {
        // Tên thẻ (HTML)
        // Tìm tất cả element giống nhau (thẻ của component giống nhau)
        // Thường dùng để tìm tất cả các textbox/ checkbox/ radio/ link/ button/...
        driver.findElements(By.tagName("button"));

        driver.findElements(By.tagName("input"));

        driver.findElements(By.tagName("label"));

    }

    @Test
    public void TC_07_Css() {
        // CSS có thể cover việc tìm element theo: ID/ Class/ Name/ LinkText/ Partial LinkText/ Tagname
        // CSS cũng có thể cover Xpath, nhưng một số trường hợp sẽ không dùng được
        driver.findElement(By.cssSelector("input#Company")); // Tìm theo id của element
        driver.findElement(By.cssSelector("#Company")); // Có thể viết tắt không cần dùng tên thẻ
        driver.findElement(By.cssSelector("input[id='Company']")); // Viết theo kiểu chuẩn

        driver.findElement(By.cssSelector("button.register-next-step-button")); // Tìm theo class của element (không lấy hết giá trị của class)
        driver.findElement(By.cssSelector("button[class='button-1 register-next-step-button']")); // Nếu viết theo kiểu chuẩn thì giá trị của class phải lấy hết

        driver.findElement(By.cssSelector("input[name='Password']")); // Tìm element theo name thì phải viết kiểu chuẩn

        // Tìm element theo LinkText, do CSS ko tìm theo text được nên sẽ dựa vào các attribute khác để tìm
        // Dùng "*=" để tìm một phần giá trị
        driver.findElement(By.cssSelector("a[href*='register']"));

        // Tìm theo element theo partial linktext cũng dùng tương tự như trên

        // Tìm element theo tag name thì truyền tên thẻ vào
        driver.findElements(By.cssSelector("a")); // Tìm tất cả thẻ a
        driver.findElements(By.cssSelector("input")); // Tìm tất cả thẻ input
        driver.findElements(By.cssSelector("button")); // Tìm tất cả thẻ button

    }

    @Test
    public void TC_08_XPath() {
        // Xpath có thể cover việc tìm element theo: ID/ Class/ Name/ LinkText/ Partial LinkText/ Tagname và thậm chí cả CSS
        driver.findElement(By.xpath("//input[@id='small-searchterms']")); // Dạng chuẩn
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        driver.findElement(By.xpath("//input[@id='LastName']"));

        // Tìm element theo class
        driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']"));
        driver.findElement(By.xpath("//button[contains(@class, 'register-next-step-button')]")); // Nếu chỉ lấy một phần trong class

        // Tìm element theo name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        // Tìm element theo tagname
        driver.findElement(By.xpath("//a"));
        driver.findElement(By.xpath("//button"));
        driver.findElement(By.xpath("//input"));

        // Tìm element theo linktext
        driver.findElement(By.xpath("//a[text()='Register']"));
        driver.findElement(By.xpath("//a[text()='Shipping & returns']"));

        // Tìm element theo partial linktext
        driver.findElement(By.xpath("//a[contains(text(),'returns')]"));
    }

    @Test
    public void TC_09_Relative_Locator() {
        // Chỉ dùng Relative Locator khi:
        // 1 - Không thể định danh được element của chính nó (dựa vào nhưng cái vị trí bên cạnh/ gần đó)
        // 2 - Được sử dụng để test GUI (giao diện - position khớp với Design)

        driver.get("https://demo.nopcommerce.com/login");
        // Khai báo element sử dụng kiểu dữ liệu By
        By rememberMeCheckboxBy = By.id("RememberMe");

        By forgotPasswordLinkBy = By.cssSelector("span.forgot-password");

        By passwordTextboxBy = By.cssSelector("input#Password");

        By loginButtonBy = By.cssSelector("button.login-button");

        driver.findElement(RelativeLocator.with(By.tagName("label")).above(loginButtonBy) // label nằm trên button Login
                .below(passwordTextboxBy) // label nằm dưới textbox password
                .toRightOf(rememberMeCheckboxBy) // label nằm bên phải của checkbox RememberMe
                .toLeftOf(forgotPasswordLinkBy) // label nằm bên trái link Forgot password
        );


    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
