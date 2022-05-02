import React from "react";
import LectureItem from "../lecture_item/lecture_item";
import styles from "./lecture_list.module.css";

const LectureList = ({ lectures, goToPayment }) => {
  return (
    <ul className={styles.lectures}>
      {lectures.map((lecture) => (
        <LectureItem
          key={lecture.lectureId}
          lecture={lecture}
          goToPayment={goToPayment}
        />
      ))}
    </ul>
  );
};

export default LectureList;
