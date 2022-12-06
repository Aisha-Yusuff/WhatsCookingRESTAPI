import { NavBar } from "../components/NavBar";
import { RecipeForm } from "../components/RecipeForm";

export const CreateRecipe = () => {
  return (
    <div>
      <NavBar />
      <div className="container px-5 py-24 mx-auto">
        <div className="flex flex-col justify-center items-center">
          <h1 className="text-gray-900 text-5xl title-font font-bold mb-5 capitalize">
            Create You're Own Recipe
          </h1>
          <p className="text-xl font-semibold pl-2 pb-4 mb-2">
            You can create a recipe and share it with others!
          </p>
        </div>
        <RecipeForm />
      </div>
    </div>
  );
};
