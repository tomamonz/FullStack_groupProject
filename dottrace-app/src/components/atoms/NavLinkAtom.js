import React from "react";
import { NavLink } from "react-router-dom";

const NavLinkAtom = ({ text, icon, path }) => {
  return (
    <NavLink
      className="nav-link active d-flex align-items-center "
      style={{ marginLeft: "25px" }}
      aria-current="page"
      to={path}
    >
      <i className={icon} aria-hidden="true"></i>
      <p style={{ marginBottom: 0, marginLeft: "5px" }}>{text}</p>
    </NavLink>
  );
};

export default NavLinkAtom;
