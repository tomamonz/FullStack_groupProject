import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import ObjectContext from "../UserObject";
import PathButtonAtom from "../atoms/PathButtonAtom";

const AdminPage = () => {
  const [parcels, setParcels] = useState([]);
  const [parcelHistories, setParcelHistories] = useState([]);
  const { setParcel } = useContext(ObjectContext);
  const navigate = useNavigate();

  useEffect(() => {
    fetchParcels();
  }, []);

  const fetchParcels = () => {
    axios
      .get("http://localhost:7312/api/gateway/parcels")
      .then((response) => {
        setParcels(response.data);
      })
      .catch((error) => {
        console.error("Could not fetch parcels.", error);
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

  const onUpdateClick = (parcel) => {
    setParcel(parcel);
    navigate(`/admin/update/${parcel.id}`);
  };

  return (
    <div className="container card mb-1">
      <div className="card-header bg-dark bg-body-tertiary border border-green d-flex justify-content-center">
        List of parcels
      </div>
      <div className="card-body ">
        {parcels
          .sort((a, b) => a.id < b.id)
          .map((parcel) => (
            <div
              className="card mb-1 d-flex justify-content-center"
              key={parcel.id}
            >
              <div className="card-body pb-1">
                Sender ID: {parcel.senderId}
                <br />
                Package Number: {parcel.parcelNumber}
                <br />
                Status: {parcel.status}
                <br />
                <div className="mt-3 mb-2">
                  <PathButtonAtom
                    className="btn btn-outline-success "
                    onClick={() => fetchParcelHistory(parcel.parcelNumber)}
                    text="Show History"
                  />

                  <PathButtonAtom
                    onClick={() => onUpdateClick(parcel)}
                    className="btn btn-outline-success  ms-3 "
                    text="Update"
                  />
                </div>
              </div>
              {parcelHistories
                .filter(
                  (history) => history.parcelNumber === parcel.parcelNumber
                )
                .map((history) => (
                  <div className="card-body pt-0" key={history.id}>
                    Location: {history.location}
                    <br />
                    Date: {history.arrivalDate}
                  </div>
                ))}
            </div>
          ))}
      </div>
    </div>
  );
};

export default AdminPage;
