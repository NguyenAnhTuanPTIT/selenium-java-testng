package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.image.Kernel;

public class Topic_09_WebElement_Exercises_01 {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    @BeforeClass
    public void initalBrowser(){
        driver = new ChromeDriver();
    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Displayed() {
        // Hàm isDisplayed: kiểm tra bất kì 1 element nào hiển thị
        // Hiển thị: có thể nhìn thấy - có kích thước cụ thể

        driver.get("https://automationfc.github.io/basic-form/index.html");

        //------------- EMAIL TEXTBOX
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
        if(emailTextbox.isDisplayed()){
            System.out.println("Email Textbox is displayed");
            //System.out.println(driver.findElement(By.cssSelector("label[for='edu']")).getText());
            emailTextbox.sendKeys("Autmation Testing");
        }
        else{
            System.out.println("Email Textbox is not displayed");
        }
        //----------------------------------------------------------------------------

        //-------------- RADIO BUTTON
        WebElement ageUnder18Radio= driver.findElement(By.cssSelector("input#under_18"));
        if(ageUnder18Radio.isDisplayed()){
            System.out.println("Age under 18 radio is displayed");

            ageUnder18Radio.click();
        }
        else{
            System.out.println("Age under 18 radio is not displayed");
        }
        //------------------------------------------------------------------------------

        //---------------- Textarea Edu
        WebElement textareaEdu = driver.findElement(By.cssSelector("textarea#edu"));
        if(textareaEdu.isDisplayed()){
            System.out.println("Textarea Education is displayed");

            textareaEdu.sendKeys("Automation FC");
        }
        else{
            System.out.println("Textarea Education is not displayed");
        }
        //---------------------------------------------------------------------------------

        //----------------- User 05 text
        WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if(user5Text.isDisplayed()){
            System.out.println("Text user5 is displayted");
        }
        else{
            System.out.println("Text user5 is not displayted");
        }
        //--------------------------------------------------------------------------------



    }

    @Test
    public void TC_02_Enabled() {
        // Hàm isDisplayed: kiểm tra bất kì 1 element nào có thể tương tác lên được, ngược lại với read-only

        driver.get("https://automationfc.github.io/basic-form/index.html");

        //------------- EMAIL TEXTBOX
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
        if(emailTextbox.isEnabled()){
            System.out.println("Email Textbox is enabled");
            //System.out.println(driver.findElement(By.cssSelector("label[for='edu']")).getText());
            emailTextbox.sendKeys("Autmation Testing");
        }
        else{
            System.out.println("Email Textbox is disabled");
        }
        //----------------------------------------------------------------------------

        //-------------- RADIO BUTTON
        WebElement ageUnder18Radio= driver.findElement(By.cssSelector("input#under_18"));
        if(ageUnder18Radio.isEnabled()){
            System.out.println("Age under 18 radio is enabled");

            ageUnder18Radio.click();
        }
        else{
            System.out.println("Age under 18 radio is not disabled");
        }
        //------------------------------------------------------------------------------

        //------------- PASSWORD TEXTBOX
        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#disable_password"));
        if(passwordTextbox.isEnabled()){
            System.out.println("Password Textbox is enabled");
            //System.out.println(driver.findElement(By.cssSelector("label[for='edu']")).getText());
            emailTextbox.sendKeys("Autmation Testing");
        }
        else{
            System.out.println("Password Textbox is disabled");
        }
        //----------------------------------------------------------------------------

        //---------------- Textarea Biography
        WebElement textareaBio = driver.findElement(By.cssSelector("textarea#bio"));
        if(textareaBio.isEnabled()){
            System.out.println("Textarea Biography is enabled");

            textareaBio.sendKeys("Automation FC");
        }
        else{
            System.out.println("Textarea Biography is not disabled");
        }
        //---------------------------------------------------------------------------------


    }

    @Test
    public void TC_03_Selected() {
        // Hàm isSelected: Kiểm tra 1 element được chọn thành công (Radio, Checkbox, Dropdown,...)

        driver.get("https://automationfc.github.io/basic-form/index.html");

        //-------------- RADIO BUTTON
        WebElement ageUnder18Radio= driver.findElement(By.cssSelector("input#under_18"));
        if(ageUnder18Radio.isDisplayed()){
            System.out.println("Age under 18 radio is displayed");

            ageUnder18Radio.click();
        }
        else{
            System.out.println("Age under 18 radio is not displayed");
        }
        //------------------------------------------------------------------------------

    }

    @Test
    public void TC_04_MailChimp__Register_Validation() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");

        Thread.sleep(5000);

        driver.findElement(By.cssSelector("input#email")).sendKeys("nguyenanhtuanptit123@gmail.com");
        driver.findElement(By.cssSelector("input#email")).sendKeys(Keys.TAB);

        Thread.sleep(5000);

        // Chỉ nhập số, verify các message đánh dấu xanh và không được đánh dấu xanh
        driver .findElement(By.cssSelector("input#new_password")).sendKeys("123");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        // TH có số trong giá trị của attribute class, sẽ không viết được kiểu này : li.8-char.not-completed
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // Chỉ nhập chữ thường, verify các message đánh dấu xanh và không được đánh dấu xanh
        driver .findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("tuan");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // Chỉ nhập chữ hoa, verify các message đánh dấu xanh và không được đánh dấu xanh
        driver .findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("TUAN");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // Chỉ nhập ký tự đặc biệt, verify các message đánh dấu xanh và không được đánh dấu xanh
        driver .findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("!@#$");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // Nhập nhiều hơn 8 ký tự, verify các message đánh dấu xanh và không được đánh dấu xanh
        driver .findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("nguyenAnhtuanptit123@gmail.com");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // Nhập giá trị hợp lệ, verify các message không hiển thị lên UI nữa
        driver .findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Ptit123@gmail");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());


    }

    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
