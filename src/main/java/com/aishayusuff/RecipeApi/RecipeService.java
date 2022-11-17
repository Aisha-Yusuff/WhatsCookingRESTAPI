package com.aishayusuff.RecipeApi;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe addNewRecipe(Recipe recipe);
    void updateRecipeById(Long id, Recipe veganPorridgeRecipe);
    void deleteRecipeById(Long id);
}
