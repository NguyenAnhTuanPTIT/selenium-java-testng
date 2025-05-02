package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_WebElement_Exercises_02 {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new ChromeDriver();
        driver.get("https://live.techpanda.org/");
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01() throws InterruptedException { // Login with empty email and password
        // Click vào linktext My Acount
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Click vào button Login
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),
                "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),
                "This is a required field.");

        Thread.sleep(2000);

    }

    @Test
    public void TC_02() { // Login with invalid email
        driver.navigate().refresh(); // reload lại page

        // Input email không hợp lệ
        driver.findElement(By.cssSelector("input[title='Email Address']")).sendKeys("123@gmail.123");
        driver.findElement(By.cssSelector("input[title='Password']")).sendKeys("123abc");

        // Click vào button Login
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),
                "Please enter a valid email address. For example johndoe@domain.com.");


    }

    @Test
    public void TC_03() { // Login with password < 6 characters
        driver.navigate().refresh(); // reload lại page

        // Input email không hợp lệ
        driver.findElement(By.cssSelector("input[title='Email Address']")).sendKeys("automation_fc@gmail.com");
        driver.findElement(By.cssSelector("input[title='Password']")).sendKeys("123ab");

        // Click vào button Login
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),
                "Please enter 6 or more characters without leading or trailing spaces.");


    }

    @Test
    public void TC_04() throws InterruptedException { // Login with password < 6 characters
        driver.navigate().refresh(); // reload lại page

        // Input email không hợp lệ
        driver.findElement(By.cssSelector("input[title='Email Address']")).sendKeys("automation_fc@gmail.com");
        driver.findElement(By.cssSelector("input[title='Password']")).sendKeys("123abc");

        // Click vào button Login
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Thread.sleep(3000);

        driver.findElement(By.cssSelector("button#proceed-button")).click();

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),
                "Invalid login or password.");


    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        //driver.quit();
    }


}
