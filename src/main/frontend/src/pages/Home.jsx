import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { NavBar } from "../components/NavBar";
import { RecipeCard } from "../components/RecipeCard";
import { Search } from "../components/Search";

export const Home = () => {
  const [recipes, setRecipes] = useState([]);

  const fetchRecipes = () => {
    axios.get("http://localhost:8080/recipe").then((res) => {
      setRecipes(res.data);
      console.log(res.data);
    });
  };

  useEffect(() => {
    fetchRecipes();
  }, []);

  return (
    <div>
      <NavBar />
      <div className="container px-5 mt-16 mx-auto">
        <div class="flex flex-col justify-center items-center mb-1">
          <h1 className="text-gray-900 text-7xl title-font font-bold mb-8">
            Welcome to What's Cooking?
          </h1>
          <p className="text-lg font-semibold pl-2">
            At What's Cooking you can discover new delicious recipes and share
            your favourite recipes with others
          </p>
          <p className="text-lg font-semibold pl-2">
            Use our search bar to search for any ingredient and we will find all
            the recipes that contain that ingredient for you
          </p>
        </div>
      </div>

      <Search />

      <div className="w-fit mx-auto mt-10 mb-5 grid grid-cols-1 lg:grid-cols-3 md:grid-cols-2 justify-items-center justify-center gap-y-20 gap-x-14">
        {recipes.map((recipe, index) => (
          <Link to={`/displayRecipe/${recipe.id}`} key={index}>
            <RecipeCard name={recipe.name} image_url={recipe.imageURI} />
          </Link>
        ))}
      </div>
    </div>
  );
};
