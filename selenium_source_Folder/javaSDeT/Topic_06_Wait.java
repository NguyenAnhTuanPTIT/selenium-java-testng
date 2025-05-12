package javaSDeT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_06_Wait {
    //1- Setup: OS/Browser/Web/Page/ Data/Variable/Object/..

    WebDriver driver;

    WebDriverWait explicitWait;

    // Khai báo theo kiểu Java Generic
    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void initalBrowser(){
        driver = new FirefoxDriver();

        // Explicit Wait
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));

        // Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Fluent Wait
        fluentWait = new FluentWait<WebDriver>(driver); // Khai báo ở đây sẽ dùng được cho các testcase trong Class này
    }


    @Test
    public void TC_01_(){
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        // Khai báo ở đây sẽ mang tính nội bộ trong testcase này thôi
        fluentWait = new FluentWait<WebDriver>(driver);
    }

    @Test
    public void TC_02_(){



    }


}
