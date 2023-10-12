package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class YakabooSteps {
    WebDriver driver;
    private String orderBookTitle;
    private String wishlistBookTitle;

    @Given("I launch chrome browser")
    public void i_launch_chrome_browser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\Course_ITEA\\courseIteaFramework\\src\\main\\resources\\driveres\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @And("I open yakaboo.com homepage")
    public void i_open_yakaboo_com_homepage() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.yakaboo.ua/");
        HomePage homePage = new HomePage(driver);
        homePage.verifyHomePageHeader();
    }

    @And("I login to my account")
    public void i_login_to_my_account() {
        HomePage homePage = new HomePage(driver);
        homePage.loginToAccount("ntruddd@gmail.com", "Qwerty123").
                verifyProfileIsDisplayed();
    }

    @And("I clear my basket")
    public void i_clear_my_basket() {
        HomePage homePage = new HomePage(driver);
        homePage.openBasket()
                .clearBasket()
                .verifyHomePageHeader();
    }

    @And("I open my wishlist")
    public void i_open_my_wishlist() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnProfile()
                .openWishlistPage()
                .verifyWishlistHeader();
    }

    @And("I select any book from the wishlist")
    public void i_select_any_book_from_the_wishlist() {
        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.selectRandomBook();
    }

    @And("I add the book to basket")
    public void i_add_the_book_to_basket() {
        WishlistPage wishlistPage = new WishlistPage(driver);
        orderBookTitle = wishlistPage.getSelectedBookTitle();
        wishlistPage.addBookToBasket().closeBookModalWindow().verifyWishlistHeader();
    }

    @And("I open my basket")
    public void i_open_my_basket() {
        HomePage homePage = new HomePage(driver);
        homePage.openBasket();
    }

    @And("I check that book in my basket")
    public void i_check_that_book_in_my_basket() {
        HomePage homePage = new HomePage(driver);
        homePage.verifyBookPresentInBasket(orderBookTitle);
    }

    @When("I checkout my order")
    public void i_checkout_my_order() {
        HomePage homePage = new HomePage(driver);
        homePage.clickCheckoutOrder()
                .verifyPageTitle();
    }

    @And("Order processing section displayed with my personal info")
    public void order_processing_section_displayed_with_my_personal_info() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.verifyContactInfoSectionTitle()
                .verifyPersonalInfoPrePopulated();
    }

    @And("I fill out delivery form")
    public void i_fill_out_delivery_form() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.verifyDeliverySectionTitle()
                .fillDeliveryForm();
    }

    @And("I fill out payment form")
    public void i_fill_out_payment_form() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.verifyPaymentSectionTitle()
                .selectCashPaymentMethod();
    }

    @And("I leave a comment for my order")
    public void i_leave_a_comment_for_my_order() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.verifyCommentSectionTitle()
                .enterComment();
    }

    @Then("Payment button becomes available")
    public void payment_button_becomes_available() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.verifyCheckoutOrderButtonDisplayed();
    }

    @And("I close browser")
    public void i_close_browser() {
        driver.quit();
    }

    @And("I select readers choice category")
    public void i_select_readers_choice_category() {
        HomePage homePage = new HomePage(driver);
        homePage.selectReadersChoiceCategory();
    }

    @And("I open any book from the list")
    public void i_open_any_book_from_the_list() {
        HomePage homePage = new HomePage(driver);
        wishlistBookTitle = homePage.openRandomBook().getBookTitle();
    }

    @Given("I click add to wishlist")
    public void i_click_add_to_wishlist() {
        HomePage homePage = new HomePage(driver);
        homePage.clickAddToWishlist()
                .addToWishlist()
                .clickSaveToWishlist()
                .closeBookModalWindow();
    }

    @Then("My book in wishlist")
    public void my_book_in_wishlist() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnProfile()
                .openWishlistPage()
                .verifyWishlistHeader()
                .verifyBookPresents(wishlistBookTitle);
    }
}
