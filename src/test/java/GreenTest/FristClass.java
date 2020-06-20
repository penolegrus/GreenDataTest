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
        driver.get("https://gdcloud.ru/release-17/auth/login");
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("WorkFlow"));
        Assert.assertTrue(driver.findElementByCssSelector("#username").getAttribute("value").equals(""));
    }

    @Test (priority = 2)
    public void ClickButtonWithoudData2()
    {
        page = new SignInPage(driver);
        page.clickButton1();
        Assert.assertTrue(page.username.findElement(driver).getAttribute("required").contains("true"));
    }

    @Test (priority = 3)
    public void TypeOnlyLogin3()
    {
        page = new SignInPage(driver);
        page.typeUsername("tester");
        page.clickButton1();
        Assert.assertTrue(page.password.findElement(driver).getAttribute("required").contains("true"));
    }

    @Test (priority = 4)
    public void TypeOnlyPassword4()
    {
        page = new SignInPage(driver);
        page.username.findElement(driver).clear();
        page.typePassword("tester");
        page.clickButton1();
        Assert.assertTrue(page.username.findElement(driver).getAttribute("required").contains("true"));
    }

    @Test (priority = 5)
    public void InCorrectCredentials5()
    {
        page = new SignInPage(driver);
        page.typeUsername("tester");
        page.typePassword("123");
        page.clickButton1();
        Assert.assertTrue(page.error.findElement(driver).isEnabled());
    }

    @Test (priority = 6)
    public void CorrectCredentials6()
    {
        page = new SignInPage(driver);
        page.username.findElement(driver).clear();
        page.password.findElement(driver).clear();
        page.typeUsername("tester");
        page.typePassword("K35G3U");
        driver.findElementByXPath("//*[@id=\"login_button\"]").click();
        WebElement name = driver.findElement(By.cssSelector("#userName"));
        Assert.assertTrue(name.getAttribute("textContent").contains("Catswill J. J. (Jinior QA. Dpt)"));
    }

    @Test(priority = 7)
    void PageLoadWithCookies7()
    {
        driver.get("https://gdcloud.ru/release-17/auth/login");
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("WorkFlow"));
        Assert.assertTrue(driver.findElementByCssSelector("#username").getAttribute("value").equals(""));
    }

    @Test(priority = 8)
    void SignInCurrentButton8()
    {
        driver.findElementByCssSelector("#login_button_current").click();
       Assert.assertTrue(page.error.findElement(driver).isEnabled());

    }
    @Test(priority = 9)
    void SignInAnotherButton9()
    {
        driver.findElementByCssSelector("#login_button_domain").click();
        Assert.assertTrue(page.error.findElement(driver).isEnabled());
    }
    @Test(priority = 10)
    void RememberButton10()
    {
        page = new SignInPage(driver);
        page.typeUsername("tester");
        page.typePassword("K35G3U");
        page.checkBtn();
        page.clickButton1();
        Assert.assertTrue(page.rememberbtn.findElement(driver).getAttribute("checked").contains("true"));
        WebElement name = driver.findElement(By.cssSelector("#userName"));
        Assert.assertTrue(name.getAttribute("textContent").contains("Catswill J. J. (Jinior QA. Dpt)"));
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
