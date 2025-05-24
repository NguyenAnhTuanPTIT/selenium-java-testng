package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Checkbox_RadioButton {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser() {
        driver = new FirefoxDriver();
        // Phóng to trình duyệt hết cỡ
        driver.manage().window().maximize();


    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        Thread.sleep(5000);

        // Scoll xuống thêm 1 đọạn 300px trừ trường hợp màn hình bé sẽ che mất đi checkbox cần tìm
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,300)");

        // Verify checkbox/ radio is enabled/ disabled
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isEnabled());

        // Verify checkbox/ radio is selected/ deselected
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isSelected());


        By dualZoneAirCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // Scoll xuống thêm 1 đọạn 300px trừ trường hợp màn hình bé sẽ che mất đi checkbox cần tìm
        //((JavascriptExecutor)driver).executeScript("window.scrollBy(0,300)");

        // Kiểm tra nếu checkbox chưa được check thì sẽ cho check vào
        if(!driver.findElement(dualZoneAirCheckbox).isSelected()) {
            // Select to checkbox "Dual-zone air conditioning"
            driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).click();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());

        // Kiểm tra nếu checkbox đã check rồi thì sẽ cho uncheck
        if(driver.findElement(dualZoneAirCheckbox).isSelected()) {
            // Deselect to checkbox "Dual-zone air conditioning"
            driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).click();
        }
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());

        // Di chuyển sang trang khác
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        Thread.sleep(5000);
        By twoPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        // Kiểm tra nếu radio button chưa được click thì sẽ cho click vào
        if(!driver.findElement(twoPetrol).isSelected()) {
            // Select to checkbox "Dual-zone air conditioning"
            driver.findElement(twoPetrol).click();
        }
        Assert.assertTrue(driver.findElement(twoPetrol).isSelected());



    }

    @Test
    public void TC_02_Login() {

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
       // driver.quit();
    }


}
