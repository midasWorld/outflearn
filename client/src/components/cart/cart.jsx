import React from "react";
import styles from "./cart.module.css";

const Cart = ({ lecture }) => {
  return (
    <section className={styles.container}>
      <p className={styles.title}>강의 정보</p>
      <div className={styles.cart}>
        <img
          className={styles.thumbnail}
          src={lecture.thumbnail_image_url}
          alt="lecture thumbnail"
        />
        <div className={styles.metadata}>
          <p className={styles.name}>{lecture.name}</p>
          <p className={styles.lecturer}>{lecture.lecturer}</p>
        </div>
        <div>
          <p className={styles.price}>{lecture.price.toLocaleString()}</p>
        </div>
      </div>
    </section>
  );
};
export default Cart;
