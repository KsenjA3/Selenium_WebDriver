package tests.aqa.loginPage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tests.aqa.TitleTest;

@Suite
@SelectClasses(TitleTest.class)
//@ParameterizedTest
//@CsvSource("chrome","edge","mozilla")
public class DemoTest {
}
