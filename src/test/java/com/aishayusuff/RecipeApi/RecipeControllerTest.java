package com.aishayusuff.RecipeApi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//Spring Test Class
@ExtendWith(SpringExtension.class)
//MVC controller
@WebMvcTest(RecipeController.class)
@AutoConfigureJsonTesters
public class RecipeControllerTest {
//    Mock service layer
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

// JSON tester - to convert recipe list to json to compare against req body
    @Autowired
    private JacksonTester<List<Recipe>> json;

    @Test
    void shouldReturnListOfAllRecipes() throws Exception {
//        given
//        build a recipe (without ingredients)
        Recipe porridgeRecipe = Recipe.builder()
                .name("Quick and easy Porridge")
                .instructions("""
                        1.Put your porridge oats in a saucepan.\s
                        2.Pour the milk into the saucepan.\s
                        3.Sprinkle a teaspoon of sugar in to the saucepan.\s
                        4. Cook on medium to low heat for 4-5 minutes and then serve and enjoy""")
                .build();

//        create ingredients for the recipe
        Ingredient porridgeOats = new Ingredient("Porridge Oats", "50g", porridgeRecipe.getId());
        Ingredient milk = new Ingredient("Milk", "350ml", porridgeRecipe.getId());
        Ingredient sugar = new Ingredient( "Sugar", "1 Teaspoon", porridgeRecipe.getId());
//        create set to hold all ingredients for recipe
        Set<Ingredient> ingredientsSet = new HashSet<>(Arrays.asList(porridgeOats, milk, sugar));
//      Add ingredients to recipe
        porridgeRecipe.setIngredients(ingredientsSet);

//      Add recipe to Recipe list
        List<Recipe> recipeList = List.of(porridgeRecipe);
        given(recipeService.getAllRecipes()).willReturn(recipeList);

//        when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

//        then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(json.write(recipeList).getJson());
        verify(recipeService).getAllRecipes();

    }



}
