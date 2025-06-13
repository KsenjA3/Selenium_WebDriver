package tests.aqa.ui.po.demoqa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FormPageLocator {
    /**
     * Locators for FormInput
     */
    GENDER_PART1_LOCATOR("//input[@value='"),
    GENDER_PART3_LOCATOR("']/following-sibling::label"),
    HOBBY_PART1_LOCATOR("//label[contains(text(),'"),
    HOBBY_PART2_LOCATOR("')]"),
    /**
     * Locators for FormSubmit
     */
    POPUP_SUBMIT_PAGE_LOCATOR(".modal-content"),
    SUCCESS_NOTIFICATION_SUBMIT_PAGE_LOCATOR("#example-modal-sizes-title-lg"),
    ;
    private String locator;
}
