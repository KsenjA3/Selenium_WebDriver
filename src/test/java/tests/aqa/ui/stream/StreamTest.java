package tests.aqa.ui.stream;

import lombok.extern.log4j.Log4j2;
import tests.aqa.utils.ConfProperties;
import tests.aqa.ui.BaseTest;
import tests.aqa.ui.po.steam.StreamPage;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
@Log4j2
public class StreamTest extends BaseTest {
    private List<StreamPage> streamPages;

    @BeforeEach
    void openPage()  {
        streamPages = new ArrayList<>();
        driverSet.forEach(driver -> {
            StreamPage streamPage = new StreamPage(driver);
            streamPages.add(streamPage);
            log.info("The site stream page " + driver.getCurrentUrl()+ " is opened with driver " + driver);
        });
        log.info("The stream page " + streamPages);
    }

    @DisplayName("Verify title Stream Page About")
    @Test
    void testStreamPageTitle() {
        streamPages.forEach(streamPage -> {
            streamPage.clickLButtonAbout();
            assertEquals("Steam — превосходная игровая Интернет-платформа",streamPage.getTitle());
        });
    }

    @DisplayName("Verify URL Stream Page About")
    @Test
    void testStreamPageURL() {
        streamPages.forEach(streamPage -> {
            streamPage.clickLButtonAbout();
            assertEquals("https://store.steampowered.com/about/",streamPage.getURL());
        });
    }

    @DisplayName("Compare amount of gamers in Network and in Game")
    @Test
    void testAmountOfGamersInNetworkAndGame() {
        streamPages.forEach(streamPage -> {
            streamPage.clickLButtonAbout();
            Integer amountInGame = streamPage.getAmountGamersInGame();
            Integer amountOnline = streamPage.getAmountGamersOnline();
            log.info("amount in Game is "+amountInGame+",  amount online is "+ amountOnline);
            assertTrue(amountInGame < amountOnline,
                    "Amount of gamers in game should be less than online amount. Actual values - In Game: " +
                            amountInGame + ", Online: " + amountOnline);
        });
    }

    @DisplayName("Verify title and cost of the best game in selected country")
    @Test
    void  getTitlesAndPriceForFirstTenGames() {
        streamPages.forEach(streamPage -> {
            int position = Integer.parseInt(ConfProperties.getProperty("stream_position"));
            streamPage.moveToPageOfSelectedCountryTopSellers(ConfProperties.getProperty("stream_country"));
            ArrayList<String> arrNameOfBestTenGames = streamPage.getListOfNameTheBestGames();
//            arrNameOfBestTenGames.forEach(System.out::println);
            ArrayList<Double> arrCostOfBestTenGames = streamPage.getListOfCostTheBestGames();
//            arrCostOfBestTenGames.forEach(System.out::println);
            streamPage.moveToPageOfTheBestGame(position);

            assertEquals(arrNameOfBestTenGames.get(position-1),streamPage.getNameGameOnItsOwnPage());
            assertEquals(arrCostOfBestTenGames.get(position-1),streamPage.getCostGameOnItsOwnPage());
            streamPage.getInfoAboutGames();
        });

    }
}
