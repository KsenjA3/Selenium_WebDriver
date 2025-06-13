package tests.aqa.api.onliner.po;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OnlinerAboutPageLocator {
    FAST_CONNECTION_WITH_REDACTION_LOCATOR ("//a[contains(text(),'Как быстро связаться с редакцией Onlíner')]"),
    ;
    private String locator;
}
