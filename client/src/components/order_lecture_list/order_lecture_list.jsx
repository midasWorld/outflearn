import React from "react";
import styles from "./order_lecture_list.module.css";

const OrderLectureList = ({ lecture, voucher }) => {
  const discountAmount = (lecture.price * voucher.percent) / 100;
  const amount = lecture.price - discountAmount;

  return (
    <section className={styles.container}>
      <div className={styles.item}>
        <div className={styles.lecture}>
          <img
            className={styles.thumbnail}
            src={lecture.thumbnail_image_url}
            alt="lecture thumbnail"
          />
          <div className={styles.metadata}>
            <p className={styles.name}>{lecture.name}</p>
          </div>
          <div className={styles.price}>
            {voucher.voucherId === null ? (
              ""
            ) : (
              <p className={styles.before_amount}>
                {lecture.price.toLocaleString()}
              </p>
            )}
            <p className={styles.amount}>{amount.toLocaleString()}</p>
          </div>
        </div>
        {voucher.voucherId === null ? (
          ""
        ) : (
          <div className={styles.voucher}>
            <span className={styles.voucher_arrow}>
              <i class="fa-solid fa-arrow-up-long"></i>
            </span>
            적용된 할인 : <span>{voucher.name} 할인 쿠폰</span>
          </div>
        )}
      </div>
    </section>
  );
};

export default OrderLectureList;
