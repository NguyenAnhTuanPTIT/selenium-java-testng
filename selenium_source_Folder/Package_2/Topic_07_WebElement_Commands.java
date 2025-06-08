package Package_2;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;

public class Topic_07_WebElement_Commands {
    // Khai báo biến driver để tương tác với Browser
    // Dòng lệnh nào được comment * thì sẽ được dùng nhiều trong dự án
    WebDriver driver;

    WebElement element;

    @Test
    public void TC_01_WebElement() {
        element = driver.findElement(By.xpath(""));

        // Click vào các element dạng: button / checkbx / radio / link / image / icon /...
        driver.findElement(By.xpath("")).click(); // *

        // Nhập liệu vào các element dạng textbox / textarea / dropdown (cho phép edit)
        element.sendKeys("tuanna9@runsystem.net");  // *
        // Giả lập thao tác lên các phím trên Keyboard
        element.sendKeys(Keys.ENTER);
        element.clear(); // Xóa dữ liệu trước khi sendKey  // *

        // Tìm theo kiểu từ cha đến con, giống như tìm theo xpath tương đối
        // Thực tế không cần viết theo dạng này
        driver.findElement(By.id("")).findElement(By.cssSelector("")).findElement(By.xpath(""));

        // Tác dụng với form (SignUp/Login/Search/...)
        // Điều kiện phải có thẻ form, thường bao gồm 2 textbox Email và password
        // Thao tác này giống thao tác Enter trên bàn phím
        // Thực tế sẽ ít dùng hàm này
        element.submit();

        // Áp dụng cho tất cả các loai element
        // Dùng để kiểm tra có hiển thị hay không ?
        // Một element hiển thị khi size > 0 (height > 0 và width > 0), có thể xem được bằng cách inspect HTML
        // Nhìn thấy / thao tác được
        element.isDisplayed(); // trả về kiểu boolean là True
        Assert.assertTrue(element.isDisplayed());

        // Áp dụng cho duy nhất 3 loại: Checkbox / Radio / Dropdown (dạng default)
        // Kiểm tra 1 element đã được chọn rồi hay chưa chọn
        element.isSelected(); // *

        // Áp dụng cho tất cả các loại
        // Kiểm tra 1 element có bị disable hay không (hay được gọi là trạng thái read-only)
        element.isEnabled();

        //================================= HÀM get() ===================================================================

        // Lấy thuộc tính CSS của 1 element
        // Phương pháp để test GUI: Font / Size / Color / Location / ...
        element.getCssValue("background-color"); // Trả về mã màu hexa #f2923  // *
        element.getCssValue("font-size"); // Trả về 14px

        // Áp dụng cho element chứa Text (Link / Button / Header / Label /....)
        element.getText();  // *

        // Lấy giá trị của 1 thuộc tính của 1 thẻ
        element.getAttribute("placeholder");  // *

        // Kiểm tra width và height của element
        Dimension dimensionElement = element.getSize(); // Trả về kiểu Dimension
        dimensionElement.getHeight();
        dimensionElement.getWidth();

        // Kiểm tra vị trí của element so với viewport (viewport là vùng mình nhìn thấy được tính từ phần dưới header chứa URL của browser cho đến phần nằm trên taskbar của Hệ điều hành)
        // Vị trí của element sẽ được tính là độ phân giải của cạnh góc trên bên trái màn hình kéo đến mép trên bên trái của element đó
        Point pointElement = element.getLocation(); // Trả về kiểu Point
        pointElement.getX();
        pointElement.getY();

        // Tổng hợp của getSize() và getLocation();
        Rectangle rectangle = element.getRect();
        // getSize()
        rectangle.getWidth();
        rectangle.getHeight();
        rectangle.getDimension(); // Trả về kiểu Dimension
        // getLocation()
        rectangle.getX();
        rectangle.getY();
        rectangle.getPoint();

        // Lấy ra thẻ HTML của element đó
        // Element A (tìm bằng css)
        String tagname = driver.findElement(By.cssSelector("#Firstname")).getTagName();
        // Element B (tìm bằng xpath)
        element.findElement(By.xpath("//" + tagname + "[@id='Last name']"));
        // => Ít dùng trong thực tế

        // Lấy ra giá trị của thuộc tính Name trong Tab Accessibility của devTool
        // Thực tế sẽ không dùng
        element.getAccessibleName();

        // Lấy ra giá trị của thuộc tính Role trong Tab Accessibility của devTool
        element.getAriaRole();

        // Lấy ra giá trị của 1 thuộc tính trong thẻ html
        // Thực tế sẽ không dùng
        element.getDomAttribute("title");

        // Lấy ra giá trị của 1 thuộc tính trong Tab Properties
        // Thực tế sẽ không dùng
        element.getDomProperty("name");  // *

        // Liên quan đến Popup
        element.getShadowRoot();  // *

        // Dùng để chụp hình, chỉ tiêt sẽ nói rõ hơn bên phần Framework
        element.getScreenshotAs(OutputType.FILE);  // *
        element.getScreenshotAs(OutputType.BASE64);  // *
        element.getScreenshotAs(OutputType.BYTES);  // *

        //=================================-----------===================================================================
    }


}
