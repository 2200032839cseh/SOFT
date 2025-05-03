package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

import static junit.framework.Assert.*;

public class AmazonStepDefinitions {
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }

    @Given("User is on the Amazon homepage")
    public void user_is_on_amazon_homepage() {
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");
    }

    @Then("The title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.contains(expectedTitle));
        driver.quit();
    }

    @Given("User searches for product with ID {string}")
    public void user_searches_for_product_by_id(String productId) {
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(productId);
        searchBox.sendKeys(Keys.ENTER);
    }

    @Then("Product should be displayed in the results")
    public void product_should_be_displayed() {
        WebElement results = driver.findElement(By.cssSelector("div.s-main-slot"));
        assertTrue(results.isDisplayed());
        driver.quit();
    }

    @When("User searches for {string}")
    public void user_searches_for(String category) {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(category);
        searchBox.sendKeys(Keys.ENTER);
    }

    @Then("Search results for {string} should be displayed")
    public void search_results_should_be_displayed(String category) {
        WebElement results = driver.findElement(By.cssSelector("div.s-main-slot"));
        assertTrue(results.getText().toLowerCase().contains(category.toLowerCase()));
        driver.quit();
    }

    @When("User scrolls down the page")
    public void user_scrolls_down_the_page() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(2000); // Wait to allow scroll to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("Page should scroll successfully")
    public void page_should_scroll_successfully() {
        Long scrolled = (Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;");
        assertTrue("Scroll failed. PageYOffset is 0", scrolled > 0);
        driver.quit();
    }
}
