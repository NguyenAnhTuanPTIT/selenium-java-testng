package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_02_Selenium_Locator {

    // Khai báo
    WebDriver driver;

    String fullName = "Selenium Loactor";

    @BeforeClass
    public void initialBrowser(){
        //Mở Browser lên
        driver = new FirefoxDriver();

        //Mở app lên đến màn hình Login
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() throws InterruptedException {
        // HTML source code
        // Thẻ - Thuộc tính - Giá trị thuộc tính
        // Tag name - Attribute - Value
        // Xpath: //tagname[@attribute='value']
        // Css:   //tagname[attribute='value']

        // Tương tác lên element trên web
        // 8 loại locator để tương tác với element của web
        driver.findElement(By.id("small-searchterms")).sendKeys("Airpod");

        Thread.sleep(3000); // Đây là hàm của java. ko phải của selenium

        driver.findElement(By.id("FirstName")).sendKeys("Automation");

        Thread.sleep(3000);


/*        // Sau dấu . gọi hàm/biến của thư viện đó ra

        // Tìm 1 element


        // 1 - Thao tác lên luôn (dùng 1 lần)
        driver.findElement(By.id(""));


        // 2 - Lưu dữ liệu lại (dùng nhiều lần)
        WebElement emailTextbox = driver.findElement(By.id(""));
        emailTextbox.click();
        emailTextbox.sendKeys("");
        emailTextbox.isDisplayed();
        emailTextbox.isDisplayed();

        // Tìm nhiều element giống nhau
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

        driver.findElement(By.cssSelector("")).click();

        driver.findElement(By.cssSelector("")).getText();*/

    }

    @Test
    public void TC_02_Class() throws InterruptedException {
        // Đặc thù của class là không lấy hết toàn bộ giá trị (nếu có khoảng trắng) và ngược lại
        driver.findElement(By.className("register-next-step-button")).click();

        Thread.sleep(3000);

    }

    @Test
    public void TC_03_Name() {
        // Lấy hết toàn bộ giá trị
        driver.findElement(By.name("FirstName"));
        driver.findElement(By.name("LastName"));
        driver.findElement(By.name("Email"));

    }

    @Test
    public void TC_04_LinkText() {
        // Chỉ làm việc được với element là link và có text
        // Nhận biết bằng thẻ a và có attribute là href
        // Phải lấy hết toàn bộ text ko chừa cái nào hết (tuyệt đối)
        driver.findElement(By.linkText("Register"));

        driver.findElement(By.linkText("Log in"));

        driver.findElement(By.linkText("Wishlist"));

    }

    @Test
    public void TC_05_Partial_Link_Text() {
    // Chỉ làm việc với element là link
    // Có thể lấy hết toàn bộ text hoặc 1 phần (hay dùng)

        driver.findElement(By.partialLinkText("Digital")); //Linktext hoàn chỉnh là Digital downloads

        driver.findElement(By.partialLinkText("downloads"));
    }

    @Test
    public void TC_06_Tagname() {
        // Tên thẻ (HTML)
        // Tìm tất cả element giống nhau (thẻ của component giống nhau)
        // Thường dùng để tìm tất cả các textbox/ checkbox/ radio/ link/ button/...
        driver.findElements(By.tagName("button"));

        driver.findElements(By.tagName("input"));

        driver.findElements(By.tagName("label"));

    }

    @Test
    public void TC_07_Css() {
        // CSS có thể cover việc tìm element theo: ID/ Class/ Name/ LinkText/ Partial LinkText/ Tagname
        // CSS cũng có thể cover Xpath, nhưng một số trường hợp sẽ không dùng được
        driver.findElement(By.cssSelector("input#Company")); // Tìm theo id của element
        driver.findElement(By.cssSelector("#Company")); // Có thể viết tắt không cần dùng tên thẻ
        driver.findElement(By.cssSelector("input[id='Company']")); // Viết theo kiểu chuẩn

        driver.findElement(By.cssSelector("button.register-next-step-button")); // Tìm theo class của element (không lấy hết giá trị của class)
        driver.findElement(By.cssSelector("button[class='button-1 register-next-step-button']")); // Nếu viết theo kiểu chuẩn thì giá trị của class phải lấy hết

        driver.findElement(By.cssSelector("input[name='Password']")); // Tìm element theo name thì phải viết kiểu chuẩn

        // Tìm element theo LinkText, do CSS ko tìm theo text được nên sẽ dựa vào các attribute khác để tìm
        // Dùng "*=" để tìm một phần giá trị
        driver.findElement(By.cssSelector("a[href*='register']"));

        // Tìm theo element theo partial linktext cũng dùng tương tự như trên

        // Tìm element theo tag name thì truyền tên thẻ vào
        driver.findElements(By.cssSelector("a")); // Tìm tất cả thẻ a
        driver.findElements(By.cssSelector("input")); // Tìm tất cả thẻ input
        driver.findElements(By.cssSelector("button")); // Tìm tất cả thẻ button

    }

    @Test
    public void TC_08_XPath() {
        // Xpath có thể cover việc tìm element theo: ID/ Class/ Name/ LinkText/ Partial LinkText/ Tagname và thậm chí cả CSS
        driver.findElement(By.xpath("//input[@id='small-searchterms']")); // Dạng chuẩn
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        driver.findElement(By.xpath("//input[@id='LastName']"));

        // Tìm element theo class
        driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']"));
        driver.findElement(By.xpath("//button[contains(@class, 'register-next-step-button')]")); // Nếu chỉ lấy một phần trong class

        // Tìm element theo name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        // Tìm element theo tagname
        driver.findElement(By.xpath("//a"));
        driver.findElement(By.xpath("//button"));
        driver.findElement(By.xpath("//input"));

        // Tìm element theo linktext
        driver.findElement(By.xpath("//a[text()='Register']"));
        driver.findElement(By.xpath("//a[text()='Shipping & returns']"));

        // Tìm element theo partial linktext
        driver.findElement(By.xpath("//a[contains(text(),'returns')]"));
    }

    @Test
    public void TC_09_Relative_Locator() throws InterruptedException {
        // Chỉ dùng Relative Locator khi:
        // 1 - Không thể định danh được element của chính nó (dựa vào nhưng cái vị trí bên cạnh/ gần đó)
        // 2 - Được sử dụng để test GUI (giao diện - position khớp với Design)

        driver.get("https://demo.nopcommerce.com/login");

        Thread.sleep(10000);
        // Khai báo element sử dụng kiểu dữ liệu By
        By rememberMeCheckboxBy = By.id("RememberMe");

        By forgotPasswordLinkBy = By.cssSelector("span.forgot-password");

        By passwordTextboxBy = By.cssSelector("input#Password");

        By loginButtonBy = By.cssSelector("button.login-button");

        driver.findElement(RelativeLocator.with(By.tagName("label")).above(loginButtonBy) // label nằm trên button Login
                .below(passwordTextboxBy) // label nằm dưới textbox password
                .toRightOf(rememberMeCheckboxBy) // label nằm bên phải của checkbox RememberMe
                .toLeftOf(forgotPasswordLinkBy) // label nằm bên trái link Forgot password
                .near(rememberMeCheckboxBy).near(forgotPasswordLinkBy)
                );
    }

    @Test
    public void TC_10_Xpath_Locator(){
        // Xpath tuyệt đối: đi từ node đầu tiên đến node cuối cùng - ko bỏ qua 1 node nào trung gian hết
        // Ko nên sử dụng xpath tuyệt đối : vì dễ bị fail
        // Ưu điểm: tìm element rất nhanh
        // Nhược điểm:
        // - Quá dễ lỗi / xấu/ khó đọc
        // - Thay đổi giao diện => dễ fail

        // Xpath tương đối: đi qua nhiều node, dùng dấu "//", có thể đi tới bất kì node nào mình muốn
        // Nên sử dụng xpath tương đối
        // Ưu điểm: ổn định hơn,ít fail
        // Nhược điểm: tốc độ tìm element không nhanh


        // 1 - Duy nhất
        // 2 - Ưu tiên nếu có id/ class/ name thì dùng trước
        // 3 - Không có id/ class/ name: dùng bất k 1 attribuet khác
        // 4 - Giá trị của attribute phải có ý nghĩa - liên quan tới cái element đó
        // 5 - Đối với loại link thì không nên dùng thẻ href vì dễ bị thay đổi
        // => Tối ưu nhất để dùng

        // Tìm kiếm theo kiểu: //*[@id='ABC'] => * đại diện cho bất kỳ thẻ nào
        // 1 - Tìm tất cả các thẻ có id = ABC

        // Dùng start-with khi: giá trị/text thay đổi (trường hợp page load lại)
        // Không support ends-with, bên CSS vẫn support được với cú pháp: $=

        // - Tìm element theo điều kiện AND, VD: //button[@class='...' and @id='...']
        // - Tìm element theo điều kiện OR, VD: //button[@class='...' or @id='...']

        // - Tìm element theo điều kiện NOT, VD: //div[not(@style='A')]/div[@class='raDiv'] => Tìm element với điều kiện không có attribute style có giá trị là A mà tìm
        // element với điều kiện có atrribute style có giá trị khác A
        // => chỉ sử dụng được trong TH chắc chắn chỉ có 2 element thì mới dùng được kiểu phủ định này

        // - Dùng hàm concat để nối chuỗi trong TH text có chứa ký tự dấu " và dấu '
        //      VD: Hello Nam "the king", what's your job => cú pháp: //span[text()=concat('Hello Nam "the king", what',"'s your job")]
        //   ====> Dùng nháy đôi bọc nháy đơn, dùng nháy đơn bọc nháy đôi

//        - TH element cùng cấp và cùng 1 thẻ cha:
//          + Tìm vị trí của số ở https://automationfc.github.io/jquery-selectable/
//            Dùng cú pháp Xpath sau: //ol[@id='selectable']/li[5] => Tìm ra element ở vị trí thứ 5
//            Hoặc có thể dùng Xpath sau với ý nghĩa tương đương: //ol[@id='selectable']/li[position()=10]
//
//          + Tìm element ở vị trí cuối: dùng hàm last()
//            VD: //ol[@id='selectable']/li[last()] => luôn luôn lấy thằng cuối cùng
//
//          + Tìm element ở vị trí kế cuối: dùng hàm count() hoặc last()
//          Hàm count()
//            VD: //ol[@id='selectable']/li[count(//ol[@id='selectable']/li)-1] => element thứ 19 trong 20 element
//            * Hàm count((//ol[@id='selectable']/li) sẽ cho kq = 20
//
//          Hàm last()
//            VD: //ol[@id='selectable']/li[last()-1]
//
//
//        - TH element cùng cấp nhưng không cùng 1 thẻ cha
//          Button [Add to cart] ở https://live.techpanda.org/index.php/mobile.html
//          Để tìm được các button tương ứng dùng cú pháp sau:
//              - (//button[@title='Add to Cart'])[1]: tìm ra button [Add to cart] thứ nhất
//              - (//button[@title='Add to Cart'])[2]: tìm ra button [Add to cart] thứ hai
//              - (//button[@title='Add to Cart'])[3]: tìm ra button [Add to cart] thứ ba
//          Việc dùng () để bọc lại đoạn Xpath: //button[@title='Add to Cart'] => coi như là 3 thằng này sẽ cùng 1 thằng cha
//
//
//        - Kỹ thuật Xpath Axes: di chuyển qua các node cha/anh/em khác khi đang đứng ở node hiện tại
//          + Muốn đi lên node cha: parent::tagname
//          + Muốn đi lên node tổ tiên (cha/ông/cụ/kị/....): ancestor::tagname (Nếu đi ngược 2 lần parent thì thay thế bằng ancestor cho nhanh)
//          + Muốn đi lên node anh: preceding-sibling::tagname
//          + Muốn đi xuống node em: following-sibling::tagname
//          + Muốn đi xuống node con: child::tagname
//            VD: //a[@title='IPhone']/following-sibling::div/child::*  -> Lấy tất cả các thẻ con trực tiếp trong thẻ div
//                  Hoặc có thể dùng: //a[@title='IPhone']/following-sibling::div/*
//          + Muốn đi xuống node hậu duệ (con/cháu/chắt/chút/...): descendant::tagname
//            VD 1: //a[@title='IPhone']/following-sibling::div/descendant::*  -> Lấy tất các các thẻ con trực tiếp và đồng thời thẻ cháu (nếu có)
//                  Hoặc có thể dùng: //a[@title='IPhone']/following-sibling::div//*
//            VD 2: //a[@title='IPhone']/following-sibling::div/descendant::span[@class='price']  -> Tìm cụ thể 1 descendant
//
//        VD: dùng https://live.techpanda.org/index.php/mobile.html -> tìm ra element button Add to cart thuộc Iphone để đảm bảo rằng nếu bị đổi vị trí ở HTML thì
//        vẫn tìm được
//        ----> Dùng cú pháp: //a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']/button
//
//        - Có thể dùng ".." để thay cho node cha: //a[text()='IPhone']/../following-sibling::div[@class='actions']/button
//
//        - Đối với cách này thì hạn chế dùng index nhất có thể:
//        - Nếu dùng cú pháp://a[text()='IPhone']/parent::h2/following-sibling::div[3]/button -> thẻ div vẫn có thể bị thay đổi vị trí
//        ======> Đây là tính năng truy ngược mà chỉ có XPath mới làm được, CSS không làm được



    }


    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
