import React, { Component } from "react";
import FormInputOrganism from "../organisms/FormInputOrganism";
import ButtonAtom from "../atoms/SearchButtonAtom";

class RegistrationPage extends Component {
  state = {
    email: "",
    password: "",
    role: "SENDER",
    errors: {},
  };

  onHandleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  onHandleSubmit = (e) => {
    e.preventDefault();

    const { email, password, role } = this.state;

    if (email === "") {
      this.setState({ errors: { email: "Email is a required field." } });
      return;
    }

    if (password === "") {
      this.setState({ errors: { password: "Password is a required field." } });
      return;
    }

    const newUser = {
      email,
      password,
      role,
    };

    this.setState({ password: "", email: "", errors: {} });
  };

  render() {
    const { email, password, errors } = this.state;
    return (
      <div className="container card mb-1">
        <div className="card-header bg-dark bg-body-tertiary border border-green d-flex justify-content-center ">
          Registration Form
        </div>
        <div className="card-body ">
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
              type="text"
              placeholder="Please create password, min 6 characters long"
              value={password}
              onChange={this.onHandleChange}
              error={errors.password}
            />
            <ButtonAtom
              type="button"
              text="Register"
              className="btn btn-outline-success"
              className2="btn btn-outline-success"
            />
          </form>
        </div>
      </div>
    );
  }
}

export default RegistrationPage;
