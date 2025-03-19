package tests.aqa.ui.po.demoqa;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import tests.aqa.ConfProperties;

@Getter
public class FormPage {
    private WebDriver driver;
    private FormInput formInput;
    private FormSubmit formSubmit;

    public FormPage(WebDriver driver){
        this.driver = driver;
        driver.get(ConfProperties.getProperty("demoqa_form_page"));
        formInput = new FormInput(driver);
        formSubmit = new FormSubmit(driver);
    }

    public void fillFullInputForm (String firstName, String lastName, String gender,  String phoneNumber,
                                    String email, String dateOfBirth, String subject, String hobbies,
                                    String filePath, String address,  String state, String city) {
        formInput.setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setDateOfBirth(dateOfBirth)
                .setSubject(subject)
                .setPicture(filePath)
                .setAddress(address)
                .setHobby(hobbies)
                .setStates(state)
                .setCity(city)
                .clickSubmitButton();
    }

    public void fillRequiredFieldsInputForm (String firstName, String lastName, String gender, String phoneNumber) {
        formInput.setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .clickSubmitButton();
    }

    public void fillRequiredFieldsInputFormAndEmail (String firstName, String lastName, String gender, String phoneNumber, String email) {
        formInput.setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .clickSubmitButton();
    }

    public void fillInputFormWithoutGender (String firstName, String lastName, String gender,
                                                                     String phoneNumber, String email, String dateOfBirth,
                                                                     String subject, String hobbies, String address,
                                                                     String state, String city) {
        formInput.setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setDateOfBirth(dateOfBirth)
                .setSubject(subject)
                .setAddress(address)
                .setHobby(hobbies)
                .setStates(state)
                .setCity(city)
                .clickSubmitButton();
    }

    public void fillInputFormWithoutGender (String firstName, String lastName, String phoneNumber, String email,
                                               String dateOfBirth, String subject, String hobbies, String address,
                                               String state, String city) {
        formInput.setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setDateOfBirth(dateOfBirth)
                .setSubject(subject)
                .setAddress(address)
                .setHobby(hobbies)
                .setStates(state)
                .setCity(city)
                .clickSubmitButton();
    }
}
