package testng;

import org.testng.annotations.Test;

public class Topic_12_Dependencies {

    @Test()
    public void TC_01_Create_New_Product(){
        System.out.println("Run TC_01");
    }

    // Test số 2 sẽ phụ thuộc vào Test số 1
    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_02_View_Product(){
        System.out.println("Run TC_02");
    }

    // Test số 3 sẽ phụ thuộc vào Test số 1
    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_03_Edit_Product(){
        System.out.println("Run TC_03");
    }

    // Test số 4 sẽ phụ thuộc vào Test số 1
    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_04_Move_Product(){
        System.out.println("Run TC_04");
    }

    // Test số 5 sẽ phụ thuộc vào Test số 1
    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_04_Delete_Product(){
        System.out.println("Run TC_05");
    }

    // Tất cả các Test từ số 2 trở đi sẽ phụ thuộc vào Test số 1 vì không create thì sẽ không có data để thực hiện các bước khác
    // Nếu Test số 1 fail thì các Test sau sẽ bị skip
}
