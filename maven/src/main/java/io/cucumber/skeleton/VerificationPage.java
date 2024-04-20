package io.cucumber.skeleton;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerificationPage {

    WebDriver driver;
    WebDriverWait wait;

    public VerificationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void  userShouldBeLoggedIn() {
        wait.until(ExpectedConditions.urlContains("/secure"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/logout']")));
        String expectedResult = "You logged into a secure area!";

        verifyText(expectedResult);
    }

    public void userShouldNotBeLoggedIn() {
        String expectedResult = "Your password is invalid!";
        verifyText(expectedResult);
    }

    private void verifyText(String expectedResult) {
        WebElement element = driver.findElement(By.cssSelector("div#flash"));
        String currentResult = element.getText();
        Assert.assertTrue(currentResult.contains(expectedResult));
    }

}
