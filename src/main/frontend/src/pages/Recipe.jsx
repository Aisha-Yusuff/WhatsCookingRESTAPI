import { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

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
    <div className="container my-auto mx-auto bg-blue-200">
      <h2 className="font-bold text-3xl mb-8 ">{details.name}</h2>
      <div className="flex">
        <img
          src={details.imageURI}
          className="w-1/3 h-1/3 mr-1 rounded-md"
          alt={details.name}
        />
        <div className="pt-0 p-8">
          <div className="grid grid cols-2 gap-x-20 gap-y-4 rounded-lg bg-gray-50 shadow-sm p-2">
            <p className="text-lg font-semibold pl-2 pb-0 p-6">Ingredients</p>
            {details.ingredients?.map((ingredient) => {
              return (
                <div key={ingredient.id}>
                  <ul>
                    <li className="pl-2 text-md">
                      {ingredient.quantity} {ingredient.name}
                    </li>
                    <hr class="my-4 mx-auto h-1 bg-gray-100 rounded border-0 md:my-4"></hr>
                  </ul>
                </div>
              );
            })}
          </div>
        </div>
        <div>
          <div className="grid grid cols-2 gap-x-20 gap-y-4 rounded-lg bg-gray-50 shadow-sm p-6">
            <p className="text-lg font-semibold pl-2 pb-0">Instructions</p>
            {details.instructions?.map((instruction) => {
              return (
                <div key={instruction.id}>
                  <ul>
                    <li className="pl-2 text-md">
                      {instruction.step_number} {instruction.step_description}
                    </li>
                    <hr class="my-4 mx-auto h-0.5 bg-gray-100 rounded border-0 md:my-4"></hr>
                  </ul>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
};
