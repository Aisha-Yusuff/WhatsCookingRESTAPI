package com.aishayusuff.RecipeApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
