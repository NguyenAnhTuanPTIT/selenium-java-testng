package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_13_Button {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Fahasa() {

        driver.get("https://www.fahasa.com/customer/account/create/");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        // Kiểm tra button đăng nhập có bị disable hay không, dùng hàm isEnabled()
        By loginButton = By.cssSelector("button.fhs-btn-login");
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        // Kiểm tra background color của button là màu xám, dùng hàm getCssValue()
        String  loginBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
        // Chuyển màu get được về mã hexa
        Color loginColor = Color.fromString(loginBackgroundColor);
        // Kiểm tra với mã màu Hexa tương ứng
        Assert.assertEquals(loginColor.asHex().toUpperCase(),"#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("tuan@gmail.com");

        driver.findElement(By.cssSelector("input#login_password")).sendKeys("abc123456");

        // Kiểm tra button Login đã enable khi nhập đủ 2 field email và password
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        // Kiểm tra màu của button khi enable
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color")).asHex().toUpperCase(),"#C92127");

        // Click button Login và verify error message hiển thị
        driver.findElement(loginButton).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fhs-login-msg")).getText(),"Số điện thoại/Email hoặc Mật khẩu sai!");

    }

    @Test
    public void TC_02_Login() {
        driver.get("");
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
