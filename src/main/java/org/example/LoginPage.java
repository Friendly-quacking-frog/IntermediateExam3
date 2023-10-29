package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Entering username")
    public LoginPage enterLogin(String login){
        driver.findElement(By.id("userName")).sendKeys(login);
        return this;
    }

    @Step("Entering password")
    public LoginPage enterPassword(String password){
        driver.findElement(By.id("password")).sendKeys(password);
        return this;
    }

    @Step("Submitting password")
    public void submitLogin(){
        driver.findElement(By.id("login")).click();
    }
}
