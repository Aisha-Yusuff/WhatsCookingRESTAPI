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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    @Autowired
    private JacksonTester<Recipe> jsonRequest;

    @Test
    void shouldReturnListOfAllRecipes() throws Exception {
//        given
//        build a recipe (without ingredients)
        Recipe porridgeRecipe = Recipe.builder()
                .name("Quick and Easy Porridge")
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

    @Test
    public void shouldCreateNewRecipe() throws Exception {
//        given
//        build a new recipe to add to recipe list
        Recipe vegSoup = Recipe.builder().name("Veggie Soup for One")
                .instructions("""
                1.Pour the water in a medium-sized pot.\s
                2.Add your stock cubes and seasonings.\s
                3.Chop your veggies and place them in your pot.\s
                4.Leave your soup to cook for 20 minutes on a medium heat.\s
                5. If you like your veggies soft let them cook for another 10 minutes and then serve your soup.
                """)
                .build();

        Ingredient water = new Ingredient("Water", "500ml", vegSoup.getId());
        Ingredient carrot = new Ingredient("Carrot", "150g", vegSoup.getId());
        Ingredient celery = new Ingredient("Celery", "150g", vegSoup.getId());
        Ingredient potato = new Ingredient("Potato", "1", vegSoup.getId());
        Ingredient stockCubes = new Ingredient("Stock Cubes", "2", vegSoup.getId());
        Ingredient salt = new Ingredient("Salt", "1tbsp", vegSoup.getId());
        Ingredient blackPepper = new Ingredient("Black Pepper", "1tbsp", vegSoup.getId());
        Ingredient paprika = new Ingredient("Paprika", "1tbsp", vegSoup.getId());

        Set<Ingredient> soupIngredientSet = new HashSet<>(Arrays.asList
                (water, carrot, celery, potato, stockCubes, salt, blackPepper, paprika));
        vegSoup.setIngredients(soupIngredientSet);

        //        given(recipeService.addNewRecipe(any(Recipe.class), eq(soupIngredientSet))).willReturn(vegSoup);
        given(recipeService.addNewRecipe(any(Recipe.class))).willReturn(vegSoup);

//        when - post request to add new recipe
        MockHttpServletResponse response = mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest.write(vegSoup)
                        .getJson())).andReturn().getResponse();

//        then
        then(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        then(response.getContentAsString()).isEqualTo(jsonRequest.write(vegSoup).getJson());
    }



}
