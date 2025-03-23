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
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;

@DisplayName("RecipeBook Tests")
class RecipeBookTest {
	
	RecipeBook recipeBook;
	
    @BeforeEach
    void setUp() {
        recipeBook = new RecipeBook();
        System.out.println("RecipeBook instance created.");
    }
    
    @Test
    @DisplayName("Test adding a recipe to an empty RecipeBook")
    void testAddRecipeValid() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setName("Cappuccino");
        recipe.setAmtCoffee("1");
        recipe.setAmtMilk("1");
        recipe.setAmtSugar("1");
        recipe.setAmtChocolate("1");
        recipe.setPrice("5");

        boolean result = recipeBook.addRecipe(recipe);
        assertTrue(result, "Recipe should be added successfully.");
    }

    @Test
    @DisplayName("Test adding an invalid recipe to the RecipeBook")
    void testAddRecipeInvalid() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setName("Cappuccino");
        recipe.setAmtCoffee("1");
        recipe.setAmtMilk("-1");
        recipe.setAmtSugar("1");
        recipe.setAmtChocolate("1");
        recipe.setPrice("5");

        assertThrows(RecipeException.class, () -> {
            recipeBook.addRecipe(recipe);
        }, "Should throw exception for invalid input.");
    }

    @Test
    @DisplayName("Test deleting a recipe from the RecipeBook")
    void testDeleteRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Cappuccino");
        recipeBook.addRecipe(recipe);

        String deletedRecipeName = recipeBook.deleteRecipe(0);
        assertEquals("Cappuccino", deletedRecipeName, "Recipe name should match.");
    }

    @Test
    @DisplayName("Test editing a recipe in the RecipeBook")
    void testEditRecipe() {
        Recipe oldRecipe = new Recipe();
        oldRecipe.setName("Cappuccino");
        recipeBook.addRecipe(oldRecipe);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Latte");

        String editedRecipeName = recipeBook.editRecipe(0, newRecipe);
        assertEquals("Cappuccino", editedRecipeName, "Old recipe name should be returned.");
    }

    @Test
    @DisplayName("Test for timeout scenario")
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void testTimeout() throws InterruptedException {
        Thread.sleep(500); 
        assertTrue(true, "Test passed within the time limit.");
    }

    @Test
    @DisplayName("Test parameterized input for price")
    @ParameterizedTest
    @ValueSource(strings = {"5", "10", "15"})
    void testSetPriceValid(String price) throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setPrice(price);
        assertNotNull(recipe.getPrice(), "Price should be set correctly.");
    }
    
    

    @AfterEach
    void tearDown() {
        recipeBook = null;
        System.out.println("Test case teardown complete.");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Test suite teardown complete.");
    }

}

