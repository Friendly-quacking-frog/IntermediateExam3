package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class TestMethods {
    WebDriver driver;
    String url, login, password;


    TestMethods(){
        //Set up driver
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        //Read properties
        Properties prop = new Properties();
        String fileName = "src/data.conf";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
            url = prop.getProperty("URL");
            login = prop.getProperty("LOGIN");
            password = prop.getProperty("PASSWORD");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeBrowser(){
        driver.close();
    }


    @Step("Logging in")
    public void authorize(){
        LoginPage loginPage = new LoginPage(driver);
        driver.get(url+"login");
        loginPage.enterLogin(login).enterPassword(password).submitLogin();
    }

    @Step("Checking if table is empty")
    public boolean isBookTableEmpty(){
        ProfilePage profilePage = new ProfilePage(driver);
        return profilePage.checkTableSize(0);
    }

}
