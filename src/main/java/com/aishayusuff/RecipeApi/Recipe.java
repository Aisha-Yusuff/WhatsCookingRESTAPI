package com.aishayusuff.RecipeApi;

import lombok.*;

import javax.persistence.*;
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

    private String name;

//    One to many mapping with ingredient entity
//    Create foreign key (recipe_id) in ingredients table
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Set<Ingredient> ingredients;

//    One to many mapping with instruction entity
//    Create foreign key (recipe_id) in instructions table
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private List<Instruction> instructions;

    private String imageURI;

    public Recipe(String name, Set<Ingredient> ingredients, List<Instruction> instructions) {
        this(null, name, ingredients, instructions, null);
    }

}

