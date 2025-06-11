package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_17_User_Interaction_Part_1 {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        action = new Actions(driver);

        action.moveByOffset(0,0).perform();


    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Hover() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

        // Di chuyển chuột đến element
        // Không nên dùng moveByOffset, moveToLocation vì dính đến tọa độ sẽ không ổn định khi thay đổi độ phân giải màn hình
        // Dùng tính năng debugger trên browser để bắt element của tooltip ( setTimeout(()->{debugger},3000) )

        // * Lưu ý:
        //   1. Khi chạy testcase có liên quan đến Action thì không đuợc dùng chuột, bàn phím cùng lúc => Xảy ra xung đột
        //   2. Nên chạy trên 1 máy độc lập

        action.moveToElement(ageTextbox).perform();

        Thread.sleep(2000);

        // Verify lại nội dung của tooltip
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");


    }

    @Test
    public void TC_02_Hover_Myntra() throws InterruptedException {
        driver.get("https://www.myntra.com/");

        Thread.sleep(2000);

        action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
        // Hoặc dùng hàm action.click
        // action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")));

        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),"Kids Home Bath");
    }

    @Test
    public void TC_03_Hover_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();

        Thread.sleep(2000);

        action.moveToElement(driver.findElement(By.cssSelector("a[title='Sách Trong Nước']"))).perform();

        driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Nhân Vật - Bài Học Kinh Doanh']")).click();

        System.out.println(driver.findElement(By.cssSelector("ol.breadcrumb")).getText());

        Assert.assertTrue(driver.findElement(By.cssSelector("ol.breadcrumb")).getText().contains("NHÂN VẬT - BÀI HỌC KINH DOANH"));
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
