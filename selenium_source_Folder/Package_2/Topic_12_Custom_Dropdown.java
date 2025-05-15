package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    public void TC_01_JQuery() throws InterruptedException {

        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");


        // TH nếu driver vẫn null và dùng các hảm của explicitWait => báo lõi
        // explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        // Gom xử lý dropdown lại thành 1 hàm, để có thể chọn nhiều option mà không cần tốn quá nhiều code viết lại
        // Chọn các option trong Dropdonw speed
        selectItemInCustomDropdown("span#speed-button","ul#speed-menu>li>div","Medium");
        // Verify lại xem option đã chọn hiển thị đúng lên dropdown hay chưa
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");

        selectItemInCustomDropdown("span#speed-button","ul#speed-menu>li>div","Fast");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");

        selectItemInCustomDropdown("span#speed-button","ul#speed-menu>li>div","Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");

        // Chọn các option trong Dropdonw number
        selectItemInCustomDropdown("span#number-button","ul#number-menu>li>div","10");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "10");

        // Chọn các option trong Dropdonw title
        selectItemInCustomDropdown("span#salutation-button","ul#salutation-menu>li>div","Mrs.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mrs.");

        // ------------------------------------------------------------------------------------------------------------------
        // Dự án thực tế: 1 hàm để thao tác lên dropdown chỉ dùng 1 site/app
        // Ko dùng cho nhiều application khác nhau
        // Nhưng cơ chế Dropdown giống nhau



    }


    @Test
    public void TC_02_React() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInCustomDropdown("div.dropdown","div.item>span","Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text")).getText(), "Matt");

        selectItemInCustomDropdown("div.dropdown","div.item>span","Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text")).getText(), "Stevie Feliciano");


    }

    @Test
    public void TC_03_vueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

        selectItemInCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

        selectItemInCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");


    }

    @Test
    public void TC_04_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        enterItemInCustomDropdown("input.search","div.item>span","Albania");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text")).getText(), "Albania");

        enterItemInCustomDropdown("input.search","div.item>span","Argentina");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text")).getText(), "Argentina");



    }

    private void selectItemInCustomDropdown(String parentCss, String childCss,String itemText) throws InterruptedException {
        // Hành vi (behavior) để thao tác lên Dropdown

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

        // STEP 1 - Chờ cho dropdown có thể thao tác lên được (Clickable)

        // Hàm ExpectedConditions.elementToBeClickable() dùng để chờ cho element đó visible và click được, đồng thời
        // thời nó sẽ trả về chính element đó nếu nó tìm được, nên gộp thêm hàm click() vào luôn
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss))).click();
        Thread.sleep(2000);
        // STEP 2 - Click vào element nào để nó xổ ra cái dropdown ra


        // STEP 3 - Chờ cho tất cả các item được load ra (Presence)
        // STEP 4 - Tìm cái item nào đúng với mong đợi

        // Ở đây sử dụng Presence vì sẽ có trường hợp selectbox đó có nhiều option, khi click vào sẽ chỉ visible được
        // 1 phần các option, các option còn lại sẽ không visible được
        // Lý do dùng không dùng thẻ li mà dùng div vì div chứa trực tiếp text option đó, nếu dùng thẻ li thì text lấy
        // ra có thể sẽ chứa nhiều ký tự không mong muốn khác

        // Hàm ExpectedConditions.presenceOfAllElementsLocatedBy() cơ chế cũng sẽ giống step 1 + 2
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        // Dùng vòng lặp, nếu element nào chứa text mong đợi thì sẽ click vào
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

    private void enterItemInCustomDropdown(String parentCss, String childCss,String itemText) throws InterruptedException {
        // Hành vi (behavior) để thao tác lên Dropdown

        // STEP 1 - Chờ cho dropdown có thể nhập được (visible)
        // STEP 2 - SendKey và dropdown
        WebElement dropdownTextbox =  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentCss)));
        dropdownTextbox.clear(); // Clear dữ liệu sau mỗi lần nhập vào dropdown
        dropdownTextbox.sendKeys(itemText);
        Thread.sleep(2000);


        // STEP 3 - Chờ cho tất cả các item được load ra (Presence)
        // STEP 4 - Tìm cái item nào đúng với mong đợi
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        for(WebElement item: allItems){
            System.out.println(item.getText());
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
