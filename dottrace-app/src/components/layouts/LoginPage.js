import React, { Component } from "react";
import { withRouter } from "react-router-dom"; // <- Import this

import FormInputOrganism from "../organisms/FormInputOrganism";
import ButtonAtom from "../atoms/SearchButtonAtom";
import axios from "axios";

class LoginPage extends Component {
  state = {
    email: "",
    password: "",
    role: "SENDER",
    errors: {},
  };

  onHandleChange = (e) => {
    //this will update state, properties
    this.setState({ [e.target.name]: e.target.value });
  };

  onHandleSubmit = (e) => {
    e.preventDefault();

    const { email, password } = this.state;

    // replace '/your_api_endpoint' with your actual API endpoint
    axios
      .post("/api_endpoint", { email, password })
      .then((response) => {
        // Handle response
        console.log(response);
        this.props.history.push({
          pathname: "/sendparcel/addpackage",
          state: { senderId: response.data.senderId }, // This is where we are passing senderId
        });
      })
      .catch((error) => {
        // Handle error
        console.log(error);
      });
  };

  render() {
    const { email, password } = this.state;
    return (
      <div className="container card mb-1">
        <div className="card-header bg-dark bg-body-tertiary border border-green d-flex justify-content-center ">
          Login
        </div>
        <div className="card-body">
          <form onSubmit={this.onHandleSubmit}>
            <FormInputOrganism
              label="Email"
              name="email"
              type="email"
              placeholder="Please insert your email address"
              value={email}
              onChange={this.onHandleChange}
            />
            <FormInputOrganism
              label="Password"
              name="password"
              type="password"
              placeholder="Please create password, min 6 characters long"
              value={password}
              onChange={this.onHandleChange}
            />
            <ButtonAtom
              type="submit"
              text="Login"
              className="btn btn-outline-success"
            />
          </form>
        </div>
      </div>
    );
  }
}

export default withRouter(LoginPage); // <- Use withRouter here
