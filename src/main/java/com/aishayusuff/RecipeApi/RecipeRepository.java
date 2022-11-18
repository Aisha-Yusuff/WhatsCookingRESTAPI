package com.aishayusuff.RecipeApi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT i FROM Ingredient i WHERE i.name = :ingredientName")
    List<Ingredient> findByIngredientName(String ingredientName);

}
