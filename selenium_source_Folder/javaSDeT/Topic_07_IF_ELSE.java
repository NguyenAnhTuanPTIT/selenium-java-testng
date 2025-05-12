package javaSDeT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_07_IF_ELSE {

    public static void main(String[] args) {
        WebDriver driver;
        String osName = System.getProperty("os.name");
        String browserName = "IE";
        // Biểu thức điều kiện
        // if
        if(browserName.equals("IE")){
            System.out.println("Click to Submit Button");
        }

        // if - else
        if (osName.contains("Windows")) {
            System.out.println("Windows OS");
        }
        else{
            System.out.println("Mac or Linux OS");
        }
        System.out.println(osName);

        // if - else - if - else (hơn 2 điều kiện)
        if(browserName.equals("Chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else{
            driver = new EdgeDriver();
        }


        // switch - case
        switch (browserName){
            case "Firefox":
                driver = new FirefoxDriver();
                break;

            case "Chrome":
                driver = new ChromeDriver();
                break;

            default: // Những trường hợp còn lại
                driver = new EdgeDriver();
                break;

        }

    }




}
