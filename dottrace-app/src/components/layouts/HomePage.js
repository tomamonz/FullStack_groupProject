import React, { Component } from "react";
import SearchFormMolecule from "../molecules/SearchFormMolecule";
import axios from "axios";

class HomePage extends Component {
  state = {
    parcelNumber: "",
    parcelData: null,
    error: null,
    history: [],
  };

  handleInputChange = (e) => {
    this.setState({ parcelNumber: e.target.value });
  };

  handleSearch = (e) => {
    e.preventDefault();

    axios
      .get(
        `http://localhost:7312/api/gateway/parcels/number/${this.state.parcelNumber}`
      )
      .then((response) => {
        this.setState({ parcelData: response.data, error: null });
      })
      .catch((error) => {
        this.setState({ error: error.message, parcelData: null });
      });
  };

  render() {
    const { parcelNumber, history } = this.state;
    return (
      <div className="container ">
        <div className="row">
          <div className="col d-flex justify-content-center">
            <h3>Track your parcel</h3>
          </div>
        </div>
        <div className="row">
          <div className="col d-flex justify-content-center">
            <SearchFormMolecule
              buttonType="submit"
              nameClass="btn btn-outline-success"
              type="search"
              placeholder="Enter tracking number"
              text="Search"
              value={parcelNumber}
              onChange={this.handleInputChange}
              onSubmit={this.handleSearch}
            />
          </div>
        </div>
        {this.state.parcelData && (
          <div className="row">
            <div className="col d-flex justify-content-center">
              <div className="card">
                <div className="card-header">Parcel Details</div>
                <ul className="list-group list-group-flush">
                  <li className="list-group-item">
                    Sender ID: {this.state.parcelData.senderId}
                  </li>
                  <li className="list-group-item">
                    Parcel Number: {this.state.parcelData.parcelNumber}
                  </li>
                  <li className="list-group-item">
                    Status: {this.state.parcelData.status}
                  </li>
                </ul>
              </div>
            </div>
          </div>
        )}
        {this.state.error && <p>Error: {this.state.error}</p>}
      </div>
    );
  }
}

export default HomePage;
