package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        driver = new FirefoxDriver();

        // select  = new Select(driver.findElement(By.cssSelector("select#day")));
        // Khai báo ở trên sai vì mới chỉ khởi tạo một cửa sổ trình duyện,nếu new select ở đây sẽ không tìm thấy element

        // Chỉ new nếu driver là tham số
        action = new Actions(driver);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        jsExcutor = (JavascriptExecutor) driver;

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Facebook_SignUp() {
        driver.get("https://www.facebook.com/reg/");

        select  = new Select(driver.findElement(By.cssSelector("select#day")));

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

        // ===>

    }

    @Test
    public void TC_02_Login() {

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {

        driver.quit();
    }


}
