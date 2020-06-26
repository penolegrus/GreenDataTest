package GreenTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;



import java.util.concurrent.TimeUnit;


public class FristClass {

    public ITestResult testResult;

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

    @Test(priority = 1, description = "Загрузка страницы с чистыми куками")
    void PageLoadWithoutCookies1()
    {
        driver.get(urlhome);
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("WorkFlow"));
        page = new SignInPage(driver);
        Assert.assertTrue(page.usvalue(page.username,"value").equals(""));
        System.out.println("Страница загружена, поле для ввода логина пустое");
    }

    @Test (priority = 2, description = "Попытка входа без ввода логина и пароля")
    public void ClickButtonWithoudData2()
    {
        page = new SignInPage(driver);
        page.clickButton1();
        Assert.assertTrue(page.usvalue(page.username,"required").contains("true"));
    }

    @Test (priority = 3, description = "Попытка войти, введя только логин")
    public void TypeOnlyLogin3()
    {
        page = new SignInPage(driver);
        page.typeUsername("tester");
        page.clickButton1();
        Assert.assertTrue(page.usvalue(page.password,"required").contains("true"));
    }

    @Test (priority = 4, description = "Попытка войти, введя только пароль")
    public void TypeOnlyPassword4()
    {
        page = new SignInPage(driver);
        page.Clear1Field(page.username);
        page.typePassword("tester");
        page.clickButton1();
        Assert.assertTrue(page.usvalue(page.username,"required").contains("true"));
    }

    @Test (priority = 5, description = "Вход с использованием некорректных данных")
    public void InCorrectCredentials5()
    {
        page = new SignInPage(driver);
        page.LoginAs("tester","123");
        Assert.assertTrue(page.enable(page.error),"true");
    }

    @Test (priority = 6, description = "Вход с использованием корректных данных")
    public void CorrectCredentials6()
    {
        page = new SignInPage(driver);
        page.ClearFields();
        page.LoginAs("tester","K35G3U");
        Assert.assertTrue(page.usvalue(page.username2,"textContent").contains("Catswill J. J. (Jinior QA. Dpt)"));
    }

    @Test(priority = 7, description = "Загрузка страницы с существующими куками")
    void PageLoadWithCookies7()
    {
        driver.get(urlhome);
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("WorkFlow"));
        Assert.assertTrue(page.usvalue(page.username,"value").equals(""));
    }

    @Test(priority = 8, description = "Нажать кнопку Текущая учетная запись")
    void SignInCurrentButton8()
    {
        page.currentloginBtn();
        Assert.assertTrue(page.enable(page.error),"true");

    }
    @Test(priority = 9, description = "Нажать кнопку Другая учетная запись")
    void SignInAnotherButton9()
    {
        page.anotherloginBtn();
        Assert.assertTrue(page.enable(page.error),"true");
    }
    @Test(priority = 10, description = "Вход с галочкой для запоминания логина и пароля")
    void RememberButton10()
    {
        page = new SignInPage(driver);
        page.checkBtn();
        page.LoginAs("tester","K35G3U");
        Assert.assertTrue(page.usvalue(page.rememberbtn,"checked").contains("true"));
        Assert.assertTrue(page.usvalue(page.username2,"textContent").contains("Catswill J. J. (Jinior QA. Dpt)"));
    }
    @Test(priority = 11, description = "Ввод логина и пароля на русском")
    void RussianLetters11()
    {
        driver.get(urlhome);
        page = new SignInPage(driver);
        page.LoginAs("тестер","пароль");
        Assert.assertTrue(page.enable(page.error),"true");
    }

    @Test(priority = 12, description = "Ввод пароля и логина с использованием пробелов")
    void LoginSpace12()
    {
        page = new SignInPage(driver);
        page.ClearFields();
        page.LoginAs(" tester ","K35G3U");
        Assert.assertTrue(page.usvalue(page.username2,"textContent").contains("Catswill J. J. (Jinior QA. Dpt)"));

    }

    @Test(priority = 13, description = "Попытка ввести неполный адрес почты без .ru/.com")
    void EmailTest13()
    {
        driver.get(urlhome);
        page = new SignInPage(driver);
        page.ClearFields();
        page.LoginAs("test@yanex","K35G3U");
        Assert.assertTrue(page.enable(page.error),"true");

    }

    @Test(priority = 14, description = "Поставить невидимый символ после логина (Alt+Num255)")
    void InvisibleSpace14()
    {
        page = new SignInPage(driver);
        page.ClearFields();
        page.LoginAs("tester ","K35G3U");
        Assert.assertTrue(page.enable(page.error),"true");

    }
    @Test(priority = 15, description = "Ввести длинный логин")
    void TooManyChars15()
    {
        page = new SignInPage(driver);
        page.ClearFields();
        page.LoginAs("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz","K35G3U");
        Assert.assertTrue(page.enable(page.error),"true");
    }


    @Test(priority = 17, description = "Проверка неудачного теста")
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
