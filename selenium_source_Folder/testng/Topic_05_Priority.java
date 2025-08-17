package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Priority {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){

    }

    // Thiết lập priority để xác định testcase nào chạy trước, testcase nào chạy sau
    // Nếu không dùng priority mà muốn testcase chạy theo ý mình, thì cách tốt nhất là set các testcase theo kiểu alphabe
    // Ví dụ: đặt tên feature ở trước rồi số thứ tự testcase phía sau
    // - Product_01_View
    // - Product_02_Edit
    // - Product_03_Delete
    // => Cách này khi chạy sẽ chạy từ trên xuống, đồng thời sẽ giúp việc quản lý testcase dễ hơn

    @Test(priority = 1)
    public void shouldBeRegisterFailWithInvalidEmail(){

        System.out.println("shouldBeRegisterFailWithInvalidEmail");

    }

    @Test(priority = 3)
    public void shouldBeLoginPass(){

        System.out.println("shouldBeLoginPass");

    }

    @Test(priority = 2)
    public void shouldBeLoginFail(){

        System.out.println("shouldBeLoginFail   ");

    }

    @AfterClass
    public void afterClass(){
    }



}
