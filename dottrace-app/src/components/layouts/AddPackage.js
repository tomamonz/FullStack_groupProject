import React, { Component } from "react";
import PropTypes from "prop-types";
import FormInputOrganism from "../organisms/FormInputOrganism";
import ButtonAtom from "../atoms/SearchButtonAtom";

class AddPackage extends Component {
  render() {
    return (
      <div className="container card mb-1">
        <ButtonAtom
          type="button"
          text="Add Package"
          className="btn btn-outline-success"
          className2="btn btn-outline-success"
        />
      </div>
    );
  }
}

AddPackage.propTypes = {};

export default AddPackage;
