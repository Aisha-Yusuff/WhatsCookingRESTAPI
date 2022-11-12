package com.aishayusuff.RecipeApi;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //   One to many unidirectional mapping
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name ="recipe_id", referencedColumnName = "id")
    private Set<Ingredient> ingredients = new HashSet<>();

    private String instructions;
//    private List<String> instructions;

    public Recipe(String name, Set<Ingredient> ingredients, String instructions) {
        this(null, name, ingredients, instructions);
    }
}

