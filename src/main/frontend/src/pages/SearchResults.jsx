import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { Link } from "react-router-dom";
import { RecipeCard } from "../components/RecipeCard";
import { Search } from "../components/Search";
import { NavBar } from "../components/NavBar";

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
      <NavBar />
      <Search />

      <div className="w-fit mx-auto mt-10 mb-5 grid grid-cols-1 lg:grid-cols-3 md:grid-cols-2 justify-items-center justify-center gap-y-20 gap-x-14">
        {recipeMatches.map((recipe, index) => (
          <Link to={`/displayRecipe/${recipe.id}`} key={index}>
            <RecipeCard name={recipe.name} image_url={recipe.imageURI} />
          </Link>
        ))}
      </div>
    </div>
  );
};
