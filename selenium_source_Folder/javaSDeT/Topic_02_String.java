package javaSDeT;

import org.testng.annotations.Test;

import java.io.File;

public class Topic_02_String {
    public static void main(String[] args) {
        String link = "http://the-internet.herokuapp.com/basic_auth";

        String username = "admin";
        String password = "admin";

        String[] linkArray = link.split("//"); //Chia thành 2 chuỗi trước và sau //

        link = linkArray[0] + "//" + username + ":" + password + "@" +linkArray[1];

        System.out.println(link);

        // Dùng File.separator để tạo ra seperator tương ứng với từng hệ điều hành khác nhau
        System.out.println(File.separator);
        System.out.println(System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator);

    }

    @Test
    public void TC_01_String_Method() {
        String schoolName = "Automation Testing Advanced";
        String schoolAddress = "Ho Chi Minh City";
        String courseName  = "AUTOMATION TESTING ADVANCED";

        // Đếm ký tự của chuỗi
        System.out.println(schoolName.length());

        // Lấy ra 1 ký tự nào đó
        System.out.println(schoolName.charAt(0)); // Lấy  ký tự đầu tiên
        System.out.println(schoolName.charAt(1)); // Lấy  ký tự thứ 2

        // Nối chuỗi
        System.out.println(schoolName.concat(schoolAddress));
        System.out.println(schoolName + schoolAddress); // Dùng  cách cộng chuỗi

        // Kiểm tra 2 chuỗi có bằng nhau hay không
        System.out.println(schoolName.equals(schoolAddress)); // Kết quả trả về false do 2 giá trị không bằng nhau

        // Kiểm tra 2 chuỗi không phân biệt hoa thường (kiểm tra tương đối)
        System.out.println(schoolName.equalsIgnoreCase(courseName)); // Kết quả trả về true

        // Kiểm tra 1  chuỗi có bắt đầu bằng 1 hay nhiều chuỗi ký tự hay ko
        System.out.println(schoolName.startsWith("A")); // True
        System.out.println(schoolName.startsWith("Automation")); // True

        // Kiểm tra 1 chuỗi có chứa  1 ký tự hay nhiều  chuỗi ký tu hay không
        System.out.println(schoolName.contains("Testing")); // KQ : true

        // Kiểm tra 1 chuỗi có kết thúc bằng 1 hay nhiều ký tự không
        System.out.println(schoolName.endsWith("ed")); // KQ : true

        // Lấy vị trí của 1 từ trong 1 chuoi
        System.out.println(schoolName.indexOf("utomation")); // KQ : 1, bắt đầu đếm từ vị trí ký tự thứ 2 là "u"
        System.out.println(schoolName.indexOf("A")); // KQ : 0
        System.out.println(schoolName.indexOf("Testing")); // KQ : 11

        // Tách 1 ký tự/chuỗi ra khỏi 1 chuỗi cho trước
        System.out.println(schoolName.substring(11)); // KQ : Testing Advanced, lấy substring bắt đầu từ ký tự thứ 11 ở string gốc
        System.out.println(schoolName.substring(11,15)); // KQ : Test, lấy substring bắt đầu từ ký tự thứ 11 đến ký tự thứ 15 từ string gốc

        // Tách chuỗi thành 1 mảng dựa vào ký tự/chuỗi ký tự
        String result = "Viewing 42 of 132 results";
        String results[]  = result.split(" "); // Tách chuỗi dựa trên ký tự khoảng trắng
        System.out.println(results[1]); // KQ : 42, mảng phần tử thứ nhất

        // Thay thế 1 ký tự/1 chuỗi trong một chuỗi
        String productPrice = "$100.00";
        productPrice = productPrice.replace("$","");
        float productPriceF = Float.parseFloat(productPrice); // Chuyển chuỗi về dạng số thực
        System.out.println(productPrice); // KQ : 100.00
        System.out.println(productPriceF); // KQ : 100.0
        System.out.println(String.valueOf(productPriceF)); // KQ : 100.0 , đây là phép gán lại từ số thực thành chuỗi

        // Cắt bỏ ký tự xuống dòng, ký tự tab và khoảng trắng ở đầu và cuối chuỗi
        String data = "                     \n \t Hello World                    ";
        System.out.println(data.trim()); //KQ : Hello World

        // Dynamic locator : một ứng dụng của hàm format
        // Đại diện 1 chuỗi : %s
        String dynamicLocatorXpath = "//a[@id='%s']";
        System.out.println("Click to Login button : " + String.format(dynamicLocatorXpath, "Login"));
        // KQ :  Click to Login button : //a[@id='Login']
        // Thế %s bằng chuỗi "Login"

    }

    @Test
    public void TC_02_String_Exercise_1(){
        String courseName = "Auto1matio2n 4Testi3ng V3N";
        char courseNameArr[] = courseName.toCharArray(); // Hàm này dùng để tách chuỗi ra thành từng ký tự
        int i = 0;
        int y = 0;
        int x = 0;
        for(char character : courseNameArr){
            if(character <= 'Z' && character >= 'A'){
                i = i + 1;
            }
        }
        for(char character : courseNameArr){
            if(character <= 'z' && character >= 'a'){
                y = y + 1;
            }
        }
        for(char character : courseNameArr){
            if(character <= '9' && character >= '0'){
                x = x + 1;
            }
        }

        System.out.println("Tổng số chữ cái viết hoa trong chuỗi là : " + i);
        System.out.println("Tổng số chữ cái viết thường trong chuỗi là : " + y);
        System.out.println("Tổng số chữ số viết thường trong chuỗi là : " + x);
    }

    @Test
    // Revert chuỗi
    public void TC_03_String_Exercise_2(){
        String courseName = "Auto1matio2n 4Testi3ng V3N";
        char temp;
        char courseNameArr[] = courseName.toCharArray(); // Hàm này dùng để tách chuỗi ra thành từng ký tự

        int j = courseNameArr.length;

        for (int i = 0; i < courseNameArr.length; i++) {
                j = j - 1;
                if(i < j){
                    temp = courseNameArr[i];
                    courseNameArr[i] = courseNameArr[j];
                    courseNameArr[j] = temp;
                }
                else {
                    break;
                }
        }

        for (int i = 0; i < courseNameArr.length; i++) {
            System.out.println(courseNameArr[i]);
        }
        System.out.println(new String(courseNameArr));

    }

}
