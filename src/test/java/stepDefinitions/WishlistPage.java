package stepDefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class WishlistPage extends BaseTestPage {
    private final By booksCardLocators = By.xpath("//div[@class='category-card__poster']");
    private final By booksTitleLocators = By.xpath("//div[@class='category-wrapper']//a");
    private final By wishlistHeaderLocator = By.xpath("//div[@class='wishlist__intro']//h1");
    private final By bookTitleLocator = By.xpath("//section[@class='preview']//div[@class='base-product__title']/h1");
    private final By addToBasketButtonLocator = By.xpath("//section[@class='preview']//button[@class='ui-btn-secondary add-to-cart']");
    private final By okButtonLocator = By.xpath("//button[contains(text(),'OK')]");
    private final By closeBookPageLocator = By.xpath("//section[@class='product-layout']//button[contains(@class,'close')]");

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public WishlistPage verifyWishlistHeader() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistHeaderLocator));
        Assertions.assertEquals(driver.findElement(wishlistHeaderLocator).getText().trim(), "Бажане", "Wishlist page isn't displayed");
        return this;
    }

    public WishlistPage selectRandomBook() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(booksCardLocators));
        List<WebElement> bookElements = driver.findElements(booksCardLocators);
        int randomIndex = new Random().nextInt(bookElements.size());
        WebElement randomBook = bookElements.get(randomIndex);
        randomBook.findElement(By.xpath(".//a")).click();
        return this;
    }

    public String getSelectedBookTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(bookTitleLocator));
        WebElement titleElement = driver.findElement(bookTitleLocator);
        JavascriptExecutor titleExecutor = (JavascriptExecutor) driver;
        String text = (String) titleExecutor.executeScript("return arguments[0].textContent;", titleElement);
        return text.replace("Книга", "").trim();
    }

    public WishlistPage addBookToBasket() {
        WebElement element = driver.findElement(addToBasketButtonLocator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        wait.until(ExpectedConditions.presenceOfElementLocated(okButtonLocator));
        driver.findElement(okButtonLocator).click();
        return this;
    }

    public WishlistPage closeBookModalWindow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeBookPageLocator));
        driver.findElement(closeBookPageLocator).click();
        return this;
    }

    public WishlistPage verifyBookPresents(String name){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(booksTitleLocators));
        List<WebElement> bookElements = driver.findElements(booksTitleLocators);
        boolean isBookPresent = bookElements.stream().map(element -> element.getAttribute("title"))
                .anyMatch(text -> text.contains(name));
        Assertions.assertTrue(isBookPresent);
        return this;
    }
}
