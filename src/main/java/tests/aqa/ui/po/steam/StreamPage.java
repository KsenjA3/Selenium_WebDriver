package tests.aqa.ui.po.steam;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.aqa.utils.ConfProperties;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static tests.aqa.ui.po.steam.StreamPageLocator.*;

@Log4j2
public class StreamPage {
    private WebDriver driver;

    public StreamPage(WebDriver driver){
        this.driver = driver;
        driver.get(ConfProperties.getProperty("stream_page"));
    }

    public void clickLButtonAbout(){
        log.info("click Button About");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath(BUTTON_ABOUT_LOCATOR.getLocator())));
        element.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public Integer getAmountGamersInGame(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return getNumberFromString(wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(NUMBER_IN_GAME_LOCATOR.getLocator()))).getText());
    }

    public Integer getAmountGamersOnline(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return getNumberFromString(wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(NUMBER_ONLINE_LOCATOR.getLocator()))).getText());
    }

    private  Integer getNumberFromString(String initString) {
        String numder = initString.substring(initString.indexOf('\n')+1);
        numder = numder.replace(",","");
        return Integer.parseInt(numder);
    }

    public void moveToPageOfSelectedCountryTopSellers(String country) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        /**
         * Focus on popup menu NEW_AND_INTERESTING
         */
        WebElement popupNewAndInteresting =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POPUP_NEW_AND_INTERESTING.getLocator())));
        popupNewAndInteresting.click();

        /**
         * Choose TOP_SELLERS in popup menu NEW_AND_INTERESTING
         */
        WebElement elementTopSellersInPopup =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TOP_SELLERS_IN_POPUP.getLocator())));
        elementTopSellersInPopup.click();
        log.info("TOP_SALLERS page is "+driver.getCurrentUrl());

        /**
         * Focus on popup menu of selection country
         */
        WebElement popupSelectionCountry =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(POPUP_COUNTRY.getLocator())));
        popupSelectionCountry.click();
        /**
         * Choose TOP_SALLERS in popup menu NEW_AND_INTERESTING
         */
        WebElement elementSelectionCountryInPopup =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath(SELECTED_COUNTRY.getLocator() + country + "')]")));
        elementSelectionCountryInPopup.click();
//        try {  Thread.sleep(1000);} catch (InterruptedException e) {  throw new RuntimeException(e); }
        log.info("TOP_SALLERS page is "+driver.getCurrentUrl());
    }

    public ArrayList<String> getListOfNameTheBestGames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ArrayList<String> arrNameOfBestTenGames = new ArrayList<>();
        List<WebElement> nameOfBestTenGamesWebElements = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath(NAME_OF_BEST_TEN_GAMES.getLocator())));

        for (WebElement elm : nameOfBestTenGamesWebElements) {
            String name = elm.getText();
            if (name.contains("(")) {
                name=name.substring(0,name.indexOf('(')-1);
            }
            arrNameOfBestTenGames.add(name);
        }
        return arrNameOfBestTenGames;
    }

    public ArrayList<Double> getListOfCostTheBestGames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ArrayList<Double> arrCostOfBestTenGames = new ArrayList<>();
        List<WebElement> costOfBestTenGamesWebElements = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath(COST_OF_BEST_TEN_GAMES.getLocator())));

        for (WebElement elm : costOfBestTenGamesWebElements) {
            String elmText = elm.getText();
            if (elmText.contains("%"))  elmText=elmText.substring(elmText.lastIndexOf("\n"));
            if (elmText.equals("Бесплатно") || elmText.equals("")) elmText = "0";

            arrCostOfBestTenGames.add(Double.parseDouble(elmText.replace("$","")
                    .replace("€","").replace(",",".")
                    .replace("Предзаказ","")
                    .replace("НОВИНКИ","")));
        }
        return arrCostOfBestTenGames;
    }

    public void moveToPageOfTheBestGame (Integer position) {
        String xPath = BEST_GAME_IN_LIST.getLocator()+position.toString()+"]/td/a";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement elementBestGame =
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        elementBestGame.click();
        log.info("The Best Game page is "+driver.getCurrentUrl());
    }

    public String getNameGameOnItsOwnPage () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(NAME_GAME_ON_ITS_OWN_PAGE.getLocator()))).getText();
    }

    public Double getCostGameOnItsOwnPage () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String costOfBestGame = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(COST_GAME_ON_ITS_OWN_PAGE.getLocator()))).getText();
        if (costOfBestGame.contains("Бесплатные") || costOfBestGame.equals("")) {
            costOfBestGame = "0";
        }
        if (costOfBestGame.contains("%")) {
            costOfBestGame = costOfBestGame.substring(costOfBestGame.lastIndexOf("\n"+1));
        }
        if (costOfBestGame.contains("$")) {
            costOfBestGame = costOfBestGame.substring(costOfBestGame.indexOf("$")+1,costOfBestGame.indexOf("U")).trim();
        }
        if (costOfBestGame.contains("€")) {
            costOfBestGame = costOfBestGame.substring(0,costOfBestGame.indexOf("€")).replace(',','.');
        }
        return Double.parseDouble(costOfBestGame);
    }

    public void  getInfoAboutGames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("The Game developer is "+getGameDeveloper());
        log.info("The Game data of edit is "+getGameDataEdition());
        log.info("The Game main genre are "+getMainGenreOfGame());
    }

    public String  getGameDeveloper() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(DEVELOPER_GAME.getLocator()))).getText();
    }

    public String  getGameDataEdition() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(DATA_EDITION.getLocator()))).getText();
    }

    public List<String>  getMainGenreOfGame() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ArrayList<String> arrMainGenreOfGame = new ArrayList<>();
        List<WebElement> mainGenreOfGameWebElements = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath(MAIN_GENRE_OF_GAME.getLocator())));
        for (WebElement elm : mainGenreOfGameWebElements)  arrMainGenreOfGame.add(elm.getText());
        return arrMainGenreOfGame;
    }
}


