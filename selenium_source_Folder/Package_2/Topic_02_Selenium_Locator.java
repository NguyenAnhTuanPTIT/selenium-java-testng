package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
    //Khai báo
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        //Mở Browser lên
        driver = new FirefoxDriver();

        //Mở app lên đến màn hình Login
        driver.get("https://demo.nopcommerce.com/login");
    }

    @Test
    public void TC_01_() {
        // HTML source code
        // Thẻ - Thuộc tính - Giá trị thuộc tính
        // Tag name - Attribute - Value
        // Xpath: //tagname[@attribute='value']
        // Css:   //tagname[attribute='value']

        // Tương tác lên element trên web
        // 8 loại locator để tương tác với element của web
        driver.findElement(By.id(""));

    }

    @Test
    public void TC_02_Run_On_Chrome() {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }

    @Test
    //anhtuan
    public void TC_03_Run_On_Edge() {
        driver = new EdgeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }


}
