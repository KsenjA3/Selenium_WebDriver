package tests.aqa.ui.demoqa;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import tests.aqa.ui.BaseTest;
import tests.aqa.ui.po.demoqa.FormInput;
import tests.aqa.ui.po.demoqa.FormPage;
import tests.aqa.ui.po.demoqa.FormSubmit;
import tests.aqa.ui.po.steam.StreamPage;

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
    @CsvFileSource(resources = "/ui/dataDemoqaFullForm.csv", delimiter = '|')
    void testEqualityFullNameInputAndSubmitForm(String firstName, String lastName, String gender,  String phoneNumber,
                                        String email, String dateOfBirth, String subjects, String hobby,
                                        String filePath, String address,  String state, String city) {
        formPages.forEach(formPage -> {
            formPage.fillFullFormOfPage(firstName, lastName, gender, phoneNumber,
                    email, dateOfBirth, subjects, hobby,filePath, address, state, city);

            String expectedFullName = firstName+" "+lastName;

            assertEquals(expectedFullName,formPage.getFormSubmit().getFullName(),
                    "The full names of InputForm and SubmitForm are different on driver"+ formPage.getDriver());
            assertEquals(gender,formPage.getFormSubmit().getGander(),
                    "The genders of InputForm and SubmitForm are different on driver" + formPage.getDriver());
            assertEquals(phoneNumber,formPage.getFormSubmit().getMobile(),
                    "The phone number of InputForm and SubmitForm are different on driver" + formPage.getDriver());
            assertEquals(email,formPage.getFormSubmit().getEmail(),
                    "The emails of InputForm and SubmitForm are different on driver" + formPage.getDriver());
            assertEquals(dateOfBirth,formPage.getFormSubmit().getDateOfBirth(),
                    "The dates of Birth of InputForm and SubmitForm are different on driver" + formPage.getDriver());
            assertEquals(subjects,formPage.getFormSubmit().getSubjects(),
                    "The the last of subjects of InputForm and SubmitForm are different on driver" + formPage.getDriver());

        });
    }

    @DisplayName("Verify equality Gender in InputForm and Submitting form ")
    @ParameterizedTest
    @CsvFileSource(resources = "/ui/dataDemoqaFullForm.csv")
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
