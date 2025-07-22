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

public class Topic_27_Wait_Element_Status {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 15s -> cứ nửa sau tìm 1 lần -> tổng 30 lần tìm

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Visible() {
        driver.get("https://www.facebook.com/");

        // ------------------------- Element có trong UI và cây HTML ---------------------------------------------------

        // Chờ cho elelent được visible trong vòng 15s, nếu như element không có trên UI -> sẽ bị fail
            // - Có 3 trường hợp wait: (Cơ chế này áp dụng cho implicitWait, WebDriverWait, FluentWait)
            //   + Case 1 : Element thỏa mãn điều kiện wait ngay lập tức
            //   + Case 2 : Vào wait tìm element nhưng chưa thỏa mãn
            //             => sẽ chờ tiếp và lặp lại điều kiện, nếu tìm thấy thì quay lại giống case 1
            //             => sẽ chờ tiếp và lặp lại và không thấy đến hết timeout luôn
            //   + Case 3 : Hết timeout và không thấy element => Fail step này

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

    }

    @Test
    public void TC_02_Invisible() {
        // Chỉ demo trên code, không chạy trên trình duyệt được do đã thay đổi business
        driver.get("https://www.facebook.com/");

        // Click vào button Create new account để mở popup đăng ký account
        driver.findElement(By.cssSelector("a[data-testid]='open-registration-form-button'")).click();

        // Wait cho textbox Email Address được visible, nghĩa là nếu textbox visible thì poup đã mở và load thành công
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email_']")));

        // ------------------------- Element không có trong UI nhưng có trong cây HTML ---------------------------------
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmtion']")));

        // Click button X để đóng popup lại
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        // ------------------------- Element không có trong UI và trong cây HTML ---------------------------------------
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmtion']")));

    }

    @Test
    public void TC_03_Presence() {
        // Chỉ demo trên code, không chạy trên trình duyệt được do đã thay đổi business
        driver.get("https://www.facebook.com/");

        // Click vào button Create new account để mở popup đăng ký account
        driver.findElement(By.cssSelector("a[data-testid]='open-registration-form-button'")).click();

        // Wait cho textbox Email Address được visible, nghĩa là nếu textbox visible thì poup đã mở và load thành công
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email_']")));

        // Nhập giá trị vào trong textbox email để bật cái textbox Confirm email lên
        // Có thể hình dung đây là điều kiện mồi để textbox confirm email được xuất hiện
        driver.findElement(By.cssSelector("input[name='reg_email_']")).sendKeys("tuan@gmaik.com");

        // ------------------------- Element có trong UI và trong cây HTML ---------------------------------
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmtion']")));

        // Tiếp tục có thêm 1 điều kiện mồi để cho textbox Confirm email không còn xuất hiện trên UI nữa
        driver.findElement(By.cssSelector("input[name='reg_email_']")).clear();

        // ------------------------- Element không có trong UI nhưng có trong cây HTML ---------------------------------
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmtion']")));

    }

    @Test
    public void TC_04_Staleness() {
        // Chỉ demo trên code, không chạy trên trình duyệt được do đã thay đổi business
        driver.get("https://www.facebook.com/");
        // ------------------- 1 - Element có trong UI và cây HTML ----------------------------------------------------

        // Click vào button Create new account để mở popup đăng ký account
        driver.findElement(By.cssSelector("a[data-testid]='open-registration-form-button'")).click();

        // Wait cho textbox Email Address được visible, nghĩa là nếu textbox visible thì poup đã mở và load thành công
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email_']")));

        // Lưu element của textbox Confirm Email vào 1 biến
        // Tại thời điểm này textbox Confirm Email đang có trong HTML
        WebElement emailConfirm = driver.findElement(By.cssSelector("input[name='reg_email_confirmtion']"));

        // Click X để đóng pppup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        // ------------------------- Element không có trong UI và trong cây HTML ---------------------------------------
        explicitWait.until(ExpectedConditions.stalenessOf(emailConfirm));



        // ------------------------- Element không có trong UI nhưng có trong cây HTML ---------------------------------
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmtion']")));

        // Click button X để đóng popup lại
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        // ------------------------- Element không có trong UI và trong cây HTML ---------------------------------------
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmtion']")));

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
