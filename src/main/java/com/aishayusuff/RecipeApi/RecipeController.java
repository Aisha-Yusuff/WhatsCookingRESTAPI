package com.aishayusuff.RecipeApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipe")
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
    ResponseEntity<Recipe> addNewRecipe(Recipe recipe) {
        return new ResponseEntity<>(
                recipeService.addNewRecipe(recipe),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/{name}")
    ResponseEntity<Recipe> updateRecipe(@PathVariable("name") String recipeName,
                                        @RequestBody Recipe updatedRecipe) {
        Recipe latestRecipe = recipeService.updateRecipe(recipeName, updatedRecipe);
        return new ResponseEntity<Recipe>(latestRecipe, HttpStatus.NO_CONTENT);

    };


}
