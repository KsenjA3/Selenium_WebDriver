package tests.aqa.loginPage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitleTest_logPage extends BaseTest {
    @Test
    void title() {
        assertEquals("Вход на сайт",driver.getTitle());
    }
}
