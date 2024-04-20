package io.cucumber.skeleton;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToLoginPage() {
//        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");

    }

    public void typeLogin(String login) {
        WebElement element = driver.findElement(By.id("username"));
        element.sendKeys(login);
    }
    public void typePassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }
    public void clickLoginButton() {
        WebElement element2 = driver.findElement(By.cssSelector("button[type=submit]"));
        element2.click();
    }
}
