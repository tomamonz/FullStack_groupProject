import React, { Component } from "react";
import PropTypes from "prop-types";
import AddPackage from "./AddPackage";
import axios from "axios";

class Parcels extends Component {
  state = {
    parcels: [],
  };

  componentDidMount() {
    axios
      .post("http://localhost:7312/api/gateway/dotTrace/parcels")
      .then((result) => {
        const parcels = result.data;
        this.setState({ parcels: parcels });
      });
  }

  render() {
    const { parcels } = this.state;
    return (
      <React.Fragment>
        {parcels.map((parcel) => (
          <AddPackage key={parcel.parcelNumber} parcel={parcel} />
        ))}
      </React.Fragment>
    );
  }
}

export default Parcels;
