package javaSDeT;

import org.testng.annotations.Test;

import java.util.Random;

public class Topic_05_Random {
    String prefixEmail = "joebiden";

    String postfixEmail = "@gmail.com"; // Web Mail Server



    @Test
    public void Random(){
        Random rand = new Random();
        String fullEmail = prefixEmail + rand.nextInt(9999) + postfixEmail;

        System.out.println(fullEmail);
    }


}
