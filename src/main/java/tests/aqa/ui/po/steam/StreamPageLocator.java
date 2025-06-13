package tests.aqa.ui.po.steam;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StreamPageLocator {
    BUTTON_ABOUT_LOCATOR ("//div[@role='navigation']/a[contains(text(),'Информация')]"),
    NUMBER_IN_GAME_LOCATOR ("//div[@class='online_stat_label gamers_in_game']/parent::*"),
    NUMBER_ONLINE_LOCATOR ("//div[@class='online_stat_label gamers_online']/parent::*"),

    POPUP_NEW_AND_INTERESTING("//*[@id='noteworthy_tab']/span"),
    TOP_SELLERS_IN_POPUP("//div[@class='popup_menu popup_menu_browse']/a[contains(text(),'Лидеры продаж')]"),
    POPUP_COUNTRY("//button[contains(@class,'DialogDropDown _DialogInputContainer')]"),
    SELECTED_COUNTRY ("//button[@class='_2oAiZidGyUxL-hfupFDQ2m']/div[contains(text(),'"),

    NAME_OF_BEST_TEN_GAMES("//table[@class='_3arZn0BMPzyhcYNADe193m']/tbody/tr[position()<11]/td[position()=3]"),
    COST_OF_BEST_TEN_GAMES("//table[@class='_3arZn0BMPzyhcYNADe193m']/tbody/tr[position()<11]/td[position()=4]"),
    BEST_GAME_IN_LIST("//table[@class='_3arZn0BMPzyhcYNADe193m']/tbody/tr[position()="),
    NAME_GAME_ON_ITS_OWN_PAGE("//*[@id='appHubAppName']"),
    COST_GAME_ON_ITS_OWN_PAGE("//div[@class='game_purchase_action']/div[@class='game_purchase_action_bg']"),
    DATA_EDITION("//div[@class='date']"),
    MAIN_GENRE_OF_GAME("//a[@class='app_tag' and @style='']"),
    DEVELOPER_GAME("//*[@id='developers_list']"),
    ;
    private String locator;
}
