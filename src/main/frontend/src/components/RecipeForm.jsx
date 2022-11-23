import React from "react";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export const RecipeForm = () => {
  const [recipe, setRecipe] = useState({
    name: "",
    ingredients: [
      {
        name: "",
        quantity: "",
      },
    ],
    instructions: [
      {
        step_number: 0,
        step_description: "",
      },
    ],
    imageURI: "",
  });

  const [submitErrorMessage, setSubmitErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleInputChange = (event) => {
    setSubmitErrorMessage("");
    const { name, value } = event.target;

    setRecipe((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleIngredientInputChange = (event) => {
    setSubmitErrorMessage("");
    const { name, value } = event.target;
    let newArray = recipe.ingredients;

    newArray[0] = { ...newArray[0], [name]: value };

    setRecipe((prevState) => ({
      ...prevState,
      ingredients: newArray,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    let errorMessage = "";

    // expects nested object array for ingredients
    const values = [
      recipe.title,
      [recipe.ingredients.name, recipe.ingredients.quantity],
      [recipe.instructions.step_number, recipe.instructions.step_description],
      recipe.imageURI,
    ];
    const allFieldsFilled = values.every((field) => {
      const value = `${field}`.trim();
      return value !== "" && value !== "0";
    });

    if (allFieldsFilled) {
      axios
        .post("http://localhost:8080/recipe", {
          name: recipe.name,
          ingredients: recipe.ingredients,
          instructions: recipe.instructions,
          imageURI: recipe.imageURI,
        })
        .then((res) => {
          console.log(res.data);
        });
    } else {
      errorMessage = "Please fill out all the fields!";
      setSubmitErrorMessage(errorMessage);
    }
  };

  return (
    <div>
      <form className="">
        <div className="">
          {submitErrorMessage && <p className="e">{submitErrorMessage}</p>}
          <label>Recipe Name: </label>
          <div>
            <input
              className=""
              type="text"
              name="name"
              value={recipe.name}
              placeholder="recipe name"
              onChange={handleInputChange}
            />
          </div>
          <label>Ingredients:</label>
          <div className="">
            <div>
              <input
                className=""
                type="text"
                name="quantity"
                placeholder="add quantity / weight"
                value={recipe.ingredients.name}
                onChange={handleIngredientInputChange}
              />
            </div>
            <div>
              <input
                className=""
                type="text"
                name="name"
                placeholder="ingredient name"
                value={recipe.ingredients.name}
                onChange={handleIngredientInputChange}
              />
            </div>
            <div>
              <input
                className=""
                type="text"
                name="quantity"
                placeholder="add quantity / weight"
                value={recipe.ingredients.name}
                onChange={handleIngredientInputChange}
              />
            </div>
            <div>
              <input
                className=""
                type="text"
                name="name"
                placeholder="ingredient name"
                value={recipe.ingredients.name}
                onChange={handleIngredientInputChange}
              />
            </div>
            <div>
              <input
                className=""
                type="text"
                name="quantity"
                placeholder="add quantity / weight"
                value={recipe.ingredients.name}
                onChange={handleIngredientInputChange}
              />
            </div>
            <div>
              <input
                className=""
                type="text"
                name="name"
                placeholder="ingredient name"
                value={recipe.ingredients.name}
                onChange={handleIngredientInputChange}
              />
            </div>
          </div>
          <label>Instructions:</label>
          <div>
            <input
              className=""
              type="number"
              name="step_number"
              placeholder="1"
              value={recipe.instructions.step_number}
              onChange={handleIngredientInputChange}
            />
          </div>
          <div>
            <input
              className=""
              type="text"
              name="step_description"
              placeholder="Add water your pot and boil on a medium heat..."
              value={recipe.instructions.step_description}
              onChange={handleIngredientInputChange}
            />
          </div>
          <div>
            <input
              className=""
              placeholder="2"
              type="number"
              name="step_number"
              value={recipe.instructions.step_number}
              onChange={handleIngredientInputChange}
            />
          </div>
          <div>
            <input
              className=""
              type="text"
              name="step_description"
              placeholder="Add water your pot and boil on a medium heat..."
              value={recipe.instructions.step_description}
              onChange={handleIngredientInputChange}
            />
          </div>
          <label>Image:</label>
          <div>
            <input
              className=""
              type="text"
              name="imageURI"
              value={recipe.imageURI}
              placeholder="Add the URL of your recipe's image here"
              onChange={handleInputChange}
            />
          </div>
        </div>
      </form>
      <button
        variant="primary"
        type="submit"
        className="submit-button"
        onClick={handleSubmit}
      >
        Submit
      </button>
    </div>
  );
};
