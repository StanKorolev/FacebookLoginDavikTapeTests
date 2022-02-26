import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DavikTest {

    private static final String TEST_PAGE_URL = "https://daviktapes.com/";
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
    public void openTestPage() {
        driver = SharedDriver.getWebDriver();
        driver.get(TEST_PAGE_URL);
    }

    @Test
    public void topMenuClick() {
        WebDriverWait wait = new WebDriverWait(driver, 3);

        driver.findElement(By.xpath("//a[text()='Company']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='About us']")));

        driver.findElement(By.xpath("//a[text()='Products']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Splicing Tapes']")));

        driver.findElement(By.xpath("//a[text()='Industries']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Agriculture']")));

        driver.findElement(By.xpath("//a[text()='Knowledge Center']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Articles']")));

        driver.findElement(By.xpath("//a[text()='CONTACT']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Email']")));
    }


    @Test
    public void companyDropDownTest() {
        WebElement companyDropMenu = driver.findElement(By.xpath("//a[text()='Company']"));
        Actions moveToCompany = new Actions(driver);
        moveToCompany.moveToElement(companyDropMenu).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='About us']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Quality']")));
    }

    @Test
    public void productsDropDownTest() {
        WebElement productsDropMenu = driver.findElement(By.xpath("//a[text()='Products']"));
        Actions moveToCompany = new Actions(driver);
        moveToCompany.moveToElement(productsDropMenu).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Splicing Tapes']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Pest Control']")));
    }

    @Test
    public void industriesDropDownTest() {
        WebElement industriesDropMenu = driver.findElement(By.xpath("//a[text()='Industries']"));
        Actions moveToCompany = new Actions(driver);
        moveToCompany.moveToElement(industriesDropMenu).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Agriculture']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Retail']")));
    }

    @Test
    public void knowledgeDropDownTest() {
        WebElement knowledgeDropMenu = driver.findElement(By.xpath("//a[text()='Knowledge Center']"));
        Actions moveToCompany = new Actions(driver);
        moveToCompany.moveToElement(knowledgeDropMenu).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Articles']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Events']")));
    }
}
