package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Frame_iFrame {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;


    @BeforeClass
    public void initalBrowser(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Iframe_FormSite() throws InterruptedException {
        // Trang HTML A
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        // Click vào img để load iframe lên
        driver.findElement(By.xpath("//img[@title='Campus-Safety-Survey-Forms-and-Examples']")).click();
        Thread.sleep(3000);

        // Switch qua iframe
        // Có 3 cách để switch qua iframe

        // driver.switchTo().frame(0); // Chọn theo index, dùng cho page hiện tại có nhiều frame/iframe
        // Khi thêm mới/ update lại/ xóa bớt đo thì đổi index của iframe

        // driver.switchTo().frame("iframe-one85593366");  // Chọn theo giá trị id, phù hợp cho site dùng iframe/frame có id hoặc name

        // Swith qua iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"))); // dùng theo element, cover cho 2 cách trên

        // Element thuộc HTML B
        // Chọn giá trị trong 2 selectbox
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");

        // Click vào radio button
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        Thread.sleep(3000);

        // Từ B quay lại A
        driver.switchTo().defaultContent();

        // Click vào button Login sau khi đã switch qua frame ngoài cùng
        driver.findElement(By.cssSelector("a.fs-btn--transparent-kashmir.menu-item-login")).click();

        Thread.sleep(3000);

        // Click tiếp vào button Login
        driver.findElement(By.cssSelector("button#login")).click();

        // Verify error message hiển thị
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");


    }

    @Test
    public void TC_02_Iframe_ToiDiCodeDao() throws InterruptedException {
        driver.get("https://toidicodedao.com/");
        Thread.sleep(3000);

        // Switch qua iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page iframe")));

        By followerText = By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div[text()]");
        Assert.assertEquals(driver.findElement(followerText).getText(),"399,465 followers");




    }

    @Test
    public void TC_03_frame_hdfcBank() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        Thread.sleep(3000);

        driver.switchTo().frame("login_page");

        // Nhập vào textbox Customer ID
        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationFC");

        // Click button CONTINUE
        driver.findElement(By.cssSelector("a.login-btn")).click();
        Thread.sleep(3000);

        // Switch về frame ngoài cùng
        driver.switchTo().defaultContent();

        // Nhập password
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789");

        // Click button Login
        driver.findElement(By.cssSelector("a#loginBtn")).click();

        // Verify error message
        Assert.assertEquals(driver.findElement(By.cssSelector("p.error-msg")).getText(),"Customer ID/IPIN (Password) is invalid. Please try again.");






    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        //driver.quit();
    }


}
