package stepDefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class HomePage extends BaseTestPage {

    private final By headerLocator = By.xpath("//div[@class='home-proposal']//h1");
    private final By loginButtonLocator = By.xpath("//button[@class='ui-btn-account']");
    private final By authorizationPopupHeaderLocator = By.xpath("//div[contains(text(),'Вхід до акаунту')]");
    private final By inputNameLocator = By.xpath("//input[@name='auth_login']");
    private final By inputPasswordLocator = By.xpath("//input[@name='auth_password']");
    private final By authorizationLoginButtonLocator = By.xpath("//button[@class='ui-btn-primary' and contains(text(),'Увійти')]");
    private final By profileLocator = By.xpath("//div[@class='ui-btn-profile btn-profile']/span");
    private final By wishlistTabLocator = By.xpath("//span[contains(text(), 'Бажані книги')]");
    private final By basketButtonLocator = By.xpath("//button[@class='ui-btn-shopping-cart']");
    private final By basketHeaderLocator = By.xpath("//span[@class='header-title']");
    private final By bookLinkInBasketLocator = By.xpath("//a[@data-testid='productLink']");
    private final By checkoutOrderButtonLocator = By.xpath("//button[contains(text(),'Перейти до оформлення замовлення')]");
    private final By readersChoiceCategoryLinkLocator = By.xpath("//a[contains(text(),'Вибір читачів')]");
    private final By categoryTitleLocator = By.xpath("//div[@class='category__name']//h1");
    private final By booksCardLocators = By.xpath("//div[@class='category-card__poster']");
    private final By bookTitleLocator = By.xpath("//section[@class='preview']//div[@class='base-product__title']/h1");
    private final By addToWishlistButtonLocator = By.xpath("//section[@class='preview']//button[@title='До бажаного']");
    private final By wishlistInputLocator = By.xpath("//div[@id='createWishlist']//input[@name='createWishlist']");
    private final By saveToWishlistLocator = By.xpath("//div[@class='wish__buttons']/button[contains(text(),'Зберегти')]");
    private final By closeBookPageLocator = By.xpath("//section[@class='product-layout']//button[contains(@class,'close')]");
    private final By clearBasketButtonLocator = By.xpath("//span[contains(text(),'Видалити все')]");
    private final By okButtonLocator = By.xpath("//button[contains(text(),'OK')]");
    private final By closeModuleButtonLocator = By.xpath("//div[@class='header']//button[contains(@class,'close')]");
    private final By wishlistCheckboxLocator = By.xpath("//div[contains(@class,'wishlist_modal')]//input[@type='checkbox']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage verifyHomePageHeader() {
        Assertions.assertEquals(driver.findElement(headerLocator).getText(), "Нація, що читає – непереможна!", "Yakaboo home page isn't displayed");
        return this;
    }

    public HomePage loginToAccount(String name, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonLocator));
        driver.findElement(loginButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(authorizationPopupHeaderLocator));
        Assertions.assertTrue(driver.findElement(authorizationPopupHeaderLocator).isDisplayed());
        driver.findElement(inputNameLocator).sendKeys(name);
        driver.findElement(inputPasswordLocator).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(authorizationLoginButtonLocator));
        driver.findElement(authorizationLoginButtonLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(authorizationLoginButtonLocator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(authorizationLoginButtonLocator));
        return this;
    }

    public HomePage verifyProfileIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileLocator));
        Assertions.assertTrue(driver.findElement(profileLocator).isDisplayed());
        return this;
    }

    public HomePage clickOnProfile() {
        driver.findElement(profileLocator).click();
        return this;
    }

    public WishlistPage openWishlistPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistTabLocator));
        driver.findElement(wishlistTabLocator).click();
        return new WishlistPage(driver);
    }

    public HomePage openBasket() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(basketButtonLocator));
        driver.findElement(basketButtonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(basketHeaderLocator));
        Assertions.assertEquals(driver.findElement(basketHeaderLocator).getText().trim(), "Кошик");
        return this;
    }

    public HomePage clearBasket() {
        driver.findElement(clearBasketButtonLocator).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(okButtonLocator));
        driver.findElement(okButtonLocator).click();
        WebElement closeModuleButton = driver.findElement(closeModuleButtonLocator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", closeModuleButton);
        return this;
    }

    public HomePage verifyBookPresentInBasket(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookLinkInBasketLocator));
        Assertions.assertEquals(driver.findElement(bookLinkInBasketLocator).getText().trim(), name);
        return this;
    }

    public HomePage selectReadersChoiceCategory() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(readersChoiceCategoryLinkLocator));
        driver.findElement(readersChoiceCategoryLinkLocator).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(categoryTitleLocator));
        Assertions.assertEquals(driver.findElement(categoryTitleLocator).getText().trim(), "Вибір читачів");
        return this;
    }

    public HomePage openRandomBook() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(booksCardLocators));
        List<WebElement> bookElements = driver.findElements(booksCardLocators);
        int randomIndex = new Random().nextInt(bookElements.size());
        WebElement randomBook = bookElements.get(randomIndex);
        randomBook.findElement(By.xpath(".//a")).click();
        return this;
    }

    public String getBookTitle() {
        wait.until(ExpectedConditions.presenceOfElementLocated(bookTitleLocator));
        WebElement titleElement = driver.findElement(bookTitleLocator);
        JavascriptExecutor titleExecutor = (JavascriptExecutor) driver;
        String text = (String) titleExecutor.executeScript("return arguments[0].textContent;", titleElement);
        return text.replace("Книга", "").trim();
    }

    public HomePage clickAddToWishlist() {
        WebElement addToWishlist = driver.findElement(addToWishlistButtonLocator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", addToWishlist);
        return this;
    }

    public HomePage addToWishlist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistInputLocator));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement checkbox = driver.findElement(wishlistCheckboxLocator);
        executor.executeScript("arguments[0].click();", checkbox);
        return this;
    }

    public HomePage clickSaveToWishlist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveToWishlistLocator));
        WebElement saveToWishlist = driver.findElement(saveToWishlistLocator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", saveToWishlist);
        return this;
    }

    public HomePage closeBookModalWindow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeBookPageLocator));
        driver.findElement(closeBookPageLocator).click();
        return this;
    }

    public CheckoutPage clickCheckoutOrder() {
        driver.findElement(checkoutOrderButtonLocator).click();
        return new CheckoutPage(driver);
    }
}
