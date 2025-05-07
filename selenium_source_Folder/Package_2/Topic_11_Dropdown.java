package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_11_Dropdown {
    // DROPDOWN được gọi là default nếu được cấu thành từ thẻ select, các thẻ con bên trong là thẻ option
    // Khác với các điều kiện trên thì được gọi là CUSTOM Dropdown


    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    Actions action;

    WebDriverWait explicitWait;

    JavascriptExecutor jsExcutor;

    Select select; // Khai báo biến sử dụng thư viện select của selenium

    @BeforeClass
    public void initalBrowser(){
        driver = new ChromeDriver();

        // select  = new Select(driver.findElement(By.cssSelector("select#day")));
        // Khai báo ở trên sai vì mới chỉ khởi tạo một cửa sổ trình duyện,nếu new select ở đây sẽ không tìm thấy element

        // Chỉ new nếu driver là tham số
        // action = new Actions(driver);
        // explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // jsExcutor = (JavascriptExecutor) driver;

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Facebook_SignUp() {
        driver.get("https://www.facebook.com/reg/");

        // Sau khi di chuyển đến trang có chứa dropdown thành công thì mới dùng hàm new Select
        select = new Select(driver.findElement(By.cssSelector("select#day")));

        //---------------- selectByVisibleText , selectByIndex, selectByValue -----------------------------------------
        // 1 - Visible Text
        // Giống như END_USER chọn, như hành vi của END_USER
        // KO bị trùng dữ liệu / ko để trống dữ liệu
        // Text ko bị thay đổi => không bị ảnh hưởng bởi thay đổi vị trí
        // Nếu chạy fail -> ít mất thời gian trong việc reproduce lại lỗi

        // 2 - Index
        // Index thay đổi vị trí => nếu đọc code sẽ không biết đang chọn giá trị nào
        // TH nếu chạy sai -> reproduce để tìm nguyên nhân sẽ mất thời gian

        // 3 - Value
        // Option không bắt buộc phải có attribute value
        // Nếu đọc code sẽ không biết đang chọn giá trị nào
        // Thêm 1 số bước để đi tìm nó là cái gì / ở đâu

        // ===> Dùng visible sẽ trực quan hơn, dễ maintain code hơn

        // Chọn 1 option
        select.selectByVisibleText("25");

        // TH nếu dùng hàm click() như dưới sẽ không được, vì ko giống hành vi END_USER
        // Chưa click vào dropdown nhưng đã click luôn option
        // driver.findElement(By.xpath("//select[@id='day']/option[text()='25']"));

        // Chọn xong verify đã chọn thành công hay chưa ?
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "25");

        // Verify dropdown có phải là multiple select hay không ?
        // Nếu là multiple => trả về true
        // Nếu là single => trả về false
        Assert.assertFalse(select.isMultiple());

        // Verify tổng số lượng option trong dropdown
        // select.getOptions() trả về tất cả các element option
        Assert.assertEquals(select.getOptions().size(),31);

        // Gán lại giá trị cho biến select
        select = new Select(driver.findElement(By.cssSelector("select#month")));

        // Chọn 1 option
        select.selectByVisibleText("Jun");

        // Verify lại option đã chọn
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Jun");

        // Gán lại giá trị cho biến select
        select = new Select(driver.findElement(By.cssSelector("select#year")));

        // Chọn 1 option
        select.selectByVisibleText("2006");

        // Verify lại option đã chọn
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"2006");

    }

    @Test
    public void TC_02_Login() {

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

       // driver.quit();
    }


}
