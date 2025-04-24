package tests.aqa.ui.sausedemo;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.aqa.utils.ConfProperties;
import tests.aqa.ui.BaseTest;
import tests.aqa.ui.po.saucedemo.HomePageSaucedemo;
import tests.aqa.ui.po.saucedemo.LoginPageSaucedemo;
import tests.aqa.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Tag("UI")
@Log4j2
public class SausedemoTest extends BaseTest {
    List<HomePageSaucedemo> homePage;

    @BeforeAll
    void openPage()  {
        homePage = new ArrayList<>();
        driverSet.forEach(driver -> {
            new LoginPageSaucedemo(driver).login();
            homePage.add(new HomePageSaucedemo(driver));
            log.info("The site home page " + driver.getCurrentUrl()+ " is opened");
        });
    }

    @DisplayName("Verify login to saucedemo.")
    @Test
    void testLogin() {
        homePage.forEach(homePage -> {
            assertEquals(ConfProperties.getProperty("saucedemo_home_page"), driver.getCurrentUrl());
        });
    }

    @DisplayName("Verify correct Sorting By Price From Low To High.")
    @Test
    void testSortPricesByPriceFromLowToHigh() {
        homePage.forEach(homePage -> {
            homePage.sortByPriceFromLowToHigh();
            List<Double> prices = homePage.getPrices();
            assertTrue(Utils.isSorted(prices), "Sort price is not sorted - " + prices);
        });
    }
}
