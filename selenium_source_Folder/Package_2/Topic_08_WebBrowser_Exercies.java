package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_WebBrowser_Exercies {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new ChromeDriver();
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_URL() {
        driver.get("https://live.techpanda.org/");
        // Click vào linktext My Account dưới footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Verify url của page mới
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");

        // Click vào button Create an Account
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        // Verify url của page mới
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");

    }

    @Test
    public void TC_02_Title() {
        driver.get("https://live.techpanda.org/");
        // Click vào linktext My Account dưới footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Verify title của page mới
        Assert.assertEquals(driver.getTitle(),"Customer Login");

        // Click vào button Create an Account
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        // Verify title của page mới
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_03_Navigation() {
        driver.get("https://live.techpanda.org/");
        // Click vào linktext My Account dưới footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Click vào button Create an Account
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        // Back về trang trước
        driver.navigate().back();

        // Verify url của page mới
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");

        // Forward tới trang tiếp
        driver.navigate().forward();

        // Verify url của page mới
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_04_Page_Source() {
        driver.get("https://live.techpanda.org/");
        // Click vào linktext My Account dưới footer
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        // Verify Login page chứa text = Login or Create an Account
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        // Click vào button Create an Account
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        // Verify Login page chứa text = Create an Account
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
