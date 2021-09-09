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

public class Request_Agent extends BaseTest{

    @Test(priority = 1, description = "THY-506: Request - Agent")
    @Severity(SeverityLevel.NORMAL)
    @Description("THY-506: Request - Agent")
    @Story("THY-506: Request - Agent")
    public void RequestAgent_case1() throws InterruptedException, IOException {
        loginPageForScratchOrg.logInOnScratchOrg(driver);
        StringBuilder propertyRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Hotel__c",
                "-w",
                "Name='Demo'",
                "-u",
                ALIAS,
                "--json"});
        String propertyID = JsonParser2.getFieldValue(propertyRecord.toString(), "Id");
        StringBuilder result = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:update",
                "-s",
                "thn__Hotel__c",
                "-w",
                "id='" + propertyID + "'",
                "-v",
                "thn__Request_Name__c='Demo'",
                "-u",
                ALIAS,
                "--json"});
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
        String agentContactId = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Agent_contact__c");
        String agentId = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Agent__c");
        String requestContactId = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Contact__c");
        String requestAccountId = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Account__c");

        StringBuilder myceQuoteRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__MYCE_Quote__c",
                "-w",
                "thn__Agent__c='" + agentId + "'",
                "-u",
                ALIAS,
                "--json"});
        String myceQuoteAgentContactId = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Agent_Contact__c");
        String myceQuoteAgentId = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Agent__c");
        String myceQuoteCompanyId = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Company__c");
        String myceQuoteCompanyContactId = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Company_Contact__c");
        Assert.assertEquals(myceQuoteAgentContactId, agentContactId);
        Assert.assertEquals(agentId, myceQuoteAgentId);
        Assert.assertEquals(myceQuoteCompanyId, requestAccountId);
        Assert.assertEquals(myceQuoteCompanyContactId, requestContactId);
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
                "thn__Agent_contact_Email__c='test3@tut.by' thn__Agent_contact_Last_Name__c='TestAgentContact'" +
                        " thn__Last_Name__c='TestContact' thn__Account_Name__c='TestAccount'  thn__Agent_Name__c='Sandman2'",
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
        String agentContactId = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Agent_contact__c");
        String agentId = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Agent__c");
        String requestContactId = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Contact__c");
        String requestAccountId = JsonParser2.getFieldValue(requestRecord.toString(), "thn__Account__c");
        StringBuilder myceQuoteRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__MYCE_Quote__c",
                "-w",
                "thn__Agent__c='" + agentId + "'",
                "-u",
                ALIAS,
                "--json"});
        String myceQuoteAgentContactId = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Agent_Contact__c");
        String myceQuoteAgentId = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Agent__c");
        String myceQuoteCompanyId = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Company__c");
        String myceQuoteCompanyContactId = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Company_Contact__c");
        Assert.assertEquals(myceQuoteAgentContactId, agentContactId);
        Assert.assertEquals(agentId, myceQuoteAgentId);
        Assert.assertEquals(myceQuoteCompanyId, requestAccountId);
        Assert.assertEquals(myceQuoteCompanyContactId, requestContactId);
    }

}
