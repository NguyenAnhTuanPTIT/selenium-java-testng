package testng;

import org.checkerframework.checker.units.qual.A;
import org.testng.annotations.*;

public class Topic_01_Annotations {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Run Before Method");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Run After Method");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Run Before Class");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("Run After Class");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Run Before Test");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("Run After Test");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Run Before Suite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("Run After Suite");
    }

    @Test
    public void TC_01(){
        System.out.println("Run TC_01");
    }

    @Test
    public void TC_02(){
        System.out.println("Run TC_02");
    }

    @Test
    public void TC_03(){
        System.out.println("Run TC_03");
    }

    // KẾT QUẢ KHI RUN các testcase trên
    // Run Before Suite
    //     Run Before Test
    //          Run Before Class
    //              Run Before Method
    //                  Run TC_01
    //              Run After Method
    //              Run Before Method
    //                  Run TC_02
    //              Run After Method
    //              Run Before Method
    //                  Run TC_03
    //              Run After Method
    //          Run After Class
    //     Run After Test
    // Run After Suite

}
