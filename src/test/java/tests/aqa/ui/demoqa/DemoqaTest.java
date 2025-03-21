package tests.aqa.ui.demoqa;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.NoSuchElementException;
import tests.aqa.ui.BaseTest;
import tests.aqa.ui.po.demoqa.FormPage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

    @DisplayName("Verify equality fields in full filling Input Form and Submitting Form")
    @ParameterizedTest
    @CsvFileSource(resources = "/ui/demoqa/dataDemoqaFullFormPositive.csv", delimiter = '|')
    void testEqualityFullFillInputFormAndSubmitForm (String firstName, String lastName, String gender,  String phoneNumber,
                                        String email, String dateOfBirth, String subjects, String hobbies,
                                        String filePath, String address,  String state, String city) {
        formPages.forEach(formPage -> {
            String expectedFullName = firstName+" "+lastName;
            String expectedLocation = state+" "+city;
            String filePathAbsolute = new File(filePath).getAbsolutePath();
            String filePathExpected =new File(filePath).getName();

            formPage.fillFullInputForm (firstName, lastName, gender, phoneNumber,
                    email, dateOfBirth, subjects, hobbies,filePathAbsolute, address, state, city);

//            log.info("Submitting title is - " +formPage.getFormSubmit().checkSuccessNotification());

            assertTrue(formPage.getFormSubmit().checkIsPresenceSubmitForm(), "Submitting form is not Displayed on driver " + formPage.getDriver());

            assertEquals(expectedFullName,formPage.getFormSubmit().getFullName(),
                    "The full names in full filling InputForm and SubmitForm are different on driver "+ formPage.getDriver());
            assertEquals(gender,formPage.getFormSubmit().getGander(),
                    "The genders in full filling InputForm and SubmitForm are different on driver " + formPage.getDriver());
            assertEquals(phoneNumber,formPage.getFormSubmit().getMobile(),
                    "The phone number in full filling InputForm and SubmitForm are different on driver " + formPage.getDriver());
            assertEquals(email,formPage.getFormSubmit().getEmail(),
                    "The emails in full filling InputForm and SubmitForm are different on driver" + formPage.getDriver());
            assertEquals(dateOfBirth,formPage.getFormSubmit().getDateOfBirth(),
                    "The dates of Birth in full filling InputForm and SubmitForm are different on driver " + formPage.getDriver());
            assertEquals(subjects,formPage.getFormSubmit().getSubjects(),
                    "The  list of subjects in full filling InputForm and SubmitForm are different on driver " + formPage.getDriver());
            assertEquals(hobbies,formPage.getFormSubmit().getHobbies(),
                    "The hobbies in full filling InputForm and SubmitForm are different on driver " + formPage.getDriver());
            assertEquals(filePathExpected,formPage.getFormSubmit().getPicture(),
                    "The filePath of Picture in full filling InputForm and SubmitForm are different on driver" + formPage.getDriver());
            assertEquals(address,formPage.getFormSubmit().getAddress(),
                    "The address in full filling InputForm and SubmitForm are different on driver "+ formPage.getDriver());
            assertEquals(expectedLocation,formPage.getFormSubmit().getStateAndCity(),
                    "The location in full filling InputForm and SubmitForm are different on driver "+ formPage.getDriver());
        });
    }

    @DisplayName("Verify equality required to fill fields in Input Form and Submitting Form")
    @ParameterizedTest
    @CsvSource( value =  {
            "Ann,Krotova,Female,1111155555",
            "Ivan,Petrov,Male,9998887770",
            "Dog,Bobby,Other,1122334455",
    })
    void testEqualityRequiredToFillFieldsInInputFormAndSubmitForm (String firstName, String lastName, String gender, String phoneNumber) {
        formPages.forEach(formPage -> {
            String expectedFullName = firstName+" "+lastName;
            formPage.fillRequiredFieldsInputForm (firstName, lastName, gender, phoneNumber);

//            log.info("Submitting title is - " +formPage.getFormSubmit().checkSuccessNotification());
            assertTrue(formPage.getFormSubmit().checkIsPresenceSubmitForm(), "Submitting form is not Displayed on driver " + formPage.getDriver());

            assertEquals(expectedFullName,formPage.getFormSubmit().getFullName(),
                    "The full names in InputForm and SubmitForm are different, when require fields only fill, on driver "+ formPage.getDriver());
            assertEquals(gender,formPage.getFormSubmit().getGander(),
                    "The genders in InputForm and SubmitForm are different, when require fields only fill, on driver " + formPage.getDriver());
            assertEquals(phoneNumber,formPage.getFormSubmit().getMobile(),
                    "The phone number in InputForm and SubmitForm are different, when require fields only fill, on driver " + formPage.getDriver());
        });
    }

    @DisplayName("Verify shifting color Gender Label for red, when Gender is not selected.")
    @ParameterizedTest
    @CsvFileSource(resources = "/ui/demoqa/dataDemoqaFullFormPositive.csv", delimiter = '|')
    void testShiftColorGenderLabelWithoutSetGender (String firstName, String lastName, String gender,  String phoneNumber,
                                                     String email, String dateOfBirth, String subjects, String hobbies,
                                                     String filePath, String address,  String state, String city) {
        formPages.forEach(formPage -> {
            formPage.fillInputFormWithoutGender (firstName, lastName, gender, phoneNumber, email, dateOfBirth, subjects, hobbies, address, state, city);
            assertEquals("rgb(220, 53, 69)",    // красный
                    formPage.getFormInput().getColorGender(gender),
                    "Gender Label do not shift color for red, when Gender is not selected on driver "+ formPage.getDriver());
        });
    }

    @DisplayName("Verify absence displaying Submitting Form, when Gender is not set.")
    @ParameterizedTest
    @CsvFileSource(resources = "/ui/demoqa/dataDemoqaFullFormPositive.csv", delimiter = '|')
    void testAbsenceSubmittingFormWithoutSettingGender (String firstName, String lastName, String gender,  String phoneNumber,
                                                     String email, String dateOfBirth, String subjects, String hobbies,
                                                     String filePath, String address,  String state, String city) {
        formPages.forEach(formPage -> {
            formPage.fillInputFormWithoutGender (firstName, lastName, phoneNumber, email, dateOfBirth, subjects, hobbies, address, state, city);
            assertThrows( NoSuchElementException.class,
                    ()->{ formPage.getFormSubmit().isPresenceSubmitForm(); },
                    "Submitting form is Displayed, when Gender is not set on driver "+ formPage.getDriver()
            );
        });
    }

    @DisplayName("Verify absence displaying Submitting Form, when invalid data fills the email field Input Form.")
    @ParameterizedTest
    @CsvSource( value =  {
            "444.get@any.ru.",
            "ppp.12345@@villy.com",
            "cat.kitty@",
            "dot.Arr@got.r",
            "@iii.ppp"
    })
    void testAbsenceSubmittingFormWithInvalidEmail (String email) {
        String firstName = "Kate";
        String lastName = "Jonson";
        String phoneNumber = "1478523698";
        String gender = "Male";
        formPages.forEach(formPage -> {
            formPage.fillRequiredFieldsInputFormAndEmail (firstName, lastName, gender, phoneNumber, email);

            assertThrows( NoSuchElementException.class,
                    ()->{ formPage.getFormSubmit().isPresenceSubmitForm(); },
                    "Submitting form is Displayed, when invalid data fills the email "+email+" field Input Form on driver "+ formPage.getDriver()
            );
        });
    }

    @DisplayName("Verify absence displaying Submitting Form, when invalid phone number fills Input Form.")
    @ParameterizedTest
    @CsvSource( value =  {
            "55555",
            "9",
            "123456789",
            "qwertyuiop",
            "547-896587",
            "#111888896",
            "987654321&"
    })
    void testAbsenceSubmittingFormWithInvalidPhoneNumber (String phoneNumber) {
        String firstName = "Ben5t";
        String lastName = "Dotty5";
        String gender = "Female";

        formPages.forEach(formPage -> {
            formPage.fillRequiredFieldsInputForm  (firstName, lastName, gender, phoneNumber);

            assertThrows( NoSuchElementException.class,
                    ()->{ formPage.getFormSubmit().isPresenceSubmitForm(); },
                    "Submitting form is Displayed, when invalid phone number fills Input Form, on driver "+ formPage.getDriver()
            );
        });
    }
}
