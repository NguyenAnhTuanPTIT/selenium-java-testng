package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Textbox_TextArea {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_TechPanda() {
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys("Autmation");
        driver.findElement(By.cssSelector("input#lastname")).sendKeys("Selenium");
        driver.findElement(By.cssSelector("input#email_address")).sendKeys("autmation1@gmail.com");
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
        Assert.assertTrue(contactInformationText.contains("Automation Selenium"));
        Assert.assertTrue(contactInformationText.contains("automation1@gmail.com"));
        // Hoặc có thể viết kẹp như sau
        Assert.assertTrue(contactInformationText.contains("Automation Selenium") && contactInformationText.contains("automation1@gmail.com"));



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
