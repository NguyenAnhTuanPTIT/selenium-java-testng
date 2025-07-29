package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_28_Static_Wait {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();


    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....

    @Test
    public void TC_01_Less_Than() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading/");


        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // Trả lỗi No Such Element do thời gian wait cứng nhỏ hơn thời gian hoàn thành của icon loading

    }

    @Test
    public void TC_02_Equal() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // => Verify thành công do trùng timeout của wait cứng với timeout hoàn thành của icon loading
    }

    @Test
    public void TC_03_Greater_Than() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        Thread.sleep(10000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // => Verify thành công do timeout của wait cứng lớn hơn timeout hoàn thành của icon loading, nhưng phải wait cho hết 10s
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
