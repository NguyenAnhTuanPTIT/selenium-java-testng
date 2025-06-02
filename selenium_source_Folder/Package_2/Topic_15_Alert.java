package Package_2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Alert {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        // Accept alert : chỉ có thể accept
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        Thread.sleep(3000);

        Alert alert = driver.switchTo().alert();
        // dùng hàm switchTo để chuyển thao tác lên alert
        // Accept Alert
        // driver.switchTo().alert().accept();

        // Cancel Alert
        // driver.switchTo().alert().dismiss();

        // Nhập text vào Alert
        // Accept Alert
        // driver.switchTo().alert().sendKeys();

        // Get text bên trong Alert (Description)
        Assert.assertEquals(driver.switchTo().alert().getText(), "I am a JS Alert");

        alert.accept(); // Sau khi chạy lệnh này xong sẽ mất alert

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");


    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        // Dùng hàm explicit wait để chờ swtich qua alert => vừa wait alert present và vừa switch qua
        // Đồng thời nếu như có alert nó sẽ tự động switch qua
        // ==> Sử dụng wait để giảm việc testcase bị fail lại
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(driver.switchTo().alert().getText(), "I am a JS Confirm");

        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        // Dùng hàm explicit wait để chờ swtich qua alert => vừa wait alert present và vừa switch qua
        // Đồng thời nếu như có alert nó sẽ tự động switch qua
        // ==> Sử dụng wait để giảm việc testcase bị fail lại
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(driver.switchTo().alert().getText(), "I am a JS prompt");

        String enterKey = "Nguyen Anh Tuan";
        alert.sendKeys(enterKey);

        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + enterKey);
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
