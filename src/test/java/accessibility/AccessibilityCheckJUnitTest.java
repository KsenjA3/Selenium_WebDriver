package accessibility;

import com.deque.html.axecore.args.AxeRunOnlyOptions;
import com.deque.html.axecore.args.AxeRunOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import java.io.FileWriter;
import java.io.IOException;

@Log4j2
public class AccessibilityCheckJUnitTest { // Имя класса изменено для ясности

    private WebDriver driver;
    // URL для тестирования. Используйте страницу с известными проблемами доступности для наглядности.
    //5 нарушений
//    private final String TEST_URL = "https://www.w3.org/WAI/demos/bad/before/home.html";

    // 1 нарушение
//    private final String TEST_URL = "https://www.google.com";

    // без нарушений
    private final String TEST_URL = "https://www.bbc.com/news";


    @BeforeEach // Изменено
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");

        driver = new ChromeDriver(options);
    }


    @Test
    public void accessibilityTest() {
        //Открывает страницу Google в браузере.
        driver.get("https://www.google.com");

        //Создает список с одним элементом "wcag21aa".
        //Этот тег относится к стандарту WCAG 2.1 уровня AA (Web Content Accessibility Guidelines).
        List<String> tags = Arrays.asList("wcag21aa");
        //AxeBuilder используется для настройки и запуска проверки доступности.
        AxeBuilder builder = new AxeBuilder();
        // Указываем, что мы хотим проверить на странице Google на соответствие WCAG 2.1 уровня AA.
        builder.withTags(tags);

        // Выполняем проверку доступности и получаем результаты.
        Results results = builder.analyze(driver);
        // Получаем список нарушений доступности.
        List<Rule> violations = results.getViolations();
        // Выводим нарушения доступности в консоль
        for (Rule i : violations) {
            System.out.println(i);
        }

        // Проверяем, что нарушений доступности нет.
        Assert.assertEquals(0, results.getViolations().size());
    }

    @Test
    public void testAccessibilityOfHomePage() {
        driver.get(TEST_URL);

        // 1. Создаем объект AxeBuilder
        AxeRunOnlyOptions runOnlyOptions = new AxeRunOnlyOptions();
        runOnlyOptions.setType("tag");
        runOnlyOptions.setValues(Arrays.asList("wcag2a", "wcag2aa"));

        AxeRunOptions options = new AxeRunOptions();
        options.setRunOnly(runOnlyOptions);

        AxeBuilder axe = new AxeBuilder().withOptions(options);

        // 2. Проверяем, есть ли нарушения
        Results result = axe.analyze(driver);
        List<Rule> violationList = result.getViolations();

        // 3. Выводим найденные нарушения в консоль
        if (violationList.size() == 0) {
            System.out.println("✅ Нарушений доступности не найдено на: " + TEST_URL);
        }
        else {
            System.out.println("❌ Обнаружены нарушения доступности на: " + TEST_URL);
            System.out.println("Количество нарушений: " + violationList.size());

            // Выводим детали каждого нарушения
            System.out.println("===============================================================");
            for (Rule r : result.getViolations()) {
                System.out.println("------------------------------------");
                System.out.println("Complete = "+r.toString());
                System.out.println("Tags = "+r.getTags());
                System.out.println("Description = "+r.getDescription());
                System.out.println("Help Url = "+r.getHelpUrl());
            }

            System.out.println("===============================================================");
            System.out.println("Inapplicable list size :"+result.getInapplicable().size());
            for (Rule r : result.getInapplicable()) {
                System.out.println("------------------------------------");
                System.out.println("Complete = "+r.toString());
                System.out.println("Правило: " + r.getId());
                System.out.println("Описание: " + r.getDescription());
                System.out.println("Воздействие: " + r.getImpact());
                System.out.println("Как исправить (Help Url): " + r.getHelpUrl());
                System.out.println("Tags = "+r.getTags());
            }

            System.out.println("===============================================================");
            System.out.println("Incomplete list size :"+result.getIncomplete().size());
            for (Rule r : result.getIncomplete()) {
                System.out.println("------------------------------------");
                System.out.println("Complete = "+r.toString());
                System.out.println("Tags = "+r.getTags());
                System.out.println("Description = "+r.getDescription());
                System.out.println("Help Url = "+r.getHelpUrl());
            }

            System.out.println("===============================================================");
            for (Rule violation : violationList) {
                System.out.println("------------------------------------");
                List<com.deque.html.axecore.results.CheckedNode> nodes = violation.getNodes();
                if (!nodes.isEmpty()) {
                    System.out.println("Затронутые элементы для правила " + violation.getId() + ":");
                    for (com.deque.html.axecore.results.CheckedNode node : nodes) {
                        System.out.println("  - Селектор: " + node.getHtml());
                        System.out.println("    Дополнительная информация: " + node.getFailureSummary());
                    }
                }
            }

            // Опционально: Сохранить результаты в JSON-файл для дальнейшего анализа или отчета
            try (FileWriter file = new FileWriter("axe-results-" + System.currentTimeMillis() + ".json")) {
                file.write(violationList.toString());
                System.out.println("Результаты сохранены в JSON-файл.");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Проваливаем тест, если найдены нарушения
            // Использование Assertions.fail() из JUnit 5
            Assertions.fail("Обнаружены нарушения доступности. Смотрите консоль/отчет.");
        }
    }

    // Пример теста для конкретного элемента (или исключения)
    @Test
    public void testAccessibilityOfSpecificElement() {
        driver.get(TEST_URL);

        // 1. Создаем объект AxeBuilder
        AxeRunOnlyOptions runOnlyOptions = new AxeRunOnlyOptions();
        runOnlyOptions.setType("tag");
        runOnlyOptions.setValues(Arrays.asList("wcag2a", "wcag2aa"));

        AxeRunOptions options = new AxeRunOptions();
        options.setRunOnly(runOnlyOptions);

        AxeBuilder axe = new AxeBuilder().withOptions(options);

        // 2. Проверяем, есть ли нарушения
        // Сканируем только один элемент (например, div с id="my-form")
        // Можно также исключить элементы: .exclude("#header")
        Results result = axe
                .include("body") // Можно указать конкретный селектор, чтобы сканировать только его содержимое
                .disableRules(Collections.singletonList("color-contrast")) // Отключить проверку контраста для этого теста
                .analyze(driver);

        List<Rule> violationList = result.getViolations();
        if (violationList.size() == 0) {
            System.out.println("✅ Нарушений доступности (с учетом фильтров) не найдено на: " + TEST_URL);
        } else {
            System.out.println("❌ Обнаружены нарушения доступности (с учетом фильтров).");
            System.out.println("Количество нарушений: " + violationList.size());

            // Выводим детали каждого нарушения
            for (Rule r : result.getViolations()) {
                System.out.println("------------------------------------");
                System.out.println("Tags = "+r.getTags());
                System.out.println("Description = "+r.getDescription());
                System.out.println("Help Url = "+r.getHelpUrl());
            }
            // Логирование нарушений
            Assertions.fail("Обнаружены нарушения доступности.");
        }
    }

    @AfterEach // Изменено
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
