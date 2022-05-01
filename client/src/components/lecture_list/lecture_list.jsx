import React from "react";
import LectureItem from "../lecture_item/lecture_item";
import styles from "./lecture_list.module.css";

const LectureList = (props) => (
  <ul className={styles.lectures}>
    {props.lectures.map((lecture) => (
      <LectureItem key={lecture.lectureId} lecture={lecture} />
    ))}
  </ul>
);

export default LectureList;
