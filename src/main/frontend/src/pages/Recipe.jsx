import { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import { MdOutlineDeleteForever } from "react-icons/md";
import { ImBin } from "react-icons/im";
import { Link } from "react-router-dom";

export const Recipe = () => {
  let params = useParams();

  const [details, setDetails] = useState([]);

  const fetchRecipeDetails = (id) => {
    axios.get(`http://localhost:8080/recipe/${id}`).then((res) => {
      setDetails(res.data);
      console.log(res.data);
    });
  };

  useEffect(() => {
    fetchRecipeDetails(params.id);
  }, []);

  return (
    <div className="text-gray-700 body-font overflow-hidden bg-white">
      <div className="container px-5 py-24 mx-auto">
        <div className="lg:w-5/6 mx-auto flex flex-wrap">
          <img
            alt={details.name}
            className="lg:w-1/2 w-full object-cover object-center rounded border"
            src={details.imageURI}
          />
          <div className="lg:w-1/2 w-full lg:pl-10 lg:py-6 mt-5 lg:mt-0">
            <h1 className="text-gray-900 text-4xl title-font font-semibold mb-3 capitalize">
              {details.name}
            </h1>
            <button className=" flex-row text-white font-semibold bg-gray-300 border-0 mt-1 py-0.5 px-1 ml-1 focus:outline-none hover:bg-red-600 rounded-sm">
              Delete
            </button>
            <Link to={`/updateRecipe/${details.id}`}>
              <button className="flex-row text-white font-semibold bg-gray-300 border-0 mt-1 py-0.5 px-1 ml-3 focus:outline-none hover:bg-green-600 rounded-sm">
                Update
              </button>
            </Link>
            <div className="mt-4 w-full">
              <div className="bg-gray-100 p-2 py-5 pr-5 pb-0 rounded-md">
                <p className="text-xl font-semibold pl-2 pb-4">Ingredients</p>
                {details.ingredients?.map((ingredient, index) => {
                  return (
                    <div key={index}>
                      <ul>
                        <li className="pl-2 text-lg pb-2">
                          {ingredient.quantity} {ingredient.name}
                        </li>
                      </ul>
                    </div>
                  );
                })}

                <div className="flex items-center pb-5 border-b-2 border-gray-200 mb-5"></div>

                <p className="text-xl font-semibold pl-2 pb-4">Instructions</p>
                {details.instructions
                  ?.sort((a, b) => (a.step_number > b.step_number ? 1 : -1))
                  .map((instruction, index) => {
                    return (
                      <div key={index}>
                        <ul>
                          <li className="pl-2 text-lg">
                            {instruction.step_number}.{" "}
                            {instruction.step_description}
                          </li>
                          <hr className="my-4 mx-auto h-0.5 bg-gray-200 rounded border-0 md:my-4"></hr>
                        </ul>
                      </div>
                    );
                  })}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
