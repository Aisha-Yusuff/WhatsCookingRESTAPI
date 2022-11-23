package com.aishayusuff.RecipeApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    ResponseEntity<List<Recipe>> getAllRecipes() {
        return new ResponseEntity<>(
                recipeService.getAllRecipes(),
                HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Recipe> addNewRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(
                recipeService.addNewRecipe(recipe),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long recipeId,
                                        @RequestBody Recipe updatedRecipe) {
        recipeService.updateRecipeById(recipeId, updatedRecipe);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    };
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Recipe> deleteRecipeById(@PathVariable("id") Long recipeId){
        recipeService.deleteRecipeById(recipeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/ingredient/{ingredientName}")
    ResponseEntity<List<Recipe>> getByIngredientName(@PathVariable("ingredientName") String ingredientName) {
        return new ResponseEntity<>(recipeService.getByIngredientName(ingredientName),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<Recipe> getById(@PathVariable("id") Long recipeId){
        return new ResponseEntity(recipeService.getById(recipeId), HttpStatus.OK);
    }

}
