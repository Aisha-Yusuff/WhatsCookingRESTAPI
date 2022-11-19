package com.aishayusuff.RecipeApi;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query(value = "SELECT * FROM ingredients WHERE name = :ingredientName", nativeQuery=true)
    List<Ingredient> findByName(@Param("ingredientName") String ingredientName);

}
