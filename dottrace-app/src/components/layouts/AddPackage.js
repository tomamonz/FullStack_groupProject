import React, { Component } from "react";
import ButtonAtom from "../atoms/SearchButtonAtom";
import axios from "axios";

class AddPackage extends Component {
  state = {
    senderId: "",
    parcels: [],
  };

  handleInputChange = (e) => {
    this.setState({ senderId: e.target.value });
  };

  createParcel = (e) => {
    e.preventDefault();

    const newParcel = {
      senderId: this.state.senderId,
    };

    axios
      .post("http://localhost:7312/api/gateway/parcels", newParcel)
      .then((response) => {
        console.log("Parcel created", response.data);

        this.setState((prevState) => ({
          parcels: [...prevState.parcels, response.data],
        }));
      })
      .catch((error) => {
        console.error("Error during package creation", error);
      });
  };

  render() {
    const { parcels, senderId } = this.state;
    return (
      <div className="container d-flex justify-content-center">
        <form onSubmit={this.createParcel} className="mb-3 ">
          <input
            type="text"
            placeholder="Enter senderId"
            value={senderId}
            onChange={this.handleInputChange}
            className="form-control"
          />
          <ButtonAtom
            type="submit"
            text="Add Package"
            className="btn btn-outline-success mt-2"
          />
        </form>
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
            </div>
          </div>
        ))}
      </div>
    );
  }
}

export default AddPackage;
