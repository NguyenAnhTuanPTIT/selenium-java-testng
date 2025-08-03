package Package_2;

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

public class Topic_29_Explicit_Wait_Ajax {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Calendar() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        // Chờ cho element Calenedar hiển thị sau khi load trang thành công => Sau đó verify element này display hay không
        Assert.assertTrue(explicitWait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1"))).isDisplayed());

        // Chờ cho text "No Selected Dates to display." hiển thị sau khi load trang thành công => Sau đó verify text này display hay không
        Assert.assertTrue(explicitWait.until(ExpectedConditions.
                textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"No Selected Dates to display.")));

        // Wait và click vào ngày 22 trong Calendar
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td//a[text()='22']"))).click();

        // Wait cho element Ajax loading invisible
        // Ký tự "$" trong locator nghĩa là kết thúc bằng giá trị gì đó
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1'] > div.raDiv"))));

        // Wait cho text sau khi chọn ngày 22 trên Calendar được hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.
                textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"),"Tuesday, July 22, 2025")));

    }

    @Test
    public void TC_02_() {

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
