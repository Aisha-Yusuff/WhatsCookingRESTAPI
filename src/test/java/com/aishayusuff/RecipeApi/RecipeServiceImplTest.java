package com.aishayusuff.RecipeApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {
//    Mock the repo layer
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private IngredientRepository ingredientRepository;

    private RecipeServiceImpl recipeService;

    public Recipe getDefaultRecipe() {
//        build recipe
        Recipe porridgeRecipe = Recipe.builder()
                .name("Quick and Easy Porridge")
                .build();

//        create ingredients for the recipe
        Ingredient porridgeOats = new Ingredient("Porridge Oats", "50g", porridgeRecipe.getId());
        Ingredient milk = new Ingredient("Milk", "350ml", porridgeRecipe.getId());
//        create set to hold all ingredients in the recipe
        List<Ingredient> ingredientsList = List.of(porridgeOats, milk);
//      Add ingredients to recipe
        porridgeRecipe.setIngredients(ingredientsList);

//      create instructions for the recipe
        Instruction porridgeStep1 = new Instruction(null, "Add your porridge oats to your saucepan.", porridgeRecipe.getId());
        Instruction porridgeStep2 = new Instruction(null, "Pour the milk into the saucepan.", porridgeRecipe.getId());
        Instruction porridgeStep3 = new Instruction(null, "Cook on medium to low heat for 4-5 minutes and then serve and enjoy",
                porridgeRecipe.getId());
//      create list to hold all instructions in recipe
        List<Instruction> instructionList = List.of(porridgeStep1, porridgeStep2, porridgeStep3);
//      Add instructions to recipe
        porridgeRecipe.setInstructions(instructionList);

        return porridgeRecipe;
    }

    @BeforeEach
    public void setUp() {

        recipeService = new RecipeServiceImpl(recipeRepository, ingredientRepository);
    }

    @Test
    public void shouldReturnAllRecipes() {
//        given
//      Add recipe to Recipe list
        List<Recipe> recipeList = List.of(getDefaultRecipe());
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
        Ingredient potato = new Ingredient("Potato", "1", vegSoup.getId());
        Ingredient stockCubes = new Ingredient("Stock Cubes", "2", vegSoup.getId());
        Ingredient blackPepper = new Ingredient("Black Pepper", "1tbsp", vegSoup.getId());
//        create set to hold all ingredients in the recipe
        List<Ingredient> soupIngredientSet = List.of(water, carrot, potato, stockCubes, blackPepper);
//        add ingredients to recipe
        vegSoup.setIngredients(soupIngredientSet);

//        create ingredients for the recipe
        Instruction vegSoupStep1 = new Instruction ("Boil the water, stock cubes and black pepper in a medium-sized pot.", vegSoup.getId());
        Instruction vegSoupStep2 = new Instruction ("Chop your veggies and place them in your pot.", vegSoup.getId());
        Instruction vegSoupStep3 = new Instruction ("Leave your soup to cook for 25 minutes on a medium heat and then serve.", vegSoup.getId());
//        create list to hold all instructions
        List<Instruction> instructionList = List.of(vegSoupStep1, vegSoupStep2, vegSoupStep3);
//        add instructions to the recipe
        vegSoup.setInstructions(instructionList);

        given(recipeRepository.save(vegSoup)).willReturn(vegSoup);
//        when
        recipeService.addNewRecipe(vegSoup);
//        then
        verify(recipeRepository).save(vegSoup);
    }

    @Test
    public void shouldUpdateAnExistingRecipe() throws Exception {
//      Add recipe to Recipe list
        List<Recipe> recipeList = List.of(getDefaultRecipe());

//        build "updated" recipe
        Recipe veganPorridgeRecipe = Recipe.builder()
                .name("Quick and Easy Vegan Porridge")
                .build();

//        create ingredients for the recipe
        Ingredient veganPorridgeOats = new Ingredient("Porridge Oats", "50g", veganPorridgeRecipe.getId());
        Ingredient oatMilk = new Ingredient("Oat Milk", "350ml", veganPorridgeRecipe.getId());
        Ingredient sugars = new Ingredient( "Sugar", "1 Teaspoon", veganPorridgeRecipe.getId());
//        create set to hold all ingredients in the recipe
        List<Ingredient> porridgeIngredientsList = List.of(veganPorridgeOats, oatMilk, sugars);
//      Add ingredients to recipe
        veganPorridgeRecipe.setIngredients(porridgeIngredientsList);

//      create instructions for the recipe
        Instruction veganPorridgeStep1 = new Instruction(null, "Add your porridge oats to your saucepan.", veganPorridgeRecipe.getId());
        Instruction veganPorridgeStep2 = new Instruction(null, "Pour the Oat Milk into the saucepan.", veganPorridgeRecipe.getId());
        Instruction veganPorridgeStep3 = new Instruction(null, "Sprinkle a teaspoon of sugar in to the saucepan.", veganPorridgeRecipe.getId());
        Instruction veganPorridgeStep4 = new Instruction(null, "Cook on medium to low heat for 4-5 minutes and then serve and enjoy", veganPorridgeRecipe.getId());
//      create list to hold all instructions in recipe
        List<Instruction> porridgeInstructionList = List.of(veganPorridgeStep1, veganPorridgeStep2, veganPorridgeStep3, veganPorridgeStep4);
//      Add instructions to recipe
        veganPorridgeRecipe.setInstructions(porridgeInstructionList);

        given(recipeRepository.findByName(getDefaultRecipe().getName())).willReturn(Optional.of(getDefaultRecipe()));

//        when
        recipeService.updateRecipe(getDefaultRecipe().getName(), veganPorridgeRecipe);
//        then
        verify(recipeRepository).save(veganPorridgeRecipe);
        assertEquals(recipeService.updateRecipe(getDefaultRecipe().getName(), veganPorridgeRecipe), veganPorridgeRecipe);
    }

}
