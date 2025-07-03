package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Random_Popup {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_JavaCodeGeek() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/#google_vignette");
        Thread.sleep(3000);

        By newletterPopupBy = By.xpath("//div[@data-title='Newsletter Free eBooks '"
                + "and not(contains(@style,'display:none'))]");

        // Hiển thị thì close đi rồi action tiếp
        // Xét điều kiện xuất hiện trong DOM và có hiển thị
        if(driver.findElements(newletterPopupBy).size()>0 && driver.findElements(newletterPopupBy).get(0).isDisplayed()){
            System.out.println("----------------- GO TO IF ------------------------");
            driver.findElement(By.xpath("//div[@data-title='Newsletter Free eBooks' "
                    + "and not(contains(@style,'display:none'))]//a[contains(@onclick,'close')]")).click();
            Thread.sleep(3000);

            // Verify popup ko còn hiển thị trong DOM nữa
            Assert.assertEquals(driver.findElements(newletterPopupBy).size(),0);
        }

        // Không hiển thị thì action tiếp
        // Nhập giá trị vào textbox
        System.out.println("----------------- IGNORE IF ------------------------");

        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile");

        // Click vào icon search
        driver.findElement(By.cssSelector("form#search span.tie-icon-search")).click();
        Thread.sleep(3000);

        // Verify kết quả tìm được bằng title
        Assert.assertTrue(driver.findElement(By.cssSelector("header#search-title-section h1.page-title")).isDisplayed());

    }

    @Test
    public void TC_02_VNK_Edu() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        Thread.sleep(6000);

        By markertingPopupBy = By.cssSelector("div.popmake");

        // Hiển thị thì close đi rồi action tiếp
        if(driver.findElements(markertingPopupBy).size()>0 && driver.findElements(markertingPopupBy).get(0).isDisplayed()){
            System.out.println("----------------- GO TO IF ------------------------");
            driver.findElement(By.cssSelector("div.popmake button")).click();
            Thread.sleep(3000);

            // Verify popup không còn hiển thị
            // Do popup này sau khi đóng vẫn còn trong DOM, nên có thể dùng assert False
            Assert.assertFalse(driver.findElement(markertingPopupBy).isDisplayed());
        }

        // Không hiển thị thì action tiếp

        System.out.println("----------------- IGNORE IF ------------------------");

        // Click vào linktext Liên hệ
        driver.findElement(By.xpath("//ul[@id='mega-menu-primary']/li/a[text()='Liên hệ']")).click();

        // Verify hiển thị title
        Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());


    }

    @Test
    public void TC_03_DeHieu() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        Thread.sleep(5000);

        By registerPopupBy = By.cssSelector("div.modal-content");

        // Hiển thị thì close đi rồi action tiếp
        if(driver.findElements(registerPopupBy).size()>0 && driver.findElements(registerPopupBy).get(0).isDisplayed()){
            System.out.println("----------------- GO TO IF ------------------------");
            driver.findElement(By.cssSelector("div.modal-content button.close")).click();
            Thread.sleep(3000);

            // Verify popup không còn hiển thị
            // Do popup này sau khi đóng vẫn còn trong DOM, nên có thể dùng assert False
            Assert.assertFalse(driver.findElement(registerPopupBy).isDisplayed());
        }

        // Không hiển thị thì action tiếp
        System.out.println("----------------- IGNORE IF ------------------------");

        // Nhập giá trị vào textbox
        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Khóa học Lập dự toán M&E");

        // Click vào icon Search
        driver.findElement(By.cssSelector("button.header-search")).click();
        Thread.sleep(3000);

        // Verify record tương ứng với từ search
        Assert.assertEquals(driver.findElement(By.cssSelector("div.course-item-detail a")).getAttribute("title"),"Khóa học Lập dự toán M&E");
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
