package org.example;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class TestMethods {
    WebDriver driver;
    String url, login, password;

    ProfilePage profilePage;
    LoginPage loginPage;
    BooksPage booksPage;


    public TestMethods(){
        //Set up driver and options
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("start-maximized");
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        //Read properties
        Properties prop = new Properties();
        String fileName = "src/data.conf";
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        booksPage = new BooksPage(driver);
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
        try {
            Thread.sleep(500);
            driver.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void logout(){ profilePage.logout();}

    @Step("Go to {0} page")
    public void openPage(String page){
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.contains(page))
            driver.get(url+page);
    }

    @Step("Logging in")
    public void authorize(){
        driver.get(url+"login");
        driver.manage().window().maximize();
        try {
            Thread.sleep(500);
            loginPage.enterLogin(login).enterPassword(password).submitLogin();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Step("Confirm there are {0} entries in collection")
    @Attachment(value = "Page screenshot", type = "image/png")
    public boolean checkCollectionSize(int size){
        //Check size of collection and attach a screenshot
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return profilePage.checkTableEntries(size);
    }

    @Step("Go to profile page and clear collection")
    public void clearCollection(){
        //Main differenece from deleteCollection
        //Delete all books from collection by pressing
        //appropriate button
        openPage("profile");
        profilePage.clickDelete().confirmDelete().acceptAlert();
    }

    @Step("Clear collection one book at a time")
    public void deleteCollection(){
        //Main difference from clearCollection
        //Delete books by pressing delete button in table
        openPage("profile");
        while (profilePage.checkTableEntries(0)) {
            profilePage.deleteFirst().confirmDelete().acceptAlert();
        }
    }

    @Step("Change table size in profile tab")
    public void changeProfileTableSize(){
        profilePage.selectSize(10);
    }

    @Step("Add {0} books")
    public void addBooks(int amount){
        for (int i=0; i<amount; i++){
            booksPage.openBook(i);
            booksPage.clickAddButon().acceptAlert();
            booksPage.backToStore();
        }
    }
}
