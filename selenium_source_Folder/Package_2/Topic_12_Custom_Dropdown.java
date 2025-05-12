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
        driver.get("https://www.facebook.com/");
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_() {
        // Hành vi (behavior) để thao tác lên Dropdown
        // 1 - Chờ cho dropdown có thể thao tác lên được (Clickable)
        // 2 - Click vào element nào để nó xổ ra cái dropdown ra
        // 3 - Chờ cho tất cả các item được load ra
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
