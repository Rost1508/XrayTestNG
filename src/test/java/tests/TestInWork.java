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

    @Test(priority = 1, description = "THY-512 Quote hotel room - time fields")
    @Severity(SeverityLevel.NORMAL)
    @Description("THY-512 Quote hotel room - time fields")
    @Story("Quote hotel room’s arrival / departure datetimes are auto populated from the Quote while creation")
    public void quoteHotelRoom_timeFieldsTest1() throws InterruptedException, IOException {
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
        StringBuilder productRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Product__c",
                "-w",
                "Name='ROOM 1 NIGHT'",
                "-u",
                ALIAS,
                "--json"});
        String productID = JsonParser2.getFieldValue(productRecord.toString(), "Id");
        StringBuilder roomTypeRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Space_Area__c",
                "-w",
                "Name='Single'",
                "-u",
                ALIAS,
                "--json"});
        String roomTypeID = JsonParser2.getFieldValue(roomTypeRecord.toString(), "Id");
        StringBuilder myseQuoteResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__MYCE_Quote__c",
                "-v",
                "Name='QHR time test 1' thn__Pax__c=10 thn__Hotel__c='" + propertyID + "' thn__Arrival_Date__c=" +
                        date.generateTodayDate2() + " thn__Departure_Date__c=" + date.generateTodayDate2_plus(0, 3),
                "-u",
                ALIAS,
                "--json"});
        String myceQuoteID = JsonParser2.getFieldValue(myseQuoteResult.toString(), "id");
        StringBuilder quoteHotelRoomResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Quote_Hotel_Room__c",
                "-v",
                "thn__MYCE_Quote__c='" + myceQuoteID + "' thn__Product__c='" + productID + "'" +
                        " thn__Space_Area__c='" + roomTypeID + "' thn__Unit_Price__c=10" ,
                "-u",
                ALIAS,
                "--json"});
        String quoteHotelRoomId = JsonParser2.getFieldValue(quoteHotelRoomResult.toString(), "id");
        StringBuilder myceQuoteRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__MYCE_Quote__c",
                "-w",
                "Id='" + myceQuoteID + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(myceQuoteRecord);
        StringBuilder quoteHotelRoomRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Quote_Hotel_Room__c",
                "-w",
                "Id='" + quoteHotelRoomId + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(quoteHotelRoomRecord);
        String arrivalDateMyceQuote = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Arrival_Date__c");
        String departureDateMyceQuote = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Departure_Date__c");
        String arrivalDateTimeQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Arrival_Date_Time__c");
        String departureDateTimeQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Departure_Date_Time__c");

        Assert.assertTrue(arrivalDateTimeQuoteHotelRoom.contains(arrivalDateMyceQuote));
        Assert.assertTrue(departureDateTimeQuoteHotelRoom.contains(departureDateMyceQuote));
    }

    @Test(priority = 2, description = "THY-512 Quote hotel room - time fields")
    @Severity(SeverityLevel.NORMAL)
    @Description("THY-512 Quote hotel room - time fields")
    @Story("Quote hotel room’s arrival / departure datetimes are manually filled while creation ")
    public void quoteHotelRoom_timeFieldsTest2() throws InterruptedException, IOException {
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
        StringBuilder productRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Product__c",
                "-w",
                "Name='ROOM 1 NIGHT'",
                "-u",
                ALIAS,
                "--json"});
        String productID = JsonParser2.getFieldValue(productRecord.toString(), "Id");
        StringBuilder roomTypeRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Space_Area__c",
                "-w",
                "Name='Single'",
                "-u",
                ALIAS,
                "--json"});
        String roomTypeID = JsonParser2.getFieldValue(roomTypeRecord.toString(), "Id");
        StringBuilder myseQuoteResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__MYCE_Quote__c",
                "-v",
                "Name='QHR time test 2' thn__Pax__c=10 thn__Hotel__c='" + propertyID + "' thn__Arrival_Date__c=" +
                        date.generateTodayDate2() + " thn__Departure_Date__c=" + date.generateTodayDate2_plus(0, 5),
                "-u",
                ALIAS,
                "--json"});
        String myceQuoteID = JsonParser2.getFieldValue(myseQuoteResult.toString(), "id");
        StringBuilder quoteHotelRoomResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Quote_Hotel_Room__c",
                "-v",
                "thn__MYCE_Quote__c='" + myceQuoteID + "' thn__Product__c='" + productID + "'" +
                        " thn__Space_Area__c='" + roomTypeID + "' thn__Unit_Price__c=10 thn__Arrival_Date_Time__c="
                        + date.generateTodayDate2_plus(0, 2) + " thn__Departure_Date_Time__c=" + date.generateTodayDate2_plus(0, 3),
                "-u",
                ALIAS,
                "--json"});
        String quoteHotelRoomId = JsonParser2.getFieldValue(quoteHotelRoomResult.toString(), "id");
        StringBuilder myceQuoteRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__MYCE_Quote__c",
                "-w",
                "Id='" + myceQuoteID + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(myceQuoteRecord);
        StringBuilder quoteHotelRoomRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Quote_Hotel_Room__c",
                "-w",
                "Id='" + quoteHotelRoomId + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(quoteHotelRoomRecord);
        String arrivalDateQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Arrival_Date__c");
        String departureDateQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Departure_Date__c");
        String arrivalDateTimeQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Arrival_Date_Time__c");
        String departureDateTimeQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Departure_Date_Time__c");
        String arrivalDateMyceQuote = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Arrival_Date__c");
        String departureDateMyceQuote = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Departure_Date__c");

        Assert.assertNotEquals(arrivalDateQuoteHotelRoom, arrivalDateMyceQuote);
        Assert.assertNotEquals(departureDateQuoteHotelRoom, departureDateMyceQuote);
        Assert.assertEquals(arrivalDateQuoteHotelRoom, date.generateTodayDate2_plus(0, 2));
        Assert.assertEquals(departureDateQuoteHotelRoom, date.generateTodayDate2_plus(0, 3));
        Assert.assertTrue(arrivalDateTimeQuoteHotelRoom.contains(arrivalDateQuoteHotelRoom));
        Assert.assertTrue(departureDateTimeQuoteHotelRoom.contains(departureDateQuoteHotelRoom));
    }

    @Test(priority = 3, description = "THY-512 Quote hotel room - time fields")
    @Severity(SeverityLevel.NORMAL)
    @Description("THY-512 Quote hotel room - time fields")
    @Story("Quote hotel room’s arrival / departure datetimes are manually updated")
    public void quoteHotelRoom_timeFieldsTest3() throws InterruptedException, IOException {
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
        StringBuilder productRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Product__c",
                "-w",
                "Name='ROOM 1 NIGHT'",
                "-u",
                ALIAS,
                "--json"});
        String productID = JsonParser2.getFieldValue(productRecord.toString(), "Id");
        StringBuilder roomTypeRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Space_Area__c",
                "-w",
                "Name='Single'",
                "-u",
                ALIAS,
                "--json"});
        String roomTypeID = JsonParser2.getFieldValue(roomTypeRecord.toString(), "Id");
        StringBuilder myseQuoteResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__MYCE_Quote__c",
                "-v",
                "Name='QHR time test 3' thn__Pax__c=10 thn__Hotel__c='" + propertyID + "' thn__Arrival_Date__c=" +
                        date.generateTodayDate2() + " thn__Departure_Date__c=" + date.generateTodayDate2_plus(0, 5),
                "-u",
                ALIAS,
                "--json"});
        String myceQuoteID = JsonParser2.getFieldValue(myseQuoteResult.toString(), "id");
        StringBuilder quoteHotelRoomResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Quote_Hotel_Room__c",
                "-v",
                "thn__MYCE_Quote__c='" + myceQuoteID + "' thn__Product__c='" + productID + "'" +
                        " thn__Space_Area__c='" + roomTypeID + "' thn__Unit_Price__c=10 thn__Arrival_Date_Time__c="
                        + date.generateTodayDate2_plus(0, 2) + " thn__Departure_Date_Time__c=" + date.generateTodayDate2_plus(0, 3),
                "-u",
                ALIAS,
                "--json"});
        String quoteHotelRoomId = JsonParser2.getFieldValue(quoteHotelRoomResult.toString(), "id");
        StringBuilder result = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:update",
                "-s",
                "thn__Quote_Hotel_Room__c",
                "-w",
                "id='" + quoteHotelRoomId + "'",
                "-v",
                "thn__Arrival_Date_Time__c=" + date.generateTodayDate2_plus(0, 1) + " thn__Departure_Date_Time__c=" + date.generateTodayDate2_plus(0, 4),
                "-u",
                ALIAS,
                "--json"});
        System.out.println(result);
        StringBuilder myceQuoteRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__MYCE_Quote__c",
                "-w",
                "Id='" + myceQuoteID + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(myceQuoteRecord);
        StringBuilder quoteHotelRoomRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Quote_Hotel_Room__c",
                "-w",
                "Id='" + quoteHotelRoomId + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(quoteHotelRoomRecord);
        String arrivalDateQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Arrival_Date__c");
        String departureDateQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Departure_Date__c");
        String arrivalDateTimeQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Arrival_Date_Time__c");
        String departureDateTimeQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Departure_Date_Time__c");
        String arrivalDateMyceQuote = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Arrival_Date__c");
        String departureDateMyceQuote = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Departure_Date__c");

        Assert.assertNotEquals(arrivalDateQuoteHotelRoom, arrivalDateMyceQuote);
        Assert.assertNotEquals(departureDateQuoteHotelRoom, departureDateMyceQuote);
        Assert.assertEquals(arrivalDateQuoteHotelRoom, date.generateTodayDate2_plus(0, 1));
        Assert.assertEquals(departureDateQuoteHotelRoom, date.generateTodayDate2_plus(0, 4));
        Assert.assertTrue(arrivalDateTimeQuoteHotelRoom.contains(arrivalDateQuoteHotelRoom));
        Assert.assertTrue(departureDateTimeQuoteHotelRoom.contains(departureDateQuoteHotelRoom));
    }

    @Test(priority = 4, description = "THY-512 Quote hotel room - time fields")
    @Severity(SeverityLevel.NORMAL)
    @Description("THY-512 Quote hotel room - time fields")
    @Story("Quote hotel room is a part of the Quote package")
    public void quoteHotelRoom_timeFieldsTest4() throws InterruptedException, IOException {
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
        StringBuilder productRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Product__c",
                "-w",
                "Name='ROOM 1 NIGHT'",
                "-u",
                ALIAS,
                "--json"});
        String productID = JsonParser2.getFieldValue(productRecord.toString(), "Id");
        StringBuilder packageResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Package__c",
                "-v",
                "Name='Test QHR Date 1' thn__Hotel__c='" + propertyID + "'",
                "-u",
                ALIAS,
                "--json"});
        String packageId = JsonParser2.getFieldValue(packageResult.toString(), "id");
        StringBuilder packageLineResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Package_Line__c",
                "-v",
                "Name='Test QHR Date 1' thn__Package__c='" + packageId + "' thn__Type__c='Hotel Room'" +
                        " thn__Product__c='" + productID + "' thn__Start_Time__c=00:00 thn__End_Time__c=09:00" +
                        " thn__Unit_Price__c=10 thn__VAT_Category__c=1",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(packageLineResult);
        StringBuilder myseQuoteResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__MYCE_Quote__c",
                "-v",
                "Name='QHR time test 4' thn__Pax__c=10 thn__Hotel__c='" + propertyID + "' thn__Arrival_Date__c=" +
                        date.generateTodayDate2() + " thn__Departure_Date__c=" + date.generateTodayDate2_plus(0, 5),
                "-u",
                ALIAS,
                "--json"});
        String myceQuoteID = JsonParser2.getFieldValue(myseQuoteResult.toString(), "id");
        StringBuilder quotePackageResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Quote_Package__c",
                "-v",
                "thn__MYCE_Quote__c='" + myceQuoteID + "' thn__Package__c='" + packageId +
                        "' thn__Unit_Price__c=10 thn__Start_Date__c=" + date.generateTodayDate2_plus(0, 2) +
                        " thn__End_Date__c=" + date.generateTodayDate2_plus(0, 3),
                "-u",
                ALIAS,
                "--json"});
        String quotePackageId = JsonParser2.getFieldValue(quotePackageResult.toString(), "id");
        StringBuilder quoteHotelRoomRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Quote_Hotel_Room__c",
                "-w",
                "thn__MYCE_Quote__c='" + myceQuoteID + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(quoteHotelRoomRecord);
        StringBuilder quotePackageRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Quote_Package__c",
                "-w",
                "Id='" + quotePackageId + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(quotePackageRecord);
        StringBuilder myceQuoteRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__MYCE_Quote__c",
                "-w",
                "Id='" + myceQuoteID + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(myceQuoteRecord);
        String arrivalDateQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Arrival_Date__c");
        String departureDateQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Departure_Date__c");
        String arrivalDateTimeQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Arrival_Date_Time__c");
        String departureDateTimeQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Departure_Date_Time__c");
        String arrivalDateMyceQuote = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Arrival_Date__c");
        String departureDateMyceQuote = JsonParser2.getFieldValue(myceQuoteRecord.toString(), "thn__Departure_Date__c");
        String startDateQuotePackage = JsonParser2.getFieldValue(quotePackageRecord.toString(), "thn__Start_Date__c");
        String endDateQuotePackage = JsonParser2.getFieldValue(quotePackageRecord.toString(), "thn__End_Date__c");

        Assert.assertNotEquals(arrivalDateQuoteHotelRoom, arrivalDateMyceQuote);
        Assert.assertNotEquals(departureDateQuoteHotelRoom, departureDateMyceQuote);
        Assert.assertEquals(arrivalDateQuoteHotelRoom, startDateQuotePackage);
        Assert.assertEquals(departureDateQuoteHotelRoom, date.generateTodayDate2_plus(0, 4));
        Assert.assertTrue(arrivalDateTimeQuoteHotelRoom.contains(arrivalDateQuoteHotelRoom));
        Assert.assertTrue(departureDateTimeQuoteHotelRoom.contains(departureDateQuoteHotelRoom));
    }

    @Test(priority = 5, description = "THY-512 Quote hotel room - time fields")
    @Severity(SeverityLevel.NORMAL)
    @Description("THY-512 Quote hotel room - time fields")
    @Story("Start date and end date fields are updated on the Quote package")
    public void quoteHotelRoom_timeFieldsTest5() throws InterruptedException, IOException {
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
        StringBuilder productRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Product__c",
                "-w",
                "Name='ROOM 1 NIGHT'",
                "-u",
                ALIAS,
                "--json"});
        String productID = JsonParser2.getFieldValue(productRecord.toString(), "Id");
        StringBuilder packageResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Package__c",
                "-v",
                "Name='Test QHR Date 2' thn__Hotel__c='" + propertyID + "'",
                "-u",
                ALIAS,
                "--json"});
        String packageId = JsonParser2.getFieldValue(packageResult.toString(), "id");
        StringBuilder packageLineResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Package_Line__c",
                "-v",
                "Name='Test QHR Date 2' thn__Package__c='" + packageId + "' thn__Type__c='Hotel Room'" +
                        " thn__Product__c='" + productID + "' thn__Start_Time__c=00:00 thn__End_Time__c=09:00" +
                        " thn__Unit_Price__c=10 thn__VAT_Category__c=1",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(packageLineResult);
        StringBuilder myseQuoteResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__MYCE_Quote__c",
                "-v",
                "Name='QHR time test 5' thn__Pax__c=10 thn__Hotel__c='" + propertyID + "' thn__Arrival_Date__c=" +
                        date.generateTodayDate2() + " thn__Departure_Date__c=" + date.generateTodayDate2_plus(0, 5),
                "-u",
                ALIAS,
                "--json"});
        String myceQuoteID = JsonParser2.getFieldValue(myseQuoteResult.toString(), "id");
        StringBuilder quotePackageResult = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:create",
                "-s",
                "thn__Quote_Package__c",
                "-v",
                "thn__MYCE_Quote__c='" + myceQuoteID + "' thn__Package__c='" + packageId +
                        "' thn__Unit_Price__c=10 thn__Start_Date__c=" + date.generateTodayDate2_plus(0, 2) +
                        " thn__End_Date__c=" + date.generateTodayDate2_plus(0, 3),
                "-u",
                ALIAS,
                "--json"});
        String quotePackageId = JsonParser2.getFieldValue(quotePackageResult.toString(), "id");
        StringBuilder result = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:update",
                "-s",
                "thn__Quote_Package__c",
                "-w",
                "id='" + quotePackageId + "'",
                "-v",
                "thn__Start_Date__c=" + date.generateTodayDate2_plus(0, 1) + " thn__End_Date__c=" +
                        date.generateTodayDate2_plus(0, 4),
                "-u",
                ALIAS,
                "--json"});
        System.out.println(result);
        StringBuilder quoteHotelRoomRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Quote_Hotel_Room__c",
                "-w",
                "thn__MYCE_Quote__c='" + myceQuoteID + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(quoteHotelRoomRecord);
        StringBuilder quotePackageRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__Quote_Package__c",
                "-w",
                "Id='" + quotePackageId + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(quotePackageRecord);
        StringBuilder myceQuoteRecord = SfdxCommand.runLinuxCommand1(new String[]{"/home/minsk-sc/sfdx/bin/sfdx",
                "force:data:record:get",
                "-s",
                "thn__MYCE_Quote__c",
                "-w",
                "Id='" + myceQuoteID + "'",
                "-u",
                ALIAS,
                "--json"});
        System.out.println(myceQuoteRecord);
        String arrivalDateQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Arrival_Date__c");
        String departureDateQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Departure_Date__c");
        String arrivalDateTimeQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Arrival_Date_Time__c");
        String departureDateTimeQuoteHotelRoom = JsonParser2.getFieldValue(quoteHotelRoomRecord.toString(), "thn__Departure_Date_Time__c");
        String startDateQuotePackage = JsonParser2.getFieldValue(quotePackageRecord.toString(), "thn__Start_Date__c");
        String endDateQuotePackage = JsonParser2.getFieldValue(quotePackageRecord.toString(), "thn__End_Date__c");

        Assert.assertEquals(startDateQuotePackage, date.generateTodayDate2_plus(0, 1));
        Assert.assertEquals(endDateQuotePackage, date.generateTodayDate2_plus(0, 4));
        Assert.assertEquals(arrivalDateQuoteHotelRoom, startDateQuotePackage);
        Assert.assertEquals(departureDateQuoteHotelRoom, date.generateTodayDate2_plus(0, 5));
        Assert.assertTrue(arrivalDateTimeQuoteHotelRoom.contains(arrivalDateQuoteHotelRoom));
        Assert.assertTrue(departureDateTimeQuoteHotelRoom.contains(departureDateQuoteHotelRoom));
    }
}
