import React from "react";

const InputSearchAtom = ({ type, placeholder, value, onChange }) => {
  return (
    <input
      type={type}
      className="form-control "
      placeholder={placeholder}
      aria-label={placeholder}
      value={value}
      onChange={onChange}
    />
  );
};

export default InputSearchAtom;
