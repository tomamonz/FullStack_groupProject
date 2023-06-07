import React from "react";
import { Link } from "react-router-dom";

const LinkAtom = ({ text, LinkClassName, path }) => {
  return (
    <Link className={LinkClassName} to={path}>
      <p style={{ marginBottom: 0, marginLeft: "5px" }}>{text}</p>
    </Link>
  );
};

export default LinkAtom;
