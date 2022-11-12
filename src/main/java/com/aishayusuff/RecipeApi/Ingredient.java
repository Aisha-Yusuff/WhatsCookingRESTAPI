package com.aishayusuff.RecipeApi;

import lombok.*;
import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String quantity;

    private Long recipe_id;

    public Ingredient( String name, String quantity, Long recipe_id) {
      this(null, name, quantity, recipe_id);
    }
}
