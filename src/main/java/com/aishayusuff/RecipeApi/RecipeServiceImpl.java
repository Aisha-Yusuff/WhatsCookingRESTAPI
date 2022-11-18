package com.aishayusuff.RecipeApi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void updateRecipeById(Long recipeId, Recipe updatedRecipe) {
//        Find the existing recipe
//        Optional<Recipe> existingRecipe = recipeRepository.findById(recipeId);
        if (recipeRepository.findById(recipeId).isPresent()) {
            updatedRecipe.setId(recipeId);
            recipeRepository.save(updatedRecipe);
        } else {
            throw new IllegalStateException("This recipe cannot be found");
        }
    }
    @Override
    public void deleteRecipeById(Long recipeId) {
//        Find the recipe to delete
        Optional<Recipe> existingRecipe = recipeRepository.findById(recipeId);
        if(existingRecipe.isPresent()){
            recipeRepository.deleteById(recipeId);
        } else {
            throw new IllegalStateException("This recipe cannot be found");
        }

    }

    @Override
    public List<Recipe> getByIngredientName(String ingredientName) {
//     find all occurrences of the ingredient in the ingredient table
        List<Ingredient> ingredientList = recipeRepository.findByIngredientName(ingredientName);
        if (!ingredientList.isEmpty()) {
            List<Recipe> recipesList = new ArrayList<>();

//        extract the recipe id from all ingredient in the list
            for (Ingredient ingredient : ingredientList) {
                Long recipeId = ingredient.getRecipe_id();
                Optional<Recipe> matchingRecipe = recipeRepository.findById(recipeId);
                recipesList.add(matchingRecipe.get());
            }
            return recipesList;

        } else {
            throw new IllegalStateException("This recipe cannot be found");
        }
    }

}
