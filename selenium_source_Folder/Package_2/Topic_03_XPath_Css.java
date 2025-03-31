package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_XPath_Css {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
    //    driver.get("https://live.techpanda.org/");
    //    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Register_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Vui lòng nhập số điện thoại.");

    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Nhập dữ liệu hợp lệ hết vào các field, chỉ trừ textbox Email và textbox Confirm Email
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Nguyen Van A");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("NguyenVanA@gmail@com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("NguyenVanA@gmail@com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("ABCD1234");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("ABCD1234");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("088877545444");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),"Email nhập lại không đúng");

    }

    @Test
    public void TC_03_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Nhập dữ liệu hợp lệ hết vào các field, chỉ trừ textbox Confirm Email
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Nguyen Van A");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("NguyenVanA@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("NguyenVanB@gmail@com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("ABCD1234");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("ABCD1234");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("088877545444");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Password_Validation() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Nhập dữ liệu hợp lệ hết vào các field, chỉ trừ textbox Password và textbox Confirm Password
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Nguyen Van A");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("NguyenVanA@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("NguyenVanA@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("ABCD");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("ABCD");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("088877545444");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test
    public void TC_05_Incorrect_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Nhập dữ liệu hợp lệ hết vào các field, chỉ trừ textbox Confirm Password
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Nguyen Van A");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("NguyenVanA@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("NguyenVanA@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("ABCD1234");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("ABCD1235");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("088877545444");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Mật khẩu bạn nhập không khớp");

    }

    @Test
    public void TC_06_Invalid_Phone_Number() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Nhập dữ liệu hợp lệ hết vào các field, chỉ trừ textbox Phone Number
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Nguyen Van A");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("NguyenVanA@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("NguyenVanA@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("ABCD1234");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("ABCD1235");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("088877545");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Số điện thoại phải từ 10-11 số.");

        driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("1238877545");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }


    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
