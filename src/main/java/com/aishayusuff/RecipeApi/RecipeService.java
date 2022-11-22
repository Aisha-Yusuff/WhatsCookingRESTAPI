package com.aishayusuff.RecipeApi;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe addNewRecipe(Recipe recipe);
    void updateRecipeById(Long id, Recipe veganPorridgeRecipe);
    void deleteRecipeById(Long id);
    List<Recipe> getByIngredientName(String ingredientName);
    Optional<Recipe> getById(Long recipeId);
}
