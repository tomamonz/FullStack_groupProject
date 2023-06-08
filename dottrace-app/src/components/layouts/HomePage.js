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

    if (this.state.parcelNumber === "") {
      this.setState({
        error: "The search field cannot be empty.",
        parcelData: null,
      });
      return;
    }

    axios
      .get(
        `http://localhost:7312/api/gateway/parcels/number/${this.state.parcelNumber}`
      )
      .then((response) => {
        this.setState({ parcelData: response.data, error: null });
        axios
          .get(
            `http://localhost:7312/api/gateway/parcelHistory/${this.state.parcelNumber}`
          )
          .then((response) => {
            this.setState({ history: response.data });
          });
      })
      .catch((error) => {
        // Check error message and set the appropriate message for the user
        if (error.response.data.message.includes("The parcel doesn't exist")) {
          this.setState({
            error: "Parcel with provided number does not exist",
            parcelData: null,
          });
        } else {
          this.setState({ error: error.message, parcelData: null });
        }
      });
  };

  render() {
    const { parcelNumber } = this.state;
    return (
      <div className="container   ">
        <div className="row">
          <div className="col d-flex justify-content-center m-5">
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
            <div className="col d-flex justify-content-center mt-3">
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
                  <li className="list-group-item">
                    <p>Parcel history:</p>
                    {this.state.history
                      .filter(
                        (hist) => hist.parcelNumber === this.state.parcelNumber
                      )
                      .map((hist) => (
                        <div key={hist.id} className="pb-2">
                          Location: {hist.location}
                          <br />
                          Date: {hist.arrivalDate}
                        </div>
                      ))}
                  </li>
                </ul>
              </div>
            </div>
          </div>
        )}
        <div className="d-flex justify-content-center m-4 error-message text-danger ">
          {this.state.error && <p> {this.state.error}</p>}
        </div>
      </div>
    );
  }
}

export default HomePage;
