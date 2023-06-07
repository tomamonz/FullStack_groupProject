import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import PathButtonAtom from "../atoms/PathButtonAtom";
import ButtonAtom from "../atoms/ButonAtom";
import axios from "axios";
import UserContext from "../UserObject"; // import the context

const AddPackage = () => {
  const { user } = useContext(UserContext); // access the user object
  const [parcels, setParcels] = useState([]);
  const navigate = useNavigate();

  const createParcel = (e) => {
    e.preventDefault();

    const newParcel = {
      senderId: user.userId, // use the senderId from the user object
    };

    axios
      .post("http://localhost:7312/api/gateway/parcels", newParcel)
      .then((response) => {
        console.log("Parcel created", response.data);

        setParcels((prevState) => [...prevState, response.data]);
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

  const handleBackClick = () => {
    console.log("Back button clicked"); // add this line
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
          text="Pokaż paczki"
          className="btn btn-outline-success mt-2"
          onClick={listParcels}
        />
      </form>
      {parcels.map((parcel) => (
        <div className="card mb-1 w-50  " key={parcel.id}>
          <div className="card-body">
            Sender ID: {parcel.senderId}
            <br />
            Package Number: {parcel.parcelNumber}
            <br />
            Status: {parcel.status}
          </div>
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
