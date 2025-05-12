package Package_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Custom_Dropdown {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_() {

        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        // Hành vi (behavior) để thao tác lên Dropdown
        // 1 - Chờ cho dropdown có thể thao tác lên được (Clickable)
        // Có 3 cách để Wait: ImplicitWait / WebDriverWait / FluentWait
        // ImplicitWait : wait ngầm định áp dụng cho việc tìm element - áp dụng cho 2 hàm: findElement/findElements
        // -> wait trong khoảng thời gian được set để tìm element, nếu hết khoảng tgian đó sẽ Fail

        // WebDriverWait (Explicit Wait): wait tường minh cho element với 1 điều kiện rõ ràng
        // VD: Hiển thị / không hiển thị / Presence (xuất hiện trong HTML, không care việc hiển thị hay không)
        //     / Clickable / Selected / ....

        // FluentWait: Có thể sửa thời gian polling lại được
        // Polling là gì ? :Trong TH wait mà element chưa xuất hiện / chưa thỏa mãn điều kiện => mặc định nửa giây (500ms) sẽ tìm 1 lần
        // hoặc kiểm tra điều kiện 1 lần => Đây là thời gian mặc định của implicitWait và explicitWait
        // ***** Nhưng đối với FluentWait có thể sửa lại polling là 1s tìm 1 lần, 1/10s tìm 1 lần => tìm được nhiều lần hơn



        // 2 - Click vào element nào để nó xổ ra cái dropdown ra
        // 3 - Chờ cho tất cả các item được load ra (Presence)
        // 4 - Tìm cái item nào đúng với mong đợi
        // 5 - Click lên item đó



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
