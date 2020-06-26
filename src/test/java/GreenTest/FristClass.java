package GreenTest;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;



import java.util.concurrent.TimeUnit;


public class FristClass {


    private String urlhome = "https://gdcloud.ru/release-17/auth/login";
    private ChromeDriver driver;
    private SignInPage page;

    @BeforeSuite
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    void PageLoadWithoutCookies1()
    {
        driver.get(urlhome);
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("WorkFlow"));
        page = new SignInPage(driver);
        Assert.assertTrue(page.usvalue(page.username,"value").equals(""));
    }

    @Test (priority = 2)
    public void ClickButtonWithoudData2()
    {
        page = new SignInPage(driver);
        page.clickButton1();
        Assert.assertTrue(page.usvalue(page.username,"required").contains("true"));
    }

    @Test (priority = 3)
    public void TypeOnlyLogin3()
    {
        page = new SignInPage(driver);
        page.typeUsername("tester");
        page.clickButton1();
        Assert.assertTrue(page.usvalue(page.password,"required").contains("true"));
    }

    @Test (priority = 4)
    public void TypeOnlyPassword4()
    {
        page = new SignInPage(driver);
        page.Clear1Field(page.username);
        page.typePassword("tester");
        page.clickButton1();
        Assert.assertTrue(page.usvalue(page.username,"required").contains("true"));
    }

    @Test (priority = 5)
    public void InCorrectCredentials5()
    {
        page = new SignInPage(driver);
        page.LoginAs("tester","123");
        Assert.assertTrue(page.enable(page.error),"true");
    }

    @Test (priority = 6)
    public void CorrectCredentials6()
    {
        page = new SignInPage(driver);
        page.ClearFields();
        page.LoginAs("tester","K35G3U");
        Assert.assertTrue(page.usvalue(page.username2,"textContent").contains("Catswill J. J. (Jinior QA. Dpt)"));
    }

    @Test(priority = 7)
    void PageLoadWithCookies7()
    {
        driver.get(urlhome);
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("WorkFlow"));
        Assert.assertTrue(page.usvalue(page.username,"value").equals(""));
    }

    @Test(priority = 8)
    void SignInCurrentButton8()
    {
        page.currentloginBtn();
        Assert.assertTrue(page.enable(page.error),"true");

    }
    @Test(priority = 9)
    void SignInAnotherButton9()
    {
        page.anotherloginBtn();
        Assert.assertTrue(page.enable(page.error),"true");
    }
    @Test(priority = 10)
    void RememberButton10()
    {
        page = new SignInPage(driver);
        page.checkBtn();
        page.LoginAs("tester","K35G3U");
        Assert.assertTrue(page.usvalue(page.rememberbtn,"checked").contains("true"));
        WebElement name = driver.findElement(By.cssSelector("#userName"));
        Assert.assertTrue(page.usvalue(page.username2,"textContent").contains("Catswill J. J. (Jinior QA. Dpt)"));
    }
    @Test(priority = 11)
    void FailTest()
    {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("TestTitle"));
    }

    @AfterClass
    public void Quit()
    {
        driver.quit();
    }
}
