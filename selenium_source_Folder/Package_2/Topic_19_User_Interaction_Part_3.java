package Package_2;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;


public class Topic_19_User_Interaction_Part_3 {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    Actions action;

    JavascriptExecutor jsExcutor;

    String projectPath = System.getProperty("user.dir");

    String osName = System.getProperty("os.name");

    // Khởi tạo ra biến Keys
    Keys keys;

    @BeforeClass
    public void initalBrowser(){
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        action = new Actions(driver);

        jsExcutor = (JavascriptExecutor)driver;

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
    public void TC_02_Drag_And_Drop_HTML5() throws InterruptedException, IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        Thread.sleep(2000);

        // Trường hợp trang web đó ko có jQuery mới dùng đến hàm này
        // String jQueryLibraries = getContentFile(projectPath + "\\Drag_And_Drop\\jQueryLib.js");
        // Inject jQuery vào trang web
        // jsExcutor.executeScript(jQueryLibraries);

        // Khi dùng một hàm có throws Exception thì khi sử dụng phải sử dụng throw Exception cho nó
        // Gọi hàm này để đọc nội dung file js
        String jQuerryDragAndDropContent = getContentFile(projectPath + "\\Drag_And_Drop\\dragAndDrop.js");

        // Thực thi đoạn code chứa trong biến jQuerryDragAndDropContent
        // Để thực thi được đoạn code javascript jquerry vào trang web => Dùng thư viện javasriptExcutor của selenium
        jsExcutor.executeScript(jQuerryDragAndDropContent);

        Thread.sleep(3000);

        // Sau khi chạy lệnh JS trên thì lúc này element A sẽ chứa text B và ngược lại
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        // Drag B sang A lại và verify
        jsExcutor.executeScript(jQuerryDragAndDropContent);

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");



    }

    @Test
    public void TC_03_Drag_And_Drop_HTML5_Java_Robot() throws AWTException, InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        Thread.sleep(2000);

        // Hàm này bị ảnh hưởng bởi scale màn hình, nếu scale không chính xác sẽ không lấy được tọa độ chính xác => fail
        // Muốn chính xác thì nên chạy ở scale màn hình:  100%
        dragAndDropHTML5ByXpath("div#column-a","div#column-b");

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        Thread.sleep(3000);

        dragAndDropHTML5ByXpath("div#column-a","div#column-b");

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }

    @Test
    public void TC_04_Sroll_To_Element() throws AWTException, InterruptedException {
        driver.get("https://live.techpanda.org/index.php/about-magento-demo-store/");
        Thread.sleep(3000);

        // Scroll tới element textbox Newsletter ở dưới (textbox này nằm ngòài phạm vi viewport)
        // Thông thường firefox sẽ không dùng được cách này, do ko nằm trong viewport => Dùng javascript để thay thế
        // Cách này sẽ hay dùng cho Chrome và Edge
        action.scrollToElement(driver.findElement(By.cssSelector("input#newsletter"))).perform();

    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.cssSelector(sourceLocator));
        WebElement target = driver.findElement(By.cssSelector(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        //driver.quit();
    }


}
