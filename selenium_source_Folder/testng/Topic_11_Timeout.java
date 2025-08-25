package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_11_Timeout {
    WebDriver driver;
    By emailTextbox = By.cssSelector("input#email");
    By passwordTextbox = By.cssSelector("input#pass");
    By loginButton = By.cssSelector("button#send2");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    // Timeout của testNG không liên quan đến implicit Timeout hay explicit Timeout, mà nó là  tổng thời gian để chạy
    // testcase  là  bao lâu. Nghĩa là chỉ tính thời gian chạy Test, không tính chạy beforeClass,  afterClass
    @Test(timeOut = 15000)
    public void shouldBeLoginToSystem() {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(passwordTextbox).sendKeys("111111");
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
