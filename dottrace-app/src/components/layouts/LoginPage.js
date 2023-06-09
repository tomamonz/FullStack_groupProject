import React, { useState, useContext } from "react";
import UserContext from "../UserObject";
import { useNavigate } from "react-router-dom";

import FormInputMolecule from "../molecules/FormInputMolecule";
import ButtonAtom from "../atoms/SearchButtonAtom";
import axios from "axios";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState(null);
  const navigate = useNavigate();
  const { setUser } = useContext(UserContext);

  const onHandleChange = (e) => {
    if (e.target.name === "email") {
      setEmail(e.target.value);
    } else if (e.target.name === "password") {
      setPassword(e.target.value);
    }
  };

  const onHandleSubmit = (e) => {
    e.preventDefault();

    axios
      .get(`http://localhost:7312/api/gateway/users/${email}/${password}`)
      .then((response) => {
        console.log(response);
        setUser(response.data);
        navigate("/sendparcel/addpackage");
      })
      .catch((error) => {
        console.log(error);
        setErrorMessage("Invalid email or password");
      });
  };

  return (
    <div className="container card mb-1">
      <div className="card-header bg-dark bg-body-tertiary border border-green d-flex justify-content-center ">
        Login
      </div>
      <div className="card-body">
        <form onSubmit={onHandleSubmit}>
          <FormInputMolecule
            label="Email"
            name="email"
            type="email"
            placeholder="Please insert your email address"
            value={email}
            onChange={onHandleChange}
          />
          <FormInputMolecule
            label="Password"
            name="password"
            type="password"
            placeholder="Please create password, min 6 characters long"
            value={password}
            onChange={onHandleChange}
          />
          {errorMessage && (
            <p className="error-message text-danger">{errorMessage}</p>
          )}
          <ButtonAtom
            type="submit"
            text="Login"
            className="btn btn-outline-success"
          />
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
