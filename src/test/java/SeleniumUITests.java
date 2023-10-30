import jdk.jfr.Description;
import org.example.TestMethods;
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
        //Clean collection and close browser after each test
        methods.deleteCollection();
        methods.logout();
    }

    @AfterAll
    static void afterAll(){
        methods.closeBrowser();
    }

    @Test
    @DisplayName("Empty table test")
    @Description("Login and check that book table is empty")
    void emptyBookTableTest() {
        //Check if table is empty upon login
        assert methods.checkCollectionSize(0);

    }

    @Test
    @DisplayName("Adding books to collection test")
    @Description("Open books page, add 6 book and check if they appear in the collection")
    void addBooksToCollectionTest(){
        //Go to books page
        methods.openPage("books");
        //Add 6 books to collection
        //To do this, we open book page and click
        //"Click to collection" button
        methods.addBooks(6);
        //Go back to profile and check collection size
        methods.openPage("profile");
        methods.changeProfileTableSize();
        assert methods.checkCollectionSize(6);
    }

    @Test
    @DisplayName("Deleting all books from collection test")
    @Description("Open books page, add 6 book and check if they appear in the collection")
    void clearBookCollectionTest(){
        //Go to books page
        methods.openPage("books");
        //Add two books to collection
        methods.addBooks(2);
        //Go back to profile page
        //and check that there are indeed two books
        methods.openPage("profile");
        assert methods.checkCollectionSize(2);
        //Then try "Delete all books" button
        methods.clearCollection();
        //Then check that collection is empty
        assert methods.checkCollectionSize(0);
    }
}
