import React from "react";
import styles from "./lecture_item.module.css";

const LectureItem = ({ lecture }) => (
  <li className={styles.container}>
    <div className={styles.lecture}>
      <img
        className={styles.thumbnail}
        src={lecture.thumbnail_image_url}
        alt="lecture thumbnail"
      />
      <div className={styles.metadata}>
        <p className={styles.name}>{lecture.name}</p>
        <p className={styles.lecturer}>{lecture.lecturer}</p>
        <p className={styles.price}>{lecture.price.toLocaleString()}</p>
      </div>
    </div>
  </li>
);

export default LectureItem;
