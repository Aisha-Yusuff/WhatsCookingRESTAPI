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

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
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
                .imageURI("https://recipeapi-images.s3.eu-west-2.amazonaws.com/porridgeandfruit.jpg")
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
        Set<Instruction> instructionSet = new HashSet<>(Arrays.asList(porridgeStep1, porridgeStep2, porridgeStep3));
//      Add instructions to recipe
        porridgeRecipe.setInstructions(instructionSet);

        return porridgeRecipe;
    }

    @Test
    void shouldReturnListOfAllRecipes() throws Exception {
//       given
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
    void shouldRetrieveARecipeById() throws Exception {
//        given
//        Create placeholder id
        Recipe existingRecipe = getDefaultRecipe();
        existingRecipe.setId(1L);
        given(recipeService.getById(1L)).willReturn(Optional.of(existingRecipe));
//        when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
//        then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonRequest.write(existingRecipe).getJson());

    }

    @Test
    public void shouldCreateNewRecipe() throws Exception {
//        given
//        build a new recipe to add to recipe list
        Recipe newRecipe = Recipe.builder()
                .name("Veggie Soup for One")
                .imageURI("https://recipeapi-images.s3.eu-west-2.amazonaws.com/newRecipe.jpg")
                .build();
        Ingredient exampleIngredient = new Ingredient("Stock Cubes", "2", newRecipe.getId());
        Instruction exampleInstruction = new Instruction (1, "In a pot, boil water and add the stock cubes", newRecipe.getId());
        newRecipe.setIngredients(Set.of(exampleIngredient));
        newRecipe.setInstructions(Set.of(exampleInstruction));

        given(recipeService.addNewRecipe(any(Recipe.class))).willReturn(newRecipe);

//        when - post request to add new recipe
        MockHttpServletResponse response = mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest.write(newRecipe)
                        .getJson())).andReturn().getResponse();

//        then
        then(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        then(response.getContentAsString()).isEqualTo(jsonRequest.write(newRecipe).getJson());
        verify(recipeService).addNewRecipe(any(Recipe.class));
    }

    @Test
    public void shouldReturnBadRequestWhenRecipeHasNoName() throws Exception {
//        given
        Recipe newRecipe = Recipe.builder()
                .imageURI("https://recipeapi-images.s3.eu-west-2.amazonaws.com/newRecipe.jpg")
                .build();
        Ingredient exampleIngredient = new Ingredient("Stock Cubes", "2", newRecipe.getId());
        Instruction exampleInstruction = new Instruction (1, "In a pot, boil water and add the stock cubes", newRecipe.getId());
        newRecipe.setIngredients(Set.of(exampleIngredient));
        newRecipe.setInstructions(Set.of(exampleInstruction));

//        when - post request to add new recipe
        MockHttpServletResponse response = mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest.write(newRecipe)
                        .getJson())).andReturn().getResponse();

//        then
        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void shouldReturnBadRequestWhenRecipeHasNoInstructions() throws Exception {
//        given
        Recipe newRecipe = Recipe.builder()
                .name("Veggie Soup for One")
                .imageURI("https://recipeapi-images.s3.eu-west-2.amazonaws.com/newRecipe.jpg")
                .build();
        Ingredient exampleIngredient = new Ingredient("Stock Cubes", "2", newRecipe.getId());
        newRecipe.setIngredients(Set.of(exampleIngredient));

//        when - post request to add new recipe
        MockHttpServletResponse response = mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest.write(newRecipe)
                        .getJson())).andReturn().getResponse();

//        then
        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }



    @Test
    public void shouldUpdateAnExistingRecipe() throws Exception {
//        given
//       Create placeholder ID for default recipe
        Recipe existingRecipe = getDefaultRecipe();
        existingRecipe.setId(1L);

//        build "updated" recipe
        Recipe updatedRecipe = Recipe.builder()
                .name("Quick and Easy Vegan Porridge")
                .imageURI("https://recipeapi-images.s3.eu-west-2.amazonaws.com/porridgeandfruit.jpg")
                .build();
        Ingredient exampleIngredient = new Ingredient("Porridge Oats","100g", updatedRecipe.getId());
        Instruction exampleInstruction = new Instruction(1, "Cook the porridge for 10 mins", updatedRecipe.getId());
        updatedRecipe.setIngredients(Set.of(exampleIngredient));
        updatedRecipe.setInstructions(Set.of(exampleInstruction));

//      expect updateRecipeById will not return a value
        when(recipeService.updateRecipeById(existingRecipe.getId(), updatedRecipe)).thenReturn(updatedRecipe);
//        when - put request
        ResultActions response = mockMvc.perform(put("/recipe/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedRecipe)));
//        then
        response.andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnBadRequestWhenUpdatedRecipeHasNoIngredients() throws Exception {
//        given
//       Create placeholder ID for default recipe
        Recipe existingRecipe = getDefaultRecipe();
        existingRecipe.setId(1L);

//        build "updated" recipe
        Recipe updatedRecipe = Recipe.builder()
                .name("Quick and Easy Vegan Porridge")
                .imageURI("https://recipeapi-images.s3.eu-west-2.amazonaws.com/porridgeandfruit.jpg")
                .build();
        Instruction exampleInstruction = new Instruction(1, "Cook the porridge for 10 mins", updatedRecipe.getId());
        updatedRecipe.setInstructions(Set.of(exampleInstruction));

//      expect updateRecipeById will not return a value
        when(recipeService.updateRecipeById(existingRecipe.getId(), updatedRecipe)).thenReturn(updatedRecipe);
//        when - put request
        ResultActions response = mockMvc.perform(put("/recipe/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedRecipe)));
//        then
        response.andExpect(status().isBadRequest());
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
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/ingredient/{ingredientName}", "Porridge Oats")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
//        then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(json.write(recipeList).getJson());
        verify(recipeService).getByIngredientName(any(String.class));
    }

    @Test
    public void shouldThrowExceptionWhenIngredientCantBeFound () throws Exception {
//        given
        List<Recipe> recipeList = List.of(getDefaultRecipe());
//      Filter recipe list and find recipe with Chocolate as an ingredients
        List<Recipe> filteredList = recipeList.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .anyMatch(ingredient -> ingredient.getName().equals("Chocolate")))
                .collect(Collectors.toList());

        given(recipeService.getByIngredientName("Chocolate")).willReturn(filteredList);
//        when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/ingredient/{ingredientName}", "Chocolate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
//        then
        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        then(response.getContentAsString()).isEqualTo("This ingredient cant be found in any of our recipes.");

    }



}
