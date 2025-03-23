package main.najah.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Calculator;
@Execution(value = ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Calculator Tests")
public class CalculatorTest {
	
    Calculator calc;
    
    @BeforeAll
    static void beforeAll() {
    	System.out.println("CalculatorTest started");
    }
    
    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("New Calculator created.");
    }
    
    @Test
    @Order(1)
    @DisplayName("Test addition with valid inputs")
    void testAddition() {
        assertAll(
            () -> assertEquals(8, calc.add(3, 4, 1), "3+4+1 should be 8"),
            () -> assertEquals(10, calc.add(5, 5), "5+5 should be 10")
        );
    }
    
    @Test
    @Order(2)
    @DisplayName("Test division with valid inputs")
    void testDivision() {
    	assertEquals(4, calc.divide(12,3), "12/3 should give us 4");
    }
    
    @Test
    @Order(3)
    @DisplayName("Test division by zero throws an exception")
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0), "Should throw an Exception");
    }
    
    @ParameterizedTest
    @Order(4)
    @CsvSource({"1,1", "2,2", "5,120", "0,1"})
    @DisplayName("Test factorial with various inputs using CSV format")
    void testFactorial(int input, int expected) {
        assertEquals(expected, calc.factorial(input), "Factorial calculation failed");
    }


    @ParameterizedTest
    @Order(5)
    @ValueSource(ints = {-1, -5, -10})
    @DisplayName("Test factorial with negative numbers (Invalid Input)")
    void testFactorialWithNegativeNumbers(int input) {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(input), "Negative should throw exception");
    }
    
    @Test
    @Order(6)
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS) 
    @DisplayName("Test addition execution time shouldnt over the limit")
    void testAdditionPerformance() {
        calc.add(1, 2, 3, 4, 5); 
    }
    @Test
    @Disabled("This test currently fails because the division logic is not handling negative numbers properly. Fix is to check for negative divisor and update the divide method.")
    @DisplayName("Test division with negative numbers (Intentionally Failing Test)")
    void testDivisionWithNegativeNumbers() {
        assertEquals(-5, calc.divide(10, -2), "Expected result is -5, but the logic is incorrect.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed.");
    }
    
    @AfterAll
    static void afterAll() {
        System.out.println("All CalculatorTest cases completed.");
    }



    
}
