package GreenTest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.By.cssSelector;
public class SignInPage {

    public ChromeDriver driver;

    public SignInPage(ChromeDriver driver) { this.driver = driver; }
    public By username2 = cssSelector("#userName");
    public By username = cssSelector("#username");
    public By password = cssSelector("#password");
    public By button1 = cssSelector("#login_button");
    public By error = cssSelector("#error");
    public By rememberbtn = cssSelector("#remember");
    public By anotherlogin = cssSelector("#login_button_domain");
    public By currentloginbtn = cssSelector("#login_button_current");

    public SignInPage typeUsername(String user){
        //Находим поле и вводим в него текст
        driver.findElement(username).sendKeys(user);
        return this;
    }

    public SignInPage checkBtn(){
        driver.findElement(rememberbtn).click();
        return this;
    }
    public SignInPage anotherloginBtn(){
        driver.findElement(anotherlogin).click();
        return this;
    }
    public SignInPage currentloginBtn(){
        driver.findElement(currentloginbtn).click();
        return this;
    }

    public SignInPage typePassword(String pass){
        driver.findElement(password).sendKeys(pass);
        return this;
    }
    public SignInPage clickButton1()
    {
        driver.findElement(button1).click();
        return this;
    }
    //получить атрибут элемента
    public String usvalue(By web, String atrib){
        String s =driver.findElement(web).getAttribute(atrib);
        return s;
    }
    //проверить существует ли элемент
    public boolean enable(By web){
        boolean s = driver.findElement(web).isEnabled();
        return s;
    }

    //вписать логин и пароль и нажать кнопку входа
    public SignInPage LoginAs(String user, String pass){
        typeUsername(user);
        typePassword(pass);
        clickButton1();
        return this;
    }
    //очистить поля
    public SignInPage ClearFields(){
        driver.findElement(username).clear();
        driver.findElement(password).clear();
        return this;
    }
    //очистить 1 поле
    public SignInPage Clear1Field(By web){
        driver.findElement(web).clear();
        return this;
    }


}
