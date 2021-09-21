package tests;

import dates.Dates;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pageObject.*;
import pages.HomePageForPackageOrg;
import pages.HomePageForScratchOrg;
import pages.LoginPageForPackageOrg;
import pages.LoginPageForScratchOrg;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected Dates date;
    protected WebDriver driver;
    protected HomePageForPackageOrg homePage;
    protected HomePageForScratchOrg homePageForScratchOrg;
    protected LoginPageForPackageOrg loginPage;
    protected LoginPageForScratchOrg loginPageForScratchOrg;
    protected AccountPage account;
    protected AccountsPage accounts;
    protected DeveloperConsoleWindow developerConsoleWindow;
    protected CreditNoteLine creditNoteLine;
    protected MyceQuotes myceQuotes;
    protected Packages packages;
    protected PackageLine packageLine;
    protected QuoteHotelRoom quoteHotelRoom;
    protected QuoteMeetingRoom quoteMeetingRoom;
    protected QuoteMeetingPackages quoteMeetingPackages;
    protected QuoteProducts quoteProducts;
    protected Guests guests;
    protected Items items;
    protected Reservations reservations;
    protected ResourceGrouping resourceGrouping;
    protected SfdxCommand sfdxCommand;
    protected JsonParser jsonParser;
    protected JsonParser2 jsonParser2;
    protected Requests requests;
    protected ConvertWindow convertWindow;
    protected MultiEditProducts multiEditProducts;

    public WebDriver getDriver() {
        return driver;
    }

    public static final String testDataExcelFileName = "testdata.xlsx";

    final protected String SFDX = "/home/minsk-sc/sfdx/bin/sfdx";
    final protected String key = "3MVG9KlmwBKoC7U1K.F4bMX_vdidE2jS4dzw0IVO5Qc.gxBNMN7jTMYSIziN3N6wxsoF9sxPmgmBcRHxtzt6h";//3MVG95AcBeaB55lWwG_jM4S.hHHrR_i9uJUnD_XBhRAqn3B6BZxjS82q3A2.o16sDpIqZIOL2lpOxXysstk8R
    final protected String ALIAS = "test-fzyag1rn0itm@example.com"; //test-lbafgbxunsgq@example.com 9EU3j&PDvJ@1l
    final protected String thynkPackDevOrg = "https://thynkpack-dev-ed.my.salesforce.com/";
    final protected String thynkPackUserName = "rostislav.orel@succraft.com";
    final protected String thynkPackKey = "3MVG91BJr_0ZDQ4ta_ZwN1EEnfj.OQSJWOBWMPFXclJ22A8oaKqM9KTLdsoSupXX0idQnMIdsI3IweGbsJx6t";
    final protected String thynkPackPassword = "Rost1508";
    final protected String THY589UserName = "test-n7jjlpiaq9kj@example.com";
    final protected String THY589Password = "p*np5rhMajgeb";
    final protected String THY589Key = "3MVG95AcBeaB55lVSyX0xnbzXm5b6aiaDLu8j_2HTcL9YWLCYX.qiyxDynWwnc7Dw.EeL8E30Oru6L85llCiv";
    public String SCRATCHORGUSERNAME = "test-wixjue9le3v0@example.com";
    public String SCRATCHORGPASSWORD = "mEA3VqI_*OUSw";
    public String byPassId = "a0B1X000002HTPWUA4"; //a061j000003e6IcAAI


    @BeforeClass
    public void classLevelSetup(){
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-cache");
        options.addArguments("--disk-cache-size=1");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("user-data-dir=/tmp/temp_profile");
        options.addArguments(" --whitelisted-ips=\"\"");
        options.addArguments("--headless", "window-size=1920,1024", "--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void methodLevelSetup(){
        date = new Dates();
        homePage = new HomePageForPackageOrg();
        homePageForScratchOrg = new HomePageForScratchOrg(driver);
        loginPage = new LoginPageForPackageOrg();
        loginPageForScratchOrg = new LoginPageForScratchOrg();
        developerConsoleWindow = new DeveloperConsoleWindow(driver);
        account = new AccountPage(driver);
        accounts = new AccountsPage(driver);
        creditNoteLine = new CreditNoteLine(driver);
        myceQuotes = new MyceQuotes(driver);
        packages = new Packages(driver);
        packageLine = new PackageLine(driver);
        quoteHotelRoom = new QuoteHotelRoom(driver);
        quoteMeetingRoom = new QuoteMeetingRoom(driver);
        quoteMeetingPackages = new QuoteMeetingPackages(driver);
        quoteProducts = new QuoteProducts(driver);
        guests = new Guests(driver);
        items = new Items(driver);
        reservations = new Reservations(driver);
        resourceGrouping = new ResourceGrouping(driver);
        sfdxCommand = new SfdxCommand(driver);
        jsonParser2 = new JsonParser2(driver);
        requests = new Requests(driver);
        convertWindow = new ConvertWindow(driver);
        multiEditProducts = new MultiEditProducts(driver);
    }



    @AfterClass
    public void teardown(){
        driver.close();
        driver.quit();
        if (driver != null) {
            driver = null;
        }
   }



}
