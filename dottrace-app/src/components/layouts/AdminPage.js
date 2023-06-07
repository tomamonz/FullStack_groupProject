import React, { Component } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import { Link } from "react-router-dom";

class AdminPage extends Component {
  state = {
    parcels: [],
    senderId: "",
  };

  componentDidMount() {
    this.fetchParcels();
  }

  fetchParcels = () => {
    axios
      .get("http://localhost:7312/api/gateway/parcels")
      .then((response) => {
        this.setState({ parcels: response.data });
      })
      .catch((error) => {
        console.error("Could not upload parcels.", error);
      });
  };

  render() {
    const { parcels, senderId } = this.state;
    return (
      <div className="container card mb-1">
        <div className="card-header bg-dark bg-body-tertiary border border-green d-flex justify-content-center">
          List of parcels
        </div>
        <div className="card-body">
          {parcels.map((parcel) => (
            <div
              className="card mb-1 d-flex justify-content-center"
              key={parcel.id}
            >
              <div className="card-body">
                Sender ID: {parcel.senderId}
                <br />
                Package Number: {parcel.parcelNumber}
                <br />
                Status: {parcel.status}
                <br />
                <Link
                  to={`/update/${parcel.id}`}
                  className="btn btn-outline-success mt-2"
                >
                  Update
                </Link>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default AdminPage;
