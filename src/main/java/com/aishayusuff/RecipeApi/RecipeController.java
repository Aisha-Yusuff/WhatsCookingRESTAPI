package com.aishayusuff.RecipeApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/recipe")
@CrossOrigin()
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
    ResponseEntity<Recipe> addNewRecipe(@Valid @RequestBody Recipe recipe) {
        return new ResponseEntity<>(
                recipeService.addNewRecipe(recipe),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long recipeId,
                                        @RequestBody  @Valid Recipe updatedRecipe) {
        recipeService.updateRecipeById(recipeId, updatedRecipe);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    };
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Recipe> deleteRecipeById(@PathVariable("id") Long recipeId){
        recipeService.deleteRecipeById(recipeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/ingredient/{ingredientName}")
    ResponseEntity<Object> getByIngredientName(@PathVariable("ingredientName") String ingredientName) {
        List resultsList = recipeService.getByIngredientName(ingredientName);
        if (resultsList.isEmpty()) {
            return ResponseEntity.badRequest().body("This ingredient cant be found in any of our recipes.");
        } else {
            return new ResponseEntity<>(resultsList, HttpStatus.OK);
        }
    }
    @GetMapping(path = "/{id}")
    ResponseEntity<Recipe> getById(@PathVariable("id") Long recipeId){
        return new ResponseEntity(recipeService.getById(recipeId), HttpStatus.OK);
    }

}
