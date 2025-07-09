package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

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
        // JavascriptExcutor không quan hiển thị hay không, nếu như có trong HTML sẽ tương tác được => Dễ bị sai hành vi của user

        // Còn nếu dùng selenium thì không hiển thị sẽ không tương tác được

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

        // Lấy ra 1 list WebElement từ jsExcutor
        List<WebElement> emailTypeTextbox = (List<WebElement>) jsExcutor.executeScript("document.querySelectorAll(\"input[type='email']\")");

        // Click vào một element mà không quan tâm nó hiển thị/ẩn đi
        jsExcutor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("ul.top-menu.notmobile a[href='/desktops']")));

        // Refresh browser
        jsExcutor.executeScript("history.go(0)");

        // Lấy hết toàn bộ text của 1 trang
        jsExcutor.executeScript("return document.documentElement.innerText;");

        // Scroll 50 pixels
        jsExcutor.executeScript("window.scrollBy(0,50);");

        // Scroll into view
        // True là kéo mép trên của element lên trên cùng, đụng với viewport
        // False là kéo mép dưới xuống dưới cùng , đụng với viewport

        jsExcutor.executeScript("document.querySelector(\"input#newsletter-email\").scrollIntoView(true);");

        // Scroll đến cuối trang
        jsExcutor.executeScript("window.scrollBy(0,document.body.scrollHeight);");

        // Mở ra một page khác bất kì
        jsExcutor.executeScript("window.location =\"https://www.jetbrains.com/\";");

        // Remove một attribute của 1 element
        jsExcutor.executeScript("document.querySelector('input#newsletter-email').removeAttribute('class');")

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
