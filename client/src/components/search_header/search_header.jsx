import styles from "./search_header.module.css";
import React, { useRef } from "react";
import Navbar from "../navbar/navbar";

const SearchHeader = ({
  onSearch,
  goToMain,
  goToOrder,
  goToLectureAddForm,
}) => {
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
      <Navbar
        onLogoClick={onLogoClick}
        goToOrder={goToOrder}
        goToLectureAddForm={goToLectureAddForm}
      />
      <input
        ref={inputRef}
        className={styles.input}
        type="search"
        placeholder="강의명으로 검색 가능합니다."
        onKeyDown={onKeyDown}
      />
      <button className={styles.button} type="submit" onClick={onClick}>
        <i className="fa-solid fa-magnifying-glass" />
      </button>
    </header>
  );
};

export default SearchHeader;
