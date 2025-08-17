package testng;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Skip_Testcase {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){

    }

    // Set enabled = fasle để skip testcase không muốn chạy
    // Hoặc có thể comment annotation @Test lại, thì hàm đó sẽ không được coi là 1 testcase

    @Test(enabled = false)
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

    @AfterClass
    public void afterClass(){
    }



}
