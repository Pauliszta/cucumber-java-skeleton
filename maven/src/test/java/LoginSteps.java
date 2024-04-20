import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void iOpenTheBrowser() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/pauladabrowska/Documents/kurs_tester/automation_tester/Webdrivers/chromedriver");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
//        driver.manage(). - cookies - początek do zarządzania ciasteczkami
//        driver.navigate(). - do zarządzania rzeczami w przeglądarce - back itp
//        maksymalizacja okienka
        driver.manage().window().maximize();
    }

    @After
    public void closeBrowser() {
//        driver.close(); zamyka tylko jedna przegladarke, a testy dalej trwaja
        driver.quit();
//        zamyka wszystkie przegladarki i całego procesu - widac ze dziala to w managerze zadań
    }

    @And("I am on website")
    public void iAmOnWebsite() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("I type login {string}")
    public void iTypeLogin(String login) {
        WebElement element = driver.findElement(By.cssSelector("input[id=username]"));
//        skrócony zapis, ale tylko dla id
//        WebElement element = driver.findElement(By.id("username"));
        element.sendKeys(login);
    }

    @And("I type password {string}")
    public void iTypePassword(String password) {
        WebElement element = driver.findElement(By.cssSelector("input[id=password]"));
        element.sendKeys(password);
    }

    @And("I click login button")
    public void iClickLoginButton() {
        WebElement element = driver.findElement(By.cssSelector("button[type=submit]"));
        element.click();
    }

    @Then("I am logged in")
    public void iAmLoginIn() {
        wait.until(ExpectedConditions.urlContains("secure"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/logout']")));
        String expectedResult = "You logged into a secure area!";
        WebElement element = driver.findElement(By.cssSelector("div#flash"));
//        lub WebElement element = driver.findElement(By.cssSelector("div[id=flash]"));
//        lub WebElement element = driver.findElement(By.id("flash"));
        String currentResult = element.getText();
        Assert.assertTrue(currentResult.contains(expectedResult));
//        wyciągnięcie koloru tła komunikatu
//        String backgroundColor = element.getCssValue("background-color");
//        System.out.println(backgroundColor);
    }

    @Then("I am not logged in")
    public void iAmNotLoginIn() {
        String expectedResult = "Your password is invalid!";
        WebElement element = driver.findElement(By.cssSelector("div#flash"));
        String currentResult = element.getText();
        Assert.assertTrue(currentResult.contains(expectedResult));
//        zamiast powyższych 4 linijek mogę
//        checkText(expectedResult);

        String loginResult = "You logged into a secure area!";
        Assert.assertFalse(currentResult.contains(loginResult));

        String backgroundColor = element.getCssValue("background-color");
        System.out.println(backgroundColor);
        String expectedColor = "rgba(198, 15, 19, 1)";
        Assert.assertEquals(expectedColor, backgroundColor);
        String unexpectedColor = "rgba(93, 164, 35, 1)";
        Assert.assertNotEquals(unexpectedColor, backgroundColor);

    }

    @When("I type {string} as login and {string} as password")
    public void iTypeAsLoginAndAsPassword(String login, String password) {
//        WebElement element = driver.findElement(By.cssSelector("input[id=username]"));
//        element.sendKeys(login);
//        WebElement element1 = driver.findElement(By.cssSelector("input[id=password]"));
//        element1.sendKeys(password);
//        WebElement element3 = driver.findElement(By.cssSelector("button[type=submit]"));
//        element3.click();
//        zamiast powyższego można wywołać całe metody
        iTypeLogin(login);
        iTypePassword(password);
        iClickLoginButton();

    }

//    private void checkText(String expectedResult) {
//        WebElement element = driver.findElement(By.cssSelector("div#flash"));
//        String currentResult = element.getText();
//        Assert.assertTrue(currentResult.contains(expectedResult));
//    }
}


