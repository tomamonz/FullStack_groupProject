import React from "react";
import InputSearchAtom from "../atoms/InputSearchAtom";
import SearchButtonAtom from "../atoms/SearchButtonAtom";

const SearchFormMolecule = ({
  buttonType,
  type,
  placeholder,
  text,
  nameClass,
  value,
  onChange,
  onSubmit,
}) => {
  return (
    <form className="searchform d-flex " onSubmit={onSubmit}>
      <InputSearchAtom
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
      />
      <SearchButtonAtom type={buttonType} className={nameClass} text={text} />
    </form>
  );
};

export default SearchFormMolecule;
