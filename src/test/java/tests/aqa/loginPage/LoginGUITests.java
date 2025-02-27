package tests.aqa.loginPage;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
public class LoginGUITests extends LoginTests {
    @DisplayName("Title site")
    @Test
    void title() {
        assertEquals("Вход на сайт",driver.getTitle());
    }

    @DisplayName("Name Label Company")
    @Test
    void NameLabelCompany() {
        assertEquals("Aspect Language Centre",loginPage.getNameLabelCompany());
    }
}
