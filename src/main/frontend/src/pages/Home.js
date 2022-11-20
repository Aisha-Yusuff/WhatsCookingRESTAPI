import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { RecipeCard } from "../components/RecipeCard";

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
    <div className="container grid grid-cols-3 gap-20 mx-auto">
      {recipes.map((recipe, index) => (
        <Link to={{ pathname: recipe.name, state: recipe }} key={index}>
          <RecipeCard name={recipe.name} image_url={recipe.imageURI} />
        </Link>
      ))}
      ;
    </div>
  );
};
