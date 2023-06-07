import React from "react";
import FormInputMolecule from "../molecules/FormInputMolecule";

const FormInputOrganism = ({
  label,
  name,
  value,
  placeholder,
  type,
  onChange,
  error,
}) => {
  return (
    <FormInputMolecule
      label={label}
      name={name}
      value={value}
      placeholder={placeholder}
      type={type}
      onChange={onChange}
      error={error}
    />
  );
};

export default FormInputOrganism;
