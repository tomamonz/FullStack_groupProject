import React from "react";
import LabelAtom from "../atoms/LabelAtom";
import InputAtom from "../atoms/InputAtom";
import FormErrorAtom from "../atoms/FormErrorAtom";

const FormInputMolecule = ({
  name,
  label,
  value,
  placeholder,
  onChange,
  error,
}) => {
  return (
    <div className="mb-3">
      <LabelAtom name={name} label={label} />
      <InputAtom
        name={name}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        error={error}
        id={name}
      />
      <FormErrorAtom error={error} />
    </div>
  );
};

export default FormInputMolecule;
