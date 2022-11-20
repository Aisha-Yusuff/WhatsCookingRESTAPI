export const RecipeCard = ({ name, image_url, ingredients }) => {
  return (
    <div className="card container rounded-lg shadow-lg bg-white transform transition-all hover:-translate-y-1 duration-100">
      <img
        src={image_url}
        className="card-image h-1/2 w-full rounded-tl-lg rounded-tr-lg"
        alt={name}
      />
      <div className="p-6">
        <h3 className="card-title font-bold mb-4 text-xl">{name}</h3>
        <button className="bg-gray-300 hover:bg-gray-400 text-gray-900 hover:text-white font-bold py-2 px-6 rounded">
          View Recipe
        </button>
      </div>
    </div>
  );
};
