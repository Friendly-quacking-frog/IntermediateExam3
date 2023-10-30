package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    String dropDownSelector = "#app > div > div > div.row > div.col-12.mt-4.col-md-6 > div.profile-wrapper > div.ReactTable.-striped.-highlight > div.pagination-bottom > div > div.-center > span.select-wrap.-pageSizeOptions > select";
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProfilePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @Step("Search table for {0} books")
    public boolean checkTableEntries(int size){
        //Checking how many entries there are in a table
        //by looking how many action-buttons there are
        //should be two for each entry
        return driver.findElements(By.className("action-buttons")).size() == size*2;
    }

    @Step("Press \"Delete all books\" button")
    public ProfilePage clickDelete(){
        //Click "Delete all books" button
        driver.findElements(By.className("btn-primary")).get(3).click();
        return this;
    }

    @Step("Press delete on first book")
    public ProfilePage deleteFirst(){
        //Delete first book in collection
        driver.findElements(By.id("delete-record-undefined")).get(0).click();
        try {
            Thread.sleep(500);
        } catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    @Step("Confirm action in the pop-up")
    public ProfilePage confirmDelete(){
        //Confirm action in the pop-up
        driver.findElements(By.className("btn-primary")).get(5).click();
        return this;
    }

    @Step("Accept alert window")
    public void acceptAlert(){
        //Close alert window that appears
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Step("Change table size to {0} rows")
    public void selectSize(int value){
        //change table size
        Select select = new Select(driver.findElement(By.cssSelector(dropDownSelector)));
        select.selectByValue(Integer.toString(value));
    }

    @Step("Logout")
    public void logout(){
        //Log out of profile
        driver.findElements(By.id("submit")).get(0).click();
    }
}
