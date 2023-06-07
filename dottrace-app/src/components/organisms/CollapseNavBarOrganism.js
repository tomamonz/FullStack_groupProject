import React from "react";
import SearchFormMolecule from "../molecules/SearchFormMolecule";
import LinksMolecule from "../molecules/LinksMolecule";

const CollapseNavBarOrganism = ({
  buttonType,
  type,
  placeholder,
  text,
  nameClass,
  text1,
  text2,
  text3,
  icon1,
  icon2,
  icon3,
  path1,
  path2,
  path3,
  CollapseClassName,
  CollapseId,
}) => {
  return (
    <div className={CollapseClassName} id={CollapseId}>
      <LinksMolecule
        text1={text1}
        icon1={icon1}
        path1={path1}
        text2={text2}
        icon2={icon2}
        path2={path2}
        text3={text3}
        icon3={icon3}
        path3={path3}
      />
    </div>
  );
};

export default CollapseNavBarOrganism;
