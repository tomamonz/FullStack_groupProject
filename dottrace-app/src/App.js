import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/layouts/Header";
import HomePage from "./components/layouts/HomePage";
import RegistrationPage from "./components/layouts/RegistrationPage";
import LoginPage from "./components/layouts/LoginPage";
import AddPackage from "./components/layouts/AddPackage";

function App() {
  return (
    <Router>
      <div className="App">
        <Header brand="Dot Trace" className="HEADER" />
        <Routes>
          <Route exact path="/" element={<HomePage />} />
          <Route exact path="/register" element={<RegistrationPage />} />
          <Route exact path="/sendparcel" element={<LoginPage />} />
          <Route exact path="/sendparcel/addpackage" element={<AddPackage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
