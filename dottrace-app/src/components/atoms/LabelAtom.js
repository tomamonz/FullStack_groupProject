import React from "react";

const LabelAtom = ({ name, label }) => {
  return (
    <label htmlFor={name} className="form-label">
      {label}
    </label>
  );
};

export default LabelAtom;
