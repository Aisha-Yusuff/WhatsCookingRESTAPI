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

    public Ingredient( String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
