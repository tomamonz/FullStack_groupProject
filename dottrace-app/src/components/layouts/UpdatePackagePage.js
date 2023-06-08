import React, { useContext, useState } from "react";
import ObjectContext from "../UserObject";
import axios from "axios";
import InputAtom from "../atoms/InputAtom";
import SubmitButtonAtom from "../atoms/PathButtonAtom";
import { useNavigate } from "react-router-dom";

const UpdatePackagePage = () => {
  const { parcel, setParcel } = useContext(ObjectContext);
  const [newStatus, setNewStatus] = useState(parcel.status);
  const [newLocation, setNewLocation] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    const updatedParcel = {
      id: parcel.id,
      senderId: parcel.senderId,
      parcelNumber: parcel.parcelNumber,
      status: newStatus,
    };
    const newHistory = {
      location: newLocation,
      parcelNumber: parcel.parcelNumber,
    };

    axios
      .put(`http://localhost:7312/api/gateway/parcels`, updatedParcel)
      .then((response) => {
        setParcel(response.data);
        axios
          .post(`http://localhost:7312/api/gateway/parcelHistory`, newHistory)
          .then((response) => {
            alert("Package updated and history added successfully!");
          })
          .catch((error) => {
            console.error("Could not add the package history.", error);
            alert("Could not add the package history.");
          });
      })
      .catch((error) => {
        console.error("Could not update the package.", error);
        alert("Could not update the package.");
      });
  };
  const handleBackClick = () => {
    console.log("Back button clicked");
    navigate("/admin");
  };

  return (
    <div className="container ">
      <div className="col d-flex justify-content-center m-4">
        <h1>Update Package</h1>
      </div>

      <div className="col d-flex justify-content-center m-5">
        <form onSubmit={handleSubmit}>
          <label>
            New Status:
            <select
              className="btn btn-outline-success m-4 ms-7 btn-lg "
              value={newStatus}
              onChange={(e) => setNewStatus(e.target.value)}
            >
              <option value="SUBMITTED">SUBMITTED</option>
              <option value="IN_TRANSIT">IN_TRANSIT</option>
              <option value="DELIVERED">DELIVERED</option>
            </select>
          </label>
          <br />
          <label>
            New Location:
            <InputAtom
              type="text"
              name="newLocation"
              value={newLocation}
              onChange={(e) => setNewLocation(e.target.value)}
              placeholder="Enter new location"
            />
          </label>
          <div className="col d-flex justify-content-center ">
            <SubmitButtonAtom
              type="submit"
              text="Update Package"
              className="btn btn-outline-success mt-3 btn-lg"
            />
          </div>
          <div className="col d-flex justify-content-center ">
            <SubmitButtonAtom
              type="button"
              text="Back to list of parcels"
              className="btn btn-outline-primary mt-5"
              onClick={handleBackClick}
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default UpdatePackagePage;
