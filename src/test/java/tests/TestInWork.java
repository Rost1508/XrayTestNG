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

    @Test(priority = 1, description = "THY-506: Request - Agent")
    @Severity(SeverityLevel.NORMAL)
    @Description("THY-506: Request - Agent")
    @Story("THY-506: Request - Agent")
    public void RequestAgent_case1() throws InterruptedException, IOException {
        loginPageForScratchOrg.logInOnScratchOrg(driver);
        StringBuilder accountResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "Account",
                "-v",
                "Name='TestRequest",
                "-u",
                ALIAS,
                "--json"});
        String accountId = JsonParser2.getFieldValue(accountResult.toString(), "id");
        StringBuilder contactResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "Contact",
                "-v",
                "LastName='Shepard' Email='test@tut.by' AccountId='" + accountId + "'",
                "-u",
                ALIAS,
                "--json"});
        String contactId = JsonParser2.getFieldValue(contactResult.toString(), "id");
        StringBuilder requestResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Request__c",
                "-v",
                "thn__Agent_contact_Email__c='test@tut.by'",
                "-u",
                ALIAS,
                "--json"});
        String requestId = JsonParser2.getFieldValue(requestResult.toString(), "id");
        StringBuilder requestRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Request__c",
                "-w",
                "Id='" + requestId + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(requestRecord);
        String agentContact = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Agent_contact__c");
        String agent = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Agent__c");
        Assert.assertEquals(agentContact, contactId);
        Assert.assertEquals(agent, accountId);
    }

    @Test(priority = 2, description = "THY-506: Request - Agent")
    @Severity(SeverityLevel.NORMAL)
    @Description("THY-506: Request - Agent")
    @Story("THY-506: Request - Agent")
    public void RequestAgent_case2() throws InterruptedException, IOException {
        loginPageForScratchOrg.logInOnScratchOrg(driver);
        StringBuilder contactResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "Contact",
                "-v",
                "LastName='Anderson' Email='test2@tut.by'",
                "-u",
                ALIAS,
                "--json"});
        String contactId = JsonParser2.getFieldValue(contactResult.toString(), "id");
        StringBuilder requestResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Request__c",
                "-v",
                "thn__Agent_contact_Email__c='test2@tut.by' thn__Agent_Name__c='Sandman'",
                "-u",
                ALIAS,
                "--json"});
        String requestId = JsonParser2.getFieldValue(requestResult.toString(), "id");
        requests.openRequestRecord(requestId);
        requests.clickConvert();
        convertWindow.fillConvertForm("Demo", "3", date.generateTodayDate(), date.generateDate_plus(0, 3));
        Thread.sleep(3000);
        StringBuilder requestRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Request__c",
                "-w",
                "Id='" + requestId + "'",
                "-u",
                ALIAS,
                "--json"});
        String agentContact = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Agent_contact__c");
        String agentId = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Agent__c");
        Assert.assertEquals(agentContact, contactId);
        Assert.assertNotNull(agentId);
        StringBuilder myceQuoteRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__MYCE_Quote__c",
                "-w",
                "thn__Agent__c='" + agentId + "'",
                "-u",
                ALIAS,
                "--json"});
        String myceQuoteAgentContact = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Agent_Contact__c");
        Assert.assertEquals(myceQuoteAgentContact,  contactId);
    }

    @Test(priority = 3, description = "THY-506: Request - Agent")
    @Severity(SeverityLevel.NORMAL)
    @Description("THY-506: Request - Agent")
    @Story("THY-506: Request - Agent")
    public void RequestAgent_case3() throws InterruptedException, IOException {
        StringBuilder requestResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Request__c",
                "-v",
                "thn__Agent_contact_Email__c='test3@tut.by' thn__Agent_Name__c='Sandman2'",
                "-u",
                ALIAS,
                "--json"});
        String requestId = JsonParser2.getFieldValue(requestResult.toString(), "id");
        requests.openRequestRecord(requestId);
        requests.clickConvert();
        convertWindow.fillConvertForm("Demo", "3", date.generateTodayDate(), date.generateDate_plus(0, 3));
        Thread.sleep(3000);
        StringBuilder requestRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Request__c",
                "-w",
                "Id='" + requestId + "'",
                "-u",
                ALIAS,
                "--json"});
        String agentContact = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Agent_contact__c");
        String agentId = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Agent__c");

    }
}
