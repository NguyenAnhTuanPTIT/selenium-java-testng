package testng;

import org.testng.annotations.Test;

public class Topic_03_Group {

    @Test(groups = {"product", "regression"})
    public void TC_01(){
        System.out.println("Product TC_01");
    }

    @Test(groups = {"product", "regression"})
    public void TC_02(){
        System.out.println("Product TC_02");
    }

    @Test(groups = {"product", "regression"})
    public void TC_03(){
        System.out.println("Product TC_03");
    }


}
