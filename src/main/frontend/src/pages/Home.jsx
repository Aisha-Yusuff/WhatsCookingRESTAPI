import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
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
