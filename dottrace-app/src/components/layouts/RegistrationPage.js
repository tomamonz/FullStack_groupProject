import React, { Component } from "react";
import FormInputOrganism from "../organisms/FormInputOrganism";
import ButtonAtom from "../atoms/SearchButtonAtom";
import axios from "axios";

class RegistrationPage extends Component {
  state = {
    email: "",
    password: "",
    role: "SENDER",
    errors: {},
    isRegistered: false,
  };

  onHandleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  onHandleSubmit = (e) => {
    e.preventDefault();

    const { email, password } = this.state;

    if (email === "") {
      this.setState({ errors: { email: "Email is a required field." } });
      return;
    }

    if (password === "") {
      this.setState({
        errors: {
          password: "Please insert password of at least 6 characters long.",
        },
      });
      return;
    }

    const newUser = {
      email,
      password,
      role: "SENDER",
    };

    axios
      .post("http://localhost:7312/api/gateway/users", newUser)
      .then((response) => {
        console.log("New user created:", response.data);
        this.setState({ isRegistered: true });
      })
      .catch((error) => {
        console.error("Error creating user:", error);
      });
  };

  render() {
    const { email, password, errors, isRegistered } = this.state;
    return (
      <div className="container card mb-1">
        <div className="card-header bg-dark bg-body-tertiary border border-green d-flex justify-content-center ">
          Registration Form
        </div>
        <div className="card-body ">
          {isRegistered ? (
            <div className="alert alert-success">Registered successfully!</div>
          ) : (
            <form onSubmit={this.onHandleSubmit}>
              <FormInputOrganism
                label="Email"
                name="email"
                type="email"
                placeholder="Please insert your email address"
                value={email}
                onChange={this.onHandleChange}
                error={errors.email}
              />
              <FormInputOrganism
                label="Password"
                name="password"
                type="password"
                placeholder="Please create password, min 6 characters long"
                value={password}
                onChange={this.onHandleChange}
                error={errors.password}
              />
              <ButtonAtom
                type="submit"
                text="Register"
                className="btn btn-outline-success"
                className2="btn btn-outline-success"
              />
            </form>
          )}
        </div>
      </div>
    );
  }
}

export default RegistrationPage;