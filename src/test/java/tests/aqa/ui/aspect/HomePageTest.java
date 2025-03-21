package tests.aqa.ui.aspect;

import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tests.aqa.ui.BaseTest;
import tests.aqa.ui.po.aspect.HomePageAspect;
import tests.aqa.ui.po.aspect.LoginPageAspect;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Log4j2
public class HomePageTest extends BaseTest {
    Set<HomePageAspect> homePages;

    @BeforeAll
    void openPage()  {
            homePages = new HashSet<>();
            driverSet.forEach(driver -> {
                new LoginPageAspect(driver).loginPage("xlyna@yandex.ru", "FVqHMbtBfnQk");
                homePages.add(new HomePageAspect(driver));
                log.info("The site home page " + driver.getCurrentUrl()+ " is opened");
            });
    }

    @Test
    void testHomePageListItemsCourseLeftMenu()  {
        homePages.forEach(homePage -> {
            ArrayList<String> arrExpected =new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/ui/aspect/list_items_course_left_menu.txt"))){
                while (br.ready()){ arrExpected.add(br.readLine()); }
            } catch (IOException e) { log.info(e); }
            log.info("Expected listItems_course_leftMenu is "+arrExpected);
            ArrayList<String> arrActual =homePage.listOfItemsCourseLeftMenu();
            log.info("Actual listItems_course_leftMenu is "+arrActual);
            assertEquals(arrExpected,arrActual);
        });
    }
}
