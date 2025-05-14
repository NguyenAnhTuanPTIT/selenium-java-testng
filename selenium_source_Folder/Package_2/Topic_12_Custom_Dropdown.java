package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Custom_Dropdown {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver; //driver lúc này đang là null

    WebDriverWait explicitWait;

    @BeforeClass
    public void initalBrowser(){
        // Không được khai báo explicit trước khi new FirefoxDriver(), nếu đưa tham số driver = null vào sẽ bị sai =>
        // => Do không biết đang chạy với trình duyệt nào, driver nào
        // ====> Lỗi này sẽ xảy ra ở SELENIUM 3x
        // ====> Từ SELENIUM 4x trở đi sẽ không bị lỗi này, do có xử lý cho trường hợp truyền driver bị null
        // explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));

        driver = new FirefoxDriver(); // Luôn luôn phải khai báo đầu tiên

        // Khai báo sau khi new FirefoxDriver()
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_() throws InterruptedException {

        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");


        // TH nếu driver vẫn null và dùng các hảm của explicitWait => báo lõi
        // explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        // Gom xử lý dropdown lại thành 1 hàm, để có thể chọn nhiều option mà không cần tốn quá nhiều code viết lại
        // Chọn các option trong Dropdonw speed
        selectItemInCustomDropdown("span#speed-button","ul#speed-menu>li>div","Medium");
        selectItemInCustomDropdown("span#speed-button","ul#speed-menu>li>div","Fast");
        selectItemInCustomDropdown("span#speed-button","ul#speed-menu>li>div","Fast");

        // Chọn các option trong Dropdonw number
        selectItemInCustomDropdown("span#number-button","ul#number-menu>li>div","10");

        // Chọn các option trong Dropdonw title
        selectItemInCustomDropdown("span#salutatuon-button","ul#salutatuon-menu>li>div","Mrs.");


    }



    @Test
    public void TC_02_Login() {

    }

    private void selectItemInCustomDropdown(String parentCss, String childCss,String itemText) throws InterruptedException {
        // Hành vi (behavior) để thao tác lên Dropdown

        // 1 - Chờ cho dropdown có thể thao tác lên được (Clickable)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss)));


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
        driver.findElement(By.cssSelector(parentCss)).click();
        Thread.sleep(2000);

        // 3 - Chờ cho tất cả các item được load ra (Presence)
        // Ở đây sử dụng Presence vì sẽ có trường hợp selectbox đó có nhiều option, khi click vào sẽ chỉ visible được
        // 1 phần các option, các option còn lại sẽ không visible được
        // Lý do dùng không dùng thẻ li mà dùng div vì div chứa trực tiếp text option đó, nếu dùng thẻ li thì text lấy
        // ra có thể sẽ chứa nhiều ký tự không mong muốn khác
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));


        // 4 - Tìm cái item nào đúng với mong đợi
        // Dùng vòng lặp, nếu element nào chứa text mong đợi thì sẽ click vào
        List<WebElement> allItems = driver.findElements(By.cssSelector(childCss));
        for(WebElement item: allItems){
            System.out.println(item.getText()); // Nếu bỏ hàm break() đi, thì 2 option cuối sẽ được in ra là null, do
            // hàm getText() chỉ lấy ra text visible, vì khi chọn option Medium rồi
            // thì list option đã bị đóng lại, 2 option cuối đã bị hidden đi
            if(item.getText().equals(itemText)){
                // 5 - Click lên item đó
                item.click();
                break;
            }
        }
    }


    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        // driver.quit();
    }


}
