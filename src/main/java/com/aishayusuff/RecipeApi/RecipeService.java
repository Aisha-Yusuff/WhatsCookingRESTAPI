package com.aishayusuff.RecipeApi;

import java.util.List;
import java.util.Set;

public interface RecipeService {
    List<Recipe> getAllRecipes();

//    Recipe addNewRecipe(Recipe recipe, Set<Ingredient> ingredients);
    Recipe addNewRecipe(Recipe recipe);
}
