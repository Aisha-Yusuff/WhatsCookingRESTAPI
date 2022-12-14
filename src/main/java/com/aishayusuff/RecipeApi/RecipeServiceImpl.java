package com.aishayusuff.RecipeApi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public List<Recipe> getAllRecipes() {

        return recipeRepository.findAll();
    }


    @Override
    public Optional<Recipe> getById(Long recipeId) {

        return recipeRepository.findById(recipeId);
    }


    @Override
    public Recipe addNewRecipe(Recipe recipe) {
//        For each new ingredient save the recipe's id in the recipe_id column
//        Cascade - saves recipe id for instructions also
        recipe.getIngredients().stream().forEach(ingredient -> ingredient.setRecipe_id(recipe.getId()));
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipeById(Long recipeId, Recipe updatedRecipe) {
//        Find the existing recipe
        if (recipeRepository.findById(recipeId).isEmpty()) {
            throw new IllegalStateException("This recipe cannot be found. Please check the recipe ID.");
        } else {
            updatedRecipe.setId(recipeId);
            return recipeRepository.save(updatedRecipe);
        }
    }

    @Override
    public void deleteRecipeById(Long recipeId) {
//        Find the recipe to delete
        Optional<Recipe> existingRecipe = recipeRepository.findById(recipeId);
        if (existingRecipe.isEmpty()) {
            throw new IllegalStateException("This recipe cannot be found. Please check the recipe ID.");
        } else {
            recipeRepository.deleteById(recipeId);
        }
    }

    @Override
    public List<Recipe> getByIngredientName(String ingredientName) {
//     find all occurrences of the ingredient in the ingredient table
        List<Ingredient> ingredientList = ingredientRepository.findByName(ingredientName);

        List<Recipe> recipeMatches = ingredientList.stream().map(ingredient -> {
            return recipeRepository.findById(ingredient.getRecipe_id()).get();
        }).collect(Collectors.toList());

        return recipeMatches;

    }
}


