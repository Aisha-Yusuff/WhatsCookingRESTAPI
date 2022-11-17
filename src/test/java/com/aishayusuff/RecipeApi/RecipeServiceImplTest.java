package com.aishayusuff.RecipeApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

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

    public Recipe getDefaultRecipe() {
//        build recipe
        Recipe porridgeRecipe = Recipe.builder()
                .name("Quick and Easy Porridge")
                .build();

//        create ingredients for the recipe
        Ingredient porridgeOats = new Ingredient("Porridge Oats", "50g", porridgeRecipe.getId());
        Ingredient milk = new Ingredient("Milk", "350ml", porridgeRecipe.getId());
//        create set to hold all ingredients in the recipe
        Set<Ingredient> IngredientsSet = new HashSet<>(Arrays.asList(porridgeOats, milk));
//      Add ingredients to recipe
        porridgeRecipe.setIngredients(IngredientsSet);

//      create instructions for the recipe
        Instruction porridgeStep1 = new Instruction(1,"Add your porridge oats to your saucepan.", porridgeRecipe.getId());
        Instruction porridgeStep2 = new Instruction(2, "Pour the milk into the saucepan.", porridgeRecipe.getId());
        Instruction porridgeStep3 = new Instruction(3, "Cook on medium to low heat for 4-5 minutes and then serve and enjoy",
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
        Set<Ingredient> soupIngredientSet = new HashSet<>(Arrays.asList(water, carrot, potato, stockCubes, blackPepper));
//        add ingredients to recipe
        vegSoup.setIngredients(soupIngredientSet);

//        create ingredients for the recipe
        Instruction vegSoupStep1 = new Instruction (1, "Boil the water, stock cubes and black pepper in a medium-sized pot.", vegSoup.getId());
        Instruction vegSoupStep2 = new Instruction (2, "Chop your veggies and place them in your pot.", vegSoup.getId());
        Instruction vegSoupStep3 = new Instruction (3, "Leave your soup to cook for 25 minutes on a medium heat and then serve.", vegSoup.getId());
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
    public void shouldUpdateAnExistingRecipe()  {
//     given
        Recipe existingRecipe = getDefaultRecipe();
//        Create placeholder ID for existing recipe
        existingRecipe.setId(1L);
        recipeService.addNewRecipe(existingRecipe);

//        build "updated" recipe
        Recipe veganPorridgeRecipe = Recipe.builder()
                .name("Quick and Easy Vegan Porridge")
                .build();

//        create ingredients for the recipe
        Ingredient veganPorridgeOats = new Ingredient("Porridge Oats", "50g", veganPorridgeRecipe.getId());
        Ingredient oatMilk = new Ingredient("Oat Milk", "350ml", veganPorridgeRecipe.getId());
//        create set to hold all ingredients in the recipe
        Set<Ingredient> porridgeIngredientsSet = new HashSet<>(Arrays.asList(veganPorridgeOats, oatMilk));
//      Add ingredients to recipe
        veganPorridgeRecipe.setIngredients(porridgeIngredientsSet);

//      create instructions for the recipe
        Instruction veganPorridgeStep1 = new Instruction(1, "Add your porridge oats to your saucepan.", veganPorridgeRecipe.getId());
        Instruction veganPorridgeStep2 = new Instruction(2, "Pour the Oat Milk into the saucepan.", veganPorridgeRecipe.getId());
        Instruction veganPorridgeStep3 = new Instruction( 3, "Cook on medium to low heat for 4-5 minutes and then serve and enjoy", veganPorridgeRecipe.getId());
//      create list to hold all instructions in recipe
        List<Instruction> porridgeInstructionList = List.of(veganPorridgeStep1, veganPorridgeStep2, veganPorridgeStep3);
//      Add instructions to recipe
        veganPorridgeRecipe.setInstructions(porridgeInstructionList);

        given(recipeRepository.findById(any(Long.class))).willReturn(Optional.of(existingRecipe));

//        when
        recipeService.updateRecipe(existingRecipe.getId(), veganPorridgeRecipe);
//        then
        verify(recipeRepository).save(veganPorridgeRecipe);
    }


}
