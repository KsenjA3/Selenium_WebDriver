package tests.aqa.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    @BeforeAll // Этот метод выполнится один раз перед всеми тестами в классе
    public static void setUp() {
        Configuration.baseUrl = "https://www.onliner.by"; // Устанавливаем базовый URL
        Configuration.browser = "chrome"; // Используем Chrome
        Configuration.browserSize = "1920x1080"; // Устанавливаем размер окна
        Configuration.timeout = 6000; // Уменьшите стандартный таймаут (по умолчанию 4000 мс)
//        Configuration.pageLoadTimeout = 10000; // Уменьшите таймаут загрузки страницы
        Configuration.headless = true; // Запускаем с не видимым окном браузера

        // Отключите загрузку изображений
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--blink-settings=imagesEnabled=false");
        Configuration.browserCapabilities = options;
    }

}
