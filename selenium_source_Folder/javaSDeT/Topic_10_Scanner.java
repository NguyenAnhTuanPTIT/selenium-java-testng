package javaSDeT;

import java.util.Scanner;

public class Topic_10_Scanner {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
//        int a = new Scanner(System.in).nextInt();
//        int b = new Scanner(System.in).nextInt();
//        String c = new Scanner(System.in).nextLine();

        int a = sc.nextInt();
        int b = sc.nextInt();
        String c = sc.nextLine();

        switch (c){
            case "+":
                System.out.println("A+B =" + (a+b));
                break;
            default:
                System.out.println("Không hợp lệ");
        }

    }
}
