package Package_2;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class Topic_18_User_Interaction_Part_2 {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    Actions action;
    String osName = System.getProperty("os.name");

    // Khởi tạo ra biến Keys
    Keys keys;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        action = new Actions(driver);

        if(osName.startsWith("Windows")){
            keys = Keys.CONTROL;
        }
        else{
            keys = Keys.COMMAND;
        }

        // Hoặc dùng toán tử 3 ngôi
        // keys = osName.startsWith("Windows") ? Keys.CONTROL : Keys.COMMAND;

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Click_And_Hold_Block() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumbers.size(),20); // Kiểm tra xem có get được 20 phần tử về không

        action.clickAndHold(allNumbers.get(0)) // Click vào số 1 và giữ chuột
                .moveToElement(allNumbers.get(3)) // Di chuột tới số 4
                .release() // Nhả chuột trái ra - kết thúc sự kiện click an hold
                .perform(); // Thực thi các câu lệnh trên (nếu ko có lệnh này thì sẽ không thực thi)
        Thread.sleep(4000);

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

        // Verify 4 số đã được chọn
        Assert.assertEquals(allNumberSelected.size(),4);
    }

    @Test
    public void TC_02_Click_And_Hold_Random() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        Thread.sleep(2000);

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumbers.size(),20);

        // Nhấn phím Ctrl xuống (chưa nhả ra)
        action.keyDown(keys).perform();

        // Click vào từng thằng riêng biệt
        // Chọn số 1,4, 8. 11, 14, 16, 18
        action.click(allNumbers.get(0))
                .click(allNumbers.get(3))
                .click(allNumbers.get(7))
                .click(allNumbers.get(10))
                .click(allNumbers.get(13))
                .click(allNumbers.get(17))
                .pause(3000) // cho dừng lại 3s sau khi click vào element thứ 17
                .perform();

        // Nhả phím Ctrl ra
        action.keyUp(keys).perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),6);

    }

    @Test
    public void TC_03_Double_Click() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Thread.sleep(2000);

        // Scroll tới khi button "Double click me" hiển th để có thể thao tác được
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(951,3300)");

        // Double click vào button
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        // Sau khi double click, verify text hiện lên "Hello Automation Guys!"
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_04_Right_Click() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        Thread.sleep(2000);

        // Click chuột phải vào button "right click me"
        action.moveToElement(driver.findElement(By.xpath("//span[text()='right click me']"))).contextClick().perform();

        // Verify Quit menu hiển thị
        WebElement quitMenuElement = driver.findElement(By.cssSelector("li.context-menu-icon-quit"));
        Assert.assertTrue(quitMenuElement.isDisplayed());

        // Hover mouse vào Quit menu
        action.moveToElement(quitMenuElement).perform();

        // Sau khi hover => verify trạng thái hover và visible
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        // Click vào Quit menu
        action.click(quitMenuElement).perform();

        Thread.sleep(3000);

        // Sau khi click Quit menu sẽ hiển thị Alert
        driver.switchTo().alert().accept();

        Thread.sleep(3000);

        // Sau khi accept trên alert => Quit menu sẽ biến mất
        Assert.assertFalse(quitMenuElement.isDisplayed());

    }

    @Test
    public void TC_05_Drag_And_Drop_HTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        Thread.sleep(2000);





    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        //driver.quit();
    }


}
