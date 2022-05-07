import React from "react";
import styles from "./navbar.module.css";

const Navbar = ({ onLogoClick, goToOrder, goToLectureAddForm }) => (
  <nav className={styles.navbar}>
    <div className={styles.logo} onClick={onLogoClick}>
      <img className={styles.logo_img} src="/images/logo.png" alt="logo" />
    </div>
    <ul className={styles.menu}>
      <li className={styles.menu_item} onClick={goToOrder}>
        주문내역
      </li>
      <li className={styles.menu_item} onClick={goToLectureAddForm}>
        강의등록
      </li>
    </ul>
  </nav>
);

export default Navbar;
