import React from "react";

const UserContext = React.createContext({
  user: null,
  setUser: () => {},
  parcel: null,
  setParcel: () => {},
});

export default UserContext;
