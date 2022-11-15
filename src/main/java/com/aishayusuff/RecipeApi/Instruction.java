package com.aishayusuff.RecipeApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "instructions")
public class Instruction {
    @Id
    @GeneratedValue
//    Create step number column
    private Long id;
    private String step_description;
    private Long recipe_id;

    public Instruction(String step_description, Long recipe_id) {
        this(null, step_description, recipe_id);
    }
}
