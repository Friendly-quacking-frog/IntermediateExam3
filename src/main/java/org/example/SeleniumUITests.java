package org.example;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;

public class SeleniumUITests {

    static TestMethods methods;

    @BeforeAll
    static void beforeAll(){
        //Initialize method class
        methods = new TestMethods();
    }

    @BeforeEach
    void beforeEach(){
        //Authorize in the beginning of each test
        methods.authorize();
    }

    @AfterEach
    void afterEach(){
        //Closing browser after each test
        methods.closeBrowser();
    }

    @Test
    @Description("Login and check that book table is empty")
    void emptyBookTableTest() {
        //Check if table is empty upon login
        assert methods.isBookTableEmpty();
    }
}
