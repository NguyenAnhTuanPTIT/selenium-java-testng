package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class Topic_29_Explicit_Wait {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();

        // -------------------------------------- Có 2 cách để set explicitWait ----------------------------------------
        // Cách 1: Duration.ofSeconds(3): tổng thời gian timeout là 3s, mặc định là 0.5s là thời gian lặp lại tìm kiếm
        // Thường sẽ dùng cách này
            explicitWait = new WebDriverWait(driver,Duration.ofSeconds(3));

        // Cách 2: Duration.ofMillis(300): set lại thời gian polling là 300 milisecond
        // Cách này sẽ ít dùng
            explicitWait = new WebDriverWait(driver,Duration.ofSeconds(3),Duration.ofMillis(300));

        driver.manage().window().maximize();

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_() {

        driver.get("");

        // Wait cho Element không hiển thị, không còn trong HTML (trước đó có tồn tại)
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        // Wait cho element không hiển thị (còn hoặc không còn trong HTML)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        // Wait cho element được hiển thị (có trong HTML và có trên UI)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

        // Wait cho element được phép click (button / radio / checkbox / linktext / ....)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        // Wait cho URL của page tuyệt đối
        explicitWait.until(ExpectedConditions.urlToBe("https://automationfc.vn/"));

        // Wait cho URL của page tương đối
        explicitWait.until(ExpectedConditions.urlContains("automationfc.vn"));

        // Wait cho URL của page thỏa mãn biểu thức
        explicitWait.until(ExpectedConditions.urlMatches("*&_...."));

        // Wait cho hàm javascript trả về kiểu dữ liệu String
        explicitWait.until(ExpectedConditions.jsReturnsValue("return arguments[0].validationMessage;"));

        // Wait cho Alert xuất hiện trong HTML
        explicitWait.until(ExpectedConditions.alertIsPresent());

        // Wait cho title của page tuyệt đối
        explicitWait.until(ExpectedConditions.titleIs("AUTOMATION FC"));

        // Wait cho title của page tương đối
        explicitWait.until(ExpectedConditions.titleContains("FC"));

        // Wait thỏa mãn 2 điều kiện (AND)
        explicitWait.until(ExpectedConditions.and(ExpectedConditions.urlContains(""),ExpectedConditions.titleIs("")));

        // Wait thỏa mãn 1 trong 2 điều kiện (OR)
        explicitWait.until(ExpectedConditions.or(ExpectedConditions.urlContains(""),ExpectedConditions.titleIs("")));

        // Wait cho 1 element có xuất hiện trong HTML (không cần hiển thị trên UI)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        // Wait cho 1 element có thuộc tính chứa 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""),"class","email"));

        // Wait cho 1 element có thuộc tính bằng 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"class","email"));

        // Wait cho 1 element có thuộc tính không được rỗng/null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")),"class"));

        // Wait cho 1 element có thuộc tính ở trong DOM bằng 1 giá trị nào đó
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector(""))
                ,"baseURI","https://automationfc.vn/"));

        //
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")),
                "innerText","Start"));

        // Wait cho element đã được chọn thành công (Checkbox / Radio button / Droopdown)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        // Wait cho element đã được chọn thành công
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),true));
        // Wait cho element chưa được chọn thành công
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),false));

        // Wait cho frame/iframe xuất hiện và switch vào
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));

        // Wait cho 1 đoạn lệnh js được thực thi và không trả về bất cứ 1 exception nào hết
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return arguments[0].validationMessage;"));

        // Phủ định lại điều kiện Wait
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.javaScriptThrowsNoExceptions("return arguments[0].validationMessage;")));

        // Wait cho 1 list element bằng bao nhiêu item
        List<WebElement> allNumberSelected = explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),6));

        // Wait cho 1 list element nhỏ hơn bao nhiêu item
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""),7));

        // Wait cho 1 list element lớn hơn bao nhiêu item
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""),5));

        // Wait cho số lượng windows/tabs bằng bao nhiêu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        // Wait cho 1 đoạn text bằng tuyệt đối (dùng trước hàm getText() )
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""),""));
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), Pattern.compile("a*b")));

        // Wait cho 1 element hay bị change/refresh lại/update lại
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.numberOfWindowsToBe(3)));

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
