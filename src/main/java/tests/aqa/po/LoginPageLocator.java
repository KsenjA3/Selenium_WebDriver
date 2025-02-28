package tests.aqa.po;


public enum LoginPageLocator {
    USERNAME_INPUT_LOCATOR ("//*[@id='LogLogin']"),
    PASSWORD_INPUT_LOCATOR ("//*[@id='LogPassword']"),
    LOGIN_BUTTON_LOCATOR ("//button[@type='submit']"),
    NAME_COMPANY_LABEL_SITE_LOCATOR ("//*[@class='card-body']//h5"),
    COMPANY_LABEL_SITE_LOCATOR ("//*[@alt='Аспект']"),
    HREF_FORGOT_PASSWORD_SITE_LOCATOR ("//a[contains(text(),'Забыли пароль?')]"),
    ERROR_REPORT_IDENTIFICATION_LOCATOR ("//*[contains(text(),'Неудачная попытка входа.')]"),

    ;

    private String str;

    LoginPageLocator (String str){
        this.str= str;
    }

    public String get (){
        return str;
    }
}
