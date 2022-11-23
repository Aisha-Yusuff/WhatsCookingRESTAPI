import { GiForkKnifeSpoon } from "react-icons/gi";
import { Link } from "react-router-dom";
export const NavBar = () => {
  return (
    <div className="bg-gray-100 w-full">
      <div className="w-screen shadow-md py-6 bg-white">
        <div className="flex container mx-auto my-auto">
          <div className=" mr-1.5 p-1.5 bg-gray-200 rounded-xl">
            <GiForkKnifeSpoon className="text-2xl" />
          </div>
          <Link to="/recipes">
            <h1 className="font-bold text-xl mt-1">What's Cooking?</h1>
          </Link>
          <div className="ml-auto font-semibold mt-0.5 p-1.5 bg-gray-200 rounded-lg hover:bg-gray-400 hover:text-white">
            <Link to="/createRecipe">
              <button>Create a Recipe</button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};
