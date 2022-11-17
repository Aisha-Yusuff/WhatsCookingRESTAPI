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
    ResponseEntity<Recipe> addNewRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(
                recipeService.addNewRecipe(recipe),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long recipeId,
                                        @RequestBody Recipe updatedRecipe) {
        recipeService.updateRecipe(recipeId, updatedRecipe);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    };


}
