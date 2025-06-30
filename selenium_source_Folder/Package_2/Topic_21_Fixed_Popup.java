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

public class Topic_21_Fixed_Popup {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_NgoaiNgu24h_In_Dom() throws InterruptedException {

        driver.get("https://ngoaingu24h.vn/");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();


        // Kiểm tra popup có hiển thị hay không
        Assert.assertTrue(driver.findElement(By.cssSelector("div.MuiDialog-paper")).isDisplayed());

        // Nhập  vào textbox Tài khoản đăng nhập
        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("automationFC");

        // Nhập password
        driver.findElement(By.cssSelector("input[autocomplete='new-password']")).sendKeys("123456789");

        // Click button Đăng nhập
        driver.findElement(By.xpath("//div[@class='auth-form']//button[text()='Đăng nhập']")).click();

        Thread.sleep(2000);

        // Verify message lỗi
        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(),"Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        // Click button X
        driver.findElement(By.cssSelector("button.close-btn")).click();

        Thread.sleep(2000);

        // Kiểm tra popup không hiển thị sau khi đóng
        Assert.assertTrue(driver.findElements(By.cssSelector("div.MuiDialog-paper")).isEmpty());

    }

    @Test
    public void TC_02_Kyna_In_HTML() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        Thread.sleep(3000);

        // Verify popup hiển thị
        By loginPopup = By.cssSelector("div#k-popup-account-login-mb div.modal-dialog");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        // Nhập email
        driver.findElement(By.cssSelector("input#user-login")).sendKeys("autmationFC@gmail.com");

        // Nhập password
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456789");

        // Click button Đăng nhập
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Thread.sleep(2000);

        // Verify error message
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");


    }

    @Test
    public void TC_03_Tiki_Not_In_DOM () throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(5000);

        /*
        // Popup 01 - Dạng Marketing
        // Hiển thị cố định khi vừa mở site
        // Khi đóng lại thì không còn trong HTML
        // Khi refresh page thì lại hiện ra

        // Kiểm tra hiển thị ngay lúc mở site
        By marketingPopup = driver.findElement(By.cssSelector("div#VIP_BUNDLE"));
        Assert.assertTrue(driver.findElement(marketingPopup).isDisplayed());

        // Đóng popup
        driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon']")).click();

        // Kiểm tra lại hiển thị của popup sau khi đóng
        // Hàm findElement dùng tìm element có trong DOM, nếu ko có trong DOM sẽ bị fail
        // Nếu dùng Assert.assertFalse(driver.findElement(marketingPopup).isDisplayed()); sẽ bị fail do không tìm thấy được element trong DOM

         */

        // CLick vào button tài khoản
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(3000);

        // Verify popup hiển thị
        By loginPopup = By.cssSelector("div.ReactModal__Content");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        // Kiểm tra element có trong HTML/DOM (present), nhưng không chắc chắn có hiển thị hay không. Vì chỉ verify con số thôi
        Assert.assertEquals(driver.findElements(loginPopup).size(),1);

        // Click vào linktext Đăng nhập bằng email
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(2000);

        // Click vào button Đăng nhập
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);

        // Verify message lỗi hiển thị
        Assert.assertEquals(driver.findElement(By.cssSelector("span.error-mess:nth-of-type(1)")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.cssSelector("span.error-mess:nth-of-type(2)")).getText(),"Mật khẩu không được để trống");

        // Close popup đi
        driver.findElement(By.cssSelector("img.close-img")).click();
        Thread.sleep(2000);

        // Verify popup không còn hiển thị nữa (không còn trong DOM/HTML)
        // Cách assert này sẽ bị phụ thuộc vào impicitWait set ở trên là 15s nên sẽ khá lâu
        Assert.assertEquals(driver.findElements(loginPopup).size(),0);

    }

    @Test
    public void TC_04_Facebook() throws InterruptedException {
        driver.get("https://www.facebook.com/");
        Thread.sleep(3000);

        // Click vào button Create new account
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // Verify popup hiển thị



    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        //driver.quit();
    }




}
