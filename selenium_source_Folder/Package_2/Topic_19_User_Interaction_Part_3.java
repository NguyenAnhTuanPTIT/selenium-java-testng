package Package_2;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class Topic_19_User_Interaction_Part_3 {
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
    public void TC_01_Drag_And_Drop_HTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        Thread.sleep(2000);

        // Drag and Drop từ element nguồn đến element đích
        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        action.dragAndDrop(sourceCircle,targetCircle).perform();

        // Verify text hiển thị "You did great!" và màu sau khi drag and drop thành công
        Assert.assertEquals(targetCircle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase(),"#03A9F4");

    }

    @Test
    public void TC_02_Drag_And_Drop_HTML5() throws InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        Thread.sleep(2000);

        // Drag and Drop từ element nguồn đến element đích
        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        action.dragAndDrop(sourceCircle,targetCircle).perform();

        // Verify text hiển thị "You did great!" và màu sau khi drag and drop thành công
        Assert.assertEquals(targetCircle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase(),"#03A9F4");

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        //driver.quit();
    }


}
