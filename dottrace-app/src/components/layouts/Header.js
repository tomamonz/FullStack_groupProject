import React from "react";
import NavbarOrganism from "../organisms/NavbarSmallAppOrganism";

const Header = ({ brand }) => {
  return (
    <React.Fragment>
      <NavbarOrganism
        classNavName="navbar navbar-expand-lg bg-body-tertiary bg-dark mb-2"
        classContainerName="container-fluid"
        LinkText={brand}
        path="/"
        LinkClassName="navbar-brand"
        buttonText=""
        buttonClassName="navbar-toggler"
        navButtonType="button"
        spanClassName="navbar-toggler-icon"
        CollapseClassName="collapse navbar-collapse"
        CollapseId="navbarSupportedContent"
        text1="Home"
        icon1="fa fa-home"
        path1="/"
        text2="Register"
        icon2="fa fa-plus"
        path2="/register"
        text3="Send Parcel"
        icon3="fa fa-paper-plane"
        path3="/sendparcel"
        // buttonType="submit"
        // nameClass="btn btn-outline-success"
        // type="search"
        // placeholder="Search"
        // text="Search"
      />
    </React.Fragment>
  );
};

export default Header;
