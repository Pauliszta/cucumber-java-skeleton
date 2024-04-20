package junit;

import io.cucumber.skeleton.LoginPage;
import io.cucumber.skeleton.VerificationPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;

    VerificationPage verificationPage;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/pauladabrowska/Documents/kurs_tester/automation_tester/Webdrivers/chromedriver");

        driver = new ChromeDriver();
        wait =  new WebDriverWait(driver, 3);
        driver.manage().window().maximize();


        loginPage = new LoginPage(driver);
        verificationPage = new VerificationPage(driver, wait);
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void correctPasswordTest() {

//        driver.get("https://the-internet.herokuapp.com/login");
//          zamiast powyższego wpisujemy poniższe
        loginPage.goToLoginPage();

//        WebElement element = driver.findElement(By.id("username"));
//        element.sendKeys("tomsmith");
        loginPage.typeLogin("tomsmith");

//        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        loginPage.typePassword("SuperSecretPassword!");

//        WebElement element2 = driver.findElement(By.cssSelector("button[type=submit]"));
//        element2.click();
        loginPage.clickLoginButton();

//        verificationPage = new VerificationPage();
//        można tutaj wywołać obiekt nowej page, ale trzeba pamiętać, żeby w każdym teście był wywołany
//        co jest uciążliwe na dłuższą metę

//        wait.until(ExpectedConditions.urlContains("/secure"));
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/logout']")));
//        String expectedResult = "You logged into a secure area!";
//        verifyText(expectedResult);

        verificationPage.userShouldBeLoggedIn();

    }

//    metoda przerzucona do wykorzystywanej Page
//    private void verifyText(String expectedResult) {
//        WebElement element = driver.findElement(By.cssSelector("div#flash"));
//        String currentResult = element.getText();
//        Assert.assertTrue(currentResult.contains(expectedResult));
//    }

    @Test
    public void incorrectPasswordTest() {

//        driver.get("https://the-internet.herokuapp.com/login");


//        WebElement element = driver.findElement(By.id("username"));
//        element.sendKeys("tomsmith");
//
//        driver.findElement(By.id("password")).sendKeys("incorrectPassword");
//
//        WebElement element2 = driver.findElement(By.cssSelector("button[type=submit]"));
//        element2.click();

//        String expectedResult = "Your password is invalid!";
//        verifyText(expectedResult);

        loginPage.goToLoginPage();
        loginPage.typeLogin("tomsmith");
        loginPage.typePassword("incorrectPassword");
        loginPage.clickLoginButton();
        verificationPage.userShouldNotBeLoggedIn();

    }
}
