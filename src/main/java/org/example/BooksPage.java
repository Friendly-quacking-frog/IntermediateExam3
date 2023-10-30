package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BooksPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BooksPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Open book number {0}")
    public void openBook(int number){
        //Open book by its position in table
        driver.findElements(By.className("mr-2")).get(number).click();
    }

    @Step("Click \"Add to your colllection\" button")
    public BooksPage clickAddButon(){
        //Press button to add book to collection
        WebElement addButton = driver.findElements(By.id("addNewRecordButton")).get(1);
        addButton.click();
        return this;
    }

    @Step("Accept alert window")
    public void acceptAlert(){
        //Close alert window that appears
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Step("Return to book store page")
    public void backToStore(){
        //Return to book store page
        driver.findElements(By.id("addNewRecordButton")).get(0).click();
        try{
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
