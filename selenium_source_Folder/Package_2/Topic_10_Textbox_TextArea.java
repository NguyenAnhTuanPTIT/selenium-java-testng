package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_10_Textbox_TextArea {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    String firstName = "Automation";
    String lastName = "Selenium";
    String fullName = firstName + " " + lastName;


    Random rand = new Random();
    String emailAddress = "autmation" + rand.nextInt(9999) + "@gmail.com";

    @BeforeClass
    public void initalBrowser(){
        driver = new ChromeDriver();
        driver.get("https://live.techpanda.org/");
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_TechPanda() throws InterruptedException {
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

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Thank you for registering with Main Website Store.");

        // Verify theo cách tương đối, vì text trong element đang có dạng nested text, text bị phân tán
        String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInformationText.contains(fullName));
        Assert.assertTrue(contactInformationText.contains(emailAddress));
        // Hoặc có thể viết kẹp như sau
        Assert.assertTrue(contactInformationText.contains(fullName) && contactInformationText.contains(emailAddress));



    }

    @Test
    public void TC_02_Login() {

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
