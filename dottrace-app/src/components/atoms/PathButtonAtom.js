import React from "react";

const NavigationButtonAtom = ({ type, text, className, onClick }) => {
  return (
    <button className={className} type={type} onClick={onClick}>
      {text}
    </button>
  );
};

export default NavigationButtonAtom;
