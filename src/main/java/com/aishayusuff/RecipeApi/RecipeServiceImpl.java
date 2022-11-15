package com.aishayusuff.RecipeApi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe addNewRecipe(Recipe recipe) {
//        For each new ingredient save the recipe's id in the recipe_id column
        recipe.getIngredients().stream().forEach( ingredient -> ingredient.setRecipe_id(recipe.getId()));
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(String recipeName, Recipe updatedRecipe) {
//        Find the "existing" recipe
        Optional<Recipe> existingRecipe = recipeRepository.findByName(recipeName);
        if (existingRecipe.isPresent()) {
            Long existingID = existingRecipe.get().getId();
            updatedRecipe.setId(existingID);
            recipeRepository.save(updatedRecipe);
            return updatedRecipe;
        }
        throw new IllegalStateException("This recipe cannot be found");
    }

}
