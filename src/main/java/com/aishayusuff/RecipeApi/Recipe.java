package com.aishayusuff.RecipeApi;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
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

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
//    One to many mapping with ingredient entity
//    Create foreign key (recipe_id) in ingredients table
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Set<Ingredient> ingredients;

    @NotNull
//    One to many mapping with instruction entity
//    Create foreign key (recipe_id) in instructions table
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Set<Instruction> instructions;

    private String imageURI;

    public Recipe(String name, Set<Ingredient> ingredients, Set<Instruction> instructions) {
        this(null, name, ingredients, instructions, null);
    }

}

