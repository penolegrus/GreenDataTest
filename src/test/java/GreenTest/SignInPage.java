package GreenTest;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class SignInPage {

    private ChromeDriver driver;

    public SignInPage(ChromeDriver driver) { this.driver = driver; }

    public By username = cssSelector("#username");
    public By password = cssSelector("#password");
    public By button1 = cssSelector("#login_button");
    public By error = cssSelector("#error");
    public By rememberbtn = cssSelector("#remember");

    public SignInPage typeUsername(String user){
        //Находим поле и вводим в него текст
        driver.findElement(username).sendKeys(user);
        return this;
    }
    public SignInPage checkBtn(){
        driver.findElement(rememberbtn).click();
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

}
