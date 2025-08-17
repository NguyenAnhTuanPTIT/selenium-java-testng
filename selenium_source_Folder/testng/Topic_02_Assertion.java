package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assertion {

    public static void main(String[] args) {
        // 3 hàm chính để kiểm tra tính đúng đắn của dữ liệu
        boolean  gender = true;
        boolean bigger = 3 > 5;

        // Kiểm tra dữ liệu nó phải ĐÚNG
        Assert.assertTrue(gender);

        Assert.assertTrue(bigger); // Thông báo lỗi ở console

        // Kiểm tra dữ liệu nó phải SAI
        Assert.assertFalse(bigger); // Không lỗi, vì kiểm tra mong muốn sai thì 3<5 là sai thì KQ sẽ đúng

        // Kiểm tra dữ liệu nó bằng với mong đợi (ACTUAL - EXPECTED)
        // Kiểm tra Kiểu dữ liệu giống nhau ?
        // Kiểm tra Giá trị của dữ liệu bằng nhau ?
        Assert.assertEquals(5,6);
        Assert.assertEquals("name","NAME");
        Assert.assertEquals(5,"NAME"); // Nếu truyền 2 kiểu dữ liệu khác nhau, thì sẽ ép kiểu 2 tham số này sang Object
    }

    WebDriver driver;

    @Test
    public void assertion(){
        // AssertTrue : khi kiểm tra 1 điều kiện mong đợi nó sẽ trả về là ĐÚNG
        // Các hàm trả về True/False
        // Selenium : isDisplayed() , isEnabled() , isSelected() , isMultiple()
        // Các hàm do user define
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());

        // AssertFalse : khi kiểm tra 1 điều kiện mong đợi nó sẽ trả về là SAI
        // Mong đợi cái button Login disable
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isEnabled());

        // AssertEqual : kiểm tra 1 điều kiện mong đợi (expected) bằng với điều kiện thực tế (actual)
        // getText / getAttribute  / getCssValue / getTitle / getURL
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(),"");

        Assert.assertEquals(driver.findElement(By.cssSelector("")).getAttribute("placeholder"),"");

        // Verify số lượng element với locator tương ứng
        Assert.assertEquals(driver.findElements(By.cssSelector("")).size(),424);


    }


}
