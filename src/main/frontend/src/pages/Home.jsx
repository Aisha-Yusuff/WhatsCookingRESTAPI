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

      <div className="container grid grid-cols-3 gap-20 mx-auto">
        {recipes.map((recipe, index) => (
          <Link
            to={`/displayRecipe/${recipe.name}`}
            state={`${recipe.name}`}
            key={index}
          >
            <RecipeCard name={recipe.name} image_url={recipe.imageURI} />
          </Link>
        ))}
        ;
      </div>
    </div>
  );
};