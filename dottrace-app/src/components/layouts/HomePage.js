import React, { Component } from "react";
import SearchFormMolecule from "../molecules/SearchFormMolecule";
import axios from "axios";

class HomePage extends Component {
  state = {
    parcelNumber: "",
    parcelData: null,
    error: null,
  };

  handleInputChange = (e) => {
    this.setState({ parcelNumber: e.target.value });
  };

  handleSearch = (e) => {
    e.preventDefault();

    axios
      .get(`/api/v1/parcels/number/${this.state.parcelNumber}`)
      .then((response) => {
        this.setState({ parcelData: response.data, error: null });
      })
      .catch((error) => {
        this.setState({ error: error.message, parcelData: null });
      });
  };

  render() {
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
              placeholder="Type tracking number"
              text="Search"
              value={this.state.parcelNumber}
              onChange={this.handleInputChange}
              onSubmit={this.handleSearch}
            />
          </div>
        </div>
        {this.state.parcelData && (
          <div className="row">
            <div className="col d-flex justify-content-center">
              <table>
                {/* Assuming parcelData is an array of objects with key-value pairs */}
                <thead>
                  <tr>
                    {Object.keys(this.state.parcelData[0]).map((key) => (
                      <th key={key}>{key}</th>
                    ))}
                  </tr>
                </thead>
                <tbody>
                  {this.state.parcelData.map((row, index) => (
                    <tr key={index}>
                      {Object.values(row).map((value, i) => (
                        <td key={i}>{value}</td>
                      ))}
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        )}
        {this.state.error && <p>Error: {this.state.error}</p>}
      </div>
    );
  }
}

export default HomePage;
