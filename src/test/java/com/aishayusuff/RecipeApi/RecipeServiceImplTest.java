package com.aishayusuff.RecipeApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {
//    Mock the repo layer
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private IngredientRepository ingredientRepository;

    private RecipeServiceImpl recipeService;

    @BeforeEach
    public void setUp() {
        recipeService = new RecipeServiceImpl(recipeRepository, ingredientRepository);
    }

    @Test
    public void shouldReturnAllRecipes() {
//        given
//        build a new recipe
        Recipe porridgeRecipe = Recipe.builder()
                .name("Quick and Easy Porridge")
                .build();

//        create ingredients for the recipe
        Ingredient porridgeOats = new Ingredient("Porridge Oats", "50g", porridgeRecipe.getId());
        Ingredient milk = new Ingredient("Milk", "350ml", porridgeRecipe.getId());
        Ingredient sugar = new Ingredient( "Sugar", "1 Teaspoon", porridgeRecipe.getId());
//        create set to hold all ingredients in the recipe
        Set<Ingredient> ingredientsSet = new HashSet<>(Arrays.asList(porridgeOats, milk, sugar));
//      Add ingredients to recipe
        porridgeRecipe.setIngredients(ingredientsSet);

//      create instructions for the recipe
        Instruction porridgeStep1 = new Instruction(null, "Add your porridge oats to your saucepan.", porridgeRecipe.getId());
        Instruction porridgeStep2 = new Instruction(null, "Pour the milk into the saucepan.", porridgeRecipe.getId());
        Instruction porridgeStep3 = new Instruction(null, "Sprinkle a teaspoon of sugar in to the saucepan.", porridgeRecipe.getId());
        Instruction porridgeStep4 = new Instruction(null, "Cook on medium to low heat for 4-5 minutes and then serve and enjoy", porridgeRecipe.getId());
//      create list to hold all instructions in recipe
        List<Instruction> instructionList = List.of(porridgeStep1, porridgeStep2, porridgeStep3, porridgeStep4);
//      Add instructions to recipe
        porridgeRecipe.setInstructions(instructionList);
//      Add recipe to Recipe list
        List<Recipe> recipeList = List.of(porridgeRecipe);
        given(recipeRepository.findAll()).willReturn(recipeList);

//        when
        List<Recipe> actualRecipeList = recipeService.getAllRecipes();

//        then
        verify(recipeRepository).findAll();
        assertEquals(recipeList, actualRecipeList);

    }


    @Test
    public void shouldAddNewRecipe() {
//        given
//        build a new recipe to add to recipe list
        Recipe vegSoup = Recipe.builder()
                .name("Veggie Soup for One")
                .build();
//        create ingredients for the recipe
        Ingredient water = new Ingredient("Water", "500ml", vegSoup.getId());
        Ingredient carrot = new Ingredient("Carrot", "150g", vegSoup.getId());
        Ingredient celery = new Ingredient("Celery", "150g", vegSoup.getId());
        Ingredient potato = new Ingredient("Potato", "1", vegSoup.getId());
        Ingredient stockCubes = new Ingredient("Stock Cubes", "2", vegSoup.getId());
        Ingredient salt = new Ingredient("Salt", "1tbsp", vegSoup.getId());
        Ingredient blackPepper = new Ingredient("Black Pepper", "1tbsp", vegSoup.getId());
        Ingredient paprika = new Ingredient("Paprika", "1tbsp", vegSoup.getId());
//        create set to hold all ingredients in the recipe
        Set<Ingredient> soupIngredientSet = new HashSet<>(Arrays.asList
                (water, carrot, celery, potato, stockCubes, salt, blackPepper, paprika));
//        add ingredients to recipe
        vegSoup.setIngredients(soupIngredientSet);

//        create ingredients for the recipe
        Instruction vegSoupStep1 = new Instruction ("Pour the water in a medium-sized pot.", vegSoup.getId());
        Instruction vegSoupStep2 = new Instruction ("Add your stock cubes and seasonings.", vegSoup.getId());
        Instruction vegSoupStep3 = new Instruction ("Chop your veggies and place them in your pot.", vegSoup.getId());
        Instruction vegSoupStep4 = new Instruction ("Leave your soup to cook for 20 minutes on a medium heat.", vegSoup.getId());
        Instruction vegSoupStep5 = new Instruction ("If you like your veggies soft let them cook for another 10 minutes and then serve your soup.", vegSoup.getId());
//        create list to hold all instructions
        List<Instruction> instructionList = List.of(vegSoupStep1, vegSoupStep2, vegSoupStep3, vegSoupStep4, vegSoupStep5);
//        add instructions to the recipe
        vegSoup.setInstructions(instructionList);
        given(recipeRepository.save(vegSoup)).willReturn(vegSoup);

//        when
        recipeService.addNewRecipe(vegSoup);
//        then
        verify(recipeRepository).save(vegSoup);
    }

}
