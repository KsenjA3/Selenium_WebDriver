package tests.aqa.api.po;

public enum OnlinerAboutPageLocator {
    FAST_CONNECTION_WITH_REDACTION_LOCATOR ("//a[contains(text(),'Как быстро связаться с редакцией Onlíner')]"),
    ;

    private String str;

    OnlinerAboutPageLocator (String str){
        this.str= str;
    }

    public String get (){
        return str;
    }
}
