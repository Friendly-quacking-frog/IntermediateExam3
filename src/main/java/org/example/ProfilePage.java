package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;

    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Looking at table")
    public boolean checkTableSize(int size){
        //Checking how many entries there are in a table
        //by looking how many action-buttons there are
        //should be two for each entry
        return driver.findElements(By.className("action-buttons")).size() == size*2;
    }
}
