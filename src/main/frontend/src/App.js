import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Home } from "./pages/Home";
import { Recipe } from "./pages/Recipe";
import { SearchResults } from "./pages/SearchResults";
import { CreateRecipe } from "./pages/CreateRecipe";
import { UpdateRecipe } from "./pages/UpdateRecipe";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/recipes" element={<Home />} />
          <Route path="/createRecipe" element={<CreateRecipe />} />
          <Route path="/displayRecipe/:id" element={<Recipe />} />
          <Route path="/results/:search" element={<SearchResults />} />
          <Route path="/updateRecipe/:id" element={<UpdateRecipe />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
