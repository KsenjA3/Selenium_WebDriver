package accessibility;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;

import com.deque.html.axecore.args.AxeRunOnlyOptions;
import com.deque.html.axecore.args.AxeRunOptions;


import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import utils.Log4jAllureIntegration;

@Log4j2
public class AccessibilityCheckJUnitTest { // Имя класса изменено для ясности

    private WebDriver driver;
    // AxeBuilder для настройки и запуска проверки доступности
    private AxeBuilder axe;

    // URL для тестирования
    private final String TEST_URL_5_CONTRAVENTIONS = "https://www.w3.org/WAI/demos/bad/before/home.html";
    private final String TEST_URL_1_CONTRAVENTION = "https://www.google.com";
    private final String TEST_URL_NO_CONTRAVENTIONS = "https://www.bbc.com/news";
    private final String TEST_URL_NO_CONTRAVENTION = "https://www.gov.uk/";


    @BeforeEach
    public void setup() {
        /**
         * Создаем WebDriver для Chrome и
         * настраиваем его для работы в режиме без графического интерфейса.
         */
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        driver = new ChromeDriver(options);

        /**
         * Создаем объект AxeBuilder для настройки и запуска проверки доступности
         * с настройкой для запуска проверки только на определенных тегах и уровней.
         */
        AxeRunOnlyOptions runOnlyOptions = new AxeRunOnlyOptions();
        runOnlyOptions.setType("tag");
        runOnlyOptions.setValues(Arrays.asList("wcag2a", "wcag2aa"));

        AxeRunOptions optionsAxe = new AxeRunOptions();
        optionsAxe.setRunOnly(runOnlyOptions);

        axe = new AxeBuilder().withOptions(optionsAxe);
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
            log.error(i);
        }

