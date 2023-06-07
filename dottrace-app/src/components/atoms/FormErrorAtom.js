import React from "react";

const FormErrorAtom = ({ error }) => {
  return (
    <React.Fragment>
      {error && <div className="invalid-feedback fw-bolder">{error}</div>}
    </React.Fragment>
  );
};

export default FormErrorAtom;
