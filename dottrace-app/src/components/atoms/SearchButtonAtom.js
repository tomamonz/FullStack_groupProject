import React from "react";

const ButtonAtom = ({ type, text, className }) => {
  return (
    <button className={className} type={type}>
      {text}
    </button>
  );
};

export default ButtonAtom;
