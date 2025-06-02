package Package_2;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v137.network.Network;
import org.openqa.selenium.devtools.v137.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic_16_Authentication_Alert {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;
    String  username = "admin";
    String password = "admin";

    @BeforeClass
    public void initalBrowser(){
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();

    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Authentication_URL(){
        // Pass thẳng trực tiếp username và password vào URL
        // http/https:// + username + : + password + @ URL
        driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_02_Authentication_Navigate() {
        driver.get("http://the-internet.herokuapp.com/");

        // Dùng hàm getAttribute để lấy giá trị của attribute href về => href sẽ có dạng full link http/https
        String basicAuthLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        System.out.println(basicAuthLink);

        driver.get(getAuthenticationUrl(basicAuthLink,"admin","admin"));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");

    }

    @Test
    public void TC_03_Authentication_devTools() {

        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");




    }

    public String getAuthenticationUrl (String link, String username, String password){

        String[] linkArray = link.split("//"); //Chia thành 2 chuỗi trước và sau //

        return linkArray[0] + "//" + username + ":" + password + "@" +linkArray[1];

    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
