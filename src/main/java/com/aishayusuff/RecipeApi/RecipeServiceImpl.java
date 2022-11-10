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

        return (List<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe addNewRecipe(Recipe recipe, Ingredient ingredient) {
        ingredientRepository.save(ingredient);
        return recipeRepository.save(recipe);
    }

}
