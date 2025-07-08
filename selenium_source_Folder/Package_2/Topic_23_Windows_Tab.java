package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_23_Windows_Tab {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    Select select;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Xem ID của driver
        System.out.println("Driver ID: " + driver.toString());
        // Kết quả in ra: FirefoxDriver: firefox on windows (e2a85a1d-3f41-488d-8856-95287eefe740)
        // Trong đó "e2a85a1d-3f41-488d-8856-95287eefe740" được gọi là GUID (Global Unique Indentifier Number (số định danh duy nhất toàn cầu))

        // Lấy ra ID của tab/Window mà driver đang active tại page đó
        String gitHubWindowID = driver.getWindowHandle();
        // Xem ID của Window đó
        System.out.println("Page ID: " + gitHubWindowID);
        System.out.println("Page Title: " + driver.getTitle());
        System.out.println("Page URL: " + driver.getCurrentUrl());

        // Click vào linktext GOOGLE -> theo business sẽ bật 1 tab mới và tự nhảy qua
        // Nhưng về code Selenium là driver sẽ không tự nhảy qua - nó vẫn ở tab cũ
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);

        // Kiểm tra xem sau khi bật qua tab mới thì Window ID có thay đổi hay không ?
        System.out.println("Github Window ID after open new tab: " + gitHubWindowID);

        // Tạo ra một hàm switchToWindowID riêng để switch qua lại giữa các tab/window
        switchToWindowID(gitHubWindowID);
        Thread.sleep(3000);

        // Lấy ra ID của page Google
        String googleWindowID = driver.getWindowHandle();

        // Lấy thông tin window sau khi switch
        System.out.println("Page ID after switch: " + gitHubWindowID);
        System.out.println("Page Title after switch: " + driver.getTitle());
        System.out.println("Page URL after switch: " + driver.getCurrentUrl());

        // Sau khi switch qua window mới thì sẽ thao tác được với các element trên window này
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium WebDriver");
        Thread.sleep(3000);

        // Switch về tab GITHUB, tận dùng hàm switchToWindowID()
        switchToWindowID(googleWindowID);
        Thread.sleep(3000);

        // Click vào linktext Facebook
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(3000);

        // Tạo ra một hàm switchToWindowByTitle() dùng để switch windows/tab theo title
        switchToWindowByTitle("Facebook – log in or sign up");

        System.out.println("Page title after switch correctly: " + driver.getTitle());

        // Quay về GITHUB
        switchToWindowByTitle("Selenium WebDriver");
        Thread.sleep(3000);

        System.out.println("Page title after switch correctly: " + driver.getTitle());

        // Click vào linktext TIKI
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        Thread.sleep(3000);

        // Switch qua TIKI
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Thread.sleep(3000);

        System.out.println("Page title after switch correctly: " + driver.getTitle());

        closeAllWindowWithoutParent(gitHubWindowID);

        // Get ra thông tin của page hiện tại
        System.out.println("Page ID after close all: " + driver.getWindowHandle());
        System.out.println("Page Title after close all: " + driver.getTitle());
    }

    @Test
    public void TC_02_TechPanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        Thread.sleep(3000);

        // Lấy ra ID của window hiện tại
        String originWindowID = driver.getWindowHandle();

        // Click vào tab Mobile
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(2000);

        // Click button Add to Compare của sản phẩm Sony Xperia
        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2//following-sibling::div[@class='actions']" +
                "//a[text()='Add to Compare']")).click();
        Thread.sleep(3000);

        // Verify đã Add to Compare thành công bằng message thông báo
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']" +
                "//a[text()='Add to Compare']")).click();
        Thread.sleep(3000);

        // Verify đã Add to Compare thành công bằng message thông báo
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The product Samsung Galaxy has been added to comparison list.");

        // Click vào button Compare
        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        Thread.sleep(3000);

        // Switch qua window Compare
        switchToWindowID(originWindowID);
        Thread.sleep(3000);

        // Verify title của window mới mở
        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");

        // Close window đó đi
        closeAllWindowWithoutParent(originWindowID);

        // Click vào linktext Clear all
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        // Accept alert
        driver.switchTo().alert().accept();
        Thread.sleep(3000);

        // Verify message Clear thành công
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The comparison list was cleared.");


    }

    @Test
    public void TC_03_Cambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");
        Thread.sleep(3000);

        // Lấy ra ID của page Cambridge
        String cambridgeWindowID = driver.getWindowHandle();

        // Click vào linktext Đăng nhập
        driver.findElement(By.cssSelector("span.cdo-login-button")).click();
        Thread.sleep(2000);

        // Swtich qua window mới
        switchToWindowByTitle("Login");

        // Click vào button
        driver.findElement(By.cssSelector("input[type='submit'][value = 'Log in']")).click();
        Thread.sleep(2000);

        // Verify hai message validate hiển thị ở 2 textbox username và password
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='username']+span[id*='error-msg']")).getText()
        , "This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='password']+span[id*='error-msg']")).getText()
                , "This field is required");

        // Close window đó đi và switch về trang trước đó
        closeAllWindowWithoutParent(cambridgeWindowID);

        // Điền giá trị vào ô search
        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("Selenium");

        // Click button Search
        driver.findElement(By.cssSelector("button.cdo-search-button")).click();
        Thread.sleep(3000);

        // Verify trang với từ khóa vừa tìm kiếm
        Assert.assertTrue(driver.getTitle().contains("SELENIUM"));
        // Verify từ khóa tìm kiếm hiển thị trên màn hình
        Assert.assertEquals(driver.findElement(By.cssSelector("div#cald4-1~div.pos-header div.di-title")).getText(),"selenium");

    }

    @Test
    public void TC_04_Harvard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        Thread.sleep(3000);

        // Lấy ra ID của page Harvard
        String harvardWindowID = driver.getWindowHandle();

        // Click vào button Student Login
        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        Thread.sleep(2000);

        // Switch qua window mới
       switchToWindowID(harvardWindowID);
       Thread.sleep(3000);

       // Verify title của window mới được mở
       Assert.assertEquals(driver.getTitle(),"Harvard Division of Continuing Education Login Portal");

       // Đóng window đó đi
       closeAllWindowWithoutParent(harvardWindowID);

       // Verify popup Authentication hiển thị
       Assert.assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());

       // Đóng poup lại
       driver.findElement(By.cssSelector("button.sam-wait__close")).click();

       // Nhập từ khóa vào textbox Keyword
       driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("Science Computer");

       // Chọn giá trị bất kì trong selecbox Term
       select = new Select(driver.findElement(By.cssSelector("select#crit-srcdb")));
       select.selectByVisibleText("Extension All Terms 2025-2026");

       // Chọn giá trị bát kì trong selectbox Part of Term
       select = new Select(driver.findElement(By.cssSelector("select#crit-session")));
       select.selectByVisibleText("Full Term");

       // Click button Search
       driver.findElement(By.cssSelector("button#search-button")).click();
       Thread.sleep(3000);

       // Verify thông tin Course được hiển thị
       Assert.assertTrue(driver.findElements(By.cssSelector("div.result--group-start span.result__title")).get(0).getText().contains("Computer Science"));

    }

    @Test
    public void TC_05_Selenium_4x() throws InterruptedException {
        // Selenium 3 không support việc new một tab/window mới
        // Selenium 4 thì có support thông qua hàm newWindow()

        driver.get("http://live.techpanda.org/");
        Thread.sleep(3000);

        // Click vào tab Mobile
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(2000);

        // Mở một tab mới có chứa url mong muốn
        // Khi thực hiện hàm này thì window ID sẽ thay đổi, nhưng driver ID vẫn giữ nguyên
        // Hành vi này giống như Business/End User side vì khi mở tab/window mới thì sẽ tự động switch qua luôn
        // Hành vi này mang tính chủ động của End user, giống như việc click chuột phải -> Open new tab/window
        driver.switchTo().newWindow(WindowType.TAB).get("https://live.techpanda.org/index.php/customer/account/");
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://live.techpanda.org/index.php/catalog/seo_sitemap/category/");

    }


    private void closeAllWindowWithoutParent(String uniqueRestWindowID) throws InterruptedException {
        // Tiếp tục lấy hết tooàn bộ các ID của window/tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua từng ID
        for(String id : allWindowIDs){
            // Kiểm tra nếu Window ID khác Window ID của GITHUB thì sẽ switch qua và đóng đi, ngoại trừ GITHUB
            if(!id.equals(uniqueRestWindowID)){
                driver.switchTo().window(id);
                Thread.sleep(2000);

                driver.close();
                Thread.sleep(2000);
                // Lưu ý khi window/tab cuối cùng được đóng, thì driver vẫn đang ở tab/window cuối cùng đó
            }
        }

        // Sau khi đóng hết các tab/window thì phải switch driver về lại tab/window duy nhất còn lại
        driver.switchTo().window(uniqueRestWindowID);
    }

    //Hàm này sẽ được dùng để handle cho case từ 3 tab/window trở lên
    private void switchToWindowByTitle(String title) {
        // Lấy hết toàn bộ ID của window/tab
        Set<String> allWindowIDs = driver.getWindowHandles();
        // Dùng vòng lặp duyệt qua từng ID
        for(String id : allWindowIDs){
            // Mỗi lần duyệt sẽ cho nó switch vào trước
            driver.switchTo().window(id);

            // Get ra title của window/tab hiện tại
            String pageTitle = driver.getTitle();

            // KIểm tra Title nếu đúng như title mình mong đợi => break vòng lặp
            if(pageTitle.equals(title)){
                break;
            }
        }
    }

    // Hàm này chỉ dùng cho 2 tab/window, không dùng cho 3 tab/window trở lên
    private void switchToWindowID(String WindowID) {
        // Trong java Collection: Set<> không cho phép lưu trùng, List<> sẽ cho phép lưu trùng
        // Nguyên nhân do trong selenium có thể mở ra nhiu windown/tab có ID giống nhau, và user chỉ muốn nhảy qua 1 trong các window/tab đó thôi
        // Lấy ra hết tất cả các ID của tab/window hiện tại
        Set<String> allWindowID = driver.getWindowHandles();

        for (String id : allWindowID) {
            System.out.println(id);
            // Kiểm tra theo điều kiện giữa 2 window ID, nếu biết được 1 ID thì sẽ nhận diện và thao tác được với id còn lại
            if(!id.equals(WindowID)){
                // Switch qua window đó
                driver.switchTo().window(id);
            }
        }
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
