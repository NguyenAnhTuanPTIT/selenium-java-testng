package testng;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Description {

    // Tên testcase = hàm / function / method của Java, theo convention của từng ngôn ngữ
    // => Khi muốn chú thích / diễn giải / note thông tin vào testcase này nhằm mục đích dễ hiểu hơn
    // => Khi chạy sẽ show đoạn description này trong report của testng

    @Test(description = "JIRA#1368 - User can create new data and verify created success")
    public void shouldBeRegisterFailWithInvalidEmail(){

        System.out.println("shouldBeRegisterFailWithInvalidEmail");

    }

    @Test()
    public void shouldBeLoginPass(){

        System.out.println("shouldBeLoginPass");

    }

    @Test()
    public void shouldBeLoginFail(){

        System.out.println("shouldBeLoginFail   ");

    }

}
