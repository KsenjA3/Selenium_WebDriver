package tests.aqa.ui;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.aqa.BaseTest;
import tests.aqa.ConfProperties;
import tests.aqa.ui.po.HomePage;
import tests.aqa.ui.po.LoginPage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Log4j2
public class HomePageTest extends BaseTest {
    HomePage homePage;

    @BeforeAll
    static void loginToHomePage()  {
        driver.get(ConfProperties.getProperty("log_page"));
        new LoginPage(driver).loginPage("xlyna@yandex.ru", "FVqHMbtBfnQk");
    }

    @BeforeEach
    void openPage()  {
        driver.get(ConfProperties.getProperty("log_page"));
        homePage=new HomePage(driver);
        log.info("The site home page " + driver.getCurrentUrl()+ " is opened");
    }


//    @ParameterizedTest
//    @CsvFileSource(resources = "list.csv")


    @Test
    void testHomePage_listItems_course_leftMenu() throws IOException {
        ArrayList<String> arrExpected =new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/ui/list_items_course_left_menu.txt"));
        while (br.ready()){
            arrExpected.add(br.readLine());
        }
        log.info("Expected listItems_course_leftMenu is "+arrExpected);
        ArrayList<String> arrActual =homePage.listOfItems_course_leftMenu();
        log.info("Actual listItems_course_leftMenu is "+arrActual);
        assertEquals(arrExpected,arrActual);
    }


}
