package Package_2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Topic_14_Checkbox_RadioButton {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    // Khai báo data dùng kiểu JavascriptExcutor cho Testcase No.4
    JavascriptExecutor jsExcutor;

    @BeforeClass
    public void initalBrowser() {
        driver = new FirefoxDriver();
        // Phóng to trình duyệt hết cỡ
        driver.manage().window().maximize();

        jsExcutor = (JavascriptExecutor) driver;



    }

    //2- Action/Excute: Tương tác lên Element nào/nhập liệu/Verify/....
    @Test
    public void TC_01_Telerik() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        Thread.sleep(5000);

        // Scoll xuống thêm 1 đọạn 300px trừ trường hợp màn hình bé sẽ che mất đi checkbox cần tìm
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,300)");

        // Verify checkbox/ radio is enabled/ disabled
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isEnabled());

        // Verify checkbox/ radio is selected/ deselected
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isSelected());


        By dualZoneAirCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // Scoll xuống thêm 1 đọạn 300px trừ trường hợp màn hình bé sẽ che mất đi checkbox cần tìm
        //((JavascriptExecutor)driver).executeScript("window.scrollBy(0,300)");

        // Kiểm tra nếu checkbox chưa được check thì sẽ cho check vào
        if(!driver.findElement(dualZoneAirCheckbox).isSelected()) {
            // Select to checkbox "Dual-zone air conditioning"
            driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).click();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());

        // Kiểm tra nếu checkbox đã check rồi thì sẽ cho uncheck
        if(driver.findElement(dualZoneAirCheckbox).isSelected()) {
            // Deselect to checkbox "Dual-zone air conditioning"
            driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).click();
        }
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());

        // Di chuyển sang trang khác
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        Thread.sleep(5000);
        By twoPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        // Kiểm tra nếu radio button chưa được click thì sẽ cho click vào
        if(!driver.findElement(twoPetrol).isSelected()) {
            // Select to checkbox "Dual-zone air conditioning"
            driver.findElement(twoPetrol).click();
        }
        Assert.assertTrue(driver.findElement(twoPetrol).isSelected());



    }

    @Test
    public void TC_02_Material() throws InterruptedException {
        driver.get("https://material.angular.dev/components/radio/examples");

        Thread.sleep(5000);

        // Scoll xuống thêm 1 đọạn 300px trừ trường hợp màn hình bé sẽ che mất đi checkbox cần tìm
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,300)");

        By summerRadioButton = By.xpath("//label[text()='Summer']/preceding-sibling::div//div[@class='mdc-radio__outer-circle']");

        // Kiểm tra nếu radiobutton chưa được check thì sẽ cho check vào
        if(!driver.findElement(summerRadioButton).isSelected()) {
            // Select to checkbox "Dual-zone air conditioning"
            driver.findElement(summerRadioButton).click();
        }
        Assert.assertTrue(driver.findElement(summerRadioButton).isSelected());

        //========================= Di chuyển sang trang khác có checkbox

        driver.get("https://material.angular.dev/components/checkbox/examples");

        By checkedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div/div[@class='mdc-checkbox__background']");

        By indeterminateCheckbox = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/div[@class='mdc-checkbox__background']");

        // Kiểm tra nếu checkbox chưa được check thì sẽ cho check vào
        if(!driver.findElement(checkedCheckbox).isSelected()) {
            // Select to checkbox "Checked"
            driver.findElement(checkedCheckbox).click();
        }
        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());

        // Kiểm tra nếu checkbox đã check rồi thì sẽ cho uncheck
        if(driver.findElement(checkedCheckbox).isSelected()) {
            // Deselect to checkbox "Checked"
            driver.findElement(checkedCheckbox).click();
        }
        Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());

        // Kiểm tra nếu checkbox chưa được check thì sẽ cho check vào
        if(!driver.findElement(indeterminateCheckbox).isSelected()) {
            // Select to checkbox "Checked"
            driver.findElement(indeterminateCheckbox).click();
        }
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

        // Kiểm tra nếu checkbox đã check rồi thì sẽ cho uncheck
        if(driver.findElement(indeterminateCheckbox).isSelected()) {
            // Deselect to checkbox "Checked"
            driver.findElement(indeterminateCheckbox).click();
        }
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());

    }

    @Test
    public void TC_03_Select_All_Checkbox() throws InterruptedException {
        driver.get("https://automationfc.github.io/multiple-fields/");

        Thread.sleep(2000);

        // Scoll xuống thêm 1 đọạn 300px trừ trường hợp màn hình bé sẽ che mất đi checkbox cần tìm
        //((JavascriptExecutor)driver).executeScript("window.scrollBy(0,500)");

        List<WebElement> allCheckboxElement = driver.findElements(By.cssSelector("input[type='checkbox']"));

        // Check vào tất cả các checkbox
        for(WebElement eachCheckboxElement: allCheckboxElement){

            // Kiểm tra nếu checkbox chưa được check thì sẽ cho check vào
            if(!eachCheckboxElement.isSelected()) {
                // Select to checkbox
                eachCheckboxElement.click();
            }
            Assert.assertTrue(eachCheckboxElement.isSelected());
        }

        // Uncheck tất cả các checkbox trừ checkbox "Heart Attack"
        for(WebElement eachCheckboxElement: allCheckboxElement){

            // Kiểm tra nếu checkbox chưa được check thì sẽ cho check vào
            if(eachCheckboxElement.isSelected()) {
                if(!(eachCheckboxElement.getDomAttribute("value").equals("Heart Attack"))){
                    // Deselect to checkbox
                    eachCheckboxElement.click();
                    Assert.assertFalse(eachCheckboxElement.isSelected());
                }
                else{
                    Assert.assertTrue(eachCheckboxElement.isSelected());
                }
            }

        }

        // Nếu muốn chọn checkbox random
        for (int i = 0; i < allCheckboxElement.size(); i++) {
            int number = new Random().nextInt(allCheckboxElement.size());
            allCheckboxElement.get(number).click();

        }
    }

    @Test
    public void TC_04_Custom_RadioButton(){
        driver.get("https://login.ubuntu.com/");

        // Thẻ input -> dùng để click
        // Dùng để verify: isSelected()
        // Nhưng sẽ có trường hợp thẻ input sẽ bị che đi bởi một thẻ khác

        /*
        By newUserRadio = By.cssSelector("input#id_new_user");
        // 1 - Dùng thẻ input để click -> Fail
        // Dùng thẻ input này để verify -> Pass
        driver.findElement(newUserRadio).click();
        Assert.assertFalse(driver.findElement(newUserRadio).isSelected());
        */

        /*
        newUserRadio = By.cssSelector("label.new-user");
        // 2- Dùng 1 thẻ khác để click -> Pass
        // Dùng thẻ đó để verify -> Fail
        // isSelected() -> Chỉ dùng cho thẻ input/option
        driver.findElement(newUserRadio).click();
        Assert.assertFalse(driver.findElement(newUserRadio).isSelected());
        */

        /*
        // 3 - Dùng 1 thẻ khác input để click -> Pass
        // Dùng thẻ input này để verify -> Pass
        By newUserRadioLabel = By.cssSelector("label.new-user");
        By newUserRadioInput = By.cssSelector("input#id_new_user");

        driver.findElement(newUserRadioLabel).click();
        Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());
        // => Thỏa mãn được yêu cầu
        // => Nhưng thông thường sẽ không define nhiều locator cho 1 element, vì maintain sẽ rất cực, dễ gây hiểu lầm
        */

        // 4 - Áp dụng click trong Javacript để dùng duy nhất thẻ input để click/verify
        // Click của JS không quan tâm đến hiển thị của element, miễn element đó có trong HTML thì sẽ thao tác được
        By newUserRadioInput = By.cssSelector("input#id_new_user");

        jsExcutor.executeScript("arguments[0].click();", driver.findElement(newUserRadioInput));
        Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

        // Tiếp tục check vào checkbox ở cuối trang
        By termCheckbox = By.cssSelector("input#id_accept_tos");
        jsExcutor.executeScript("arguments[0].click();", driver.findElement(termCheckbox));
        Assert.assertTrue(driver.findElement(termCheckbox).isSelected());

    }

    // Trường hợp khi radio button / Checkbox không có thể input đại diện => Dùng các thuộc tính trong thẻ đó để verify thay cho hàm isSelected()
    @Test
    public void TC_05_Google_Doc(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        // Tìm radio button "Hồ Chí Minh" và click vào
        By hcmRadio = By.xpath("//div[@aria-label]='Hồ Chí Minh'");
        driver.findElement(hcmRadio).click();
        // Verify radio button đã được click bằng cách kiểm tra attribute aria-checked
        Assert.assertEquals(driver.findElement(hcmRadio).getDomAttribute("aria-checked"),"true");

        // Tìm Checkbox "Mì Quảng" và click vào
        By mqCheckbox = By.xpath("//div[@aria-label]='Mì Quảng'");
        if(driver.findElement(mqCheckbox).getDomAttribute("aria-checked").equals("false")){
            driver.findElement(mqCheckbox).click();
        }
        // Kiểm tra checkbox đã được check
        Assert.assertEquals(driver.findElement(mqCheckbox).getAttribute("aria-checked"),"true");

        // Uncheck checkbox
        if(driver.findElement(mqCheckbox).getDomAttribute("aria-checked").equals("true")){
            driver.findElement(mqCheckbox).click();
        }
        Assert.assertEquals(driver.findElement(mqCheckbox).getAttribute("aria-checked"),"false");


    }


    //3- Clean: Delete data test/account/close browser/..
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }


}
