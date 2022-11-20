import axios from "axios";
import { useEffect, useState } from "react";

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

  return recipes.map((recipe, index) => {
    return (
      <div className="bg-blue-100">
        {/* recipe */}
        <div key={index}>
          <h1>{recipe.name}</h1>

          {/* ingredients  */}
          <div>
            {recipe.ingredients.map((ingredient) => (
              <div>
                <p>
                  {ingredient.quantity} {ingredient.name}
                </p>
              </div>
            ))}
          </div>

          {/* instructions */}
          <div>
            {recipe.instructions.map((instruction) => (
              <div>
                <p>
                  {instruction.step_number}. {instruction.step_description}
                </p>
              </div>
            ))}
          </div>
        </div>
      </div>
    );
  });
};
