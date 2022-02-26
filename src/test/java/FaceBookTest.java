import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FaceBookTest {
    private static final String TEST_PAGE_URL = "https://www.facebook.com/";
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get(TEST_PAGE_URL);
        String actualURL = driver.getCurrentUrl();
        assertEquals(TEST_PAGE_URL, actualURL, "Pages don't match");
    }

    @AfterAll
    public static void classTearDowm() {
        SharedDriver.closeDriver();
    }

    @BeforeEach
    public void openTestPage() throws InterruptedException {
        driver = SharedDriver.getWebDriver();
        driver.get(TEST_PAGE_URL);
        WebElement createNewAccount = driver.findElement(By.xpath("//*[text()='Create new account']"));
        assertNotNull(createNewAccount);
        createNewAccount.click();
        Thread.sleep(1000);
    }

    @Test
    public void errorMessageSignupTest() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement signUpButton = driver.findElement(By.xpath("//button[@name ='websubmit']"));
        signUpButton.click();

        driver.findElement(By.xpath("//input[@name ='firstname']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'your name?')]")));

        driver.findElement(By.xpath("//input[@name ='lastname']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class= '_5633 _5634 _53ij']")));

        driver.findElement(By.xpath("//*[@aria-label='Mobile number or email']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'to reset your password')]")));

        driver.findElement(By.xpath("//input[@name ='reg_passwd__']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'at least six numbers')]")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "7"})
    public void monthTestParam(String monthInput) {

        WebElement monthDropList = driver.findElement(By.xpath("//*[@title='Month']"));
        Select monthDrop = new Select(monthDropList);
        monthDrop.selectByValue(monthInput);
        String monthValue = driver.findElement(By.xpath("//*[@title='Month']")).getAttribute("value");
        assertEquals(monthInput, monthValue);

    }

    @ParameterizedTest
    @ValueSource(strings = {"1950", "1905", "2021"})
    public void yearTestParam(String yearInput) {

        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text()='" + yearInput + "']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");
        assertEquals(yearInput, yearValue);
    }

    @Test
    public void genderRadioTest() {

        String femaleRadioXpath = "//*[text() = 'Female']//following-sibling::*[@type = 'radio']";
        driver.findElement(By.xpath(femaleRadioXpath)).click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleRadioXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);

        String maleRadioXpath = "//*[text() = 'Male']//following-sibling::*[@type = 'radio']";
        driver.findElement(By.xpath(maleRadioXpath)).click();
        String isMaleChecked = driver.findElement(By.xpath(maleRadioXpath)).getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);

    }

    @Test
    public void termsLinksTest() {

        WebElement termsPage = driver.findElement(By.xpath("//a[@id = 'terms-link']"));
        termsPage.click();

        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        driver.getCurrentUrl();

        String actualTermsURL = driver.getCurrentUrl();
        assertNotNull(actualTermsURL);

    }

    @Test
    public void policyLinksTest() {

        WebElement policyPage = driver.findElement(By.xpath("//a[@id = 'privacy-link']"));
        policyPage.click();

        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        driver.getCurrentUrl();

        String actualPolicyURL = driver.getCurrentUrl();
        assertNotNull(actualPolicyURL);
    }


    @Test
    public void cookiesLinksTest() {

        WebElement cookiesPage = driver.findElement(By.xpath("//a[@id = 'cookie-use-link']"));
        cookiesPage.click();

        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        driver.getCurrentUrl();

        String actualCookiesURL = driver.getCurrentUrl();
        assertNotNull(actualCookiesURL);
    }

}