import React from "react";

const InputSearchAtom = ({ type, placeholder }) => {
  return (
    <input
      type={type}
      className="form-control "
      placeholder={placeholder}
      aria-label={placeholder}
    />
  );
};

export default InputSearchAtom;
