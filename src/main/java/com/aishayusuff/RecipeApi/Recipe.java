package com.aishayusuff.RecipeApi;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String instructions;

    //   One to many unidirectional mapping
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name ="recipe_id", referencedColumnName = "id")
    private Set<Ingredient> ingredients = new HashSet<>();

    public Recipe(String name, String instructions, Set<Ingredient> ingredients) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }
}

/*
INSERT INTO recipes (name)
VALUES ("Hard Boiled Eggs");
 */
