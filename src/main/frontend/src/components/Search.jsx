import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { TbSearch } from "react-icons/tb";

export const Search = () => {
  const [input, setInput] = useState("");
  const navigate = useNavigate();

  const submitHandler = (e) => {
    e.preventDefault();
    navigate(`/results/${input}`);
  };

  return (
    <div className="flex flex-col p-60 py-6  m-h-screen">
      <div className="bg-white items-center justify-between w-full flex rounded-lg shadow-lg p-2 mt-10 mb-8">
        <form onSubmit={submitHandler} className="w-full">
          <div className="flex">
            <input
              type="text"
              value={input}
              placeholder="What ingredient are you cooking with today?"
              onChange={(e) => setInput(e.target.value)}
              className="font-bold uppercase rounded-lg w-full py-4 pl-4 text-gray-700 bg-gray-100 leading-tight focus:outline-gray-200 focus:shadow-outline text-md"
            />
            <TbSearch className="flex mt-3 mb-3 m-4 w-8 h-8 text-gray-300 font-semibold" />
          </div>
        </form>
      </div>
    </div>
  );
};
