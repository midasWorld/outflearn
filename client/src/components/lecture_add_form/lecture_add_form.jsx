import React, { useRef } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./lecture_add_form.module.css";

const LectureAddForm = ({ lecture, goToPage }) => {
  const nameRef = useRef();
  const lecturerRef = useRef();
  const priceRef = useRef();
  const fileRef = useRef();
  const imgRef = useRef();

  const onInputRowClick = (event) => {
    if (event.target.nodeName === "TD") {
      event.target.childNodes[0].focus();
    }
  };

  const onInputFileChange = () => {
    const files = fileRef.current.files;
    if (files && files[0]) {
      const reader = new FileReader();
      reader.onload = (e) => {
        imgRef.current.src = e.target.result;
      };
      reader.readAsDataURL(fileRef.current.files[0]);
      return;
    }

    imgRef.current.src = "";
  };

  const onSubmitClick = () => {
    const name = nameRef.current.value;
    const lecturer = lecturerRef.current.value;
    const price = priceRef.current.value;

    if (name === "") {
      alert("강의명을 입력하세요.");
      return;
    }

    if (lecturer === "") {
      alert("저자명을 입력하세요.");
      return;
    }

    if (price === "") {
      alert("가격을 입력하세요.");
      return;
    }

    if (price < 0) {
      alert("가격은 0 이상으로만 등록가능 합니다.");
      return;
    }

    const lec = {
      name: name,
      lecturer: lecturer,
      price: price,
      images: fileRef.current.files[0],
    };

    lecture
      .create(lec)
      .then((response) => {
        alert("강의등록에 성공하였습니다.");
        goToPage();
      }) //
      .catch((error) => {
        console.error(error.response.data);
        alert(error.response.data.error.message);
      });
  };

  return (
    <section className={styles.container}>
      <h2>강의 등록란</h2>
      <table className={styles.table}>
        <tbody>
          <tr>
            <td>강의명</td>
            <td onClick={onInputRowClick}>
              <input type="text" className={styles.input} ref={nameRef} />
            </td>
          </tr>
          <tr>
            <td>저자</td>
            <td onClick={onInputRowClick}>
              <input type="text" className={styles.input} ref={lecturerRef} />
            </td>
          </tr>
          <tr>
            <td>가격</td>
            <td onClick={onInputRowClick}>
              <input type="number" className={styles.input} ref={priceRef} />
            </td>
          </tr>
          <tr>
            <td>썸네일</td>
            <td className={styles.thumbnail}>
              <input
                type="file"
                multiple="multiple"
                ref={fileRef}
                onChange={onInputFileChange}
              />
              <img src="" alt="" className={styles.img} ref={imgRef} />
            </td>
          </tr>
        </tbody>
      </table>
      <button className={styles.button} type="button" onClick={onSubmitClick}>
        강의 등록하기
      </button>
    </section>
  );
};

export default LectureAddForm;
