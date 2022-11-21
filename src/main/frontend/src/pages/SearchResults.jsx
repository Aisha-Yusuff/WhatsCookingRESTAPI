import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { Link } from "react-router-dom";
import { RecipeCard } from "../components/RecipeCard";
import { Search } from "../components/Search";

export const SearchResults = () => {
  const [recipeMatches, setRecipeMatches] = useState([]);
  let params = useParams();

  const getSearchResults = (ingredientName) => {
    axios
      .get(`http://localhost:8080/recipe/ingredient/${ingredientName}`)
      .then((res) => {
        setRecipeMatches(res.data);
        console.log(res.data);
      });
  };

  useEffect(() => {
    const input =
      params.search.charAt(0).toUpperCase() +
      params.search.slice(1).toLowerCase();
    getSearchResults(input);
    console.log(input);
  }, [params.search]);

  return (
    <div>
      <Search />
      <div className="container grid grid-cols-3 gap-20 mx-auto">
        {recipeMatches.map((recipe, index) => (
          <Link
            to={`/displayRecipe/${recipe.name}`}
            state={`${recipe.name}`}
            key={index}
          >
            <RecipeCard name={recipe.name} image_url={recipe.imageURI} />
          </Link>
        ))}
      </div>
    </div>
  );
};
