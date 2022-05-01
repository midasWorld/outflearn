import React from "react";
import LectureItem from "../lecture_item/lecture_item";

const LectureList = (props) => (
  <ul>
    {props.lectures.map((lecture) => (
      <LectureItem key={lecture.lectureId} lecture={lecture} />
    ))}
  </ul>
);

export default LectureList;
