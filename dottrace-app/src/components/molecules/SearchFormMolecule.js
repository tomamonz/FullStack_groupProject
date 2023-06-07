import React from "react";
import InputSearchAtom from "../atoms/InputSearchAtom";
import SearchButtonAtom from "../atoms/SearchButtonAtom";

const SearchFormMolecule = ({
  buttonType,
  type,
  placeholder,
  text,
  nameClass,
}) => {
  return (
    <form className="searchform d-flex ">
      <InputSearchAtom type={type} placeholder={placeholder} />
      <SearchButtonAtom type={buttonType} className={nameClass} text={text} />
    </form>
  );
};

export default SearchFormMolecule;
