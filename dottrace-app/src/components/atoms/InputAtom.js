import React from "react";
import classnames from "classnames";

const InputAtom = ({ id, type, name, value, onChange, placeholder, error }) => {
  return (
    <input
      type={type}
      className={classnames("form-control form-control-lg", {
        "is-invalid": error,
      })}
      id={id}
      name={name}
      value={value}
      onChange={onChange}
      placeholder={placeholder}
    />
  );
};

export default InputAtom;
