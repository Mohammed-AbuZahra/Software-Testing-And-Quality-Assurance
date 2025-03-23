package main.najah.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;

import main.najah.code.UserService;

@DisplayName("UserService Tests")
class UserServiceTest {
	UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        userService = new UserService();
        System.out.println("New UserService created.");
    }
    
    @Test
    @DisplayName("Test valid email")
    void testValidEmail() {
        assertTrue(userService.isValidEmail("test@example.com"), "Email should be valid.");
    }

    @Test
    @DisplayName("Test invalid email (missing @)")
    void testInvalidEmailMissingAt() {
        assertFalse(userService.isValidEmail("testexample.com"), "Email should be invalid without @.");
    }

    @Test
    @DisplayName("Test invalid email (missing .)")
    void testInvalidEmailMissingDot() {
        assertFalse(userService.isValidEmail("test@examplecom"), "Email should be invalid without .");
    }

    @Test
    @DisplayName("Test valid authentication")
    void testValidAuthentication() {
        assertTrue(userService.authenticate("admin", "1234"), "Authentication should be successful for valid credentials.");
    }

    @Test
    @DisplayName("Test invalid authentication (wrong username)")
    void testInvalidAuthenticationWrongUsername() {
        assertFalse(userService.authenticate("user", "1234"), "Authentication should fail for incorrect username");
    }

    @Test
    @DisplayName("Test invalid authentication (wrong password)")
    void testInvalidAuthenticationWrongPassword() {
        assertFalse(userService.authenticate("admin", "wrongpassword"), "Authentication should fail for incorrect password.");
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Test authentication performance")
    void testAuthenticationPerformance() {
        userService.authenticate("admin", "1234");
    }



    @AfterEach
    void tearDown() {
        System.out.println("Test completed.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("All UserServiceTest cases completed.");
    }
}

