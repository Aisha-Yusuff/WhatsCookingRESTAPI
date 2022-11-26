package com.aishayusuff.RecipeApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .imageURI("https://recipeapi-images.s3.eu-west-2.amazonaws.com/porridgeandfruit.jpg")
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
        Set<Instruction> instructionSet = new HashSet<>((Arrays.asList(porridgeStep1, porridgeStep2, porridgeStep3)));
//      Add instructions to recipe
        porridgeRecipe.setInstructions(instructionSet);
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
    public void shouldReturnARecipeById() {
//        given
        Recipe existingRecipe = getDefaultRecipe();
        existingRecipe.setId(1L);
        given(recipeRepository.findById(1L)).willReturn(Optional.of(existingRecipe));

//        when
       Optional<Recipe> actualRecipe = recipeService.getById(1L);
//        then
        verify(recipeRepository).findById(1L);
        assertEquals(Optional.of(existingRecipe), actualRecipe);
    }

    @Test
    public void shouldAddNewRecipe() {
//        given
//        build a new recipe to add to recipe list
        Recipe newRecipe = Recipe.builder()
                .name("Veggie Soup for One")
                .imageURI("https://recipeapi-images.s3.eu-west-2.amazonaws.com/newRecipe.jpg")
                .build();
        Ingredient exampleIngredient = new Ingredient("Stock Cubes", "2", newRecipe.getId());
        Instruction exampleInstruction = new Instruction (1, "In a pot, boil water and add the stock cubes", newRecipe.getId());
        newRecipe.setIngredients(Set.of(exampleIngredient));
        newRecipe.setInstructions(Set.of(exampleInstruction));

        given(recipeRepository.save(newRecipe)).willReturn(newRecipe);
//        when
        recipeService.addNewRecipe(newRecipe);
//        then
        verify(recipeRepository).save(newRecipe);
    }

    @Test
    public void shouldUpdateAnExistingRecipe()  {
//     given
        Recipe existingRecipe = getDefaultRecipe();
//        Create placeholder ID for existing recipe
        existingRecipe.setId(1L);
        recipeService.addNewRecipe(existingRecipe);

//        build "updated" recipe
        Recipe updatedRecipe = Recipe.builder()
                .name("Quick and Easy Vegan Porridge")
                .imageURI("https://recipeapi-images.s3.eu-west-2.amazonaws.com/porridgeandfruit.jpg")
                .build();
        Ingredient exampleIngredient = new Ingredient("Porridge Oats","100g", updatedRecipe.getId());
        Instruction exampleInstruction = new Instruction(1, "Cook the porridge for 10 mins", updatedRecipe.getId());
        updatedRecipe.setIngredients(Set.of(exampleIngredient));
        updatedRecipe.setInstructions(Set.of(exampleInstruction));
        given(recipeRepository.findById(any(Long.class))).willReturn(Optional.of(existingRecipe));

//        when
        recipeService.updateRecipeById(existingRecipe.getId(), updatedRecipe);
//        then
        verify(recipeRepository).save(updatedRecipe);
    }

        @Test
    public void shouldDeleteARecipe() {
//        given
        Recipe existingRecipe = getDefaultRecipe();
//        Create placeholder ID for existing recipe
        existingRecipe.setId(1L);
        recipeService.addNewRecipe(existingRecipe);

        given(recipeRepository.findById(any(Long.class))).willReturn(Optional.of(getDefaultRecipe()));

//        when - delete request
        recipeService.deleteRecipeById(existingRecipe.getId());
//        then
        verify(recipeRepository).deleteById(existingRecipe.getId());
    }

    @Test
    public void ShouldRetrieveRecipesByIngredientName() {
//        given
//        create "expected" list of ingredient - porridge oats for getDefaultRecipe
        Stream<Ingredient> porridgeOatsStream = getDefaultRecipe().getIngredients().stream()
                .filter(ingredient -> ingredient.getName().equals("Porridge Oats"));
        List<Ingredient> porridgeOats = porridgeOatsStream.collect(Collectors.toList());

        given(ingredientRepository.findByName(any(String.class))).willReturn(porridgeOats);
        given(recipeRepository.findById(porridgeOats.get(0).getRecipe_id())).willReturn(Optional.of(getDefaultRecipe()));

        recipeService.getByIngredientName("Porridge Oats");
        assertEquals(porridgeOats, ingredientRepository.findByName(("Porridge Oats")));

    }

}
