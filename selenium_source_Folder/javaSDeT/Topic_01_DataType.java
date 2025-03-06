package javaSDeT;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

public class  Topic_01_DataType {
    //Access Modifier: phạm vi truy cập (private/public/protected/default)
    // Cách 1: Access Modifier - Kiểu dữ liệu - Tên biến - Giá trị của biến (Ngoài hàm/Trong hàm đều được
    public char cName = 'b';

    // Cách 2.1: Access Modifier - Kiểu dữ liệu - Tên biến
    public char cAddress;

    // Cách 2.2: Tên biến - Giá trị gán sau (Bắt buộc trong hàm)
    public void clickToElement(){
        cAddress = 'c' ;
    }

    // 2 nhóm kiểu dữ liệu
    // Nhóm 1 - Kiểu dữ liệu nguyên thủy
    // char: ký tự, khi gán giá trị (khởi tạo) thì nằm trong nháy đơn (')

    // byte/ short / int/ long: số nguyên, khi gán giá trị (khởi tạo) thì không nằm trong dấu gì
    byte bNumber = 120;

    short sNumber = 1200;

    int iNumber = 35000;

    long lNumber = 123421424;

    // float/ double: số thực, khi gán giá trị (khởi tạo) thì không nằm trong dấu gì
    float fNumber = 15.7f;

    double dNumber = 15.925d;

    // boolean: logic
    boolean gender = false;

    //-----------------------------------------------------------------------------------------------------------
    // Nhóm 2 - Kiểu dữ lệu tham chiêú
    // String
    // Khi gán trị (khởi tạo) thì nằm trong dấu nháy đôi (")
    String fullName = "Automation FC";

    // Class
    FirefoxDriver fDriver = new FirefoxDriver();
    Actions actions = new Actions(fDriver);
    Topic_01_DataType topic01 = new Topic_01_DataType();

    // Interface
    WebDriver driver;
    JavascriptExecutor jsExcuter;

    // Array - Cố định bao nhiêu giá trị
    String[] studentName = {"John", "Davide", "Vanguard"};//Các giá trị bên trong phải cùng kiểu dữ liệu
    Integer[] phoneNumber = {7234234,52344,523656};

    // List/Set/Queue - Không có định bao nhiêu giá trị
    List<String> studentAddress = new ArrayList<String>();
    List<String> studentCity = new LinkedList<String>();

    // Map
    Map<String,Integer> zip = new HashMap<String, Integer>();

    //Object - Ép được về kiểu dữ liệu khác
    Object name = "";
    Object phone = 1724;
    Object isDisplayed = true;
}