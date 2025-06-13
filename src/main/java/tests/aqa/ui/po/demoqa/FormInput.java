package tests.aqa.ui.po.demoqa;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;


import static tests.aqa.ui.po.demoqa.FormPageLocator.*;

@Log4j2
public final class FormInput {
    private WebDriver driver;

    public FormInput(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id ="lastName" )
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(id ="userNumber" )
    private WebElement phoneNumber;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirth;

    @FindBy(id = "subjectsInput")
    private WebElement subjectInput;

    @FindBy(id = "react-select-2-option-0")
    private WebElement subjectOption;

   @FindBy(id = "uploadPicture")
    private WebElement picture;

   @FindBy(id = "currentAddress")
   private WebElement address;

   @FindBy(id = "react-select-3-input")
   private WebElement listStates;

   @FindBy(xpath = "//div[contains(@id,'react-select-3-option')]")
   private WebElement state;

   @FindBy(id = "react-select-4-input")
   private WebElement listCities;

    @FindBy(xpath = "//div[contains(@id,'react-select-4-option')]")
   private WebElement city;

   @FindBy(id = "submit")
   private WebElement submitButton;

   private WebElement gender;
   private WebElement hobby;

   public FormInput setFirstName(String firstName) {
       this.firstName.sendKeys(firstName);
       return this;
   }

    public FormInput setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public FormInput setGender(String gender) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        findGenderLabel(gender);
        wait.until(ExpectedConditions.elementToBeClickable(this.gender)).click();
        return this;
    }

    public String getColorGender(String gender) {
        findGenderLabel(gender);
    String color = this.gender.getCssValue("color");
        if (color.startsWith("rgba(")) {
            color = color.replace("rgba(", "rgb(");
            int index = color.lastIndexOf(",");
            color = color.substring(0, index)+")";
        }
       log.info("Selected color gender: " + color);
       return color;
    }

    private void findGenderLabel (String gender) {
        String genderLocator = String.format("%s%s%s",
                GENDER_PART1_LOCATOR.getLocator(), gender,GENDER_PART3_LOCATOR.getLocator());
        this.gender =driver.findElement(By.xpath(genderLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.gender);
    }

    public FormInput setEmail(String email) {
       this.email.sendKeys(email);
       return this;
    }

    public FormInput setPhoneNumber(String phoneNumber) {
       this.phoneNumber.sendKeys(phoneNumber);
       return this;
    }

    public FormInput setDateOfBirth(String dateOfBirth) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.dateOfBirth);
        this.dateOfBirth.sendKeys(Keys.CONTROL + "a");
//        this.dateOfBirth.sendKeys(Keys.DELETE);
//        this.dateOfBirth.sendKeys("\b\b\b\b\b\b\b\b\b\b");
//        this.dateOfBirth.clear();
//        this.dateOfBirth.click();
        this.dateOfBirth.sendKeys(dateOfBirth);
        this.dateOfBirth.click();
       return this;
    }

    public FormInput setSubject(String subjects) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.subjectInput);
        String[] subjectsArray = subjects.split(", ");
        Arrays.stream(subjectsArray).forEach(s -> {
            this.subjectInput.sendKeys(s);
            wait.until(ExpectedConditions.elementToBeClickable(this.subjectOption)).click();
        });
        return this;
    }

    public FormInput setPicture (String filePath) {
        this.picture.sendKeys(filePath);
        return this;
    }

    public FormInput setAddress(String address) {
       this.address.sendKeys(address);
       return this;
    }

    public FormInput setStates(String state) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.listStates);
        this.listStates.sendKeys(state);
        wait.until(ExpectedConditions.elementToBeClickable(this.state)).click();
        return this;
    }

    public FormInput setCity(String city) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.listCities.sendKeys(city);
        wait.until(ExpectedConditions.elementToBeClickable(this.city)).click();
        return this;
    }

    public FormInput setHobby(String hobbies) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String[] hobbiesArray = hobbies.split(", ");

        Arrays.stream(hobbiesArray).forEach(hobby -> {
            String hobbyLocator = String.format("%s%s%s",
                    HOBBY_PART1_LOCATOR.getLocator(), hobby,HOBBY_PART2_LOCATOR.getLocator());
            this.hobby =driver.findElement(By.xpath(hobbyLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", this.hobby);
            wait.until(ExpectedConditions.elementToBeClickable(this.hobby)).click();
        });
        return this;
    }

     public void clickSubmitButton() {
       //до середины элемента
//         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", this.submitButton);
       // до низа страницы
         ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//         Wait<WebDriver> wait = new FluentWait<>(driver)
//                 .withTimeout(Duration.ofSeconds(10))
//                 .pollingEvery(Duration.ofSeconds(1))
//                 .ignoring(Exception.class);

         //locator рекламы
         //iframe[contains(@id,'google_ads_iframe_/21849154601,22343295815/Ad.Plus-Anchor_0')]
//         driver.switchTo().frame()
//         JavascriptExecutor js = (JavascriptExecutor) driver;
//         js.executeScript("document.querySelector('iframe').style.display='none';");
         wait.until(ExpectedConditions.elementToBeClickable(this.submitButton)).click();
     }
}
