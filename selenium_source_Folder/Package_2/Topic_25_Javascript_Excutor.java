package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_25_Javascript_Excutor {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    String email;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        email = "automation" + new Random().nextInt(9999) + "@gmail.com";
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
        System.out.println("Get title by jsExcutor : " + jsExecutor.executeScript("return document.title;"));

        // Lấy ra URL
        driver.getCurrentUrl();
        System.out.println("Get URL by jsExcutor : " + jsExecutor.executeScript("return document.URL;"));

        // Lấy ra domain, phải dùng jsExcutor
        // Thử trên tab console của browser trước
        System.out.println(jsExecutor.executeScript("return document.domain;"));

        // Lấy ra 1 WebElement và thao tác lên element
        // Khi muốn return về kiểu dữ liệu gì thì phải ép về kiếu đó
        WebElement searchTextbox = (WebElement) jsExecutor.executeScript("return document.querySelector('input#small-searchterms');");
        searchTextbox.sendKeys("automationfc.vn");

        // Lấy ra 1 list WebElement từ jsExcutor
        List<WebElement> emailTypeTextbox = (List<WebElement>) jsExecutor.executeScript("document.querySelectorAll(\"input[type='email']\")");

        // Click vào một element mà không quan tâm nó hiển thị/ẩn đi
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("ul.top-menu.notmobile a[href='/desktops']")));

        // Refresh browser
        jsExecutor.executeScript("history.go(0)");

        // Lấy hết toàn bộ text của 1 trang
        jsExecutor.executeScript("return document.documentElement.innerText;");

        // Scroll 50 pixels
        jsExecutor.executeScript("window.scrollBy(0,50);");

        // Scroll into view
        // True là kéo mép trên của element lên trên cùng, đụng với viewport
        // False là kéo mép dưới xuống dưới cùng , đụng với viewport

        jsExecutor.executeScript("document.querySelector(\"input#newsletter-email\").scrollIntoView(true);");

        // Scroll đến cuối trang
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight);");

        // Mở ra một page khác bất kì
        jsExecutor.executeScript("window.location =\"https://www.jetbrains.com/\";");

        // Remove một attribute của 1 element
        jsExecutor.executeScript("document.querySelector('input#newsletter-email').removeAttribute('class');");

    }

    @Test
    public void TC_02_TechPanda() throws InterruptedException {
        // Di chuyển đến trang Tech panda bằng jsExcutor
        jsExecutor.executeScript("window.location ='https://live.techpanda.org/';");
        // Wait cho URL được load ra thành công
        explicitWait.until(ExpectedConditions.urlToBe("https://live.techpanda.org/"));

        /*
        // Dùng hàm scriptTimeout để chờ cho các đoạn javascript được thực thi xong
        // Hàm này chỉ áp dụng cho ham excuteAsyncScript(), không áp dụng cho hàm excuteScript()
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
        */

        // Get về domain của page và verify
        String techPandaDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(techPandaDomain,"live.techpanda.org");

        // Verify URL của page
        String homepageURL = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(homepageURL,"https://live.techpanda.org/");

        // Click vào tab MOBILE
        // Trước khi click vào tab MOBILE, wait cho đến khi element có thể click được
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mobile']")));
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Mobile']")));

        // Click vào Add to cart của SAMSUNG GALAXY
        // Trước khi click vào Add to cart, wait cho đên khi element đó có thể click được
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//" +
                "following-sibling::div[@class='actions']//button")));
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//" +
                "following-sibling::div[@class='actions']//button")));

        Thread.sleep(3000);

        // Verify message hiển thị sau khi add sản phẩm thành công
        String message = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
        Assert.assertTrue(message.contains("Samsung Galaxy was added to your shopping cart."));

        // Click vào linktext customer service
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Customer Service']")));
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Customer Service']")));

        Thread.sleep(3000);

        // Scroll tới textbox News letter nằm ở cuối page
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("input#newsletter")));
        Thread.sleep(3000);

        // Nhập giá trị hợp lệ vào trong textbox News letter
        jsExecutor.executeScript("arguments[0].setAttribute('value','" + email + "');",driver.findElement(By.cssSelector("input#newsletter")));

        // Click button Subcribe
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("button[title='Subscribe']")));

        Thread.sleep(3000);

        driver.switchTo().alert().accept();

        Thread.sleep(3000);

        // Verify message hiển thị sau khi subcrribe thành công
        Assert.assertTrue(((String) jsExecutor.executeScript("return document.documentElement.innerText;")).contains("Thank you for your subscription."));

        // Chuyển qua trang khác
        jsExecutor.executeScript("window.location ='https://facebook.com/';");

    }

    @Test
    public void TC_03_TechPanda_Using_Methods(){
        navigateToUrlByJS("https://live.techpanda.org/");
        // Wait cho URL được load ra thành công
        explicitWait.until(ExpectedConditions.urlToBe("https://live.techpanda.org/"));

        // Get về domain của page và verify
        Assert.assertEquals(getDomain(),"live.techpanda.org");

        // Verify URL của page
        Assert.assertEquals(getURL(),"https://live.techpanda.org/");

        // Click vào tab MOBILE
        // Trước khi click vào tab MOBILE, wait cho đến khi element có thể click được
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mobile']")));
        clickToElementByJS("//a[text()='Mobile']");

        sleepInSecond(3);

        // Click vào Add to cart của SAMSUNG GALAXY
        // Trước khi click vào Add to cart, wait cho đên khi element đó có thể click được
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//" +
                "following-sibling::div[@class='actions']//button")));
        clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2//" +
                "following-sibling::div[@class='actions']//button");

        sleepInSecond(3);

        // Verify message hiển thị sau khi add sản phẩm thành công
        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        // Click vào linktext customer service
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Customer Service']")));
        clickToElementByJS("//a[text()='Customer Service']");

        sleepInSecond(3);

        // Scroll tới textbox News letter nằm ở cuối page
        scrollToElementOnTop("//input[@id='newsletter']");
        sleepInSecond(3);

        // Nhập giá trị hợp lệ vào trong textbox News letter
        sendkeyToElementByJS("//input[@id='newsletter']",email);

        // Click button Subcribe
        clickToElementByJS("//button[@title='Subscribe']");

        sleepInSecond(3);

        driver.switchTo().alert().accept();

        sleepInSecond(3);

        // Verify message hiển thị sau khi subcrribe thành công
        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

        // Chuyển qua trang khác
        navigateToUrlByJS("https://facebook.com/");

    }

    @Test
    public void TC_04_HTML5_Validation_Message(){
        // Dạng HTML55 message này kiểu như 1 tooltips, không thể bắt được bằng cách thông thường được
        driver.get("https://account.rode.com/login");
        sleepInSecond(3);

        // Click vào button Sign in
        WebElement buttonSubmit = driver.findElement(By.cssSelector("button[type='submit']"));
        buttonSubmit.click();
        sleepInSecond(3);

        // T/H để trống email
        String emptyEmailMessage = getElementValidationMessage("//input[@id='email']");
        Assert.assertEquals(emptyEmailMessage,"Vui lòng điền vào trường này.");

        // Nhập giá trị vào textbox email
        String invalidEmailData = "aaa";
        WebElement textboxEmail = driver.findElement(By.xpath("//input[@id='email']"));
        textboxEmail.sendKeys(invalidEmailData);

        buttonSubmit.click();
        sleepInSecond(3);

        // T/H input giá trị không hợp lệ, sẽ có sự khác biệt giữa 2 browser khi ban đầu chọn
        String invalidEmail = getElementValidationMessage("//input[@id='email']");

        if(driver.toString().contains("ChromeDriver")){
            // Verify message nếu đang dùng Chrome
            Assert.assertEquals(invalidEmail, "Vui lòng bao gồm '@' trong địa chỉ email. '" + invalidEmailData + "' bị thiếu '@'.");
        }else {
            // Verify message nếu đang dùng Firefox
            Assert.assertEquals(invalidEmail, "Vui lòng điền một địa chỉ email.");
        }

        // Xóa giá trị trước đó ở textbox email
        textboxEmail.clear();

        // T/H input giá trị không hợp lệ lần 2
        invalidEmailData = "aaa@";
        textboxEmail.sendKeys(invalidEmailData);

        buttonSubmit.click();
        sleepInSecond(3);

        invalidEmail = getElementValidationMessage("//input[@id='email']");

        if(driver.toString().contains("ChromeDriver")){
            Assert.assertEquals(invalidEmail, "Vui lòng nhập phần đứng sau '@'. '" + invalidEmailData + "' không hoàn chỉnh.'");
        }else {
            Assert.assertEquals(invalidEmail, "Vui lòng điền một địa chỉ email.");
        }

        // Xóa giá trị trước đó ở textbox email
        textboxEmail.clear();

        // T/H input giá trị không hợp lệ lần 3
        invalidEmailData = "aaa@aaa.";
        textboxEmail.sendKeys(invalidEmailData);

        buttonSubmit.click();
        sleepInSecond(3);

        invalidEmail = getElementValidationMessage("//input[@id='email']");

        if(driver.toString().contains("ChromeDriver")){
            Assert.assertEquals(invalidEmail, "'.' bị sử dụng sai vị trí trong '" + invalidEmailData.split("@")[1] + "'.");
        }else {
            Assert.assertEquals(invalidEmail, "Vui lòng điền một địa chỉ email.");
        }

        // Xóa giá trị trước đó ở textbox email
        textboxEmail.clear();

        // Nhập giá trị hợp lệ vào textbox email
        textboxEmail.sendKeys(email);
        buttonSubmit.click();
        sleepInSecond(3);

        String invalidPassword = getElementValidationMessage("//input[@id='password']");

        // Verify message hiển thị
        Assert.assertEquals(invalidPassword,"Vui lòng điền vào trường này.");


    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        //driver.quit();
    }

    //------------------------------------- Các hàm jsExecutor được tạo ra để tái sử dụng nhiều lần ---------------------------------------------------------

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public String getDomain() {
        String domain = (String) jsExecutor.executeScript("return document.domain;");
        return domain;
    }

    public String getURL() {
        String domain = (String) jsExecutor.executeScript("return document.URL;");
        return domain;
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }


}
