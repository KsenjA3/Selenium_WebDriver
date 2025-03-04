package tests.aqa.ui.po;

public enum HomePageLocator {
    EXIT_MENU_ACCOUNT_LOCATOR ("//a[@class='dropdown-toggle']"),
    EXIT_ACCOUNT_LOCATOR ("//a[@href='/Account/LogOff']"),
    LEFT_MENU_LOCATOR ("//aside"),
    COURSE_LEFT_MENU_LOCATOR ("//span[.='Занятия']/ancestor::a[1]"),
    ITEMS_COURSE_LEFT_MENU_LOCATOR ("//span[.='Занятия']/ancestor::a/following-sibling::ul[@class='nav NavSubParent']/descendant::span"),
    ;

    private String str;

    HomePageLocator (String str){
        this.str= str;
    }

    public String get (){
        return str;
    }
}


//span[.='Занятия']/ancestor::a/following-sibling::ul[@class='nav NavSubParent']