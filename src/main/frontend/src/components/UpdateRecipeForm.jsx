import React from "react";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";

export const UpdateRecipeForm = ({ recipeId }) => {
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

    axios
      .put("http://localhost:8080/recipe/" + recipeId, {
        name: recipe.name,
        ingredients: recipe.ingredients,
        instructions: recipe.instructions,
        imageURI: recipe.imageURI,
      })
      .then((res) => {
        console.log(res.data);
      });
  };

  return (
    <div className="max-w-screen-md mx-auto p-5 rounded-lg border shadow-md">
      <form className="w-full">
        <div className="mx-3 p-3">
          <div className="mb-8 mt-2">
            {submitErrorMessage && (
              <p className="e text-red-700 text-lg font-semibold mb-0.5">
                {submitErrorMessage}
              </p>
            )}
            <label className="block capitalize text-gray-700 text-lg font-bold mb-2 mr-2">
              Recipe Name:
            </label>
            <div>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-3 px-5 mb-3 leading-tight"
                type="text"
                name="name"
                value={recipe.name}
                placeholder="Recipe Name"
                onChange={handleInputChange}
              />
            </div>
          </div>

          <div className="flex flex-row">
            <div className="pr-15 mr-5 w-full md:w-full px-1">
              <label className="flex-row capitalize text-gray-700 text-lg font-bold">
                Ingredient Name
              </label>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-3 px-5 mt-2 mb-3 leading-tight"
                type="text"
                name="name"
                placeholder="Eg. Butter"
                value={recipe.ingredients.name}
                onChange={handleIngredientInputChange}
              />
            </div>
            <div className=" pl-5 ml-5 w-full md:w-full px-1">
              <label className="flex-row capitalize text-gray-700 text-lg font-bold mb-2 ">
                Quantity
              </label>
              <input
                className="font-semibold rounded-lg block w-1/3 bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-3 px-5 mt-2 mb-3 leading-tight"
                type="text"
                name="quantity"
                placeholder="Eg. 80g"
                value={recipe.ingredients.quantity}
                onChange={handleIngredientInputChange}
              />
            </div>
          </div>
          <div className="flex flex-row ">
            <div className="pr-15 mr-5 w-full md:w-full px-1">
              <label className="flex-row capitalize text-gray-700 text-lg font-bold">
                Ingredient Name
              </label>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-3 px-5 mt-2 mb-3 leading-tight"
                type="text"
                name="name"
                placeholder="Eg. Butter"
                value={recipe.ingredients.name}
                onChange={handleIngredientInputChange}
              />
            </div>
            <div className=" pl-5 ml-5 w-full md:w-full px-1">
              <label className="flex-row capitalize text-gray-700 text-lg font-bold  ">
                Quantity
              </label>
              <input
                className="font-semibold rounded-lg block w-1/3 bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-3 px-5 mt-2 mb-3 leading-tight"
                type="text"
                name="quantity"
                placeholder="Eg. 80g"
                value={recipe.ingredients.quantity}
                onChange={handleIngredientInputChange}
              />
            </div>
          </div>
          <div className="flex flex-row ">
            <div className="pr-15 mr-5 w-full md:w-full px-1">
              <label className="flex-row capitalize text-gray-700 text-lg font-bold">
                Ingredient Name
              </label>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-3 px-5 mt-2 mb-3 leading-tight"
                type="text"
                name="name"
                placeholder="Eg. Butter"
                value={recipe.ingredients.name}
                onChange={handleIngredientInputChange}
              />
            </div>
            <div className=" pl-5 ml-5 w-full md:w-full px-1">
              <label className="flex-row capitalize text-gray-700 text-lg font-bold  ">
                Quantity
              </label>
              <input
                className="font-semibold rounded-lg block w-1/3 bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-3 px-5 mt-2 mb-3 leading-tight"
                type="text"
                name="quantity"
                placeholder="Eg. 80g"
                value={recipe.ingredients.quantity}
                onChange={handleIngredientInputChange}
              />
            </div>
          </div>
          <div className="flex flex-row mb-6 ">
            <div className="pr-15 mr-5 w-full md:w-full px-1">
              <label className="flex-row capitalize text-gray-700 text-lg font-bold">
                Ingredient Name
              </label>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-3 px-5 mt-2 mb-3 leading-tight"
                type="text"
                name="name"
                placeholder="Eg. Butter"
                value={recipe.ingredients.name}
                onChange={handleIngredientInputChange}
              />
            </div>
            <div className=" pl-5 ml-5 w-full md:w-full px-1">
              <label className="flex-row capitalize text-gray-700 text-lg font-bold  ">
                Quantity
              </label>
              <input
                className="font-semibold rounded-lg block w-1/3 bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-3 px-5 mt-2 mb-3 leading-tight"
                type="text"
                name="quantity"
                placeholder="Eg. 80g"
                value={recipe.ingredients.quantity}
                onChange={handleIngredientInputChange}
              />
            </div>
          </div>

          <label className="block capitalize text-gray-700 text-lg font-bold mr-2">
            Instructions:
          </label>
          <div className="flex flex-row">
            <div className="pr-1 mr-1 w-1/2 md:w-1/4 px-1">
              <label className="flex-row capitalize text-gray-700 text-md font-semibold">
                Step No. :
              </label>

              <input
                className="font-semibold rounded-lg block w-1/2 bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-auto py-3 px-1 mb-3 mt-2 leading-tight"
                type="number"
                name="step_number"
                placeholder="1"
                value={recipe.instructions.step_number}
                onChange={handleIngredientInputChange}
              />
            </div>

            <div className="pl-1 ml-3 w-full md:w-full px-1">
              <label className="flex-row text-gray-700 text-md font-semibold">
                What do you need to do in this step?
              </label>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-md rounded py-3 px-5 mb-3 mt-2 leading-tight"
                type="text"
                name="step_description"
                placeholder="Add water your pot and boil on a medium heat..."
                value={recipe.instructions.step_description}
                onChange={handleIngredientInputChange}
              />
            </div>
          </div>
          <div className="flex flex-row">
            <div className="pr-1 mr-1 w-1/2 md:w-1/4 px-1">
              <label className="flex-row capitalize text-gray-700 text-md font-semibold">
                Step No. :
              </label>

              <input
                className="font-semibold rounded-lg block w-1/2 bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-auto py-3 px-1 mb-3 mt-2 leading-tight"
                type="number"
                name="step_number"
                placeholder="2"
                value={recipe.instructions.step_number}
                onChange={handleIngredientInputChange}
              />
            </div>

            <div className="pl-1 ml-3 w-full md:w-full px-1">
              <label className="flex-row text-gray-700 text-md font-semibold">
                What do you need to do in this step?
              </label>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-md rounded py-3 px-5 mb-3 mt-2 leading-tight"
                type="text"
                name="step_description"
                placeholder="Add water your pot and boil on a medium heat..."
                value={recipe.instructions.step_description}
                onChange={handleIngredientInputChange}
              />
            </div>
          </div>
          <div className="flex flex-row">
            <div className="pr-1 mr-1 w-1/2 md:w-1/4 px-1">
              <label className="flex-row capitalize text-gray-700 text-md font-semibold">
                Step No. :
              </label>

              <input
                className="font-semibold rounded-lg block w-1/2 bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-auto py-3 px-1 mb-3 mt-2 leading-tight"
                type="number"
                name="step_number"
                placeholder="3"
                value={recipe.instructions.step_number}
                onChange={handleIngredientInputChange}
              />
            </div>

            <div className="pl-1 ml-3 w-full md:w-full px-1">
              <label className="flex-row text-gray-700 text-md font-semibold">
                What do you need to do in this step?
              </label>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-md rounded py-3 px-5 mb-3 mt-2 leading-tight"
                type="text"
                name="step_description"
                placeholder="Add water your pot and boil on a medium heat..."
                value={recipe.instructions.step_description}
                onChange={handleIngredientInputChange}
              />
            </div>
          </div>
          <div className="flex flex-row">
            <div className="pr-1 mr-1 w-1/2 md:w-1/4 px-1">
              <label className="flex-row capitalize text-gray-700 text-md font-semibold">
                Step No. :
              </label>

              <input
                className="font-semibold rounded-lg block w-1/2 bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-auto py-3 px-1 mb-3 mt-2 leading-tight"
                type="number"
                name="step_number"
                placeholder="4"
                value={recipe.instructions.step_number}
                onChange={handleIngredientInputChange}
              />
            </div>

            <div className="pl-1 ml-3 w-full md:w-full px-1">
              <label className="flex-row text-gray-700 text-md font-semibold">
                What do you need to do in this step?
              </label>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-md rounded py-3 px-5 mb-3 mt-2 leading-tight"
                type="text"
                name="step_description"
                placeholder="Add water your pot and boil on a medium heat..."
                value={recipe.instructions.step_description}
                onChange={handleIngredientInputChange}
              />
            </div>
          </div>
          <div className="flex flex-row">
            <div className="pr-1 mr-1 w-1/2 md:w-1/4 px-1">
              <label className="flex-row capitalize text-gray-700 text-md font-semibold">
                Step No. :
              </label>

              <input
                className="font-semibold rounded-lg block w-1/2 bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-lg rounded py-auto py-3 px-1 mb-3 mt-2 leading-tight"
                type="number"
                name="step_number"
                placeholder="5"
                value={recipe.instructions.step_number}
                onChange={handleIngredientInputChange}
              />
            </div>

            <div className="pl-1 ml-3 w-full md:w-full px-1">
              <label className="flex-row text-gray-700 text-md font-semibold">
                What do you need to do in this step?
              </label>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-md rounded py-3 px-5 mb-3 mt-2 leading-tight"
                type="text"
                name="step_description"
                placeholder="Add water your pot and boil on a medium heat..."
                value={recipe.instructions.step_description}
                onChange={handleIngredientInputChange}
              />
            </div>
          </div>

          <div class="w-full md:w-full">
            <label className="block capitalize text-gray-700 text-lg font-bold mb-2 mr-2">
              Image:
            </label>
            <div>
              <input
                className="font-semibold rounded-lg block w-full bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-md rounded py-3 px-5 mb-3 leading-tight"
                type="text"
                name="imageURI"
                value={recipe.imageURI}
                placeholder="Add the URL of your image here"
                onChange={handleInputChange}
              />
            </div>
          </div>
        </div>
      </form>
      <button
        variant="primary"
        type="submit"
        className=" mx-auto block w-1/4 bg-blue-500 text-white font-bold rounded-lg shadow hover:bg-green-600 focus:shadow-outline focus:outline-none font-bold py-2.5"
        onClick={handleSubmit}
      >
        Submit
      </button>
    </div>
  );
};
