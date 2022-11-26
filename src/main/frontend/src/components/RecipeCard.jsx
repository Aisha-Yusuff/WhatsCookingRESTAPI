export const RecipeCard = ({ name, image_url }) => {
  return (
    <div className="w-72 bg-white shadow-md rounded-xl duration-300 hover:scale-105 hover:shadow-xl">
      <img
        src={image_url}
        alt={name}
        className="h-80 w-72 object-cover rounded-t-xl"
      />
      <div className="px-5 py-3 pb-6 w-72">
        <p className="text-lg font-bold capitalize text-black truncate block">
          {name}
        </p>
        <div className="flex items-center">
          <button className="bg-gray-300 hover:bg-green-400 text-white text-sm mt-4 px-1.5 py-1 font-semibold hover:text-white rounded-sm">
            View Recipe
          </button>
        </div>
      </div>
    </div>
  );
};
