package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_27_Wait_Implicit {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("https://automationfc.github.io/dynamic-loading/");

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....

    @Test
    public void TC_01_Not_Set() {

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // Trả lỗi No Such Element do khi click vào và tìm ngay element
    }


    @Test
    public void TC_02_Less_Than() {
        // Set implitcit wait là 3s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // Trả lỗi No Such Element do thời gian implicit wait nhỏ hơn thời gian hoàn thành của icon loading

    }

    @Test
    public void TC_03_Equal() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // => Verify thành công do trùng timeout của implicitWait với timeout hoàn thành của icon loading
    }

    @Test
    public void TC_04_Greater_Than() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // => Verify thành công do timeout của implicitWait lớn hơn timeout hoàn thành của icon loading
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
