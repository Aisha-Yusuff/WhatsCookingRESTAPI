import { UpdateRecipeForm } from "../components/UpdateRecipeForm";
import { useParams } from "react-router-dom";
import { NavBar } from "../components/NavBar";

export const UpdateRecipe = () => {
  let { id } = useParams();
  return (
    <div>
      <NavBar />
      <div className="container px-5 py-16 mx-auto">
        <div class="flex flex-col justify-center items-center">
          <h1 className="text-gray-900 text-5xl title-font font-bold mb-5 capitalize">
            Update a Recipe here:
          </h1>
          <p className="text-xl font-semibold pl-2 pb-4">
            Do you want to add an ingredient or edit a step?
          </p>
          <p className="text-xl font-semibold pl-2 pb-4 mb-2 mt-0">
            Fill out all the fields and include any changes you want to make to
            this recipe.
          </p>
        </div>

        <UpdateRecipeForm recipeId={id} />
      </div>
    </div>
  );
};
