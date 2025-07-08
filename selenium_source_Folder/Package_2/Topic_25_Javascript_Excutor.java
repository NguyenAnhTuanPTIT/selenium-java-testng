package Package_2;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_25_Javascript_Excutor {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    JavascriptExecutor jsExcutor;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        jsExcutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_() {
        // --------------------------------- Ngữ cảnh dùng JavascriptExcutor ---------------------------------------------------
        // Nếu có xử lý nào mà Selenium không hỗ trợ, thì bên JavascriptExcutor có thể support được

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        // Lấy title của browser
        driver.getTitle();
        System.out.println("Get title by jsExcutor : " + jsExcutor.executeScript("return document.title;"));

        // Lấy ra URL
        driver.getCurrentUrl();
        System.out.println("Get URL by jsExcutor : " + jsExcutor.executeScript("return document.URL;"));

        // Lấy ra domain, phải dùng jsExcutor
        // Thử trên tab console của browser trước
        System.out.println(jsExcutor.executeScript("return document.domain;"));

        // Lấy ra 1 WebElement và thao tác lên element
        // Khi muốn return về kiểu dữ liệu gì thì phải ép về kiếu đó
        WebElement searchTextbox = (WebElement) jsExcutor.executeScript("return document.querySelector('input#small-searchterms');");
        searchTextbox.sendKeys("automationfc.vn");
    }

    @Test
    public void TC_02_() {

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        //driver.quit();
    }


}
