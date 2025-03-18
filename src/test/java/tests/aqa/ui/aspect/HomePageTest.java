package tests.aqa.ui.aspect;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.aqa.ui.BaseTest;
import tests.aqa.ui.po.aspect.HomePage;
import tests.aqa.ui.po.aspect.LoginPage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Log4j2
public class HomePageTest extends BaseTest {
    Set<HomePage> homePages;

    @BeforeAll
    void openPage()  {
        homePages = new HashSet<>();
        driverSet.forEach(driver -> {
            new LoginPage(driver).loginPage("xlyna@yandex.ru", "FVqHMbtBfnQk");
            homePages.add(new HomePage(driver));
            log.info("The site home page " + driver.getCurrentUrl()+ " is opened");
        });
    }

    @Test
    void testHomePageListItemsCourseLeftMenu() throws IOException {
        homePages.forEach(homePage -> {
            ArrayList<String> arrExpected =new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/ui/list_items_course_left_menu.txt"))){
                while (br.ready()){ arrExpected.add(br.readLine()); }
            } catch (IOException e) { log.info(e); }
            log.info("Expected listItems_course_leftMenu is "+arrExpected);
            ArrayList<String> arrActual =homePage.listOfItemsCourseLeftMenu();
            log.info("Actual listItems_course_leftMenu is "+arrActual);
            assertEquals(arrExpected,arrActual);
        });
    }
}
