import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import PathButtonAtom from "../atoms/PathButtonAtom";
import ButtonAtom from "../atoms/ButonAtom";
import axios from "axios";
import UserContext from "../UserObject";

const AddPackage = () => {
  const { user } = useContext(UserContext);
  const [parcels, setParcels] = useState([]);
  const [parcelHistories, setParcelHistories] = useState([]);
  const navigate = useNavigate();

  const createParcel = (e) => {
    e.preventDefault();

    const newParcel = {
      senderId: user.userId,
    };

    axios
      .post("http://localhost:7312/api/gateway/parcels", newParcel)
      .then((response) => {
        console.log("Parcel created", response.data);

        setParcels((prevState) => [...prevState, response.data]);
        const newParcelHistory = {
          location: "Prepared by the sender",
          parcelNumber: response.data.parcelNumber,
        };
        axios.post(
          "http://localhost:7312/api/gateway/parcelHistory",
          newParcelHistory
        );
        console.log("history", newParcelHistory);
      })
      .catch((error) => {
        console.error("Error during package creation", error);
      });
  };

  const listParcels = () => {
    axios
      .get(`http://localhost:7312/api/gateway/parcels/parcels/${user.userId}`)
      .then((response) => {
        console.log("Parcels fetched", response.data);
        setParcels(response.data);
      })
      .catch((error) => {
        console.error("Error during fetching parcels", error);
      });
  };
  const fetchParcelHistory = (parcelNumber) => {
    axios
      .get(`http://localhost:7312/api/gateway/parcelHistory/${parcelNumber}`)
      .then((response) => {
        setParcelHistories(response.data);
      })
      .catch((error) => {
        console.error(
          `Could not find history for parcel ${parcelNumber}.`,
          error
        );
      });
  };

  const handleBackClick = () => {
    console.log("Back button clicked");
    navigate("/");
  };

  return (
    <div className="container d-flex flex-column justify-content-center align-items-center">
      <form onSubmit={createParcel} className="mb-3">
        <ButtonAtom
          type="submit"
          text="Add Package"
          className="btn btn-outline-success mt-2"
        />
        <ButtonAtom
          type="button"
          text="Show all packages"
          className="btn btn-outline-success mt-2"
          onClick={listParcels}
        />
      </form>
      {parcels
        .sort((a, b) => a.id < b.id)
        .map((parcel) => (
          <div className="card mb-2 w-50" key={parcel.id}>
            <div className="card-body pb-2">
              Sender ID: {parcel.senderId}
              <br />
              Package Number: {parcel.parcelNumber}
              <br />
              Status: {parcel.status}
              <br />
              <button
                className="btn btn-outline-success mt-2"
                onClick={() => fetchParcelHistory(parcel.parcelNumber)}
              >
                Show History
              </button>
            </div>
            {parcelHistories
              .filter((history) => history.parcelNumber === parcel.parcelNumber)
              .map((history) => (
                <div className="card-body pt-0" key={history.id}>
                  Location: {history.location}
                  <br />
                  Date: {history.arrivalDate}
                </div>
              ))}
          </div>
        ))}
      <PathButtonAtom
        type="button"
        text="Back to home page"
        className="btn btn-outline-primary mt-2"
        onClick={handleBackClick}
      />
    </div>
  );
};

export default AddPackage;
