package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

// Để uplpad được file bắt buộc phải có một thẻ input với type = 'file', nếu không có sẽ không upload được
// Thẻ này có thể không nằm chính trong element upload file, nó có thể nằm ở vùng khác hoặc bị ẩn đị

public class Topic_26_Upload_File {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Upload_Single_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        Thread.sleep(3000);

        // Tìm ra thẻ input file khớp với button upload file trên UI

        By inputBy = By.cssSelector("input[type='file']");

        // Upload từng file lên UI
        driver.findElement(inputBy).sendKeys(photo_1Path);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(photo_2Path);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(photo_3Path);
        Thread.sleep(2000);

        // Verify file được load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +  photo_1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +  photo_2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +  photo_3 + "']")).isDisplayed());

        // Click upload cho từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
        for(WebElement startButton : startButtons){
            startButton.click();
            Thread.sleep(3000);
        }

        // Verify các file được upload thành công
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"  + photo_1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"  + photo_2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"  + photo_3 + "']")).isDisplayed());

    }

    @Test
    public void TC_02_Upload_Multiple_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        Thread.sleep(3000);

        // Tìm ra thẻ input file khớp với button upload file trên UI

        By inputBy = By.cssSelector("input[type='file']");

        // Upload 1 lần nhiều file lên UI
        // Dùng \n để nối chuỗi đường dẫn các file, \n là ký tự xuống dòng
        // ---------- CHÚ Ý: cần kiểm tra element có attribute là multiple hay không, nếu không có thuộc tính này sẽ ko cho upload nhiều file
        driver.findElement(inputBy).sendKeys(photo_1Path + "\n" + photo_2Path + "\n" + photo_3Path);
        Thread.sleep(2000);

        // Verify file được load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +  photo_1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +  photo_2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" +  photo_3 + "']")).isDisplayed());

        // Click upload cho từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
        for(WebElement startButton : startButtons){
            startButton.click();
            Thread.sleep(3000);
        }

        // Verify các file được upload thành công
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"  + photo_1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"  + photo_2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"  + photo_3 + "']")).isDisplayed());

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
