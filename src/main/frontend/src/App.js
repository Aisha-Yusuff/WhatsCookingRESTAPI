import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Home } from "./pages/Home";
import { RecipeForm } from "./pages/RecipeForm";
import { Recipe } from "./pages/Recipe";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/recipes" element={<Home />} />
          <Route path="/recipeForm" element={<RecipeForm />} />
          <Route path="/recipeName" element={<Recipe />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
