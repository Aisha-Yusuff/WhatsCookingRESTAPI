package com.aishayusuff.RecipeApi;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe addNewRecipe(Recipe recipe);
    void updateRecipe(Long recipeId, Recipe updatedRecipe);
}
