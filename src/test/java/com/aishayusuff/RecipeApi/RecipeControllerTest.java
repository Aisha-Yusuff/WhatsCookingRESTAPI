package com.aishayusuff.RecipeApi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
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
//        given - create a recipe list, with a recipe object and ingredient objects
        Ingredient porridgeOats = new Ingredient(1L,"Porridge Oats", "50g");
        Ingredient milk = new Ingredient(2L,"Milk", "350ml");
        Ingredient sugar = new Ingredient(3L, "Sugar", "1 Teaspoon");

//        create set to hold all ingredients for recipe
        Set<Ingredient> ingredientsSet = new HashSet<Ingredient>(Arrays.asList(porridgeOats, milk, sugar));

        Recipe porridgeRecipe = Recipe.builder()
                .name("Quick and easy Porridge")
                .instructions("1.Put your porridge oats in a saucepan \n" +
                        "2.Pour the milk into the saucepan \n" +
                        "3.Sprinkle a teaspoon of sugar in to the saucepan.\n" +
                        "4. Cook on medium to low heat for 4-5 minutes and then serve and enjoy")
                .ingredients(ingredientsSet)
                .build();

        List<Recipe> recipeList = List.of(porridgeRecipe);
        given(recipeService.getAllRecipes()).willReturn(recipeList);

//        when
        MockHttpServletResponse response = mockMvc.perform(get("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

//        then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(json.write(recipeList));
        verify(recipeService).getAllRecipes();

    }




}
