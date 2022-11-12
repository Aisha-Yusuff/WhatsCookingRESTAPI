package com.aishayusuff.RecipeApi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public List<Recipe> getAllRecipes() {

//        return recipeRepository.findAll();
        return (List<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe addNewRecipe(Recipe recipe) {
//        For each new ingredient save the recipe's id in recipe_id
        recipe.getIngredients().stream().forEach( ingredient -> ingredient.setRecipe_id(recipe.getId()));
        return recipeRepository.save(recipe);
    }

}
