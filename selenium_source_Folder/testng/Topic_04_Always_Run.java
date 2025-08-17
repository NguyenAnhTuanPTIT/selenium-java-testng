package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_04_Always_Run {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("https://www.fahasa.com/");

        // Giả định fail tại step này, các testcase ở dưới sẽ bị skip, afterClass sẽ không chạy => không đóng được browser
        // Nếu càng nhiều case bị fail thế này, thì trong task manager sẽ giữ lại driver của browser => tốn memory => máy sẽ bị đơ
        // Như vậy các khắc phục là sẽ thêm alwaysRun = true để ép afterClass chạy trong T/H beforeClass bị fail, đồng thời xóa driver
        // của browser trong task manager
        Assert.assertTrue(false);
    }

    @Test
    public void TC_01(){
        System.out.println("Run TC_01");
    }

    @Test
    public void TC_02(){
        System.out.println("Run TC_02");
    }

    @Test
    public void TC_03(){
        System.out.println("Run TC_03");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        driver.quit();
    }



}
