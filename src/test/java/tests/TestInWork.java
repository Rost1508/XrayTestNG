package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.JsonParser2;
import pageObject.SfdxCommand;


import java.io.IOException;

public class TestInWork extends BaseTest{


    @Test(priority = 21, description = "Quote_Meetings_Room__c.VR19_SetupResource")
    @Severity(SeverityLevel.NORMAL)
    @Description("Quote_Meetings_Room__c.VR19_SetupResource")
    @Story("")
    public void testCreateQuoteMeetingsRoom1() throws InterruptedException, IOException {
        ///home/minsk-sc/sfdx/bin/sfdx
        //force:data:record:get -s thn__MYCE_Quote__c -w "Name=Test24" -u THYNK-VR --json
        //force:data:soql:query -q "SELECT thn__Commissionable__c, thn__Commission_to__c FROM thn__MYCE_Quote__c where Name='Test24'" -u THYNK-VR --json
        //System.out.println("/home/minsk-sc/sfdx/bin/sfdx force:data:soql:query -q \"SELECT thn__Commissionable__c, thn__Commission_to__c FROM thn__MYCE_Quote__c where Name='Test24'\" -u THYNK-VR --json");
        SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__MYCE_Quote__c",
                "-v",
                "Name='Test24' thn__Commissionable__c=true",
                "-u",
                "THYNK-VR",
                "--json"});
        StringBuilder res = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
        "force:data:record:get",
        "-s",
        "thn__MYCE_Quote__c",
        "-w",
        "Name=Test24",
        "-u",
        "THYNK-VR",
        "--json"});
        System.out.println(res);
        String name = JsonParser2.getFieldValue(res.toString(), "Name");
        String commissionable = JsonParser2.getFieldValue(res.toString(), "thn__Commissionable__c");
        //String commission_to =  JsonParser2.getFieldValue(res.toString(), "thn__Commission_to__c");
        Assert.assertEquals(name, "Test24");
        Assert.assertEquals(commissionable, "true");
        //Assert.assertEquals(commission_to, null);
    }











}
