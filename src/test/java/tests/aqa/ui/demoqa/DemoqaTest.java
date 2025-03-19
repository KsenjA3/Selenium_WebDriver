package tests.aqa.ui.demoqa;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import tests.aqa.ui.BaseTest;
import tests.aqa.ui.po.demoqa.FormPage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
public class DemoqaTest extends BaseTest {
    private List<FormPage> formPages;

    @BeforeEach
    void openPage()  {
        formPages = new ArrayList<>();
        driverSet.forEach(driver -> {
            FormPage formPage = new FormPage(driver);
            formPages.add(formPage);
            log.info("The site form page " + driver.getCurrentUrl()+ " is opened with driver " + driver);
        });
        log.info("The stream page " + formPages);
    }

    @DisplayName("Verify equality FullName in InputForm and Submitting form ")
    @ParameterizedTest
    @CsvFileSource(resources = "/ui/demoqa/dataDemoqaFullFormPositive.csv", delimiter = '|')
    void testEqualityFullNameInputAndSubmitForm(String firstName, String lastName, String gender,  String phoneNumber,
                                        String email, String dateOfBirth, String subjects, String hobbies,
                                        String filePath, String address,  String state, String city) {
        formPages.forEach(formPage -> {
            String expectedFullName = firstName+" "+lastName;
            String expectedLocation = state+" "+city;
            String filePathAbsolute = new File(filePath).getAbsolutePath();
            String filePathExpected =new File(filePath).getName();

            formPage.fillFullFormOfPage(firstName, lastName, gender, phoneNumber,
                    email, dateOfBirth, subjects, hobbies,filePathAbsolute, address, state, city);

            log.info("!!!- " +formPage.getFormSubmit().checkSuccessNotification());

            assertEquals(expectedFullName,formPage.getFormSubmit().getFullName(),
                    "The full names in InputForm and SubmitForm are different on driver "+ formPage.getDriver());
            assertEquals(gender,formPage.getFormSubmit().getGander(),
                    "The genders in InputForm and SubmitForm are different on driver " + formPage.getDriver());
            assertEquals(phoneNumber,formPage.getFormSubmit().getMobile(),
                    "The phone number in InputForm and SubmitForm are different on driver " + formPage.getDriver());
            assertEquals(email,formPage.getFormSubmit().getEmail(),
                    "The emails in InputForm and SubmitForm are different on driver" + formPage.getDriver());
            assertEquals(dateOfBirth,formPage.getFormSubmit().getDateOfBirth(),
                    "The dates of Birth in InputForm and SubmitForm are different on driver " + formPage.getDriver());
            assertEquals(subjects,formPage.getFormSubmit().getSubjects(),
                    "The  last of subjects in InputForm and SubmitForm are different on driver " + formPage.getDriver());
            assertEquals(hobbies,formPage.getFormSubmit().getHobbies(),
                    "The hobbies in InputForm and SubmitForm are different on driver " + formPage.getDriver());
            assertEquals(filePathExpected,formPage.getFormSubmit().getPicture(),
                    "The filePath of Picture in InputForm and SubmitForm are different on driver" + formPage.getDriver());
            assertEquals(address,formPage.getFormSubmit().getAddress(),
                    "The address in InputForm and SubmitForm are different on driver "+ formPage.getDriver());
            assertEquals(expectedLocation,formPage.getFormSubmit().getStateAndCity(),
                    "The location in InputForm and SubmitForm are different on driver "+ formPage.getDriver());
        });
    }

    @DisplayName("Verify equality Gender in InputForm and Submitting form ")
    @ParameterizedTest
    @CsvFileSource(resources = "/ui/demoqa/dataDemoqaFullFormPositive.csv")
    void testEqualityGenderInputAndSubmitForm(String firstName, String lastName, String gender,  String phoneNumber,
                                        String email, String dateOfBirth, String subject, String hobby,
                                        String filePath, String address,  String state, String city) {
        formPages.forEach(formPage -> {
            formPage.fillFullFormOfPage(firstName, lastName, gender, phoneNumber,
                    email, dateOfBirth, subject, hobby,filePath, address, state, city);

            assertEquals(gender,formPage.getFormSubmit().getGander());
        });
    }
}
