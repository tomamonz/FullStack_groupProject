import React from "react";

const ButtonAtom = ({ type, text, className, className2 }) => {
  return (
    <button
      className={className}
      type={type}
      data-bs-toggle="collapse"
      data-bs-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span className={className2}>{text}</span>
    </button>
  );
};

export default ButtonAtom;
