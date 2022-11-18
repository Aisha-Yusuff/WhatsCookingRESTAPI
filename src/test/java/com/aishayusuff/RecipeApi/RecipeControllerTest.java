package com.aishayusuff.RecipeApi;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Autowired
    private ObjectMapper objectMapper;

    public Recipe getDefaultRecipe() {
//        build recipe
        Recipe porridgeRecipe = Recipe.builder()
                .name("Quick and Easy Porridge")
                .build();

//        create ingredients for the recipe
        Ingredient porridgeOats = new Ingredient("Porridge Oats", "50g", porridgeRecipe.getId());
        Ingredient milk = new Ingredient("Milk", "350ml", porridgeRecipe.getId());
//        create set to hold all ingredients in the recipe
        Set<Ingredient> ingredientsSet = new HashSet<>(Arrays.asList(porridgeOats, milk));
//      Add ingredients to recipe
        porridgeRecipe.setIngredients(ingredientsSet);

//      create instructions for the recipe
        Instruction porridgeStep1 = new Instruction( 1,"Add your porridge oats to your saucepan.", porridgeRecipe.getId());
        Instruction porridgeStep2 = new Instruction( 2, "Pour the milk into the saucepan.", porridgeRecipe.getId());
        Instruction porridgeStep3 = new Instruction( 3, "Cook on medium to low heat for 4-5 minutes and then serve and enjoy",
                porridgeRecipe.getId());
//      create list to hold all instructions in recipe
        List<Instruction> instructionList = List.of(porridgeStep1, porridgeStep2, porridgeStep3);
//      Add instructions to recipe
        porridgeRecipe.setInstructions(instructionList);

        return porridgeRecipe;
    }

    @Test
    void shouldReturnListOfAllRecipesWhenGetAllRecipesInvoked() throws Exception {
//      Add recipe to Recipe list
        List<Recipe> recipeList = List.of(getDefaultRecipe());
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
        Recipe vegSoup = Recipe.builder()
                .name("Veggie Soup for One")
                .build();
//        create ingredients for the recipe
        Ingredient water = new Ingredient("Water", "500ml", vegSoup.getId());
        Ingredient carrot = new Ingredient("Carrot", "150g", vegSoup.getId());
        Ingredient potato = new Ingredient("Potato", "1", vegSoup.getId());
        Ingredient stockCubes = new Ingredient("Stock Cubes", "2", vegSoup.getId());
//        create set to hold all ingredients in the recipe
        Set<Ingredient> soupIngredientSet = new HashSet<>(Arrays.asList(water, carrot, potato, stockCubes));
//        add ingredients to recipe
        vegSoup.setIngredients(soupIngredientSet);

//        create instructions for the recipe
         Instruction vegSoupStep1 = new Instruction (1, "Boil the water, stock cubes in to your pot.", vegSoup.getId());
         Instruction vegSoupStep2 = new Instruction (2, "Chop your veggies and place them in your pot.", vegSoup.getId());
         Instruction vegSoupStep3 = new Instruction (3, "Leave your soup to cook for 25 minutes and then serve.", vegSoup.getId());
//        create list to hold all instructions
        List<Instruction> instructionList = List.of(vegSoupStep1, vegSoupStep2, vegSoupStep3);
//        add instructions to the recipe
        vegSoup.setInstructions(instructionList);
        given(recipeService.addNewRecipe(any(Recipe.class))).willReturn(vegSoup);

//        when - post request to add new recipe
        MockHttpServletResponse response = mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest.write(vegSoup)
                        .getJson())).andReturn().getResponse();

//        then
        then(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        then(response.getContentAsString()).isEqualTo(jsonRequest.write(vegSoup).getJson());
        verify(recipeService).addNewRecipe(any(Recipe.class));
    }

    @Test
    public void shouldUpdateAnExistingRecipe() throws Exception {
//        given
//       Create placeholder ID for default recipe
       getDefaultRecipe().setId(1L);

//        build "updated" recipe
        Recipe veganPorridgeRecipe = Recipe.builder()
                .name("Quick and Easy Vegan Porridge")
                .build();

//        create ingredients for the recipe
        Ingredient veganPorridgeOats = new Ingredient("Porridge Oats", "50g", veganPorridgeRecipe.getId());
        Ingredient oatMilk = new Ingredient("Oat Milk", "350ml", veganPorridgeRecipe.getId());
//        create set to hold all ingredients in the recipe
        Set<Ingredient> porridgeIngredientsSet = new HashSet<>(Arrays.asList(veganPorridgeOats, oatMilk));
//      Add ingredients to recipe
        veganPorridgeRecipe.setIngredients(porridgeIngredientsSet);

//      create instructions for the recipe
        Instruction veganPorridgeStep1 = new Instruction(1, "Add your porridge oats to your saucepan.", veganPorridgeRecipe.getId());
        Instruction veganPorridgeStep2 = new Instruction(2, "Pour the Oat Milk into the saucepan.", veganPorridgeRecipe.getId());
        Instruction veganPorridgeStep3 = new Instruction( 3, "Cook on medium to low heat for 4-5 minutes and then serve and enjoy", veganPorridgeRecipe.getId());
//      create list to hold all instructions in recipe
        List<Instruction> porridgeInstructionList = List.of(veganPorridgeStep1, veganPorridgeStep2, veganPorridgeStep3);
//      Add instructions to recipe
        veganPorridgeRecipe.setInstructions(porridgeInstructionList);

//      expect updateRecipeById will not return a value
        doNothing().when(recipeService).updateRecipeById(getDefaultRecipe().getId(), veganPorridgeRecipe);

//        when - put request
        ResultActions response = mockMvc.perform(put("/recipe/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(veganPorridgeRecipe)));
//        then
        response.andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteARecipe() throws Exception {
//        given
//        create placeholder ID for default recipe
        getDefaultRecipe().setId(1L);

//      expect deleteRecipeById will not return a value
        doNothing().when(recipeService).deleteRecipeById(getDefaultRecipe().getId());
//        when
        ResultActions response = mockMvc.perform(delete("/recipe/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON));
//        then
        response.andExpect(status().isNoContent());
    }

    @Test
    public void shouldRetrieveRecipesByIngredientName() throws Exception {
//        given
        List<Recipe> recipeList = List.of(getDefaultRecipe());

        given(recipeService.getByIngredientName(any(String.class))).willReturn(recipeList);
//        when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/{ingredientName}", "Porridge Oats")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
//        then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(json.write(recipeList).getJson());
        verify(recipeService).getByIngredientName(any(String.class));
    }


}
