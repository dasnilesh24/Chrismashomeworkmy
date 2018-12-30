
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Chrismashomework1 {
    private static WebDriver driver;

    public void select_element(By by) {
        driver.findElement(by).click();
    }

    public void enter_text(By by, String value) {
        driver.findElement(by).sendKeys(value);
    }


    @BeforeTest
    public void openBrowser() {
        //set driver path
        System.setProperty("webdriver.chrome.driver", "src\\browserdriver\\chromedriver.exe");
        //asign driver
        driver = new ChromeDriver();
        //20second implicitywait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // open full screen
        // driver.manage().window().fullscreen();
        //open url
        driver.get("https://demo.nopcommerce.com");

    }

    //       @AfterTest
    public void closebrowser() {
        driver.quit();
    }

    @Test(priority = 1)
    public void user_should_register_succesfully() {
        //click register
        select_element(By.className("ico-register"));
        System.out.println("register button ok");
        //enter gender
        select_element(By.className("forcheckbox"));
        System.out.println("click on male");
        //enter male
        enter_text(By.id("FirstName"), "Nilesh");
        System.out.println("print NILESH");
        //Enter last name
        enter_text(By.id("LastName"), "PATEL");
        System.out.println("print patel");
        //date of birth
        Select day = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthDay\"]")));

        day.selectByIndex(5);
        Select month = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthMonth\"]")));
        month.selectByVisibleText("March");
        Select year = new Select(driver.findElement(By.xpath("//select[@name=\"DateOfBirthYear\"]")));
        year.selectByValue("1979");
        //enter email
        DateFormat dt = new SimpleDateFormat("MMddYYHHMMSS");
        Date date = new Date();
        String date1 = dt.format(date);
        enter_text(By.id("Email"), "dasabc" + date1 + "@gmail.com");
        System.out.println("print email");
        //enter company name
        enter_text(By.id("Company"), "east india");
        System.out.println("enter company name");
        WebElement checkbox = driver.findElement(By.xpath("//input[@id=\"Newsletter\"]"));
        checkbox.isSelected();
        //enter password
        enter_text(By.id("Password"), "das123");
        System.out.println("print password");
        //confirm password
        enter_text(By.id("ConfirmPassword"), "das123");
        System.out.println("confirm password");
        //register button
        select_element(By.id("register-button"));
        String actualRegisterSuccessmessage = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();
        System.out.println(actualRegisterSuccessmessage);
        String expectedresult = "Your registration completed";
        System.out.println("This test is pass");

        Assert.assertEquals("test case failed", actualRegisterSuccessmessage, expectedresult);
        //    select_element(By.xpath("class=\"ico-logout\""));

        // click continue
        select_element(By.xpath("//input[@name=\"register-continue\"]"));
    }

    @Test(priority = 2)
    public void emailafriend() {

        user_should_register_succesfully();

        //click on product phone
        select_element(By.xpath("//img[@alt=\"Picture of HTC One M8 Android L 5.0 Lollipop\"]"));
        DateFormat dt = new SimpleDateFormat("MMddYYHHMMSS");
        Date date = new Date();
        String date1 = dt.format(date);
        //click on email
        select_element(By.xpath("//input[@value=\"Email a friend\"]"));
        enter_text(By.id("FriendEmail"), "das123" + date1 + "@gmail.com");
        //enter my email address
        //   enter_text(By.id("YourEmailAddress"),"dasabc"+date1+"@gmail.com");
        //enter comment
        enter_text(By.id("PersonalMessage"), "this is very good phone");
        select_element(By.xpath("//input[@class=\"button-1 send-email-a-friend-button\"]"));
        String actualemailsentmessage = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();
        System.out.println("actualemailsentmessage");
        String expectedresult = "Your message has been sent.";
        Assert.assertEquals("test case failed", actualemailsentmessage, expectedresult);


    }
    //programme3

    @Test
    public void unregister_user_shouldnot_able_to_send_email() {
        //click on item
        select_element(By.xpath("//img[@alt=\"Picture of HTC One M8 Android L 5.0 Lollipop\"]"));
        DateFormat dt = new SimpleDateFormat("MMddYYHHMMSS");
        Date date = new Date();
        String date1 = dt.format(date);
        //click on email
        select_element(By.xpath("//input[@value=\"Email a friend\"]"));
        enter_text(By.id("FriendEmail"), "das123" + date1 + "@gmail.com");
        //enter my email address
        enter_text(By.id("YourEmailAddress"), "dasabc" + date1 + "@gmail.com");
        //enter comment
        enter_text(By.id("PersonalMessage"), "this is very good phone");
        select_element(By.xpath("//input[@class=\"button-1 send-email-a-friend-button\"]"));
        String acutalemailmessage = driver.findElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]/ul/li")).getText();
        System.out.println("actualemailmessage");
        String expectedresult = "Only registered customers can use email a friend feature";
        Assert.assertEquals("test case failed", acutalemailmessage, expectedresult);

    }
    // programme4

    @Test
    public void user_should_accept_terms_and_condition() {
        //click on product
        select_element(By.xpath("//img[@alt=\"Picture of HTC One M8 Android L 5.0 Lollipop\"]"));
        //add to cart
        select_element(By.xpath("//input[@id=\"add-to-cart-button-18\"]"));
        //click on cart
        select_element(By.xpath("//span[@class=\"cart-label\"]"));
        //select checkout
        select_element(By.id("checkout"));
        String actualmessage = driver.findElement(By.id("terms-of-service-warning-box")).getText();
        System.out.println("actualmessage");
        String expectedresult = "Please accept the terms of service before the next step.";
        Assert.assertEquals("test case failed", actualmessage, expectedresult);
    }
