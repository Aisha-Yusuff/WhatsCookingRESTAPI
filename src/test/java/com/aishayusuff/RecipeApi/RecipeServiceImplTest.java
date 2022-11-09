package com.aishayusuff.RecipeApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
//        build a recipe (without ingredients)
        Recipe porridgeRecipe = Recipe.builder()
                .name("Quick and easy Porridge")
                .instructions("""
                        1.Put your porridge oats in a saucepan.\s
                        2.Pour the milk into the saucepan.\s
                        3.Sprinkle a teaspoon of sugar in to the saucepan.\s
                        4. Cook on medium to low heat for 4-5 minutes and then serve and enjoy""")
                .build();

//        create ingredients for the recipe
        Ingredient porridgeOats = new Ingredient("Porridge Oats", "50g", porridgeRecipe.getId());
        Ingredient milk = new Ingredient("Milk", "350ml", porridgeRecipe.getId());
        Ingredient sugar = new Ingredient( "Sugar", "1 Teaspoon", porridgeRecipe.getId());
//        create set to hold all ingredients for recipe
        Set<Ingredient> ingredientsSet = new HashSet<>(Arrays.asList(porridgeOats, milk, sugar));
//      Add ingredients to recipe
        porridgeRecipe.setIngredients(ingredientsSet);

//      Add recipe to Recipe list
        List<Recipe> recipeList = List.of(porridgeRecipe);
//        given(ingredientRepository.findByRecipeId()).willReturn(ingredientsSet);
        given(recipeRepository.findAll()).willReturn(recipeList);

//        when
        List<Recipe> actualRecipeList = recipeService.getAllRecipes();

//        then
        verify(recipeRepository).findAll();
        assertEquals(recipeList, actualRecipeList);

    }

}
