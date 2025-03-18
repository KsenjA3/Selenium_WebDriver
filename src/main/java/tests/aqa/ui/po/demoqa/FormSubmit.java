package tests.aqa.ui.po.demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.aqa.ConfProperties;

public class FormSubmit {
    private WebDriver driver;

    public FormSubmit(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement successNotification;

    @FindBy(xpath = "//td[text()='Student Name']/following-sibling::td")
    private WebElement fullName;

    @FindBy(xpath = "//td[text()='Student Email']/following-sibling::td")
    private WebElement email;

    @FindBy(xpath = "//td[text()='Gender']/following-sibling::td")
    private WebElement gander;

    @FindBy(xpath = "//td[text()='Mobile']/following-sibling::td")
    private WebElement mobile;

    @FindBy(xpath = "//td[text()='Date of Birth']/following-sibling::td")
    private WebElement dateOfBirth;

    @FindBy(xpath = "//td[text()='Subjects']/following-sibling::td")
    private WebElement subjects;

    @FindBy(xpath = "//td[text()='Hobbies']/following-sibling::td")
    private WebElement hobby;

    @FindBy(xpath = "//td[text()='Picture']/following-sibling::td")
    private WebElement picture;

    @FindBy(xpath = "//td[text()='Address']/following-sibling::td")
    private WebElement address;

    @FindBy(xpath = "//td[text()='State and City']/following-sibling::td")
    private WebElement stateAndCity;

    public Boolean getSuccessNotification() {
        return successNotification.getText().equals("Thanks for submitting the form");
    }

    public String getFullName() {
        return fullName.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getGander() {
        return gander.getText();
    }

    public String getMobile() {
        return mobile.getText();
    }

    public String getDateOfBirth() {
        return dateOfBirth.getText();
    }

    public String getSubjects() {
        return subjects.getText();
    }

    public String getHobby() {
        return hobby.getText();
    }

    public String getPicture() {
        return picture.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public String getStateAndCity() {
        return stateAndCity.getText();
    }


}
