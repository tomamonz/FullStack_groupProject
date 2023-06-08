import React from "react";

const SearchButtonAtom = ({ type, className, text }) => {
  return (
    <button type={type} className={className}>
      {text}
    </button>
  );
};

export default SearchButtonAtom;
