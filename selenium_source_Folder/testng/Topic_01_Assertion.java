package testng;

import org.testng.Assert;

public class Topic_01_Assertion {

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
}
