package Package_2;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_24_Shadow_Dom {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://automationfc.github.io/shadow-dom/");
        Thread.sleep(3000);

        // LƯU Ý: Nếu tìm các element trong shadow root bằng xpath sẽ không work được, lỗi "Operation is not supported"

        // Định vị ra element cha chứa shadow
        WebElement shadowHostParent = driver.findElement(By.cssSelector("div#shadow_host"));

        // Hàm getShadowRoot() sẽ trả về SearchContext, đây là thằng cha của WebDriver
        // Lấy ra element Shadow Root
        SearchContext firstShadow = shadowHostParent.getShadowRoot();

        // Dùng element Shadow Root tìm được để tìm các element bên trong

        // Kiểm tra hiển thị của element
        Assert.assertTrue(firstShadow.findElement(By.cssSelector("input[type='file']")).isDisplayed());

        // Verify text của element
        Assert.assertEquals(firstShadow.findElement(By.cssSelector("span.info")).getText(),"some text");

        // T/H nếu bên trong shadow root có chứa một shadow root nữa
        WebElement firstShadowHostParent = firstShadow.findElement(By.cssSelector("div#nested_shadow_host"));

        // Tiếp tục lấy ra element Shadow Root thứ hai
        SearchContext secondShadow = firstShadowHostParent.getShadowRoot();

        // Dùng secondShadow để thao tác với các element bên trong
        // Verify text của element
        Assert.assertEquals(secondShadow.findElement(By.cssSelector("div#nested_shadow_content div")).getText(),"nested text");

        // Verify các element trong first Shadow Root, không cần switch về
        Assert.assertTrue(firstShadow.findElement(By.cssSelector("a[href='scroll.html']")).isDisplayed());

        // Verify các element trong DOM ngoài cùng, nằm ngoài shadow root, ở đây sẽ dùng được xpath
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='scroll.html']")).isDisplayed());
    }

    @Test
    public void TC_02_Books() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(5000);

        // Định vị element đầu tiên chứa shadow root
        WebElement firstShadowHostElement = driver.findElement(By.xpath("//book-app"));

        SearchContext firstShadowRoot = firstShadowHostElement.getShadowRoot();

        // Nhập giá trị vào textbox
        firstShadowRoot.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");

        // Định vị element tứ hai chứa shadow root thứ 2
        WebElement secondShadowHostElement = firstShadowRoot.findElement(By.cssSelector("book-input-decorator"));

        SearchContext secondShadowRoot = secondShadowHostElement.getShadowRoot();

        // Click icon Search
        secondShadowRoot.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(3000);

        // Định vị ra element chứa shadow root mà tại đó có thể lấy được title của các record được search ra
        WebElement thirdShadowHostElement = firstShadowRoot.findElement(By.cssSelector("book-explore"));

        SearchContext thirdShadowRoot = thirdShadowHostElement.getShadowRoot();

        for (int i = 1; i <= thirdShadowRoot.findElements(By.cssSelector("ul>li")).size() ; i++) {

            // Định vị tiếp element chứa shadow root mà tại đó có thể lấy được title của các record được search ra
            WebElement fourthShadowHostElement = thirdShadowRoot.findElement(By.cssSelector("ul>li:nth-of-type(" + i + ")>book-item"));
            SearchContext fourthShadowRoot = fourthShadowHostElement.getShadowRoot();

            // Verify title của record đầu tiên
            // Assert.assertEquals(fourthShadowRoot.findElement(By.cssSelector("h2.title")).getText(),"The Psychology of Harry Potter");

            // In ra console title của record
            System.out.println(fourthShadowRoot.findElement(By.cssSelector("h2.title")).getText());
        }

    }


    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