//   programme 5
    @Test
    public void user_able_to_buy_product_successfully() {
        user_should_register_succesfully();
        //click on product
        select_element(By.xpath("//img[@alt=\"Picture of HTC One M8 Android L 5.0 Lollipop\"]"));
        //add to cart
        select_element(By.xpath("//input[@id=\"add-to-cart-button-18\"]"));
        //click on cart
        select_element(By.xpath("//span[@class=\"cart-label\"]"));
        //select term of service
        select_element(By.xpath("//label[@for=\"termsofservice\"]"));
        //click on check out
        select_element(By.id("checkout"));
        //enter country
        Select country = new Select(driver.findElement(By.xpath("//select[@id=\"BillingNewAddress_CountryId\"]")));
        country.selectByVisibleText("India");
        //enter cityname

        enter_text(By.xpath("//input[@id=\"BillingNewAddress_City\"]"), "Mumbai");
        enter_text(By.xpath("//input[@id=\"BillingNewAddress_Address1\"]"), "simpoli road");
        enter_text(By.xpath("//input[@id=\"BillingNewAddress_ZipPostalCode\"]"), "mumbai 92");
        enter_text(By.xpath("//input[@id=\"BillingNewAddress_PhoneNumber\"]"), "0795123123");
        select_element(By.xpath("//div[@id=\"billing-buttons-container\"]/input"));
        select_element(By.xpath("//div[@id=\"shipping-method-buttons-container\"]"));
        select_element(By.xpath("//img[@alt=\"Credit Card\"]"));
        select_element(By.xpath("//div[@id=\"payment-method-buttons-container\"]"));
        enter_text(By.xpath("//input[@id=\"CardholderName\"]"), "Nilesh");
        enter_text(By.xpath("//input[@id=\"CardNumber\"]"), "4111 1111 1111 1111");
        Select month = new Select(driver.findElement(By.xpath("//select[@id=\"ExpireMonth\"]")));
        month.selectByValue("3");
        Select year = new Select(driver.findElement(By.xpath("//select[@id=\"ExpireYear\"]")));
        year.selectByValue("2020");
        enter_text(By.xpath("//input[@id=\"CardCode\"]"), "737");
        select_element(By.xpath("//div[@id=\"payment-info-buttons-container\"]"));
        select_element(By.xpath("//div[@id=\"confirm-order-buttons-container\"]"));
        String actualmessage = driver.findElement(By.xpath("//strong[contains(text(),\"Your order has been successfully processed!\")]")).getText();

        System.out.println("actualmessage");
        String expectedresult = "Your order has been successfully processed!";
        Assert.assertEquals("test case failed", actualmessage, expectedresult);

    }
    // programme 6
    @Test
    public void user_able_to_see_high_to_low_price() {
        user_should_register_succesfully();

        select_element(By.xpath("//img[@alt=\"Picture for category Electronics\"]"));
        select_element(By.xpath("//img[@alt=\"Picture for category Camera & photo\"]"));
        select_element(By.xpath("//select[@id=\"products-orderby\"]"));
        Select option = new Select(driver.findElement(By.xpath("//select[@id=\"products-orderby\"]")));
        option.selectByVisibleText("Price: High to Low");
        String price_of_first_product = driver.findElement(By.xpath("//span[contains(text(),\"$1,300.00\")]")).getText();
        String price_of_last_product = driver.findElement(By.xpath("//span[contains(text(),\"$530.00\")]")).getText();
        //$1300.00 needs to be trim to remove dollar sign and last 3 digits".00"
        //use index concept i.e index 0= first digit "1"
        String fp = price_of_first_product.substring(1, price_of_first_product.length() - 3);
        System.out.println(fp);
        //follow above step for last product
        String lp = price_of_last_product.substring(1, price_of_last_product.length() - 3);
        System.out.println(lp);
        //initialissing the final value for if loop
        String fp1 = "";
        for (int i = 0; i <= fp.length() - 1; i++) {
            //took trimvalue.length()-1 because need to run loop
            char c = fp.charAt(i);
            //to remove coma from the string
            if (c != ',') {
                fp1 = fp1 + c;

            }
        }
        System.out.println(fp1);


        //change the string by predefine method`in integer value int
        int final_value_of__first_product_in_int = Integer.parseInt(fp1);
        System.out.println(fp1);

        int final_vale_of_last_product_in_int = Integer.parseInt(lp);
        System.out.println(lp);
        //if value to use assert statement

             if (final_value_of__first_product_in_int>final_vale_of_last_product_in_int) {
                        Assert.assertNotEquals(final_value_of__first_product_in_int,final_vale_of_last_product_in_int);
             }

    }}







