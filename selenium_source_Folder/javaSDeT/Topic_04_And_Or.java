package javaSDeT;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_04_And_Or {

        @Test
        public void verifyAnd(){
            String contactConfirmation = "Testing Automation\n" + "automationfc1562024.com\n" + "Change Password";

            Assert.assertTrue(contactConfirmation.contains("Change") && contactConfirmation.contains("1562024"));
        }
}