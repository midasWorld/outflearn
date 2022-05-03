import styles from "./search_header.module.css";
import React, { useRef } from "react";

const SearchHeader = ({ onSearch, goToMain }) => {
  const inputRef = useRef();
  const handleSearch = () => {
    const value = inputRef.current.value;
    onSearch(value);
  };
  const onClick = () => {
    handleSearch();
  };

  const onKeyDown = (event) => {
    if (event.key === "Enter") {
      handleSearch();
    }
  };

  const onLogoClick = () => {
    inputRef.current.value = "";
    onSearch("");
    goToMain();
  };

  return (
    <header className={styles.header}>
      <img
        className={styles.logo}
        src="/images/logo.png"
        alt="logo"
        onClick={onLogoClick}
      />
      <input
        ref={inputRef}
        className={styles.input}
        type="search"
        placeholder="Search..."
        onKeyDown={onKeyDown}
      />
      <button className={styles.button} type="submit" onClick={onClick}>
        <i className="fa-solid fa-magnifying-glass" />
      </button>
    </header>
  );
};

export default SearchHeader;
