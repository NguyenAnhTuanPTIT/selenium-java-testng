package javaSDeT;

import org.openqa.selenium.remote.JsonToWebElementConverter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Topic_08_For {
    public static void main(String[] args) {
        // Biểu thức vòng lặp (loop)
        int number = 100;

        // FOR (classic - iterater)
        for (int i = 0; i < number; i++) {
            if (i == 5) {
                System.out.println(i);
                break; // Nếu i = 5 thì sau khi in ra màn hình sẽ thoát khỏi vòng lặp
            }
        }

        // Collection: List / Set / Queue / Map
        List<String> name = new ArrayList<String>();
        name.add("Manual Testing");
        name.add("Automation Testing");
        name.add("API Testing");
        name.add("Blackbox Testing");
        // FOR -  EACH
        for(String a:name){
            System.out.println(a);
        }

        for(String b:name){
            if(b.equals("Automation Testing"))
                System.out.println("Interview");
        }

        // WHILE
        int i = 0;
        while(i < number){ // Kiểm tra điều kiện trước
            System.out.println(i);
            i++;
        }

        // DO - WHILE (chạy ít nhất 1 lần)
        int u = 1000;
        do{ // Action trước
            System.out.println(u);
            u++;
        }while(u < number); // Kiểm tra điều kiện sau
    }


}
