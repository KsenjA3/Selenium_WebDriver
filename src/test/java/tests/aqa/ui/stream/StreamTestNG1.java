package tests.aqa.ui.stream;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.testng.annotations.*;
import tests.aqa.utils.ConfProperties;
import tests.aqa.ui.BaseTestNG;
import tests.aqa.ui.po.steam.StreamPage;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@Log4j2
public class StreamTestNG1 extends BaseTestNG {
    StreamPage streamPage;

    @BeforeMethod
    public void setStreamPage(){
        streamPage = new StreamPage(driver);
        log.info("StreamPage is created for" + driver);
    }

    @Test (description = "Verify title Stream Page About")
    void testStreamPageTitle() {
        streamPage.clickLButtonAbout();
        assertEquals("Steam — превосходная игровая Интернет-платформа",streamPage.getTitle());
    }

    @Test (description = "Verify URL Stream Page About")
    void testStreamPageURL() {
        streamPage.clickLButtonAbout();
        assertEquals("https://store.steampowered.com/about/",streamPage.getURL());
    }

    @Test (description = "Compare amount of gamers in Network and in Game")
    void testAmountOfGamersInNetworkAndGame() {
        streamPage.clickLButtonAbout();
        Integer amountInGame = streamPage.getAmountGamersInGame();
        Integer amountOnline = streamPage.getAmountGamersOnline();
        log.info("amount in Game is "+amountInGame+",  amount online is "+ amountOnline);

        assertTrue(amountInGame<amountOnline);
    }

    @Test (description = "Verify title and cost of the best game in selected country")
    void  getTitlesAndPriceForFirstTenGames() {
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
    }
}
