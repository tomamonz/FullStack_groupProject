import React from "react";
import LiNavItemAtom from "../atoms/LiNavItemAtom";
import NavLinkAtom from "../atoms/NavLinkAtom";

const LinksMolecule = ({
  text1,
  text2,
  text3,
  icon1,
  icon2,
  icon3,
  path1,
  path2,
  path3,
}) => {
  return (
    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
      <LiNavItemAtom
        children={<NavLinkAtom text={text1} icon={icon1} path={path1} />}
      />
      <LiNavItemAtom
        children={<NavLinkAtom text={text2} icon={icon2} path={path2} />}
      />
      <LiNavItemAtom
        children={<NavLinkAtom text={text3} icon={icon3} path={path3} />}
      />
    </ul>
  );
};

export default LinksMolecule;
