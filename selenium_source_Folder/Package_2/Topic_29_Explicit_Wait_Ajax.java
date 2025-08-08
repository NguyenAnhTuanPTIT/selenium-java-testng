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

import java.io.File;
import java.time.Duration;

public class Topic_29_Explicit_Wait_Ajax {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    WebDriverWait explicitWait;

    // Dùng File.separator để xác định được dấu ngăn cách giữa các folder/file ở nhiều hệ điều hành khác nhau
    String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;


    String photo_1 = "photo-1.jpg";
    String photo_2 = "photo-2.jpg";
    String photo_3  = "photo-3.jpg";


    String photo_1Path = uploadFolderPath + photo_1;
    String photo_2Path = uploadFolderPath + photo_2;
    String photo_3Path  = uploadFolderPath + photo_3;

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
    public void TC_02_Upload() throws InterruptedException {
        // Đối với bài này sẽ set thêm implicit để phục vụ cho các hàm liên quan đến findElement ở bên dưới
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.get("https://gofile.io/?t=uploadFiles");

        // Wait cho loading icon ở màn hình upload không còn hiển thị
        // Có 2 icon loading cần wait: 1 icon ở left menu và 1 icon ở phần block bên phải
        // Có thể sẽ không cần wait 2 icon loading này vì tại thời điểm load trang xong thì 2 icon này sẽ mất đi => dẫn đến việc
        // hàm findElement bên trong hàm explicit  sẽ chạy hết timeout, thời gian chờ này sẽ không đáng để chờ

        // Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.animate-spin.h-8"))));

        // Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.animate-spin.h-16"))));

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt='Big Logo']"))).isDisplayed());

        //Define ra một biến locator để sử dụng cho hàm upload file
        By inputBy = By.xpath("//input[@type='file']");

        // Upload 1 lần nhiều file lên UI
        driver.findElement(inputBy).sendKeys(photo_1Path + "\n" + photo_2Path + "\n" + photo_3Path);
        Thread.sleep(2000);

        // Sau khi upload nhiều file lên sẽ xuất hiện icon loading lại => tiếp tục Wait để icon này không hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements
                (driver.findElements(By.cssSelector("div.animate-spin")))));

        // Khi upload thành công sẽ hiển thị text Upload Complete. Dùng wait để verify text này
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//h2[text()='Upload Complete']"))).isDisplayed());

        // Click vào link ở dưới mục Folder Link. Dùng wait để click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.linkSuccessCard"))).click();

        // Di chuyển sang màn hình hiển thị các file vừa up lên. Dùng wait để verify tên các file này
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[text()='" + photo_1 +"']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[text()='" + photo_2 +"']"))).isDisplayed());


        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[text()='" + photo_3 +"']"))).isDisplayed());








    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
