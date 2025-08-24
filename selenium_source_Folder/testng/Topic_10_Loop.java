package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

public class Topic_10_Loop {
//1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    String firstName = "Automation";
    String lastName = "Selenium";
    String fullName = firstName + " " + lastName;


    Random rand = new Random();
    String emailAddress = "autmation" + rand.nextInt(9999) + "@gmail.com";

    // Khai báo các thông tin cần thiết để lưu các giá trị vào file user.properties
    Properties prop = new Properties();
    FileOutputStream  outputStream;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void initalBrowser() throws FileNotFoundException {
        driver = new ChromeDriver();

        String path = projectPath + "\\dataTest\\user.properties";
        outputStream = new FileOutputStream(path);

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    // Dùng invocationCount để set cho testcase chạy lặp lại 5 lần
    // Phù hợp cho việc tạo ra bộ dữ liệu phục vụ cho việc manual test
    @Test(invocationCount = 2)
    public void TC_Register() throws InterruptedException, IOException {
        driver.get("https://live.techpanda.org/");

        String emailAddress = "autmation" + rand.nextInt(9999) + "@gmail.com";

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys("123abc");
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys("123abc");

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        Thread.sleep(2000);

        driver.findElement(By.cssSelector("button#proceed-button")).click();

        Thread.sleep(2000);

        // Click vào linktext My Account trên header
        driver.findElement(By.cssSelector("div.account-cart-wrapper a")).click();

        // Click vào logout
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();

        Thread.sleep(2000);

        System.out.println("Email Address : " + emailAddress);

        prop.setProperty("email",emailAddress);

        // Khi set rồi phải store luôn, vì khi chạy hết 5 lần thì nó chỉ lưu vào file giá trị cuối cùng
        prop.store(outputStream,null);

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
