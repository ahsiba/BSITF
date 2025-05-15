package org.cardinality.tests.Registration;

import org.cardinality.base.BaseTest;
import org.cardinality.pages.LoginPage;
import org.cardinality.pages.RegistrationPage;
import org.cardinality.utils.ConfigReader;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TC_01 extends BaseTest {

    private String username = "mshivam@quodeworks.com"; // Replace with actual username
    private String password = "Mukul@@1997";            // Replace with actual password
    private static final String URL = "https://gcm-dev.cardinality.info/login";




    @Test
    public void shouldRegisterNewApplicant() {
        page.navigate(URL);

        // Initialize ConfigReader with the properties file
        ConfigReader.init("registration.properties");

        RegistrationPage regPage = new RegistrationPage(page);
        // Click on the "Register" button
        regPage.ClickRegister();

        regPage.fillRegistrationForm(
            ConfigReader.get("firstName"),
            ConfigReader.get("middleName"),
            ConfigReader.get("lastName"),
            ConfigReader.get("gender"),
            ConfigReader.get("dob"),
            ConfigReader.get("email"),
            ConfigReader.get("phone"),
            ConfigReader.get("ssn"),
            ConfigReader.get("address"),
            ConfigReader.get("city"),
            ConfigReader.get("county"),
            ConfigReader.get("zip")
        );
        regPage.submit();
        assertTrue(
            regPage.isBackToLoginVisible(),
            "‘Back to Login’ link should appear after successful registration"
        );
    }

    @Test
    public void testLogin() {
        page.navigate(URL);
        LoginPage loginPage = new LoginPage(page);
        boolean result = loginPage.login(username, password);
        assert result : "Login failed. Please check credentials or page locators.";
    }

   @Test
    public void testForgotAndResetPasswordFlow() {
        RegistrationPage registrationPage = new RegistrationPage(page);


        // Perform the Forgot Password and Reset Password process
        registrationPage.forgotAndResetPassword("testuser@example.com");
    }

    
}