        // Проверяем, что нарушений доступности нет.
        Assert.assertEquals(0, results.getViolations().size());
    }


    @Test
    public void testAccessibilityOfHomePage() {
        // 1. Открывает страницу в браузере.
        driver.get(TEST_URL_1_CONTRAVENTION);

        // 2. Запускаем анализ доступности на текущей странице и возвращает результаты.
        Results result = axe.analyze(driver);

        // 3. Сохраняем результаты в JSON-файл для дальнейшего анализа или отчета
        try {
            // Создайте ObjectMapper для сериализации JSON
            ObjectMapper objectMapper = new ObjectMapper();
            // Включите красивое форматирование для читаемости файла
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            // Указание пути к файлу, куда будут сохранены результаты
            File outputFile = Paths.get("axe/axe_results_jackson.json").toFile();

            // Запишись объекта 'axeResult' в JSON-файл
            // writeValue: метод ObjectMapper для сериализации Java-объекта в JSON-файл
            objectMapper.writeValue(outputFile, result);

            Allure.addAttachment("Axe report", new FileInputStream(outputFile) );

        } catch (IOException e) {
            log.error("Ошибка при сохранении результатов анализа доступности", e);
        }

        // 4. Получаем список нарушений доступности.
        List<Rule> violationList = result.getViolations();

        // Выводим найденные нарушения в консоль
        if (violationList.size() == 0) {
            log.info("✅ Нарушений доступности не найдено на: " + TEST_URL_1_CONTRAVENTION);
        }
        else {
            log.info("❌ Обнаружены нарушения доступности на: " + TEST_URL_1_CONTRAVENTION);
            log.info("Количество нарушений: " + violationList.size());
            System.out.println("===============================================================");
            System.out.println();

            /**
             * 4.1 Выводим детали каждого нарушения,
             * конкретный и детализированный список проблем, найденных на исследуемой веб-странице
             */
            for (Rule r : result.getViolations()) {
                log.info("Complete = "+r.toString());
                log.info("Правило: " + r.getId());
                log.info("Описание: " + r.getDescription());
                log.info("Tags = "+r.getTags());
                log.info("Воздействие: " + r.getImpact());
                log.info("Как исправить (Help Url): " + r.getHelpUrl());

                System.out.println();
            }
            System.out.println("===============================================================");

            /**
             * 4.2 Для каждого нарушения получаем список узлов (nodes), которые связаны с этим нарушением.
             * Эти узлы представляют конкретные элементы на веб-странице, где обнаружено нарушение доступности.
             */
            for (Rule violation : violationList) {
                List<com.deque.html.axecore.results.CheckedNode> nodes = violation.getNodes();
                //Проверяем, есть ли узлы для данного нарушения.
                if (!nodes.isEmpty()) {
                    // Возвращаем идентификатор правила
                    log.info("Затронутые элементы для правила " + violation.getId() + ":");
                    System.out.println();
                    // Перебираем все узлы, связанные с текущим нарушением.
                    for (com.deque.html.axecore.results.CheckedNode node : nodes) {
                        // Выводим информацию о текущем узле
                        log.info("  - Селектор: " + node.getHtml());
                        log.info("    Дополнительная информация: " + node.getFailureSummary());
                        System.out.println();
                    }
                }
            }
            System.out.println("===============================================================");
            System.out.println();

            /**
             * 4.3 Inapplicable правила
             * они не указывают на прямые проблемы доступности,
             * их анализ помогает получить более полное представление о результатах тестирования,
             * подтвердить ожидаемое поведение системы и даже выявить косвенные проблемы в разработке или дизайне.
             * Это часть всестороннего анализа результатов тестирования доступности.
             */
            log.info("Inapplicable list size :"+result.getInapplicable().size());
            System.out.println("===============================================================");
            for (Rule r : result.getInapplicable()) {
                log.info("Правило: " + r.getId());
                log.info("Описание: " + r.getDescription());
                log.info("Tags = "+r.getTags());
                log.info("Воздействие: " + r.getImpact());
                log.info("Как исправить (Help Url): " + r.getHelpUrl());

                System.out.println("--------------------------------------------------------------");
            }
            System.out.println();

            /**
             * 4.4 Incomplete правила
             * это не ошибки, но и не подтверждения отсутствия проблем.
             * Это "красный флаг", указывающий на необходимость более глубокого,
             * ручного анализа для обеспечения полной доступности веб-ресурса.
             */
            log.info("Incomplete list size :"+result.getIncomplete().size());
            System.out.println("===============================================================");
            for (Rule r : result.getIncomplete()) {
                log.info("Правило: " + r.getId());
                log.info("Описание: " + r.getDescription());
                log.info("Tags = "+r.getTags());
                log.info("Воздействие: " + r.getImpact());
                log.info("Как исправить (Help Url): " + r.getHelpUrl());
                System.out.println("--------------------------------------------------------------");
            }
            System.out.println();


            // 5. Проваливаем тест, если найдены нарушения используя Assertions.fail() из JUnit 5
            Assertions.fail("Обнаружены нарушения доступности. Смотрите консоль/отчет.");
        }
    }

    // Пример теста для конкретного элемента (или исключения)
    @Test
    public void testAccessibilityOfSpecificElement() {
        driver.get(TEST_URL_1_CONTRAVENTION);

        // Create AxeRunOptions and set XPath
        AxeRunOptions axeRunOptions = new AxeRunOptions();
        axeRunOptions.setXPath(true);

        // Проверяем, есть ли нарушения
        // Сканируем только один элемент (например, div с id="my-form")
        // Можно также исключить элементы: .exclude("#header")
        Results result = axe
                // Можно указать конкретный селектор, чтобы сканировать только его содержимое
                .include("body")
                // Отключить проверку контраста для этого теста
                .disableRules(Collections.singletonList("color-contrast"))
                // Исключаем определенные элементы из проверки
                .exclude("#header, #footer")
                // Устанавливаем дополнительные опции
                .withOptions(axeRunOptions)
                // Указываем конкретные теги для проверки
                .withTags(Arrays.asList("wcag2a", "wcag2aa", "best-practice"))
                // Указываем конкретные правила для проверки
                .withRules(Arrays.asList("label", "image-alt", "button-name"))
                .analyze(driver);

        List<Rule> violationList = result.getViolations();

        if (violationList.size() == 0) {
            log.info("✅ Нарушений доступности (с учетом фильтров) не найдено на: " + TEST_URL_1_CONTRAVENTION);
        } else {
            log.info("❌ Обнаружены нарушения доступности (с учетом фильтров).");
            log.info("Количество нарушений: " + violationList.size());

        // Выводим детали каждого нарушения
        for (Rule r : result.getViolations()) {
            System.out.println("------------------------------------");
            log.info("Tags = "+r.getTags());
            log.info("Description = "+r.getDescription());
            log.info("Help Url = "+r.getHelpUrl());
        }

        // Логирование нарушений
        Assertions.fail("Обнаружены нарушения доступности.");
        }
    }

    @AfterEach // Изменено
    public void teardown() {
//        File logFile = new File("logs/test-class.log");
//        if (logFile.exists()) {
//            try (FileInputStream fis = new FileInputStream(logFile)) {
//                Allure.addAttachment("Логи теста", "text/plain", fis, "log");
//            } catch (IOException e) {
//                log.error("Ошибка при добавлении логов в Allure", e);
//            }
//        } else {
//            log.warn("Файл логов не найден: " + "logs/test-class.log");
//        }
        if (driver != null) {
            driver.quit();
        }
    }

}