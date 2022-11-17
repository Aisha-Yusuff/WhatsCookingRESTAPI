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
        System.out.println(recipe);
        return recipeRepository.save(recipe);
    }

    @Override
    public void updateRecipe(Long recipeId, Recipe updatedRecipe) {
//        Find the existing recipe
//        Optional<Recipe> existingRecipe = recipeRepository.findById(recipeId);
        if (recipeRepository.findById(recipeId).isPresent()) {
            updatedRecipe.setId(recipeId);
            recipeRepository.save(updatedRecipe);
        } else {
            throw new IllegalStateException("This recipe cannot be found");
        }
    }



}
