package tests.aqa.ui.po.demoqa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FormPageLocator {
    USER_FORM_LOCATOR ("userForm"),

//    FIRST_NAME_ID_LOCATOR("firstName"),
//    LAST_NAME_ID_LOCATOR("lastName"),
//    EMAIL_ID_LOCATOR("userEmail"),
//    PHONE_NUMBER_ID_LOCATOR("userNumber"),
//    DATE_OF_BIRTH_ID_LOCATOR("dateOfBirthInput"),
//    SUBJECT_ID_LOCATOR("subjectsContainer"),
//    SUBJECT_INPUT_LOCATOR("subjectsInput"),
//    SUBJECT_OPTION_ID_LOCATOR("react-select-2-option-0"),
//    UPLOAD_PICTURE_ID_LOCATOR("uploadPicture"),
//    ADDRESS_ID_LOCATOR("currentAddress"),
//    STATES_MENU_ID_LOCATOR("state"),
//    STATE_ITEM_ID_LOCATOR("react-select-3-option-0"),
//    CITIES_MENU_ID_LOCATOR("city"),
//    CITY_ITEM_ID_LOCATOR("react-select-4-option-0"),
//    SUBMIT_BUTTON_ID_LOCATOR("submit"),

    GENDER_PART1_LOCATOR("//input[@value='"),
    GENDER_PART3_LOCATOR("']/following-sibling::label"),
    HOBBY_LOCATOR(" //label[contains(text(), 'Sports')]"),

//input[@value='Male']
    //*[@id='example-modal-sizes-title-lg']
    ;
    private String locator;
}
