package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_29_Explicit_Wait_Exercises {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();



    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Less_Than() {
        // Set explicit wait là 3s
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(3));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        // Điều kiện wait cho đến khí element được visible (wait 3s như đã set ở trên )
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // Trả lỗi TimeOut Exception do chờ element xuất hiện quá 3s mà chưa thấy hiển thị

    }

    @Test
    public void TC_02_Equal() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        // Điều kiện wait cho đến khí element được visible (wait 5s như đã set ở trên)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // => Verify thành công do trùng timeout của explicit với timeout hoàn thành của icon loading
    }

    @Test
    public void TC_03_Greater_Than() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        // Điều kiện wait cho đến khí element được visible (wait 10s như đã set ở trên)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // => Verify thành công do timeout của explicit lớn hơn timeout hoàn thành của icon loading
    }

    @Test
    // Đây là testcase để dùng các điều kiện wait khác với các điều kiện wai ở 3 testcase trên đã dùng
    public void TC_04_Other_ways() {
        // Set explicit wait là 3s
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        // Khi click button icon loading sẽ hiển thị trong vòng 5s

        // Visible (Dành cho 1 element ở step sau xuất hiện)
        // => Cách wait visible sẽ phù hợp, do code đọc dễ hiểu nhất, và verify được kết quả rõ ràng nhất
        WebElement helloText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(helloText.getText(), "Hello World!");

        // Invisible (dành cho 1 element sắp biến mất / kì vọng mất đi)
        // Tìm icon loading để dùng cho wait này
        // => Cách wait này sẽ đi qua thêm một step để verify text nữa
        boolean loadingIconStatus = explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertTrue(loadingIconStatus);

        // Text (dành để chờ cho 1 element có chứa text đó hiện ra)
        boolean helloTextStatus = explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"),"Hello World!"));
        Assert.assertTrue(helloTextStatus);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
        // Trả lỗi No Such Element do thời gian explicit wait nhỏ hơn thời gian hoàn thành của icon loading
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
