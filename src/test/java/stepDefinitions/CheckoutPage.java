package stepDefinitions;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BaseTestPage {
    private final By checkoutTabHeaderLocator = By.xpath("//section[@class='cart-block']//h2");
    private final By nameFieldLocator = By.xpath("//input[@name='first name' and @autocomplete]");
    private final By lastNameFieldLocator = By.xpath("//input[@name='last name' and @autocomplete]");
    private final By emailFieldLocator = By.xpath("//input[@name='email' and @autocomplete]");
    private final By telephoneFieldLocator = By.xpath("//input[@type='tel' and @autocomplete]");
    private final By personalDetailsHeaderLocator = By.xpath("//section[@class='personal-details']/h2");
    private final By deliveryHeaderLocator = By.xpath("//section[@class='delivery-methods']/h2");
    private final By paymentHeaderLocator = By.xpath("//section[@class='payment-methods']/h2");
    private final By commentHeaderLocator = By.xpath("//div[@class='order-comment']/h2");
    private final By cityFieldLocator = By.xpath("//input[@placeholder='Введіть назву міста...']");
    private final By kyivOptionLocator = By.xpath("//li[@class='item nested' and contains(text(),'Київ')]");
    private final By deliveryOptionsLocator = By.xpath("//input[@value='Самовивіз із книгарні Yakaboo, Хрещатик 22, в Головпоштамті']");
    private final By spinnerLocator = By.xpath("//div[@class='ui-loader delivery-loader']");
    private final By cashPaymentMethodLocator = By.xpath("//input[@id='cashondelivery']");
    private final By commentFieldLocator = By.xpath("//div[contains(@class,'order-comment')]//textarea");
    private final By checkoutOrderButtonLocator = By.xpath("//section[@class='order-submit']//button[contains(text(),'Підтвердити замовлення')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage verifyPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTabHeaderLocator));
        Assertions.assertEquals(driver.findElement(checkoutTabHeaderLocator).getText().trim(), "Оформлення замовлення");
        return this;
    }

    public CheckoutPage verifyContactInfoSectionTitle() {
        Assertions.assertEquals(driver.findElement(personalDetailsHeaderLocator).getText().trim(), "Контактні дані");
        return this;
    }

    public CheckoutPage verifyDeliverySectionTitle() {
        Assertions.assertEquals(driver.findElement(deliveryHeaderLocator).getText().trim(), "Доставка");
        return this;
    }

    public CheckoutPage verifyPaymentSectionTitle() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        Assertions.assertEquals(driver.findElement(paymentHeaderLocator).getText().trim(), "Спосіб оплати");
        return this;
    }

    public CheckoutPage verifyCommentSectionTitle() {
        Assertions.assertEquals(driver.findElement(commentHeaderLocator).getText().trim(), "Коментар до замовлення");
        return this;
    }

    public CheckoutPage fillDeliveryForm() {
        driver.findElement(cityFieldLocator).click();
        driver.findElement(kyivOptionLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        driver.findElement(deliveryOptionsLocator).click();
        return this;
    }

    public CheckoutPage verifyPersonalInfoPrePopulated() {
        Assertions.assertTrue(driver.findElement(nameFieldLocator).isDisplayed());
        Assertions.assertTrue(driver.findElement(lastNameFieldLocator).isDisplayed());
        Assertions.assertTrue(driver.findElement(emailFieldLocator).isDisplayed());
        Assertions.assertTrue(driver.findElement(telephoneFieldLocator).isDisplayed());
        return this;
    }

    public CheckoutPage selectCashPaymentMethod() {
        driver.findElement(cashPaymentMethodLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        return this;
    }

    public CheckoutPage enterComment() {
        driver.findElement(commentFieldLocator).sendKeys("Test");
        return this;
    }

    public CheckoutPage verifyCheckoutOrderButtonDisplayed() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        Assertions.assertTrue(driver.findElement(checkoutOrderButtonLocator).isEnabled());
        return this;
    }

}
