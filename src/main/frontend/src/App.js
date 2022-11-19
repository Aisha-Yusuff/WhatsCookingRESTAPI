import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Home } from "./pages/Home";
import { RecipeForm } from "./pages/RecipeForm";
import { SearchResults } from "./pages/SearchResults";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/recipes" element={<Home />} />
          <Route path="/results" element={<SearchResults />} />
          <Route path="/recipeForm" element={<RecipeForm />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
