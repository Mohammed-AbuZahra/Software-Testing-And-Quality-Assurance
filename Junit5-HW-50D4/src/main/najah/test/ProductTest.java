package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Product;

@DisplayName("Product Tests")
public class ProductTest {
    Product p;

	@BeforeEach
	void setUp() throws Exception {
        p = new Product("Test Product", 100.0);
        System.out.println("New Product created");
	}
	
    @Test
    @DisplayName("Test valid product creation")
    void testValidProductCreation() {
        assertAll(
            () -> assertEquals("Test Product", p.getName(), "Product name should be 'Test Product'"),
            () -> assertEquals(100.0, p.getPrice(), "Product price should be 100.0"),
            () -> assertEquals(0, p.getDiscount(), "Initial discount should be 0")
        );
    }

    @Test
    @DisplayName("Test applying valid discount")
    void testApplyDiscount() {
        p.applyDiscount(30.0);
        assertEquals(70.0, p.getFinalPrice(), "Final price should be 70.0 after 30% discount");
    }

    @Test
    @DisplayName("Test applying invalid discount (greater than 50%)")
    void testApplyInvalidDiscountGreaterThan50() {
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(60.0), "Discount should not be greater than 50%");
    }

    @Test
    @DisplayName("Test applying invalid discount (negative value)")
    void testApplyInvalidDiscountNegative() {
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(-10.0), "Discount should not be negative");
    }

    @ParameterizedTest
    @CsvSource({"10, 90.0", "20, 80.0", "50, 50.0"})
    @DisplayName("Test final price with various discounts")
    void testFinalPriceWithDiscounts(double discount, double expectedPrice) {
        p.applyDiscount(discount);
        assertEquals(expectedPrice, p.getFinalPrice(), "Final price should match expected value");
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS) 
    @DisplayName("Test that applying discount doesn't take too long")
    void testDiscountPerformance() {
        p.applyDiscount(10.0);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("All ProductTest cases completed.");
    }
}

