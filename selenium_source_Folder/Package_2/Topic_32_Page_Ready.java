package Package_2;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_32_Page_Ready {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    // Sử dụng WebDriverWait đơn thuần
    public void TC_01_PageReady() {
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#Email")));

        // Input email và password
        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");

        // Click Button Login
        driver.findElement(By.cssSelector("button.login-button")).click();

        // Wait trong trường hợp có popup thì click OK để đóng popup lại
        WebElement popupCustomerAlert = explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div[id*='loadCustomerStatisticsAlert'] div[class='modal-content']")));
        if(popupCustomerAlert.isDisplayed()){
            driver.findElement(By.cssSelector("div[id*='loadCustomerStatisticsAlert'] div[class='modal-content'] " +
                    "div[class='modal-footer'] span")).click();
        }

        WebElement popupOrderAlert = explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div[id*='loadOrderStatisticsAlert'] div[class='modal-content']")));
        if(popupOrderAlert.isDisplayed()){
            driver.findElement(By.cssSelector("div[id*='loadOrderStatisticsAlert'] div[class='modal-content'] " +
                    "div[class='modal-footer'] span")).click();
        }

        // Wait cho modal backdrop này biến mất sau khi tắt hai popup ở trên
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.cssSelector("div[class='modal-backdrop fade']")));

        // Wait cho icon Loading biến mất
        // Khi icon Loading biến mất rồi thì page sẽ ready
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy"))));

        // Click vào Button Logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        // Wait cho login form hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.form-fields"))).isDisplayed());

    }

    @Test
    // Sử dụng hàm isPageLoadedSuccess() viết ở dưới
    public void TC_02_Function() {
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#Email")));

        // Input email và password
        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");

        // Click Button Login
        driver.findElement(By.cssSelector("button.login-button")).click();

        // Wait trong trường hợp có popup thì click OK để đóng popup lại
        WebElement popupCustomerAlert = explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div[id*='loadCustomerStatisticsAlert'] div[class='modal-content']")));
        if(popupCustomerAlert.isDisplayed()){
            driver.findElement(By.cssSelector("div[id*='loadCustomerStatisticsAlert'] div[class='modal-content'] " +
                    "div[class='modal-footer'] span")).click();
        }

        WebElement popupOrderAlert = explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div[id*='loadOrderStatisticsAlert'] div[class='modal-content']")));
        if(popupOrderAlert.isDisplayed()){
            driver.findElement(By.cssSelector("div[id*='loadOrderStatisticsAlert'] div[class='modal-content'] " +
                    "div[class='modal-footer'] span")).click();
        }

        // Wait cho modal backdrop này biến mất sau khi tắt hai popup ở trên
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.cssSelector("div[class='modal-backdrop fade']")));

        // Wait cho icon Loading biến mất
        // Khi icon Loading biến mất rồi thì page sẽ ready
        Assert.assertTrue(isPageLoadedSuccess());

        // Click vào Button Logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        // Wait cho login form hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.form-fields"))).isDisplayed());
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

       driver.quit();
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

}
