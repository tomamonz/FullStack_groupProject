import React from "react";
import { Link } from "react-router-dom";

const LinkAtom = ({ text, LinkClassName, path }) => {
  return (
    <Link to={path}>
      <img
        src={text}
        style={{ height: "45px" }}
        alt="Dot Trace"
        className={LinkClassName}
      />
    </Link>
  );
};

export default LinkAtom;
