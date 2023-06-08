import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/layouts/Header";
import HomePage from "./components/layouts/HomePage";
import RegistrationPage from "./components/layouts/RegistrationPage";
import LoginPage from "./components/layouts/LoginPage";
import AddPackage from "./components/layouts/AddPackage";
import AdminPage from "./components/layouts/AdminPage";
import UserContext from "./components/UserObject";
import React, { useState } from "react";
import UpdatePackagePage from "./components/layouts/UpdatePackagePage";

function App() {
  const [user, setUser] = useState(null);
  const [parcel, setParcel] = useState(null);
  return (
    <Router>
      <UserContext.Provider value={{ user, setUser, parcel, setParcel }}>
        <div className="App">
          <Header brand="Dot Trace" className="HEADER" />
          <Routes>
            <Route exact path="/" element={<HomePage />} />
            <Route exact path="/register" element={<RegistrationPage />} />
            <Route exact path="/sendparcel" element={<LoginPage />} />
            <Route
              exact
              path="/sendparcel/addpackage"
              element={<AddPackage />}
            />
            <Route exact path="/admin" element={<AdminPage />} />
            <Route
              exact
              path="/admin/update/:id"
              element={<UpdatePackagePage />}
            />
          </Routes>
        </div>
      </UserContext.Provider>
    </Router>
  );
}

export default App;
