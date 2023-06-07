import React from "react";
import CollapseNavBarOrganism from "./CollapseNavBarOrganism";
import ButtonAtom from "../atoms/ButonAtom";
import LinkAtom from "../atoms/LinkAtom";

const NavbarOrganism = ({
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
  classContainerName,
  LinkText,
  path,
  LinkClassName,
  buttonText,
  buttonClassName,
  navButtonType,
  spanClassName,
  classNavName,
}) => {
  return (
    <nav className={classNavName}>
      <div className={classContainerName}>
        <LinkAtom text={LinkText} path={path} LinkClassName={LinkClassName} />
        <ButtonAtom
          text={buttonText}
          className={buttonClassName}
          type={navButtonType}
          className2={spanClassName}
        />

        <CollapseNavBarOrganism
          CollapseClassName={CollapseClassName}
          CollapseId={CollapseId}
          text1={text1}
          icon1={icon1}
          path1={path1}
          text2={text2}
          icon2={icon2}
          path2={path2}
          text3={text3}
          icon3={icon3}
          path3={path3}
          buttonType={buttonType}
          type={type}
          placeholder={placeholder}
          text={text}
          nameClass={nameClass}
        />
      </div>
    </nav>
  );
};

export default NavbarOrganism;
